package com.daolin.ad.service.impl;

import com.daolin.ad.constant.Constants;
import com.daolin.ad.dao.AdPlanRepository;
import com.daolin.ad.dao.AdUnitRepository;
import com.daolin.ad.dao.CreativeRepository;
import com.daolin.ad.dao.unitcondition.AdUnitDistrictRepository;
import com.daolin.ad.dao.unitcondition.AdUnitItRepository;
import com.daolin.ad.dao.unitcondition.AdUnitKeywordRepository;
import com.daolin.ad.dao.unitcondition.CreativeUnitRepository;
import com.daolin.ad.entity.AdPlan;
import com.daolin.ad.entity.AdUnit;
import com.daolin.ad.entity.Creative;
import com.daolin.ad.entity.unitcondition.AdUnitDistrict;
import com.daolin.ad.entity.unitcondition.AdUnitIt;
import com.daolin.ad.entity.unitcondition.AdUnitKeyword;
import com.daolin.ad.entity.unitcondition.CreativeUnit;
import com.daolin.ad.exception.AdException;
import com.daolin.ad.service.IAdUnitService;
import com.daolin.ad.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Daolin
 * @date 2019/04/29
 */
@Service
public class AdUnitServiceImpl implements IAdUnitService {

    private final AdPlanRepository planRepository;

    private final AdUnitRepository unitRepository;

    private final AdUnitKeywordRepository unitKeywordRepository;

    private final AdUnitItRepository unitItRepository;

    private final AdUnitDistrictRepository unitDistrictRepository;

    private final CreativeRepository creativeRepository;

    private final CreativeUnitRepository creativeUnitRepository;

    @Autowired
    public AdUnitServiceImpl(AdPlanRepository planRepository,
                             AdUnitRepository unitRepository,
                             AdUnitKeywordRepository unitKeywordRepository,
                             AdUnitItRepository unitItRepository,
                             AdUnitDistrictRepository unitDistrictRepository,
                             CreativeRepository creativeRepository,
                             CreativeUnitRepository creativeUnitRepository) {
        this.planRepository = planRepository;
        this.unitRepository = unitRepository;
        this.unitKeywordRepository = unitKeywordRepository;
        this.unitItRepository = unitItRepository;
        this.unitDistrictRepository = unitDistrictRepository;
        this.creativeRepository = creativeRepository;
        this.creativeUnitRepository = creativeUnitRepository;
    }

    @Override
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {
        if(!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        Optional<AdPlan> adPlan = planRepository.findById(request.getPlanId());
        if(!adPlan.isPresent()){
            throw new AdException(Constants.ErrorMsg.CANNOT_FIND_RECORD);
        }

        AdUnit oldUnit = unitRepository.findByPlanIdAndUnitName(
                request.getPlanId(), request.getUnitName()
        );

        if(oldUnit != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
        }
        AdUnit newAdUnit = unitRepository.save(
                new AdUnit(request.getPlanId(), request.getUnitName(),
                        request.getPositionType(), request.getBudget())
        );
        return new AdUnitResponse(newAdUnit.getId(), newAdUnit.getUnitName());
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {
        List<Long> unitIds = request.getUnitKeywords().stream()
                .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<Long> ids = Collections.emptyList();

        List<AdUnitKeyword> unitKeywords = new ArrayList<>();
        if(!CollectionUtils.isEmpty(request.getUnitKeywords())){
            request.getUnitKeywords().forEach(i -> unitKeywords.add(
                    new AdUnitKeyword(i.getUnitId(), i.getKeyword())
            ));
            ids = unitKeywordRepository.saveAll(unitKeywords).stream()
                    .map(AdUnitKeyword::getId)
                    .collect(Collectors.toList());
        }
        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        List<Long> unitIds = request.getUnitIts().stream()
                .map(AdUnitItRequest.UnitIt::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitIt> unitIts = new ArrayList<>();
        request.getUnitIts().forEach(i -> unitIts.add(
                new AdUnitIt(i.getUnitId(), i.getItTag())
        ));
        List<Long> ids = unitItRepository.saveAll(unitIts).stream()
                .map(AdUnitIt::getId)
                .collect(Collectors.toList());
        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        List<Long> unitIds = request.getUnitDistricts().stream()
                .map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitDistrict> unitDistricts = new ArrayList<>();
        request.getUnitDistricts().forEach(d -> unitDistricts.add(
                new AdUnitDistrict(d.getUnitId(), d.getProvince(), d.getCity())
        ));
        List<Long> ids = unitDistrictRepository.saveAll(unitDistricts).stream()
                .map(AdUnitDistrict::getId)
                .collect(Collectors.toList());
        return new AdUnitDistrictResponse(ids);
    }

    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {
        List<Long> unitIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getUnitId)
                .collect(Collectors.toList());
        List<Long> creativeIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getCreativeId)
                .collect(Collectors.toList());
        if(!(isRelatedUnitExist(unitIds) && isRelatedUnitExist(creativeIds))){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<CreativeUnit> creativeUnits = new ArrayList<>();
        request.getUnitItems().forEach(i -> creativeUnits.add(
                new CreativeUnit(i.getCreativeId(), i.getUnitId())
        ));
        List<Long> ids = creativeUnitRepository.saveAll(creativeUnits).stream()
                .map(CreativeUnit::getId).collect(Collectors.toList());
        return new CreativeUnitResponse(ids);
    }

    private boolean isRelatedUnitExist(List<Long> unitIds){
        if(CollectionUtils.isEmpty(unitIds)){
            return false;
        }
        return unitRepository.findAllById(unitIds).size() ==
                new HashSet<>(unitIds).size();
    }

    private boolean isRelatedCreativeExit(List<Long> creativeIds){
        if(CollectionUtils.isEmpty(creativeIds)) {
            return false;
        }
        return creativeRepository.findAllById(creativeIds).size() ==
                new HashSet<>(creativeIds).size();
    }

}
