package com.zcy.pet.config;

import com.zcy.pet.filter.JwtFilter;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    JwtFilter jwtFilter;
    /**
     * 不需要拦截地址
     */
    public static final String[] EXCLUDE_URLS = {
            "/api/auth/**",
            "/api/test/**",
            "/api/file/**",
            "/Myfile/**"
    };
    @Value("${file.path}")
    private String path;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(jwtFilter)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_URLS);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Myfile/**").addResourceLocations("file:" + path);
    }
}
