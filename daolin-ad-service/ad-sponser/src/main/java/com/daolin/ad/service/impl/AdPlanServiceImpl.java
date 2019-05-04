package com.daolin.ad.service.impl;

import com.daolin.ad.constant.CommonStatus;
import com.daolin.ad.constant.Constants;
import com.daolin.ad.dao.AdPlanRepository;
import com.daolin.ad.dao.AdUserRepository;
import com.daolin.ad.entity.AdPlan;
import com.daolin.ad.entity.AdUser;
import com.daolin.ad.exception.AdException;
import com.daolin.ad.service.IAdPlanService;
import com.daolin.ad.util.CommonUtils;
import com.daolin.ad.vo.AdPlanGetRequest;
import com.daolin.ad.vo.AdPlanRequest;
import com.daolin.ad.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Daolin
 * @date 2019/04/28
 */
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    private final AdUserRepository userRepository;

    private final AdPlanRepository planRepository;

    @Autowired
    public AdPlanServiceImpl(AdUserRepository userRepository,
                             AdPlanRepository planRepository) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    @Override
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if (!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        //确保关联的user对象是存在的
        Optional<AdUser> adUser = userRepository.findById(request.getId());
        if(!adUser.isPresent()){
            throw new AdException(Constants.ErrorMsg.CANNOT_FIND_RECORD);
        }

        AdPlan oldPlan = planRepository.findByUserIdAndPlanName(
                request.getUserId(),
                request.getPlanName()
        );

        if (oldPlan != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }
        AdPlan newPlan = planRepository.save(
                new AdPlan(request.getUserId(), request.getPlanName(),
                        CommonUtils.parseStringDate(request.getStartDate()),
                        CommonUtils.parseStringDate(request.getEndDate())
                )
        );

        return new AdPlanResponse(newPlan.getId(), newPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        if(!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        return planRepository.findAllByIdInAnAndUserId(
                request.getIds(), request.getUserId()
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if(!request.updateValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan plan = planRepository.findByIdAndUserId(
                request.getId(), request.getUserId()
        );

        if(plan == null){
            throw new AdException(Constants.ErrorMsg.CANNOT_FIND_RECORD);
        }
        if(request.getPlanName() != null){
            plan.setPlanName(request.getPlanName());
        }
        if(request.getStartDate() != null){
            plan.setStartDate(CommonUtils.parseStringDate(request.getStartDate()));
        }
        if(request.getEndDate() != null){
            plan.setEndDate(CommonUtils.parseStringDate(request.getEndDate()));
        }
        plan.setUpdateTime(new Date());
        plan = planRepository.save(plan);
        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if(!request.deleteValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan plan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if(plan == null){
            throw new AdException(Constants.ErrorMsg.CANNOT_FIND_RECORD);
        }
        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        planRepository.save(plan);
    }
}
