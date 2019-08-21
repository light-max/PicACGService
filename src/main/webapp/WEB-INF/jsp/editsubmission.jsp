<%--
  Created by IntelliJ IDEA.
  User: lifengqiang
  Date: 2019/8/19
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑稿件</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/submission.css">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.js"></script>
    <script src="/js/editsubmission.js"></script>
    <script src="/js/function.js"></script>
</head>
<body style="background-color: #d2e9fb">
<div class="root">
    <div style="text-align: center;"><h2>编辑稿件</h2></div>
    <p>不能更换图片内容</p>
    <hr style="filter: alpha(opacity=100,finishopacity=0,style=3)" width="100%" color=#ccc size=1>
    <p>标题</p>
    <input type="text" class="form-control" id="title"><br>
    <p>搜索关键字</p>
    <input type="text" class="form-control" id="keyword"><br>
    <p>简介</p>
    <input type="text" class="form-control" id="introduction"><br>
    <p id="message"></p>
    <input style="width: 100px;" type="button" class="btn-primary btn" value="提交" onclick="post()" id="uploadbt">
</div>
</body>
</html>
