package com.restapp.servlet;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class SwaggerConfigurationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setBasePath("/app/rest");
        beanConfig.setHost("localhost:8080");
        beanConfig.setTitle("Alexandria API Documentation");
        beanConfig.setResourcePackage("com.restapp.resource");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setVersion("1.0");
    }
}
