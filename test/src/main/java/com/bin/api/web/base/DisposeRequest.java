package com.bin.api.web.base;

import com.bin.api.operate.domain.cache.WebSessionDo;
import com.bin.api.utils.Constants;
import com.bin.api.utils.RequestUtils;
import com.bin.api.utils.Sha256;
import com.bin.api.web.BinHttpServletRequestWrapper;
import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.web.ApiToken;
import com.bin.webase.core.web.IDisposeRequest;
import com.bin.webase.core.web.WebContext;
import com.bin.webase.exception.ApplicationException;
import com.bin.webase.exception.ErrorCheck;
import com.bin.webase.exception.ErrorCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DisposeRequest implements IDisposeRequest {
    private final static String USER_LIMIT_KEY = "user_limit_";
    private final static String USER_COUNT_KEY = "user_count_";
    public final static Integer OPERATOR_LIMIT_TIME = 2;
    private final static String OPERATOR_LIMIT_KEY = "op_limit_";

    @Override
    public WebContext createSession(boolean validSession) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        WebContext webContext = new WebContext();
        webContext.setVersionId(request.getHeader(Constants.HEADER_VERSION_ID));
        webContext.setParameter(Constants.IP, request.getHeader(Constants.IP));
        String userAgent = request.getHeader(Constants.USER_AGENT);
        webContext.setParameter(Constants.HEADER_SESSION_ID, request.getHeader(Constants.HEADER_SESSION_ID));
        webContext.setParameter(Constants.HEADER_CLIENT_TYPE, request.getHeader(Constants.HEADER_CLIENT_TYPE));
        webContext.setParameter(Constants.PROFILE, null == userAgent ? "EmptyUserAgent" : userAgent);
        if (StringUtils.hasText(request.getRequestURI())) {
            webContext.setParameter(Constants.URL, "none");
        } else {
            String url = request.getRequestURI();
            if (StringUtils.hasText(request.getQueryString())) {
                url = url + "?" + request.getQueryString();
            }
            webContext.setParameter(Constants.URL, url);
        }
        webContext.setParameter(Constants.METHOD, request.getHeader(Constants.METHOD));


        String sessionType = RequestUtils.getClientType(request);
        if (validSession) {
            String sessionId = RequestUtils.getSessionId(request);
            ErrorCheck.check(StringUtils.hasText(sessionId), ErrorCode.InvalidAccessToken);
            String json = WebaseContext.getCacheBean().get(sessionId);
            ErrorCheck.check(StringUtils.hasText(json), ErrorCode.InvalidAccessToken);
            WebSessionDo webSession = WebSessionDo.parse(json);
            ErrorCheck.check(sessionType.equals(webSession.getClientType().toString()), ErrorCode.InvalidAccessToken, "授权类型不正确");
            ErrorCheck.check(!webSession.isNull(), ErrorCode.InvalidAccessToken);
            webContext.setToken(webSession.getModel());
        }
        return webContext;
    }

    @Override
    public void frequentAccess(WebContext webContext) {
        if (webContext.hasToken()) {
            String sessionKey = webContext.getToken().getUniqueId();
            String userLimitKey = USER_LIMIT_KEY + sessionKey;
            if (WebaseContext.getCacheBean().hasKey(userLimitKey)) {
                throw new ApplicationException(ErrorCode.UserLimit);
            } else {
                String countKey = USER_COUNT_KEY + sessionKey;
                Long count = WebaseContext.getCacheBean().increment(countKey, Constants.DATA_TIME_SPAN);
                if (count > 200) {
                    WebaseContext.getCacheBean().set(userLimitKey, count.toString(), Constants.LIMIT_TIME_SPAN);
                }
            }
        }
    }

    @Override
    public void quickSubmit(WebContext webContext) {
        if (webContext.hasToken()) {
            if (StringUtils.hasText(webContext.getBusinessType())) {
                ApiToken token = webContext.getToken();
                String cacheKey = OPERATOR_LIMIT_KEY + token.getUniqueId() + "_" + webContext.getBusinessType();
                ErrorCheck.check(!WebaseContext.getCacheBean().hasKey(cacheKey), ErrorCode.QuickOperate);
                WebaseContext.getCacheBean().set(cacheKey, "", OPERATOR_LIMIT_TIME);
            }
        }
    }


    @Override
    public void validSign(WebContext webContext) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String timestamp = request.getParameter(Constants.TIMESTAMP);
        ErrorCheck.check(StringUtils.hasText(timestamp), ErrorCode.InvalidSign, "时间撮不存在");

        Long timeLong = Long.parseLong(timestamp);
        Long nowLong = System.currentTimeMillis();
        long diff = Math.abs(nowLong - timeLong);
        ErrorCheck.check(diff <= 15 * 60 * 100, ErrorCode.InvalidSign, "请检查本地时间是否正确");

        String nonce = request.getParameter(Constants.NONCE);
        ErrorCheck.check(StringUtils.hasText(nonce), ErrorCode.InvalidSign, "唯一标识不存在");
        validBodySign(webContext, request);

        List<String> list = Collections.list(request.getParameterNames());
        list.remove(Constants.SIGN_KEY);
        list.sort(Comparator.naturalOrder());
        StringBuilder url = new StringBuilder();
        url.append(request.getRequestURI());
        url.append("?");
        for (int i = 0; i < list.size(); i++) {
            String key = list.get(i);
            String value = request.getParameter(key);
            url.append(key);
            url.append("=");
            url.append(value);
            if (i < list.size() - 1) {
                url.append("&");
            }
        }
        if (webContext.hasToken()) {
            ApiToken token = webContext.getToken();
            url.append(token.getSecretKey());
        }
        String encryptSign;
        String salt = "";
        if (StringUtils.hasText(salt)) {
            encryptSign = Sha256.encrypt(salt + url.toString() + salt);
        } else {
            encryptSign = Sha256.encrypt(url.toString());
        }
        String sign = request.getParameter(Constants.SIGN_KEY);
        ErrorCheck.check(StringUtils.hasText(sign), ErrorCode.InvalidSign, "签名不存在");

        ErrorCheck.check(sign.equals(encryptSign), ErrorCode.InvalidSign, "签名不正确");
        ErrorCheck.check(!WebaseContext.getCacheBean().hasKey(encryptSign), ErrorCode.InvalidSign, "签名已使用");
        WebaseContext.getCacheBean().set(encryptSign, timestamp, 15 * 60);
    }

    public void validBodySign(WebContext webContext, HttpServletRequest request) {
        if (request.getMethod().equals(RequestMethod.POST.name())) {
            String body = "";
            if (request.getMethod().equals(RequestMethod.POST.name())) {
                body = ((BinHttpServletRequestWrapper) request).getBodyString();
            }
            if (!StringUtils.hasText(body)) {
                return;
            }
            webContext.setParameter(Constants.REQUEST_BODY, body);
            String bodySign = request.getParameter(Constants.BODY_SIGN);
            if (webContext.hasToken()) {
                ApiToken token = webContext.getToken();
                String encryptString = Sha256.encrypt(body + token.getSecretKey());
                ErrorCheck.check(StringUtils.hasText(encryptString), ErrorCode.InvalidSign);
                ErrorCheck.check(encryptString.equals(bodySign), ErrorCode.InvalidSign);
            } else {
                String encryptString = Sha256.encrypt(body);
                ErrorCheck.check(StringUtils.hasText(encryptString), ErrorCode.InvalidSign);
                ErrorCheck.check(encryptString.equals(bodySign), ErrorCode.InvalidSign);
            }
        }
    }
}
