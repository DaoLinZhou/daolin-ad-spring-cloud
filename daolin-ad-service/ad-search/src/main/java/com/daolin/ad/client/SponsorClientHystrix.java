package com.daolin.ad.client;

import com.daolin.ad.client.vo.AdPlan;
import com.daolin.ad.client.vo.AdPlanGetRequest;
import com.daolin.ad.common.ResponseCode;
import com.daolin.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author Daolin
 * @date 2019/05/05
 */
@Component
public class SponsorClientHystrix implements SponsorClient {
    @Override
    public CommonResponse<List<AdPlan>> getAdPlans(AdPlanGetRequest request) {
        return new CommonResponse<>(ResponseCode.AD_EXCEPTION.getCode(),
                "eureka-client-ad-sponsor error");
    }
}
