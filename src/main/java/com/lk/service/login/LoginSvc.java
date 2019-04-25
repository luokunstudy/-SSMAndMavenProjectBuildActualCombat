package com.lk.service.login;

import com.lk.entity.ResponseBean;
import com.lk.entity.database.UserInfoBean;
import com.lk.entity.module.LoginBean;

import javax.servlet.http.HttpServletRequest; /**
 * @author luokun
 * @date 2019/4/19 16:59
 */
public interface LoginSvc {
    ResponseBean<LoginBean> login(HttpServletRequest request, LoginBean loginBean);

    ResponseBean<UserInfoBean> loginTime();

    ResponseBean<UserInfoBean> logout(HttpServletRequest request);
}
