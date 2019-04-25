package com.lk.service.login.impl;

import com.lk.common.GetReturnContent;
import com.lk.common.attributerule.LoginAttributeRule;
import com.lk.common.attributerule.UserInfoAttributeRule;
import com.lk.common.returncode.login.LoginReturnCode;
import com.lk.dao.mybatis.dao.ISessionUserInfoRedis;
import com.lk.dao.mybatis.dao.UserInfoDao;
import com.lk.entity.ResponseBean;
import com.lk.entity.database.UserInfoBean;
import com.lk.entity.module.LoginBean;
import com.lk.service.login.LoginSvc;
import com.lk.tools.date.DateUtils;
import com.lk.tools.string.StringUtils;
import com.lk.tools.security.EncryptUtils;
import com.lk.tools.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * @author luokun
 * @date 2019/4/19 17:37
 */
@Service
public class LoginSvcImpl implements LoginSvc {

    private Logger log = LoggerFactory.getLogger(LoginSvcImpl.class);

    @Resource
    private UserInfoDao userInfoDaoImpl;

    @Resource
    private GetReturnContent getReturnContent;

    @Resource
    private ISessionUserInfoRedis sessionUserInfoRedisImpl;

    @Override
    public ResponseBean<LoginBean> login(HttpServletRequest request, LoginBean loginBean) {

        log.info("user login begin:loginBean:"+loginBean);

        String strUserId = (String) request.getSession().getAttribute("userId");

        //创建响应对象
        ResponseBean<LoginBean> responseLoginBean = new ResponseBean<LoginBean>();

        LoginBean loginInfoBean = new LoginBean();

        try {
            //获得验证码
            String strVerificationCode =(String) request.getSession().getAttribute("verificationCode");

            //验证实体内对象参数是否正确，如果正确直接返回
            if (!this.verifidationEntityBean(responseLoginBean,loginBean,strUserId,strVerificationCode)){
                return responseLoginBean;
            }
            //获得用户的对象
            //查询数据
            if (!this.operatingDataSource(responseLoginBean,loginInfoBean,loginBean,strUserId)){
                return responseLoginBean;
            }
            //验证数据源内的数据是否正确
            if (!this.verificationEntityBean(request,responseLoginBean,loginBean,strUserId)){
                return  responseLoginBean;
            }
            //将登陆信息插入用户表
            if (!this.operatingDataSource(request,responseLoginBean,strUserId)){
                return  responseLoginBean;
            }
            //将用户信息放入redis
            if(!this.setUserInfoBeanForRedis(request, responseLoginBean)){
                return responseLoginBean;
            }
            //將userId放入session
            request.getSession().setAttribute("userId", responseLoginBean.getObj().getUserId());

            //组合正确响应数据
            responseLoginBean.setStatus(
                    LoginReturnCode.S_N_LOGIN_SUCCESS);
            responseLoginBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_SUCCESS));

