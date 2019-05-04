package com.daolin.ad.controller;

import com.alibaba.fastjson.JSON;
import com.daolin.ad.exception.AdException;
import com.daolin.ad.service.ICreativeService;
import com.daolin.ad.vo.CreativeRequest;
import com.daolin.ad.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daolin
 * @date 2019/05/03
 */
@Slf4j
@RestController
public class CreativeOPController {

    private final ICreativeService creativeService;

    @Autowired
    public CreativeOPController(ICreativeService creativeService) {
        this.creativeService = creativeService;
    }

    @PostMapping("/create/creative")
    public CreativeResponse createCreative(@RequestBody CreativeRequest request){
        log.info("ad-sponsor: createCreative -> {}",
                JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }


}
