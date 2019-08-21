<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lifengqiang
  Date: 2019/8/19
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>投稿</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/submission.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery.js"></script>
    <script src="<c:url value="/js/submission.js"/>"></script>
    <script src="/js/function.js"></script>
</head>
<body style="background-color: #d2e9fb">
<div class="root">
    <div style="text-align: center;"><h2>图片投稿</h2></div>
    <hr style="filter: alpha(opacity=100,finishopacity=0,style=3)" width="100%" color=#ccc size=1>
    <p style="color: #99a2aa">图片上传 <span id="number">0</span>/24</p>
    <div class="list" id="list">
    </div>
    <input style="display: none" type="file" accept="image/jpg,image/jpeg,image/png" name="file" onchange="addImage(this)" id="fileselect">
    <input type="button" value="添加文件" class="btn btn-primary" onclick="$('#fileselect').click()">
    <hr style="filter: alpha(opacity=100,finishopacity=0,style=3)" width="100%" color=#ccc size=1>
    <p>标题</p>
    <input type="text" class="form-control" id="title"><br>
    <p>搜索关键字</p>
    <input type="text" class="form-control" id="keyword"><br>
    <p>简介</p>
    <input type="text" class="form-control" id="Introduction"><br>
    <p id="message"></p>
    <input style="width: 100px;" type="button" class="btn-primary btn" value="提交" onclick="upload()" id="uploadbt">
</div>
</body>
</html>
