package com.daolin.ad.service.impl;

import com.daolin.ad.dao.CreativeRepository;
import com.daolin.ad.entity.Creative;
import com.daolin.ad.service.ICreativeService;
import com.daolin.ad.vo.CreativeRequest;
import com.daolin.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Daolin
 * @date 2019/04/30
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    private final CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {
        Creative creative = creativeRepository.save(request.convertToEntity());
        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
