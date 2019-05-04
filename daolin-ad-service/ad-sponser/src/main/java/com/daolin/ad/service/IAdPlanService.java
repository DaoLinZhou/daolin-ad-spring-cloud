package com.daolin.ad.service;

import com.daolin.ad.entity.AdPlan;
import com.daolin.ad.exception.AdException;
import com.daolin.ad.vo.AdPlanGetRequest;
import com.daolin.ad.vo.AdPlanRequest;
import com.daolin.ad.vo.AdPlanResponse;

import java.util.List;

/**
 * @author Daolin
 * @date 2019/04/28
 */
public interface IAdPlanService {

    /**
     * <h2>创建推广计划</h2>
     */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * <h2>获取推广计划</h2>
     */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    /**
     * <h2>更新推广计划</h2>
     */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * <h2>删除推广计划</h2>
     */
    void deleteAdPlan(AdPlanRequest request) throws AdException;

}