            log.info("user login end is success:loginBean:"+responseLoginBean);
            return responseLoginBean;
        }catch (Exception e){
            //组合异常响应数据
            responseLoginBean.setStatus(
                    LoginReturnCode.S_N_LOGIN_ERROR);
            responseLoginBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_ERROR));
            responseLoginBean.setObj(loginInfoBean);
            log.error("user login unknown error end:"
                    + "userId:"+strUserId+";"
                    + "responseLoginBean:" + responseLoginBean.toString(), e);
            return responseLoginBean;
        }
    }

    @Override
    public ResponseBean<UserInfoBean> logout(HttpServletRequest request) {
        ResponseBean<UserInfoBean> responseLogoutBean = new ResponseBean<UserInfoBean>();
        String strUserId =  (String) request.getSession().getAttribute("userId");
        log.info("user logout begin");
        try {
            request.getSession().invalidate();
            responseLogoutBean.setStatus(LoginReturnCode.S_N_LOGOUT_SUCCESS);
            responseLogoutBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGOUT_SUCCESS));

            log.info("user logout end:responseLogoutBean:"+responseLogoutBean+";"
                    + "userId:"+strUserId);
        }catch (Exception e){
            responseLogoutBean.setStatus(LoginReturnCode.S_N_LOGOUT_SUCCESS);
            responseLogoutBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGOUT_ERROR));
            log.error("user logout error:responseLogoutBean:"+responseLogoutBean+";"
                    + "strUserId:"+strUserId, e);
        }
            return responseLogoutBean;

    }

    @Override
    public ResponseBean<UserInfoBean> loginTime() {
        log.info("user loginTime begin");
        ResponseBean<UserInfoBean> responseLoginTimeBean =new ResponseBean<UserInfoBean>();
        try {
          responseLoginTimeBean.setStatus(LoginReturnCode.S_N_LOGIN_TIMEOUT);
          responseLoginTimeBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_TIMEOUT));
            log.info("uesr loginTime end:responseBean:" + responseLoginTimeBean.toString());
        }catch (Exception e){
          responseLoginTimeBean.setStatus(LoginReturnCode.S_N_LOGIN_FAILED);
          responseLoginTimeBean.setContent(this.getReturnContent.getContent(
                LoginReturnCode.S_N_LOGIN_FAILED));
        log.error("user loginTime error:", e);
        log.error("user loginTime end");
        }
        return responseLoginTimeBean;
    }

    private boolean setUserInfoBeanForRedis(HttpServletRequest request, ResponseBean<LoginBean> responseLoginBean) {
        boolean booValue = false;
        log.info("userInfoBean:"+responseLoginBean.getObj());
        try {
            //放入redis
            booValue = sessionUserInfoRedisImpl.setSessionUserInfo(responseLoginBean.getObj());
        } catch (Exception e) {
            responseLoginBean.setStatus(
                    LoginReturnCode.S_N_LOGIN_FAILED);
            responseLoginBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_FAILED));
            log.error("user login end redis is error",e);
            return false;
        }
        if(!booValue){
            //组合正确响应数据
            responseLoginBean.setStatus(
                    LoginReturnCode.S_N_LOGIN_ERROR);
            responseLoginBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_ERROR));

            log.warn("user login end is error:loginBean:"+responseLoginBean);
            return false;
        }

        return true;
    }

    private boolean operatingDataSource(HttpServletRequest request,
                                        ResponseBean<LoginBean> responseLoginBean, String strUserId) {
        int nLoginUpdateCount;
        LoginBean loginUpdateBean = new LoginBean();
        loginUpdateBean.setLoginIp(Utils.getIp(request));
        loginUpdateBean.setLoginTime(DateUtils.getDate());
        loginUpdateBean.setLastLoginIp(responseLoginBean.getObj().getLoginIp());
        loginUpdateBean.setLastLoginTime(responseLoginBean.getObj().getLoginTime());
        loginUpdateBean.setLoginTotal(responseLoginBean.getObj().getLoginTotal()+1);

        LoginBean loginWhereBean = new LoginBean();
        loginWhereBean.setUserId(responseLoginBean.getObj().getUserId());
        loginUpdateBean.setUserInfoBean(loginWhereBean);
        try {
            nLoginUpdateCount = this.userInfoDaoImpl.updateSingleByLoginForUserId(loginUpdateBean);
        } catch (Exception e) {
            responseLoginBean.setStatus(LoginReturnCode.S_N_LOGIN_FAILED);
            responseLoginBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_FAILED));
            log.error("user login mysql is error end:"
                    + "userId:"+strUserId+";"
                    + "responseLoginBean:" + responseLoginBean.toString(), e);
            return false;
        }
        if (nLoginUpdateCount!=1) {
            responseLoginBean.setStatus(LoginReturnCode.S_N_LOGIN_ERROR);
            responseLoginBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_ERROR));
            log.error("user login mysql is error end:"
                    + "userId:"+strUserId+";"
                    + "nLoginUpdateCount:" + nLoginUpdateCount);
            return false;
        }
        return true;
    }

    private boolean verificationEntityBean(HttpServletRequest request, ResponseBean<LoginBean>
            responseLoginBean, LoginBean loginBean, String strUserId) {
        String strPassword;
        try {
            strPassword = EncryptUtils.md5(loginBean.getPassword());
        }catch (NoSuchAlgorithmException e){
            responseLoginBean.setStatus(
                    LoginReturnCode.S_N_LOGIN_PASSWORD_ERROR);
            responseLoginBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_PASSWORD_ERROR));
            responseLoginBean.setObj(loginBean);
            log.warn("user login password error end:"
                    + "userId:"+strUserId+";"
                    + "responseLoginBean:" + responseLoginBean.toString());
            return false;
        }
        //判断用户名是否正确
        if (responseLoginBean.getObj() == null) {
            responseLoginBean.setStatus(
                    LoginReturnCode.S_N_LOGIN_LOGIN_NAME_ERROR);
            responseLoginBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_LOGIN_NAME_ERROR));
            responseLoginBean.setObj(loginBean);
            log.warn("user login login name error end:"
                    + "userId:"+strUserId+";"
                    + "responseLoginBean:" + responseLoginBean.toString());
            return false;
        }
        //判断密码是否正确
        if (!StringUtils.equals(responseLoginBean.getObj().getPassword(), strPassword)
                ||!StringUtils.equals(responseLoginBean.getObj().getPassword(), strPassword)) {
            responseLoginBean.setStatus(
                    LoginReturnCode.S_N_LOGIN_PASSWORD_ERROR);
            responseLoginBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_PASSWORD_ERROR));
            responseLoginBean.setObj(loginBean);
            log.warn("user login password error end:"
                    + "userId:"+strUserId+";"
                    + "responseLoginBean:" + responseLoginBean.toString());
            return false;
        }
        return true;
    }

    private boolean operatingDataSource(ResponseBean<LoginBean> responseLoginBean, LoginBean loginInfoBean,
                                        LoginBean loginBean, String strUserId) {
        UserInfoBean userInfoBean =null;
        try {
            loginBean.setField(
                    "userId,password,loginName,userStatus,authIp,"
                            + "loginIp,loginTime,merchantId,loginTotal,userName"
            );
            userInfoBean =
                    this.userInfoDaoImpl.selectSingleByLoginForLoginName(loginBean);
        }catch (Exception e){
            responseLoginBean.setStatus(LoginReturnCode.S_N_LOGIN_FAILED);
            responseLoginBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_FAILED));
            responseLoginBean.setObj(loginInfoBean);
            log.error("user login mysql is error end:"
                    + "userId:"+strUserId+";"
                    + "responseLoginBean:" + responseLoginBean.toString(), e);
            return false;
        }
        if (userInfoBean == null) {
            responseLoginBean.setStatus(LoginReturnCode.S_N_LOGIN_LOGIN_NAME_ERROR);
            responseLoginBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_LOGIN_NAME_ERROR));
            responseLoginBean.setObj(loginInfoBean);
            log.error("user login userInfoBean is null error end:"
                    + "userId:"+strUserId+";"
                    + "responseLoginBean:" + responseLoginBean.toString());
            return false;
        }
        loginInfoBean.add(userInfoBean);
        responseLoginBean.setObj(loginInfoBean);
        return  true;
    }

    private boolean verifidationEntityBean(ResponseBean<LoginBean> responseLoginBean, LoginBean loginBean,
                                           String strUserId, String strVerificationCode) {
        //验证登录名是否正确
        if (!StringUtils.isValue(loginBean.getLoginName())||loginBean.getLoginName().length()>
                UserInfoAttributeRule.S_N_USER_LOGIN_NAME_CODE_LENGTH_MAX||
                loginBean.getLoginName().length()<UserInfoAttributeRule.S_N_USER_LOGIN_NAME_CODE_LENGTH_MIN){
            responseLoginBean.setStatus(LoginReturnCode.S_N_LOGIN_LOGIN_NAME_ERROR);
            responseLoginBean.setContent(this.getReturnContent.getContent(LoginReturnCode.S_N_LOGIN_LOGIN_NAME_ERROR));
            responseLoginBean.setObj(loginBean);
            log.warn("user login login name error end:"+strUserId+"responseLoginBean:"+responseLoginBean.toString());
            return  false;
        }
        //验证登录密码
        if ((!StringUtils.isValue(loginBean.getPassword())||loginBean.getPassword().length()!=
                UserInfoAttributeRule.S_N_USER_LOGIN_PASSWORD_CODE_LENGTH)) {
            responseLoginBean.setStatus(
                    LoginReturnCode.S_N_LOGIN_PASSWORD_ERROR);
            responseLoginBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_PASSWORD_ERROR));
            responseLoginBean.setObj(loginBean);
            log.warn("user login password error end:"
                    + "userId:"+strUserId+";"
                    + "responseLoginBean:" + responseLoginBean.toString());
            return false;
        }
        //验证验证码是否正确
        if (
                (!StringUtils.isValue(loginBean.getVerificationCode())
                        ||loginBean.getVerificationCode().length()
                        != LoginAttributeRule.S_N_VERIFICATION_CODE_LENGTH
                        ||!StringUtils.equals(loginBean.getVerificationCode(), strVerificationCode)
                )
                ) {
            responseLoginBean.setStatus(
                    LoginReturnCode.S_N_LOGIN_VERFICATION_CODE_ERROR);
            responseLoginBean.setContent(this.getReturnContent.getContent(
                    LoginReturnCode.S_N_LOGIN_VERFICATION_CODE_ERROR));
            responseLoginBean.setObj(loginBean);
            log.warn("user login verfication code error end:"
                    + "userId:"+strUserId+";"
                    + "responseLoginBean:" + responseLoginBean.toString());
            return false;
        }
        return  true;
    }
}
