package com.daolin.ad.service.impl;

import com.daolin.ad.constant.Constants;
import com.daolin.ad.dao.AdUserRepository;
import com.daolin.ad.entity.AdUser;
import com.daolin.ad.exception.AdException;
import com.daolin.ad.service.IUserService;
import com.daolin.ad.util.CommonUtils;
import com.daolin.ad.vo.CreateUserRequest;
import com.daolin.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Daolin
 * @date 2019/04/26
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {


    private final AdUserRepository userRepository;

    @Autowired
    public UserServiceImpl(AdUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {

        if(!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdUser oldUser = userRepository.findByUsername(request.getUsername());
        if(oldUser != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        AdUser newUser = userRepository.save(
                new AdUser(request.getUsername(),
                CommonUtils.md5(request.getUsername()))
        );

        return new CreateUserResponse(
                newUser.getId(), newUser.getUsername(), newUser.getToken(),
                newUser.getCreateTime(), newUser.getUpdateTime()
        );
    }

}
