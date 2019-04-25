package com.lk.dao.mybatis.dao.impl;

import com.alibaba.fastjson.JSON;
import com.lk.dao.mybatis.dao.IReturnCodeContentRedis;
import com.lk.dao.mybatis.dao.RedisIndex;
import com.lk.dao.mybatis.dao.key.ReturnCodeContentRedisKey;
import com.lk.entity.database.ReturnCodeContentBean;
import com.lk.tools.jedis.springredis.RedisSpringUtils;

import javax.annotation.Resource;

/**
 * @author luokun
 * @date 2019/4/22 10:46
 */
public class ReturnCodeContentRedisImpl implements IReturnCodeContentRedis{

    @Resource
    private RedisSpringUtils redisSpringUtils;

    @Override
    public ReturnCodeContentBean selectCodeContentByCodeId(Integer nReturnCodeId) throws Exception {
        String strReturnCodeContent = redisSpringUtils.hget(
                RedisIndex.S_N_RETURN_CODE_CONTENT_INDEX,
                ReturnCodeContentRedisKey.S_STR_HASH_MANAGE_RETURN_CODE_CONTENT_BY_CODE_ID,
                nReturnCodeId+""
        );
        return JSON.parseObject(strReturnCodeContent, ReturnCodeContentBean.class);
    }
}
