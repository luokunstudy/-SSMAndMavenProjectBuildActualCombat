package com.lk.entity.module;

import com.lk.entity.database.UserInfoBean;

/**
 * @author luokun
 * @date 2019/4/19 17:17
 */
public class LoginBean extends UserInfoBean{

    private String verificationCode;
    private LoginBean loginBean;
    public LoginBean clone() throws CloneNotSupportedException{
        LoginBean LoginBean = (LoginBean) super.clone();
        return LoginBean;
    }

    public boolean equals(Object anObject) {
        super.equals(anObject);
        if (this == anObject) {
            return true;
        }

        if (!(anObject instanceof LoginBean)) {
            return false;
        }

        LoginBean LoginBean = (LoginBean) anObject;

        if(!this.verificationCode.equals(LoginBean.getVerificationCode())) {
            return false;
        }

        if(!this.loginBean.equals(LoginBean.getLoginBean())) {
            return false;
        }

        return true;
    }

    public void add(LoginBean loginBean) {
        super.add(loginBean);
        this.verificationCode = loginBean.getVerificationCode();
        this.loginBean = loginBean.getLoginBean();
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String toString(){
        super.toString();
        StringBuffer strb = new StringBuffer();
        strb.append("{");
        if(verificationCode!=null){
            strb.append(",\"verificationCode\":\""+verificationCode+"\"");
        }
        if(loginBean!=null){
            strb.append(",\"loginBean\":\""+loginBean+"\"");
        }
        strb.append("}");

        return strb.toString();
    }
}
