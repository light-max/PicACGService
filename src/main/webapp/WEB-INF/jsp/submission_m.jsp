<%--
  Created by IntelliJ IDEA.
  User: lifengqiang
  Date: 2019/8/19
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>投稿管理</title>
    <link rel="stylesheet" type="text/css" href="/css/submission_m.css">
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/function.js"></script>
    <script src="/js/submission_m.js"></script>
</head>
<body style="background-color: #d2e9fb">
<div class="root">
    <div style="text-align: center;"><h2>我的投稿</h2></div>
    <p style="font-size: 20px;">总共有<span id="number"></span>个投稿</p>
    <hr style="filter: alpha(opacity=100,finishopacity=0,style=3);width:100% ;color:#ccc; size:1px">
    <div class="list" id="list">
    </div>
</div>
</body>
</html>
