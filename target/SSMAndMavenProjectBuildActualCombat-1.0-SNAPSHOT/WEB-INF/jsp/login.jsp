<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Demo系统</title>
    <meta name="keywords" content="启信小程序系统" />
    <meta name="description" content="LarryCMS Version:1.09" />
    <meta name="Author" content="larry" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="Shortcut Icon" href="/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login/login.css" media="all">
</head>
<body style="background-image: url(${pageContext.request.contextPath}/images/tools/1.jpg); background-size:cover; background-position:center;">
<form id="loginForm" name="loginForm" class="layui-form" method="post">
    <div class="layui-layout layui-layout-login">
        <h1>
            <strong>Demo系统</strong>
            <em>&nbsp;</em>
        </h1>
        <div class="layui-user-icon larry-login">
            <input type="text" placeholder="用户名" class="login_txtbx" name="loginName" id="loginName" lay-verify="loginName" autocomplete="off" maxlength="16"/>
        </div>
        <div class="layui-pwd-icon larry-login">
            <input type="password" placeholder="密码" class="login_txtbx" name="passwordVeri" id="passwordVeri" lay-verify="passwordVeri" autocomplete="off" maxlength="16"/>
        </div>
        <div class="layui-val-icon larry-login">
            <div class="layui-code-box">
                <input type="text" placeholder="验证码" class="login_txtbx" id="verificationCode" name="verificationCode" lay-verify="verificationCode" maxlength="4">
                <button class="resetVeriCode" data-type="resetVeriCode">
                    <img src="${pageContext.request.contextPath}/veriCode" alt="" class="verifyImg" id="verifyImg" name="verifyImg">
                </button>
            </div>
        </div>
        <div class="layui-submit larry-login">
            <button class="submit_btn" lay-submit lay-filter="login">登录</button>
        </div>
        <div class="layui-login-text">
            <p>© XXXXXXX有限公司 &nbsp; 版权所有</p>
            <p></p>
        </div>
    </div>
    <div id="div1"></div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tools/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tools/jparticle.jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tools/md5.js"></script>
</body>
<script type="text/javascript">
    layui.use(['jquery','layedit','layer','form'],function(){
        'use strict';
        var $ = layui.jquery
            ,layer=layui.layer
            ,form =layui.form;
        //跳转请求消息提示开始
        //成功提示消息
        //失败提示信息
        if(
            '${responseLoginBean.status}'==S_N_SYS_LOGIN_FAILED
            ||'${responseLoginBean.status}'==S_N_SYS_LOGIN_ERROR
            ||'${responseLoginBean.status}'==S_N_SYS_LOGIN_LOGIN_NAME_ERROR
            ||'${responseLoginBean.status}'==S_N_SYS_LOGIN_PASSWORD_ERROR
            ||'${responseLoginBean.status}'==S_N_SYS_LOGIN_VERFICATION_CODE_ERROR
            ||'${responseLoginBean.status}'==S_N_SYS_LOGIN_USER_STATUS_CLOSE
            ||'${responseLoginBean.status}'==S_N_SYS_LOGIN_USER_LOCK
        ){
            //提示获取列表失败
            layer.msg('${responseLoginBean.content}', {
                icon: 5,
                shift: 1
            })
        }
        //跳转请求消息提示结束

        var ran=Math.random();
        var imgSrc="${pageContext.request.contextPath}/veriCode?id="+ran;

        //验证表单
        form.verify({
            loginName: function(loginName) {
                if(loginName==null||typeof(loginName)=='undefined'||loginName=='') {
                    return '请输入用户名！';
                }
                if(loginName.length>16){
                    return '用户名或密码错误！';
                }
            },
            passwordVeri: function(passwordVeri) {
                if(passwordVeri==null||typeof(passwordVeri)=='undefined'||passwordVeri=='') {
                    return '请输入密码！';
                }
                if(passwordVeri.length>16){
                    return '用户名或密码错误！';
                }
            },
            verificationCode: function(verificationCode) {
                if(verificationCode==null||typeof(verificationCode)=='undefined'||verificationCode=='') {
                    $("#verifyImg").attr('src',imgSrc);
                    return '请输入验证码！';
                }
                if(verificationCode.length!=4){
                    $("#verifyImg").attr('src',imgSrc);
                    return '验证码输入错误！';
                }
            }
        });
        //监听表单
        form.on('submit(login)', function(data) {
            var index = layer.load(1, {
                shade: [0.1,'#fff'] //0.1透明度的白色背景
            });
            $("#div1").html("<input type=\"hidden\" id=\"password\" name=\"password\" value=\""+hex_md5(data.field.passwordVeri)+"\">");
            $("#loginForm").attr("action","login").submit();
            return false;
        });
        //触发事件
        var active = {
            resetVeriCode: function(){
                //重置数据
                $("#verifyImg").attr('src',imgSrc);
                return false;
            }
        };
        $('.resetVeriCode').on('click', function(){
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
            //阻止触发事件提交表单
            return false;
        });
        $(window).on('resize',function(){
            var w = $(window).width();
            var h = $(window).height();
            $('.larry-canvas').width(w).height(h);
        }).resize();
        $(function(){
            $("#canvas").jParticle({
                background: "#141414",
                color: "#E5E5E5"
            });
        });
        this.enterEsc = function(event){
            if(event.keyCode === 13){
                $(".submit_btn").click();
                return false; //阻止系统默认回车事件
            }
        };
        $(document).on('keydown', this.enterEsc);	//监听键盘事件，关闭层
    });
</script>
</html>