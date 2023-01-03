package com.gdsig.common.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
public class IpUtil {
    private static final String IP_UTILS_FLAG = ",";
    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST_IP = "0:0:0:0:0:0:0:1";
    private static final String LOCALHOST_IP1 = "127.0.0.1";

    private static final String[] PROXY_HEADERS = new String[]{
            "X-Original-Forwarded-For",
            "X-Forwarded-For", "x-forwarded-for",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP", "Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED",
    };

    /**
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;

        for (String proxyHeader : PROXY_HEADERS) {
            String temp = request.getHeader(proxyHeader);
            if (isValidIp(temp)) {
                ip = temp;
                break;
            }
        }
        // 本地IP，返回127.0.0.1
        if (LOCALHOST_IP.equalsIgnoreCase(ip)) {
            ip = LOCALHOST_IP1;
        }
        // 获取remoteAddr
        if (StringUtils.isBlank(ip)) {
            ip = request.getRemoteAddr();
        }
        //使用代理，则获取第一个IP地址
        if (StringUtils.isNotBlank(ip) && ip.indexOf(IP_UTILS_FLAG) > 0) {
            ip = ip.substring(0, ip.indexOf(IP_UTILS_FLAG));
        }
        return ip;
    }

    public static boolean isValidIp(String ip) {
        return StringUtils.isNotBlank(ip) && !StringUtils.equals(ip, UNKNOWN);
    }

}
