package com.lk.controller.login;

import com.alibaba.fastjson.JSON;
import com.lk.entity.ResponseBean;
import com.lk.entity.database.UserInfoBean;
import com.lk.entity.module.LoginBean;
import com.lk.service.login.LoginSvc;
import com.lk.tools.vericode.NumericVeriCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luokun
 * @date 2019/4/19 16:55
 */
@Controller
@RequestMapping
public class LoginCtrlr {
    private Logger log = LoggerFactory.getLogger(LoginCtrlr.class);

    @Resource
    private LoginSvc loginSvcImpl;
    //先判断session中的userId是否过期
    @RequestMapping("/")
    public String loginPage(HttpServletRequest request, Model model){
        //获得session内的userId,如已经存在则重定向到index.如未存在则前往login
        log.info("user login page begin");
        String strUserId = (String) request.getSession().getAttribute("userId");
        if (strUserId != null&&!"".equals(strUserId)) {
            log.info("user login page end sysUserId:"+strUserId);
            return "jsp/index";
        } else {
            log.info("user login page end");
            return "jsp/login";
        }
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request, LoginBean loginBean,Model model,RedirectAttributes redirectAttributes){
        log.info("user login end:LoginCtrlr:"+loginBean+";");
        ResponseBean<LoginBean> responseLoginBean = this.loginSvcImpl.login(request,loginBean);
        redirectAttributes.addFlashAttribute("responseLoginBean", JSON.toJSON(responseLoginBean));
        log.info("user login end:responseLoginBean:"+responseLoginBean);
        return "redirect:/";
    }


    @RequestMapping("/loginTime")
    public String loginTime(HttpServletRequest request,Model model,
                            RedirectAttributes redirectAttributes){
        log.info("user loginTime begin");
        ResponseBean<UserInfoBean> responseLoginBean =this.loginSvcImpl.loginTime();
        redirectAttributes.addFlashAttribute("responseLoginBean",JSON.toJSON(responseLoginBean));
        log.info("user loginTime end");
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public  String logout(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes){
        log.info("user logout begin");
        ResponseBean<UserInfoBean> responselogoutBean = new ResponseBean<UserInfoBean>();
        responselogoutBean= this.loginSvcImpl.logout(request);
        redirectAttributes.addFlashAttribute("responselogoutBean",JSON.toJSON(responselogoutBean));
        log.info("user logout end");
        return "redirect:/";

    }

    //生成验证码
    @RequestMapping("/veriCode")
    public void veriCode(HttpServletRequest request,HttpServletResponse response){
        log.info("user veriCode begin");
        try{
            NumericVeriCode.getNumericVeriCode(request,response);
        }catch (IOException e){
            log.error("get veri code error:", e);
        }
        log.info("user veriCode end");
    }

}
