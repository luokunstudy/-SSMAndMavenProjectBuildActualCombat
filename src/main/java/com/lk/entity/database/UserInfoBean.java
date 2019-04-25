package com.lk.entity.database;

import com.lk.entity.EntityBean;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @author luokun
 * @date 2019/4/19 16:48
 */
public class UserInfoBean extends EntityBean implements Cloneable{
    private String authIp;
    private Integer operationStatus;
    private Integer operationStatusBegin;
    private Integer operationStatusEnd;
    private Integer userStatus;
    private Integer userStatusBegin;
    private Integer userStatusEnd;
    private String createUserName;
    private String remark;
    private String openUserName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openUserTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openUserTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openUserTimeEnd;
    private String telePhone;
    private String lastLoginIp;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTimeEnd;
    private String merchantId;
    private String loginName;
    private String loginIp;
    private Long id;
    private Long idBegin;
    private Long idEnd;
    private String userDescribe;
    private String email;
    private Long loginTotal;
    private Long loginTotalBegin;
    private Long loginTotalEnd;
    private String nickName;
    private String roleId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTimeEnd;
    private String userName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeUserTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeUserTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeUserTimeEnd;
    private String userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTimeEnd;
    private String mobilePhone;
    private String userPortrait;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;
    private String closeUserName;

    private UserInfoBean userInfoBean;

    public UserInfoBean clone() throws CloneNotSupportedException{
        UserInfoBean userInfoBean = (UserInfoBean) super.clone();
        return userInfoBean;
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }

        if (!(anObject instanceof UserInfoBean)) {
            return false;
        }

        UserInfoBean userInfoBean = (UserInfoBean) anObject;

        if(!this.authIp.equals(userInfoBean.authIp)) {
            return false;
        }
        if(!this.operationStatus.equals(userInfoBean.operationStatus)) {
            return false;
        }
        if(!this.userStatus.equals(userInfoBean.userStatus)) {
            return false;
        }
        if(!this.createUserName.equals(userInfoBean.createUserName)) {
            return false;
        }
        if(!this.remark.equals(userInfoBean.remark)) {
            return false;
        }
        if(!this.openUserName.equals(userInfoBean.openUserName)) {
            return false;
        }
        if(!this.openUserTime.equals(userInfoBean.openUserTime)) {
            return false;
        }
        if(!this.telePhone.equals(userInfoBean.telePhone)) {
            return false;
        }
        if(!this.lastLoginIp.equals(userInfoBean.lastLoginIp)) {
            return false;
        }
        if(!this.password.equals(userInfoBean.password)) {
            return false;
        }
        if(!this.loginTime.equals(userInfoBean.loginTime)) {
            return false;
        }
        if(!this.merchantId.equals(userInfoBean.merchantId)) {
            return false;
        }
        if(!this.loginName.equals(userInfoBean.loginName)) {
            return false;
        }
        if(!this.loginIp.equals(userInfoBean.loginIp)) {
            return false;
        }
        if(!this.id.equals(userInfoBean.id)) {
            return false;
        }
        if(!this.userDescribe.equals(userInfoBean.userDescribe)) {
            return false;
        }
        if(!this.email.equals(userInfoBean.email)) {
            return false;
        }
        if(!this.loginTotal.equals(userInfoBean.loginTotal)) {
            return false;
        }
        if(!this.nickName.equals(userInfoBean.nickName)) {
            return false;
        }
        if(!this.roleId.equals(userInfoBean.roleId)) {
            return false;
        }
        if(!this.updateTime.equals(userInfoBean.updateTime)) {
            return false;
        }
        if(!this.userName.equals(userInfoBean.userName)) {
            return false;
        }
        if(!this.closeUserTime.equals(userInfoBean.closeUserTime)) {
            return false;
        }
        if(!this.userId.equals(userInfoBean.userId)) {
            return false;
        }
        if(!this.lastLoginTime.equals(userInfoBean.lastLoginTime)) {
            return false;
        }
        if(!this.mobilePhone.equals(userInfoBean.mobilePhone)) {
            return false;
        }
        if(!this.userPortrait.equals(userInfoBean.userPortrait)) {
            return false;
        }
        if(!this.createTime.equals(userInfoBean.createTime)) {
            return false;
        }
        if(!this.closeUserName.equals(userInfoBean.closeUserName)) {
            return false;
        }

