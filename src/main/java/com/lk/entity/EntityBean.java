package com.lk.entity;

/**
 * @author luokun
 * @date 2019/4/19 16:39
 */
public class EntityBean implements Cloneable {

    private Integer pageSize;
    private Integer pageNo;
    private Integer pageCount;
    private String orderByParam;
    private String orderByAccording;
    private String field;

    public EntityBean clone() throws CloneNotSupportedException{
        EntityBean entityBean = (EntityBean) super.clone();
        return entityBean;
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }

        if (!(anObject instanceof EntityBean)) {
            return false;
        }

        EntityBean entityBean = (EntityBean) anObject;

        if(!this.pageSize.equals(entityBean.pageSize)) {
            return false;
        }
        if(!this.pageNo.equals(entityBean.pageNo)) {
            return false;
        }
        if(!this.pageCount.equals(entityBean.pageCount)) {
            return false;
        }
        if(!this.orderByParam.equals(entityBean.orderByParam)) {
            return false;
        }
        if(!this.orderByAccording.equals(entityBean.orderByAccording)) {
            return false;
        }
        if(!this.field.equals(entityBean.field)) {
            return false;
        }

        return true;
    }

    public void add(EntityBean entityBean) {
        this.pageSize = entityBean.getPageSize();
        this.pageNo = entityBean.getPageNo();
        this.pageCount = entityBean.getPageCount();
        this.orderByParam = entityBean.getOrderByParam();
        this.orderByAccording = entityBean.getOrderByAccording();
        this.field = entityBean.getField();
    }

    public Integer getPageSize(){
        return pageSize;
    }
    public void setPageSize(Integer pageSize){
        this.pageSize=pageSize;
    }

    public Integer getPageNo(){
        return pageNo;
    }
    public void setPageNo(Integer pageNo){
        this.pageNo=pageNo;
    }

    public Integer getPageCount(){
        return pageCount;
    }
    public void setPageCount(Integer pageCount){
        this.pageCount=pageCount;
    }

    public String getOrderByParam(){
        return orderByParam;
    }
    public void setOrderByParam(String orderByParam){
        this.orderByParam=orderByParam;
    }

    public String getOrderByAccording(){
        return orderByAccording;
    }
    public void setOrderByAccording(String orderByAccording){
        this.orderByAccording=orderByAccording;
    }

    public String getField(){
        return field;
    }
    public void setField(String field){
        this.field=field;
    }

    public String toString(){
        StringBuffer strb = new StringBuffer();
        if(pageSize!=null){
            strb.append(",\"pageSize\":\""+pageSize+"\"");
        }
        if(pageNo!=null){
            strb.append(",\"pageNo\":\""+pageNo+"\"");
        }
        if(orderByParam!=null){
            strb.append(",\"orderByParam\":\""+orderByParam+"\"");
        }
        if(orderByAccording!=null){
            strb.append(",\"orderByAccording\":\""+orderByAccording+"\"");
        }
        if(field!=null){
            strb.append(",\"field\":\""+field+"\"");
        }
        strb.append("}");

        return strb.toString();
    }
}
