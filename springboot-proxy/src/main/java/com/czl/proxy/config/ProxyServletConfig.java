package com.czl.proxy.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zack chen
 * @Date: 2023/3/22 11:21
 */
@Configuration
public class ProxyServletConfig {

    @Bean
    public Servlet getProxyServlet() {
//        return new ProxyServlet();
        return new ProxyService();
    }

    /**
     * 这里可以多加几个，但是servlet名字需要不一样
     */
    @Bean
    public ServletRegistrationBean proxyServletRegistrationBean() {
        // 配置路由策略
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(getProxyServlet(), "/proxy/edge-agents/*");
        Map<String, String> params = new HashMap<>();
        // 路由策略。默认值。
        params.put("targetUri", "http://localhost:30015");
        params.put(ProxyService.P_LOG, "true");
        servletRegistrationBean.setInitParameters(params);
        return servletRegistrationBean;
    }
}
