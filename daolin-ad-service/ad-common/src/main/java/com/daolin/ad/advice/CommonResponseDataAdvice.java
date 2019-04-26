package com.daolin.ad.advice;

import com.daolin.ad.annotation.IgnoreResponseAdvice;
import com.daolin.ad.common.ResponseCode;
import com.daolin.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by Daolin
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        //如果 class 上有 @IgnoreResponseAdvice 则跳过过滤
        if(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class))
            return false;

        //如果 method 上有 @IgnoreResponseAdvice 则跳过过滤
        if(methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class))
            return false;

        return true;
    }

    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        CommonResponse<Object> response = new CommonResponse<>(ResponseCode.SUCCESS.getCode(), "");
        if(null == o)
            return response;
        else if(o instanceof CommonResponse)
            response = (CommonResponse<Object>) o;
        else
            response.setData(response);

        return response;
    }
}
