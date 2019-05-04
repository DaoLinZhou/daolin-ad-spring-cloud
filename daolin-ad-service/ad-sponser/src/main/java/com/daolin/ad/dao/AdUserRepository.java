package com.daolin.ad.dao;

import com.daolin.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Daolin
 * @date 2019/04/26
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    /**
     * <h2>根据用户名查找用户记录</h2>
     * @param username 用户名
     * @return  用户类
     */
    AdUser findByUsername(String username);



}
