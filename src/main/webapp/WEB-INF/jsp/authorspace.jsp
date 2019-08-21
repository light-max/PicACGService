<%--
  Created by IntelliJ IDEA.
  User: lifengqiang
  Date: 2019/8/17
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>空间</title>
    <link rel="stylesheet" type="text/css" href="/css/authorspace.css">
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/function.js"></script>
    <script src="/js/authorspace.js"></script>
</head>
<body style="background-color: #d2e9fb">
<div class="root">
    <div class="info">
        <img class="head-icon" id="head-icon"/>
        <div>
            <div class="nickname-sex">
                <span id="nickname"></span>
                <img class="sex-icon" id="sex-icon">
            </div>
            <span class="signature" id="signature"></span>
        </div>
    </div>
    <div class="content">
        <div class="content-text">
            <span class="text-1">TA的投稿</span><span class="number" id="number"></span>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=3)" width="99%" color=#ccc size=1>
        <div id="list" class="list">
        </div>
    </div>
</div>
</body>
</html>
