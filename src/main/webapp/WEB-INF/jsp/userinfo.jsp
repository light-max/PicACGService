<%--
  Created by IntelliJ IDEA.
  User: lifengqiang
  Date: 2019/8/18
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人空间</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/css/userinfo.css">
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/function.js"></script>
    <script src="/js/userinfo.js"></script>
</head>
<body style="background-color: #d2e9fb">
<div class="root">
    <div class="head-div">
        <img id="head-icon" class="head-icon" onclick="uploadHeader()">
    </div>
    <div class="info">
        <span class="text-primary">昵称</span><br><br>
        <input type="text" class="form-control" id="nickname"><br>
        <span class="text-primary">个人简介</span><br><br>
        <input type="text" class="form-control" id="word"><br>
        <span class="text-primary">性别</span><br>
        <div style="margin-top: 10px">
            <input name="sex" type="radio" style="vertical-align: middle" id="sex0"/>
            <label for="sex0"><span class="text-primary" style="margin:0 20px 0 4px">隐藏</span></label>
            <input name="sex" type="radio" style="vertical-align: middle" id="sex1"/>
            <label for="sex1"><span class="text-primary" style="margin:0 20px 0 4px">男</span></label>
            <input name="sex" type="radio" style="vertical-align: middle" id="sex2"/>
            <label for="sex2"><span class="text-primary" style="padding:0 20px 0 4px">女</span></label>
        </div>
        <br>
        <input type="button" class="btn btn-primary" value="保存" onclick="updateInfo()">
        <span style="color: green;display: none" id="message1">保存成功</span>
    </div>
    <div class="info">
        <span class="text-primary">修改登录名</span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span style="color: red;" id="message2"></span><br><br>
        <input type="text" class="form-control" id="username"><br>
        <input type="button" class="btn btn-primary" value="保存" onclick="updateUserName()">
    </div>
    <div class="info">
        <span class="text-primary">修改密码</span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span style="color: red;" id="message3"></span><br><br>
        <input type="password" class="form-control" placeholder="原来的密码" id="pass1"><br>
        <input type="password" class="form-control" placeholder="新密码" id="pass2"><br>
        <input type="password" class="form-control" placeholder="确认密码" id="pass3"><br>
        <input type="button" class="btn btn-primary" value="保存" onclick="updatePassword()">
    </div>
</div>
</body>
</html>
