package com.lk.common.returncode.login;

/**
 * @author luokun
 * @date 2019/4/22 9:36
 */
public class LoginReturnCode {

    public static Integer S_N_LOGIN_SUCCESS = 100000;
    public static Integer S_N_LOGIN_FAILED = 100001;//登录失败
    public static Integer S_N_LOGIN_ERROR = 100002;//登陆错误
    public static Integer S_N_LOGIN_LOGIN_NAME_ERROR = 100003;//您的用户名或密码错误！
    public static Integer S_N_LOGIN_PASSWORD_ERROR = 100004;//您的用户名或密码错误！
    public static Integer S_N_LOGIN_VERFICATION_CODE_ERROR = 100005;//登录验证码错误
    public static Integer S_N_LOGIN_USER_STATUS_CLOSE_ERROR = 100006;
    public static Integer S_N_LOGIN_USER_LOCK_ERROR = 100007;
    public static Integer S_N_LOGIN_USER_AUTH_IP = 100008;
    public static Integer S_N_LOGIN_TIMEOUT = 100009;//登陆超时，请重新登陆！

    public static Integer S_N_LOGOUT_SUCCESS = 100100;//退出成功
    public static Integer S_N_LOGOUT_FAILED = 100101;
    public static Integer S_N_LOGOUT_ERROR = 100102;




}
