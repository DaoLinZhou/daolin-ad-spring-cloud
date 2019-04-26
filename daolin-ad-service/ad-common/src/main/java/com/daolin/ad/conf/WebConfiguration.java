package com.daolin.ad.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by Daolin
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //如果没有声明（自定义）消息转换器，SpringMVC 就会使用它定义的一系列消息转换器, 所以先clear
        converters.clear();
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
