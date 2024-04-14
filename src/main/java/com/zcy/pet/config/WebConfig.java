package com.zcy.pet.config;

import com.zcy.pet.filter.JwtFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Resource
    JwtFilter jwtFilter;
    /**
     * 不需要拦截地址
     */
    public static final String[] EXCLUDE_URLS = {
            "/api/auth/**",
            "/api/test/**",
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(jwtFilter)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_URLS);
    }
}
