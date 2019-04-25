package com.lk.entity.database;

import com.lk.entity.EntityBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author luokun
 * @date 2019/4/22 10:40
 */
public class ReturnCodeContentBean extends EntityBean implements Cloneable{

    private Long codeId;
    private Long codeIdBegin;
    private Long codeIdEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;
    private String codeContent;
    private String createUserName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTimeEnd;
    private String remark;
    private Long id;
    private Long idBegin;
    private Long idEnd;
    private String codeDescribe;

    private ReturnCodeContentBean returnCodeContentBean;

    public ReturnCodeContentBean clone() throws CloneNotSupportedException{
        ReturnCodeContentBean returnCodeContentBean = (ReturnCodeContentBean) super.clone();
        return returnCodeContentBean;
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }

        if (!(anObject instanceof ReturnCodeContentBean)) {
            return false;
        }

        ReturnCodeContentBean returnCodeContentBean = (ReturnCodeContentBean) anObject;

        if(!this.codeId.equals(returnCodeContentBean.codeId)) {
            return false;
        }
        if(!this.createTime.equals(returnCodeContentBean.createTime)) {
            return false;
        }
        if(!this.codeContent.equals(returnCodeContentBean.codeContent)) {
            return false;
        }
        if(!this.createUserName.equals(returnCodeContentBean.createUserName)) {
            return false;
        }
        if(!this.updateTime.equals(returnCodeContentBean.updateTime)) {
            return false;
        }
        if(!this.remark.equals(returnCodeContentBean.remark)) {
            return false;
        }
        if(!this.id.equals(returnCodeContentBean.id)) {
            return false;
        }
        if(!this.codeDescribe.equals(returnCodeContentBean.codeDescribe)) {
            return false;
        }

        return true;
    }

    public void add(ReturnCodeContentBean returnCodeContentBean) {
        super.add(returnCodeContentBean);
        this.codeId = returnCodeContentBean.getCodeId();
        this.createTime = returnCodeContentBean.getCreateTime();
        this.codeContent = returnCodeContentBean.getCodeContent();
        this.createUserName = returnCodeContentBean.getCreateUserName();
        this.updateTime = returnCodeContentBean.getUpdateTime();
        this.remark = returnCodeContentBean.getRemark();
        this.id = returnCodeContentBean.getId();
        this.codeDescribe = returnCodeContentBean.getCodeDescribe();

    }

    public Long getCodeId(){
        return codeId;
    }
    public void setCodeId(Long codeId){
        this.codeId=codeId;
    }

    public Long getCodeIdBegin(){
        return codeIdBegin;
    }
    public void setCodeIdBegin(Long codeIdBegin){
        this.codeIdBegin=codeIdBegin;
    }

    public Long getCodeIdEnd(){
        return codeIdEnd;
    }
    public void setCodeIdEnd(Long codeIdEnd){
        this.codeIdEnd=codeIdEnd;
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

    public String getCodeContent(){
        return codeContent;
    }
    public void setCodeContent(String codeContent){
        this.codeContent=codeContent;
    }

    public String getCreateUserName(){
        return createUserName;
    }
    public void setCreateUserName(String createUserName){
        this.createUserName=createUserName;
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

    public String getRemark(){
        return remark;
    }
    public void setRemark(String remark){
        this.remark=remark;
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

    public String getCodeDescribe(){
        return codeDescribe;
    }
    public void setCodeDescribe(String codeDescribe){
        this.codeDescribe=codeDescribe;
    }


    public void setReturnCodeContentBean(ReturnCodeContentBean returnCodeContentBean){
        this.returnCodeContentBean=returnCodeContentBean;
    }
    public ReturnCodeContentBean getReturnCodeContentBean(){
        return returnCodeContentBean;
    }

    public String toString(){
        StringBuffer strb = new StringBuffer();
        strb.append("{");
        if(codeId!=null){
            strb.append(",\"codeId\":\""+codeId+"\"");
            strb.append(",\"codeIdBegin\":\""+codeIdBegin+"\"");
            strb.append(",\"codeIdEnd\":\""+codeIdEnd+"\"");
        }
        if(createTime!=null){
            strb.append(",\"createTime\":\""+createTime+"\"");
            strb.append(",\"createTimeBegin\":\""+createTimeBegin+"\"");
            strb.append(",\"createTimeEnd\":\""+createTimeEnd+"\"");
        }
        if(codeContent!=null){
            strb.append(",\"codeContent\":\""+codeContent+"\"");
        }
        if(createUserName!=null){
            strb.append(",\"createUserName\":\""+createUserName+"\"");
        }
        if(updateTime!=null){
            strb.append(",\"updateTime\":\""+updateTime+"\"");
            strb.append(",\"updateTimeBegin\":\""+updateTimeBegin+"\"");
            strb.append(",\"updateTimeEnd\":\""+updateTimeEnd+"\"");
        }
        if(remark!=null){
            strb.append(",\"remark\":\""+remark+"\"");
        }
        if(id!=null){
            strb.append(",\"id\":\""+id+"\"");
            strb.append(",\"idBegin\":\""+idBegin+"\"");
            strb.append(",\"idEnd\":\""+idEnd+"\"");
        }
        if(codeDescribe!=null){
            strb.append(",\"codeDescribe\":\""+codeDescribe+"\"");
        }
        if(returnCodeContentBean!=null){
            strb.append(",\"returnCodeContentBean\":\""+returnCodeContentBean.toString()+"\"");
        }

        return strb.toString();
    }
}
