package com.bin.api.utils;


import javax.servlet.http.HttpServletRequest;

public final class RequestUtils {

    public static String getIpAddr(HttpServletRequest request) {
        String ip;
        ip = request.getHeader(Constants.X_FORWARDED_FOR);
        if (isInvalidIP(ip)) {
            ip = request.getHeader(Constants.PROXY_CLIENT_IP);
        }
        if (isInvalidIP(ip)) {
            ip = request.getHeader(Constants.WL_PROXY_CLIENT_IP);
        }
        if (isInvalidIP(ip)) {
            ip = request.getRemoteAddr();
        }
        if (isInvalidIP(ip)) {
            ip = request.getHeader(Constants.X_REAL_IP);
        }
        if (isInvalidIP(ip) && null != request.getAttribute(Constants.X_REAL_IP)) {
            ip = request.getAttribute(Constants.X_REAL_IP).toString();
        }
        if (null == ip) {
            ip = "unknown";
        }
        return ip;
    }

    private static boolean isInvalidIP(String ip) {
        return ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "127.0.0.1".equals(ip);
    }

    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path;
        return basePath;
    }

    public static String getBasePathNotPort(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + path;
        return basePath;
    }

    public static String getContextPath(HttpServletRequest request) {
        return request.getContextPath();
    }


    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader(Constants.USER_AGENT);
    }

    public static String getMethod(HttpServletRequest request) {
        return request.getMethod();
    }

    public static String getSessionId(HttpServletRequest request) {
        return request.getHeader(Constants.HEADER_SESSION_ID);
    }

    public static String getClientType(HttpServletRequest request) {
        return request.getHeader(Constants.HEADER_CLIENT_TYPE);
    }

    public static Long getBranchId(HttpServletRequest request) {
        return Long.parseLong(request.getHeader(Constants.BRANCH));
    }

    public static String getDeviceId(HttpServletRequest request) {
        return request.getHeader(Constants.DEVICE_ID);
    }

    public static String getVersionId(HttpServletRequest request) {
        return request.getHeader(Constants.HEADER_VERSION_ID);
    }
}
