<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lifengqiang
  Date: 2019/8/21
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title id="title">Title</title>
    <link rel="stylesheet" type="text/css" href="/css/home.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css"/>
    <script src="/js/jquery/jquery.js"></script>
    <script src="/js/leaderboard.js"></script>
    <script src="/js/function.js"></script>
</head>
<body style="background-color: #d2e9fb">
<%@include file="template/navigation.jsp" %>
<div style="height: 50px;"></div>
<div id="list" class="list "></div>
<%@include file="template/end.jsp" %>
</body>
</html>
