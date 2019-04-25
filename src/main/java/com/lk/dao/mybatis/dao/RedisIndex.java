package com.lk.dao.mybatis.dao;

/**
 * @author luokun
 * @date 2019/4/22 15:07
 */
public class RedisIndex {

    /**
     * 用户redis实例
     */
    public static int S_N_SESSION_USER_INFO_INDEX = 2;

    /**
     * 处理返回代码的redis实例
     */
    public static int S_N_RETURN_CODE_CONTENT_INDEX = 2;

    /**
     * 处理菜单的redis实例
     */
    public static int S_N_MENU_INFO_INDEX = 2;

    /**
     * 处理菜单权限的redis实例
     */
    public static int S_N_ROLE_MENU_JURISDICTION_INFO_INDEX = 2;

    /**
     * 处理系统代码的redis实例
     */
    public static int S_N_SYSTEM_CODE_INFO_INDEX = 2;

    /**
     * 处理任务ID的redis实例
     */
    public static int S_N_WORK_ID_INFO_INDEX = 2;
}
