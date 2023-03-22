package com.czl.proxy.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.mitre.dsmiley.httpproxy.ProxyServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zack chen
 * @Date: 2023/3/22 11:24
 */
public class ProxyService extends ProxyServlet {


    protected static final String ATTR_TARGET_URI =
            ProxyServlet.class.getSimpleName() + ".targetUri";
    protected static final String ATTR_TARGET_HOST =
            ProxyServlet.class.getSimpleName() + ".targetHost";

    @Override
    protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        String clusterId = servletRequest.getHeader("edge_clouster_id");
        // url和host可根据需要动态生成，如从redis中获取配置
        servletRequest.setAttribute(ATTR_TARGET_URI, "/agents/" + clusterId);
        HttpHost httpHost = new HttpHost("localhost",8080, "http");
        servletRequest.setAttribute(ATTR_TARGET_HOST, httpHost);


        super.service(servletRequest, servletResponse);
    }
}
