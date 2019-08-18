<%--
  Created by IntelliJ IDEA.
  User: lifengqiang
  Date: 2019/8/16
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    body {
        margin: 0;
    }

    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #00a1d699;
        position: fixed;
        top: 0;
        width: 100%;
    }

    li {
        float: left;
    }

    li a {
        display: block;
        color: black;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        cursor: pointer;
    }

    li img {
        width: 48px;
        height: 48px;
        display: flex;
        border-radius: 50%;
        align-items: center;
        justify-content: center;
        overflow: hidden;
        margin-top: 2px;
        margin-right: 8px;
        cursor: pointer;
    }
</style>
<ul>
    <li style="margin-left: 20%"><a href="#home">主页</a></li>
    <li><a href="#news">最新发布</a></li>
    <li><a href="#contact">最多点赞</a></li>
    <li><a href="#about">最多浏览</a></li>
    <li style="float: right; margin-right: 20%;background-color: #fb7299;border-radius: 4px;">
        <a style="color: #fff;" href="#about">投稿</a>
    </li>
    <li style="float: right;"><img onclick="window.open('space?id='+window.sessionStorage.getItem('id'))" id="icon"/>
    </li>
    <li style="float: right;"><a onclick="window.open('sign_in', '登录')" id="login">登录</a></li>
</ul>