        return true;
    }

    public void add(UserInfoBean userInfoBean) {
        super.add(userInfoBean);
        this.authIp = userInfoBean.getAuthIp();
        this.operationStatus = userInfoBean.getOperationStatus();
        this.userStatus = userInfoBean.getUserStatus();
        this.createUserName = userInfoBean.getCreateUserName();
        this.remark = userInfoBean.getRemark();
        this.openUserName = userInfoBean.getOpenUserName();
        this.openUserTime = userInfoBean.getOpenUserTime();
        this.telePhone = userInfoBean.getTelePhone();
        this.lastLoginIp = userInfoBean.getLastLoginIp();
        this.password = userInfoBean.getPassword();
        this.loginTime = userInfoBean.getLoginTime();
        this.merchantId = userInfoBean.getMerchantId();
        this.loginName = userInfoBean.getLoginName();
        this.loginIp = userInfoBean.getLoginIp();
        this.id = userInfoBean.getId();
        this.userDescribe = userInfoBean.getUserDescribe();
        this.email = userInfoBean.getEmail();
        this.loginTotal = userInfoBean.getLoginTotal();
        this.nickName = userInfoBean.getNickName();
        this.roleId = userInfoBean.getRoleId();
        this.updateTime = userInfoBean.getUpdateTime();
        this.userName = userInfoBean.getUserName();
        this.closeUserTime = userInfoBean.getCloseUserTime();
        this.userId = userInfoBean.getUserId();
        this.lastLoginTime = userInfoBean.getLastLoginTime();
        this.mobilePhone = userInfoBean.getMobilePhone();
        this.userPortrait = userInfoBean.getUserPortrait();
        this.createTime = userInfoBean.getCreateTime();
        this.closeUserName = userInfoBean.getCloseUserName();

    }

    public String getAuthIp(){
        return authIp;
    }
    public void setAuthIp(String authIp){
        this.authIp=authIp;
    }

    public Integer getOperationStatus(){
        return operationStatus;
    }
    public void setOperationStatus(Integer operationStatus){
        this.operationStatus=operationStatus;
    }

    public Integer getOperationStatusBegin(){
        return operationStatusBegin;
    }
    public void setOperationStatusBegin(Integer operationStatusBegin){
        this.operationStatusBegin=operationStatusBegin;
    }

    public Integer getOperationStatusEnd(){
        return operationStatusEnd;
    }
    public void setOperationStatusEnd(Integer operationStatusEnd){
        this.operationStatusEnd=operationStatusEnd;
    }

    public Integer getUserStatus(){
        return userStatus;
    }
    public void setUserStatus(Integer userStatus){
        this.userStatus=userStatus;
    }

    public Integer getUserStatusBegin(){
        return userStatusBegin;
    }
    public void setUserStatusBegin(Integer userStatusBegin){
        this.userStatusBegin=userStatusBegin;
    }

    public Integer getUserStatusEnd(){
        return userStatusEnd;
    }
    public void setUserStatusEnd(Integer userStatusEnd){
        this.userStatusEnd=userStatusEnd;
    }

    public String getCreateUserName(){
        return createUserName;
    }
    public void setCreateUserName(String createUserName){
        this.createUserName=createUserName;
    }

    public String getRemark(){
        return remark;
    }
    public void setRemark(String remark){
        this.remark=remark;
    }

    public String getOpenUserName(){
        return openUserName;
    }
    public void setOpenUserName(String openUserName){
        this.openUserName=openUserName;
    }

    public Date getOpenUserTime(){
        return openUserTime;
    }
    public void setOpenUserTime(Date openUserTime){
        this.openUserTime=openUserTime;
    }

    public Date getOpenUserTimeBegin(){
        return openUserTimeBegin;
    }
    public void setOpenUserTimeBegin(Date openUserTimeBegin){
        this.openUserTimeBegin=openUserTimeBegin;
    }

    public Date getOpenUserTimeEnd(){
        return openUserTimeEnd;
    }
    public void setOpenUserTimeEnd(Date openUserTimeEnd){
        this.openUserTimeEnd=openUserTimeEnd;
    }

    public String getTelePhone(){
        return telePhone;
    }
    public void setTelePhone(String telePhone){
        this.telePhone=telePhone;
    }

    public String getLastLoginIp(){
        return lastLoginIp;
    }
    public void setLastLoginIp(String lastLoginIp){
        this.lastLoginIp=lastLoginIp;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    public Date getLoginTime(){
        return loginTime;
    }
    public void setLoginTime(Date loginTime){
        this.loginTime=loginTime;
    }

    public Date getLoginTimeBegin(){
        return loginTimeBegin;
    }
    public void setLoginTimeBegin(Date loginTimeBegin){
        this.loginTimeBegin=loginTimeBegin;
    }

    public Date getLoginTimeEnd(){
        return loginTimeEnd;
    }
    public void setLoginTimeEnd(Date loginTimeEnd){
        this.loginTimeEnd=loginTimeEnd;
    }

    public String getMerchantId(){
        return merchantId;
    }
    public void setMerchantId(String merchantId){
        this.merchantId=merchantId;
    }

    public String getLoginName(){
        return loginName;
    }
    public void setLoginName(String loginName){
        this.loginName=loginName;
    }

    public String getLoginIp(){
        return loginIp;
    }
    public void setLoginIp(String loginIp){
        this.loginIp=loginIp;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    public Long getIdBegin(){
        return idBegin;
    }
    public void setIdBegin(Long idBegin){
        this.idBegin=idBegin;
    }

    public Long getIdEnd(){
        return idEnd;
    }
    public void setIdEnd(Long idEnd){
        this.idEnd=idEnd;
    }

    public String getUserDescribe(){
        return userDescribe;
    }
    public void setUserDescribe(String userDescribe){
        this.userDescribe=userDescribe;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public Long getLoginTotal(){
        return loginTotal;
    }
    public void setLoginTotal(Long loginTotal){
        this.loginTotal=loginTotal;
    }

    public Long getLoginTotalBegin(){
        return loginTotalBegin;
    }
    public void setLoginTotalBegin(Long loginTotalBegin){
        this.loginTotalBegin=loginTotalBegin;
    }

    public Long getLoginTotalEnd(){
        return loginTotalEnd;
    }
    public void setLoginTotalEnd(Long loginTotalEnd){
        this.loginTotalEnd=loginTotalEnd;
    }

    public String getNickName(){
        return nickName;
    }
    public void setNickName(String nickName){
        this.nickName=nickName;
    }

    public String getRoleId(){
        return roleId;
    }
    public void setRoleId(String roleId){
        this.roleId=roleId;
    }

    public Date getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime=updateTime;
    }

    public Date getUpdateTimeBegin(){
        return updateTimeBegin;
    }
    public void setUpdateTimeBegin(Date updateTimeBegin){
        this.updateTimeBegin=updateTimeBegin;
    }

    public Date getUpdateTimeEnd(){
        return updateTimeEnd;
    }
    public void setUpdateTimeEnd(Date updateTimeEnd){
        this.updateTimeEnd=updateTimeEnd;
    }

    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }

    public Date getCloseUserTime(){
        return closeUserTime;
    }
    public void setCloseUserTime(Date closeUserTime){
        this.closeUserTime=closeUserTime;
    }

    public Date getCloseUserTimeBegin(){
        return closeUserTimeBegin;
    }
    public void setCloseUserTimeBegin(Date closeUserTimeBegin){
        this.closeUserTimeBegin=closeUserTimeBegin;
    }

    public Date getCloseUserTimeEnd(){
        return closeUserTimeEnd;
    }
    public void setCloseUserTimeEnd(Date closeUserTimeEnd){
        this.closeUserTimeEnd=closeUserTimeEnd;
    }

    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }

    public Date getLastLoginTime(){
        return lastLoginTime;
    }
    public void setLastLoginTime(Date lastLoginTime){
        this.lastLoginTime=lastLoginTime;
    }

    public Date getLastLoginTimeBegin(){
        return lastLoginTimeBegin;
    }
    public void setLastLoginTimeBegin(Date lastLoginTimeBegin){
        this.lastLoginTimeBegin=lastLoginTimeBegin;
    }

    public Date getLastLoginTimeEnd(){
        return lastLoginTimeEnd;
    }
    public void setLastLoginTimeEnd(Date lastLoginTimeEnd){
        this.lastLoginTimeEnd=lastLoginTimeEnd;
    }

    public String getMobilePhone(){
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone){
        this.mobilePhone=mobilePhone;
    }

    public String getUserPortrait(){
        return userPortrait;
    }
    public void setUserPortrait(String userPortrait){
        this.userPortrait=userPortrait;
    }

    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }

    public Date getCreateTimeBegin(){
        return createTimeBegin;
    }
    public void setCreateTimeBegin(Date createTimeBegin){
        this.createTimeBegin=createTimeBegin;
    }

    public Date getCreateTimeEnd(){
        return createTimeEnd;
    }
    public void setCreateTimeEnd(Date createTimeEnd){
        this.createTimeEnd=createTimeEnd;
    }

    public String getCloseUserName(){
        return closeUserName;
    }
    public void setCloseUserName(String closeUserName){
        this.closeUserName=closeUserName;
    }


    public void setUserInfoBean(UserInfoBean userInfoBean){
        this.userInfoBean=userInfoBean;
    }
    public UserInfoBean getUserInfoBean(){
        return userInfoBean;
    }

    public String toString(){
        StringBuffer strb = new StringBuffer();
        strb.append("{");
        if(authIp!=null){
            strb.append(",\"authIp\":\""+authIp+"\"");
        }
        if(operationStatus!=null){
            strb.append(",\"operationStatus\":\""+operationStatus+"\"");
            strb.append(",\"operationStatusBegin\":\""+operationStatusBegin+"\"");
            strb.append(",\"operationStatusEnd\":\""+operationStatusEnd+"\"");
        }
        if(userStatus!=null){
            strb.append(",\"userStatus\":\""+userStatus+"\"");
            strb.append(",\"userStatusBegin\":\""+userStatusBegin+"\"");
            strb.append(",\"userStatusEnd\":\""+userStatusEnd+"\"");
        }
        if(createUserName!=null){
            strb.append(",\"createUserName\":\""+createUserName+"\"");
        }
        if(remark!=null){
            strb.append(",\"remark\":\""+remark+"\"");
        }
        if(openUserName!=null){
            strb.append(",\"openUserName\":\""+openUserName+"\"");
        }
        if(openUserTime!=null){
            strb.append(",\"openUserTime\":\""+openUserTime+"\"");
            strb.append(",\"openUserTimeBegin\":\""+openUserTimeBegin+"\"");
            strb.append(",\"openUserTimeEnd\":\""+openUserTimeEnd+"\"");
        }
        if(telePhone!=null){
            strb.append(",\"telePhone\":\""+telePhone+"\"");
        }
        if(lastLoginIp!=null){
            strb.append(",\"lastLoginIp\":\""+lastLoginIp+"\"");
        }
        if(password!=null){
            strb.append(",\"password\":\""+password+"\"");
        }
        if(loginTime!=null){
            strb.append(",\"loginTime\":\""+loginTime+"\"");
            strb.append(",\"loginTimeBegin\":\""+loginTimeBegin+"\"");
            strb.append(",\"loginTimeEnd\":\""+loginTimeEnd+"\"");
        }
        if(merchantId!=null){
            strb.append(",\"merchantId\":\""+merchantId+"\"");
        }
        if(loginName!=null){
            strb.append(",\"loginName\":\""+loginName+"\"");
        }
        if(loginIp!=null){
            strb.append(",\"loginIp\":\""+loginIp+"\"");
        }
        if(id!=null){
            strb.append(",\"id\":\""+id+"\"");
            strb.append(",\"idBegin\":\""+idBegin+"\"");
            strb.append(",\"idEnd\":\""+idEnd+"\"");
        }
        if(userDescribe!=null){
            strb.append(",\"userDescribe\":\""+userDescribe+"\"");
        }
        if(email!=null){
            strb.append(",\"email\":\""+email+"\"");
        }
        if(loginTotal!=null){
            strb.append(",\"loginTotal\":\""+loginTotal+"\"");
            strb.append(",\"loginTotalBegin\":\""+loginTotalBegin+"\"");
            strb.append(",\"loginTotalEnd\":\""+loginTotalEnd+"\"");
        }
        if(nickName!=null){
            strb.append(",\"nickName\":\""+nickName+"\"");
        }
        if(roleId!=null){
            strb.append(",\"roleId\":\""+roleId+"\"");
        }
        if(updateTime!=null){
            strb.append(",\"updateTime\":\""+updateTime+"\"");
            strb.append(",\"updateTimeBegin\":\""+updateTimeBegin+"\"");
            strb.append(",\"updateTimeEnd\":\""+updateTimeEnd+"\"");
        }
        if(userName!=null){
            strb.append(",\"userName\":\""+userName+"\"");
        }
        if(closeUserTime!=null){
            strb.append(",\"closeUserTime\":\""+closeUserTime+"\"");
            strb.append(",\"closeUserTimeBegin\":\""+closeUserTimeBegin+"\"");
            strb.append(",\"closeUserTimeEnd\":\""+closeUserTimeEnd+"\"");
        }
        if(userId!=null){
            strb.append(",\"userId\":\""+userId+"\"");
        }
        if(lastLoginTime!=null){
            strb.append(",\"lastLoginTime\":\""+lastLoginTime+"\"");
            strb.append(",\"lastLoginTimeBegin\":\""+lastLoginTimeBegin+"\"");
            strb.append(",\"lastLoginTimeEnd\":\""+lastLoginTimeEnd+"\"");
        }
        if(mobilePhone!=null){
            strb.append(",\"mobilePhone\":\""+mobilePhone+"\"");
        }
        if(userPortrait!=null){
            strb.append(",\"userPortrait\":\""+userPortrait+"\"");
        }
        if(createTime!=null){
            strb.append(",\"createTime\":\""+createTime+"\"");
            strb.append(",\"createTimeBegin\":\""+createTimeBegin+"\"");
            strb.append(",\"createTimeEnd\":\""+createTimeEnd+"\"");
        }
        if(closeUserName!=null){
            strb.append(",\"closeUserName\":\""+closeUserName+"\"");
        }
        if(userInfoBean!=null){
            strb.append(",\"userInfoBean\":\""+userInfoBean.toString()+"\"");
        }

        return strb.toString();
    }
}
