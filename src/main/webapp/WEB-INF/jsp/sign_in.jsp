<%--
  Created by IntelliJ IDEA.
  User: lifengqiang
  Date: 2019/8/16
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="/css/bootstrap.css"/>
    <script src="/js/login.js"></script>
    <script src="/js/jquery/jquery.js"></script>
</head>
<body>
<div class="col-lg-offset-4 col-lg-4" style="padding-bottom: 100px">
    <div style="text-align: center;"><h2>登入</h2></div>
    <br><br>
    <span style="color: red; " id="message"></span>
    <form>
        <input type="text" class="form-control" placeholder="Username" name="name" id="name"><br>
        <input type="password" class="form-control" placeholder="Password" name="password" id="password"><br>
        <input class="btn btn-primary" value="登入" onclick="sign_in()">
        <a href="sign_up" style="float: right">注册</a>
    </form>
</div>
</body>
</html>
