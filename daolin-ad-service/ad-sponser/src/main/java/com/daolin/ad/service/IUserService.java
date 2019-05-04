package com.daolin.ad.service;

import com.daolin.ad.exception.AdException;
import com.daolin.ad.vo.CreateUserRequest;
import com.daolin.ad.vo.CreateUserResponse;
import org.springframework.stereotype.Service;

/**
 * @author Daolin
 * @date 2019/04/26
 */
public interface IUserService {

    /**
     * <h2>创建用户</h2>
     * */
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;

}
