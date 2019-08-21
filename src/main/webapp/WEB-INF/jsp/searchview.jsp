<%--
  Created by IntelliJ IDEA.
  User: lifengqiang
  Date: 2019/8/21
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索</title>
    <link rel="stylesheet" type="text/css" href="/css/searchview.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/function.js"></script>
    <script src="/js/searchview.js"></script>
</head>
<body style="background-color: #d2e9fb">
<div class="root">
    <input type="text" class="search" id="value" placeholder="请输入搜索内容" onkeyup="if (event.keyCode===13){
        search($(this).val())
    } ">
    <ul class="nav">
        <span class="nav-item" id="title" onclick="location.replace('searchview?view=title')">标题</span>
        <span class="nav-item" id="keyword" onclick="location.replace('searchview?view=keyword')">关键字</span>
        <span class="nav-item" id="user" onclick="location.replace('searchview?view=user')">用户名</span>
    </ul>
    <hr style="filter: alpha(opacity=100,finishopacity=0,style=3);width:100% ;color:#ccc; size:1px">
    <div class="list" id="list">
    </div>
</div>
</body>
</html>
