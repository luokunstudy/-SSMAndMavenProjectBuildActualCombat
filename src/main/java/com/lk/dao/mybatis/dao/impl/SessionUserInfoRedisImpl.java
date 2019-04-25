package com.lk.dao.mybatis.dao.impl;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.lk.dao.mybatis.dao.ISessionUserInfoRedis;
import com.lk.dao.mybatis.dao.RedisIndex;
import com.lk.dao.mybatis.dao.key.SessionUserInfoRedisKey;
import com.lk.entity.database.UserInfoBean;
import com.lk.tools.jedis.springredis.RedisSpringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSON;

/**
 * @author luokun
 * @date 2019/4/23 10:32
 */
@Service
public class SessionUserInfoRedisImpl implements ISessionUserInfoRedis {
    @Resource
    RedisSpringUtils redisSpringUtils;


    @Override
    public UserInfoBean getSessionUserInfo(String strSysUserId) throws Exception {

        if (StringUtils.isEmpty(strSysUserId)) {
            return new UserInfoBean();
        }
        String strUserInfo = redisSpringUtils.hget(
                RedisIndex.S_N_SESSION_USER_INFO_INDEX,
                SessionUserInfoRedisKey.S_STR_SESSION_USER_INFO_BY_USER_ID,
                strSysUserId
        );
        return JSON.parseObject(strUserInfo, UserInfoBean.class);
    }

    @Override
    public boolean setSessionUserInfo(UserInfoBean userInfo) throws Exception {

        if (userInfo==null) {
            throw new Exception("userInfo is null");
        }
        Map<String, String> sysUserInfoMap = new HashMap<String, String>();

        sysUserInfoMap.put(userInfo.getUserId(), JSON.toJSONString(userInfo));

        if (!redisSpringUtils.hmset(
                RedisIndex.S_N_SESSION_USER_INFO_INDEX,
                SessionUserInfoRedisKey.S_STR_SESSION_USER_INFO_BY_USER_ID,
                sysUserInfoMap)) {
            return false;
        }
        return true;
    }
}
