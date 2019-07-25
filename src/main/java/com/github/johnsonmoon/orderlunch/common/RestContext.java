package com.github.johnsonmoon.orderlunch.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create by xuyh at 2019/7/25 14:21.
 */
public class RestContext {
    private static ThreadLocal<HttpServletRequest> httpServletRequestThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> httpServletResponseThreadLocal = new ThreadLocal<>();

    public static HttpServletRequest getHttpServletRequest() {
        return httpServletRequestThreadLocal.get();
    }

    public static void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        RestContext.httpServletRequestThreadLocal.set(httpServletRequest);
    }

    public static HttpServletResponse getHttpServletResponse() {
        return httpServletResponseThreadLocal.get();
    }

    public static void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        RestContext.httpServletResponseThreadLocal.set(httpServletResponse);
    }
}
