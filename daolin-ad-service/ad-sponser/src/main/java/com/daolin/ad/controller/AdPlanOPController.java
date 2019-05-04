package com.daolin.ad.controller;

import com.alibaba.fastjson.JSON;
import com.daolin.ad.entity.AdPlan;
import com.daolin.ad.exception.AdException;
import com.daolin.ad.service.IAdPlanService;
import com.daolin.ad.vo.AdPlanGetRequest;
import com.daolin.ad.vo.AdPlanRequest;
import com.daolin.ad.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Daolin
 * @date 2019/05/03
 */
@Slf4j
@RestController
public class AdPlanOPController {

    private final IAdPlanService adPlanService;

    @Autowired
    public AdPlanOPController(IAdPlanService adPlanService) {
        this.adPlanService = adPlanService;
    }

    @PostMapping("/create/adPlan")
    public AdPlanResponse createAdPlan(@RequestBody AdPlanRequest request) throws AdException{
        log.info("ad-sponsor: createAdPlan -> {}",
                JSON.toJSONString(request));
        return adPlanService.createAdPlan(request);
    }

    @PostMapping("/get/adPlan")
    public List<AdPlan> getAdPlanByIds(@RequestBody AdPlanGetRequest request) throws AdException{
        log.info("ad-sponsor: getAdPlanByIds -> {}",
                JSON.toJSONString(request));
        return adPlanService.getAdPlanByIds(request);
    }

    @PostMapping("/update/adPlan")
    public AdPlanResponse updateAdPlan(@RequestBody AdPlanRequest request) throws AdException{
        log.info("ad-sponsor: updateAdPlan -> {}",
                JSON.toJSONString(request));
        return adPlanService.updateAdPlan(request);
    }

    @PostMapping("/delete/adPlan")
    public void deleteAdPlan(@RequestBody AdPlanRequest request) throws AdException{
        log.info("ad-sponsor: deleteAdPlan -> {}",
                JSON.toJSONString(request));
        adPlanService.deleteAdPlan(request);
    }

}
