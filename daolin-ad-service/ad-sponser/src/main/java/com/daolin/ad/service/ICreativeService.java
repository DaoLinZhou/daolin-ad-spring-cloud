package com.daolin.ad.service;

import com.daolin.ad.exception.AdException;
import com.daolin.ad.vo.CreateUserRequest;
import com.daolin.ad.vo.CreativeRequest;
import com.daolin.ad.vo.CreativeResponse;

/**
 * @author Daolin
 * @date 2019/04/30
 */
public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request);

}
