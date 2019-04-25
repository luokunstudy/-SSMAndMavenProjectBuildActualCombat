package com.lk.dao.mybatis.dao;

import com.lk.entity.database.UserInfoBean;

/**
 * @author luokun
 * @date 2019/4/23 10:30
 */
public interface ISessionUserInfoRedis {
    /**
     * @Descrption
     */
    boolean setSessionUserInfo(UserInfoBean userInfoBean) throws Exception;

    /**
     * @Descrption
     */
    UserInfoBean getSessionUserInfo(String strUserId) throws Exception;
}
