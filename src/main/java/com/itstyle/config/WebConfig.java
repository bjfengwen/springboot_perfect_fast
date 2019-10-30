package com.itstyle.config;

import com.itstyle.interceptor.TokenInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig
        implements ApplicationContextAware, WebMvcConfigurer {
    private ApplicationContext applicationContext;

    @Autowired
    private TokenInterceptor tokenInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.tokenInterceptor)
                .addPathPatterns(new String[]{"/**"})
                .excludePathPatterns(new String[]{"/favicon.ico", "/login**/**", "/sendSms**/**", "/device/login/**", "/test/sw", "/time"});


    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/static/**"}).addResourceLocations(new String[]{"classpath:/static/"});
        registry.addResourceHandler(new String[]{"/templates/**"})
                .addResourceLocations(new String[]{"classpath:/templates/"});


    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
}