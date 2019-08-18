<%--
  Created by IntelliJ IDEA.
  User: lifengqiang
  Date: 2019/8/16
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <script src="js/jquery/jquery.js"></script>
    <script src="js/login.js"></script>
</head>
<body onload="get_verify()">
<div class="col-lg-offset-4 col-lg-4" style="padding-bottom: 100px">
    <div style="text-align: center;"><h2>注册</h2></div>
    <br><br>
    <span style="color:red" id="message"></span>
    <form>
        <input type="text" class="form-control" placeholder="Username" name="name" id="name"><br>
        <input type="password" class="form-control" placeholder="Password" name="pass" id="password"><br>
        <input type="password" class="form-control" placeholder="confirmPassword" id="confirmPassword"><br>
        <div style="display: flex; width: 100%;">
            <input type="text" class="form-control" style="width: 100%;margin-right: 10px" placeholder="请输入验证码"
                   name="verify" id="verify">
            <span id="verify_text" class="form-control-static" style="color: #ff0000;"></span>
            <input class="btn btn-primary" style="width: 120px;margin-left: 10px" value="换一个" onclick="get_verify()">
        </div>
        <br>
        <input class="btn btn-primary" value="注册" onclick="sign_up()">
        <a href="sign_in" style="float: right">返回</a>
    </form>
</div>
</body>
</html>
