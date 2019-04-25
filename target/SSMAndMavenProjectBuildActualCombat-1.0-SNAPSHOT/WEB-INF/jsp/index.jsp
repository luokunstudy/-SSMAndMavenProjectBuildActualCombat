<%--
  Created by IntelliJ IDEA.
  User: LUOKUN
  Date: 2019/4/23
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Demo首页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="//res.layui.com/layui/dist/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
    <title>首页</title>
    <!-- 加载js文件-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/larrycms.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tools/md5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/index.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/login/login.js"></script>
</head>
<body>
<h1>您已登录成功！</h1>
<a id="logout">退出</a>
</body>
<script type="text/javascript">
    layui.use(
        [ 'jquery', 'larry', 'navtab', 'form', 'common' ],
        function() {
            var $ = layui.jquery,
                device = layui.device(),
                larry = layui.larry(),
                form = layui.form,
                common = layui.common;

            // 登出系统
            $('#logout').on('click', function() {
                logout();
            })

            function logout(){
                var url = '${pageContext.request.contextPath}/logout';
                common.logOut('退出登陆提示！', '你真的确定要退出系统吗？', url);
            }

        }
    )


</script>
</html>
