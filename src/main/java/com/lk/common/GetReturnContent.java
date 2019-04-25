package com.lk.common;

import com.lk.dao.mybatis.dao.IReturnCodeContentRedis;
import com.lk.entity.database.ReturnCodeContentBean;
import com.lk.tools.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author luokun
 * @date 2019/4/22 9:44
 */
@Service("/getReturnContent")
public class GetReturnContent {
    private Logger log = LoggerFactory.getLogger(GetReturnContent.class);

    @Resource
    private IReturnCodeContentRedis returnCodeContentRedisImpl;

    public String getContent(Integer lReturnCodeId){
        ReturnCodeContentBean returnCodeContentBean;
        String strReturnContent ="出现错误！";
        try{
            returnCodeContentBean =
                    this.returnCodeContentRedisImpl.selectCodeContentByCodeId(lReturnCodeId);
            if (returnCodeContentBean==null){
                log.warn("get prompt message returnCodeContentBean is null");
                return strReturnContent;
            }
            if (!StringUtils.isValue(returnCodeContentBean.getCodeContent())){
                log.info("get prompt message returnCodeContentBean.getCodeContent() is null"
                        + ",lReturnCodeId:"+lReturnCodeId);
                return strReturnContent;
            }
            return returnCodeContentBean.getCodeContent();
        }catch (Exception e){
            log.error("get prompt message error");
            return strReturnContent;
        }
    }

}
