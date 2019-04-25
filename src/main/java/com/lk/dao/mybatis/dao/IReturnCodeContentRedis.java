package com.lk.dao.mybatis.dao;

import com.lk.entity.database.ReturnCodeContentBean;

/**
 * @author luokun
 * @date 2019/4/22 10:06
 */
public interface IReturnCodeContentRedis {

    ReturnCodeContentBean selectCodeContentByCodeId(Integer nReturnCodeId) throws  Exception;

}
