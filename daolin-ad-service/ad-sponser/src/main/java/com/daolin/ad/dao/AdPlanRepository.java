package com.daolin.ad.dao;

import com.daolin.ad.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @author Daolin
 * @date 2019/04/26
 */
public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {

    AdPlan findByIdAndUserId(Long id, Long userId);

    /**
     * 通过 planId 和 userId 查找 AdPlan
     * @param ids   planId 的集合
     * @param userId    用户ID
     */
    List<AdPlan> findAllByIdInAnAndUserId(List<Long> ids, Long userId);

    AdPlan findByUserIdAndPlanName(Long userId, String planName);

    List<AdPlan> findAllByPlanStatus(Integer planStatus);

}
