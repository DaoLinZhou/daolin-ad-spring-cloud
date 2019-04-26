package com.daolin.ad.advice;

import com.daolin.ad.common.ResponseCode;
import com.daolin.ad.exception.AdException;
import com.daolin.ad.vo.CommonResponse;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Daolin
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerException(HttpServletRequest req,
                                                   AdException ex){
        CommonResponse<String> response = new CommonResponse<>(ResponseCode.AD_EXCEPTION.getCode(), ResponseCode.AD_EXCEPTION.getDesc());
        response.setData(ex.getMessage());
        return response;
    }

}
