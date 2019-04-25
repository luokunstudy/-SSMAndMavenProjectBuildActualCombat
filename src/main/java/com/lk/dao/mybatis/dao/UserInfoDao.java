package com.lk.dao.mybatis.dao;

import com.lk.entity.database.UserInfoBean; /**
 * @author luokun
 * @date 2019/4/22 9:30
 */
public interface UserInfoDao {

    /**
     * 根据登录名获得用户相应信息。
     * @author eric
     * @param userInfoBean
     * @return
     */
    public  UserInfoBean selectSingleByLoginForLoginName(UserInfoBean userInfoBean);

    /**
     * 根据用户ID修改用户信息
     * @author eric
     * @date 2018年1月30日下午2:21:46
     * @param userInfoBean
     * @return
     */
    public int updateSingleByLoginForUserId(UserInfoBean userInfoBean);

}
