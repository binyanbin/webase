package com.bin.webase.domain.web;


import com.bin.webase.domain.container.Container;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yanbin
 * @date 2017/7/1
 */
public class WebContext {
    private ApiToken token;
    private final Map<String, String> parameters;
    private String versionId;
    private ApiMethodAttribute methodAttribute;
    private Long streamId;
    private Date beginTime;
    private String businessType;

    public WebContext() {
        parameters = new HashMap<>();
        beginTime = new Date();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public ApiMethodAttribute getMethodAttribute() {
        return methodAttribute;
    }

    public void setMethodAttribute(ApiMethodAttribute methodAttribute) {
        this.methodAttribute = methodAttribute;
    }

    public boolean hasToken() {
        return token != null;
    }

    public ApiToken getToken() {
        if (token != null) {
            return token;
        } else {
            return null;
        }
    }

    public void setToken(ApiToken token) {
        this.token = token;
    }

    public void setParameter(String key, String value) {
        parameters.put(key, value);
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public Long getStreamId() {
        if (streamId == null) {
            streamId = Container.getSequenceBean().newKey(this.getClass());
        }
        return streamId;
    }

    public boolean isExistsId() {
        return streamId != null;
    }

    public Date getBeginTime() {
        return beginTime;
    }
}