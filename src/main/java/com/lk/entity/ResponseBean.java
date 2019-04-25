package com.lk.entity;

/**
 * @author luokun
 * @date 2019/4/19 17:32
 */
public class ResponseBean<T> {

    private Integer status;
    private T obj;
    private String content;

    @SuppressWarnings("unchecked")
    public ResponseBean<T> clone() throws CloneNotSupportedException{
        ResponseBean<T> responseBean = (ResponseBean<T>) super.clone();
        return responseBean;
    }

    @SuppressWarnings("unchecked")
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }

        if (!(anObject instanceof ResponseBean)) {
            return false;
        }

        ResponseBean<T> responseBean = (ResponseBean<T>) anObject;

        if(!this.status.equals(responseBean.status)) {
            return false;
        }
        if(!this.obj.equals(responseBean.obj)) {
            return false;
        }
        if(!this.content.equals(responseBean.content)) {
            return false;
        }

        return true;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getObj() {
        return obj;
    }
    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String toString(){
        StringBuffer strb = new StringBuffer();
        strb.append("\"status\":\""+status+"\"");
        strb.append(",\"obj\":\""+obj+"\"");
        strb.append(",\"content\":\""+content+"\"");

        return strb.toString();
    }
}
