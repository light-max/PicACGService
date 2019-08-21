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

    .nav {
        /*list-style-type: none;*/
        margin: 0;
        padding: 0;
        /*overflow: hidden;*/
        background-color: #00a1d6;
        position: fixed;
        top: 0;
        width: 100%;
        text-align: center;
    }

    .nav-content {
        width: 760px;
        vertical-align: middle;
        margin: 0 auto;
    }

    .nav-item-left {
        float: left;
    }

    .nav-item-right {
        float: right;
    }

    .nav-item-a {
        display: block;
        color: black;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        cursor: pointer;
    }

    .nav-item-a-activity {
        background-color: #00000033;
        border-radius: 4px;
    }

    .nav-img {
        width: 48px;
        height: 48px;
        display: flex;
        border-radius: 50%;
        align-items: center;
        justify-content: center;
        overflow: hidden;
        margin-right: 8px;
        cursor: pointer;
        border: 1px solid #99a2aa;
    }

    .show {
        width: 200px;
        background: white;
        position: absolute;
        opacity: 0;
        display: block;
        font-size: 12px;
        transition: 0.3s;
        -webkit-transition: .5s;
        -moz-transition: .5s;
        border-radius: 4px;
        margin-left: -76px;
        border: 1px solid #99a2aa;
    }

    .nav-item-right:hover .show {
        color: #656e73;
        opacity: 1;
    }

    .nav-search {
        margin-top: 6px;
        width: 300px;
        height: 34px;
        padding: 6px 12px;
        font-size: 14px;
        line-height: 1.42857143;
        color: #555;
        background-color: #fff;
        background-image: none;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
</style>

<div class="nav">
    <div class="nav-content">
        <div class="nav-item-left" id="nav-home">
            <a class="nav-item-a" href="home">主页</a>
        </div>
        <div class="nav-item-left" id="nav-time">
            <a class="nav-item-a" href="leaderboard?type=time">最新发布</a>
        </div>
        <div class="nav-item-left" id="nav-star">
            <a class="nav-item-a" href="leaderboard?type=star">最多点赞</a>
        </div>
        <div class="nav-item-left" id="nav-watch">
            <a class="nav-item-a" href="leaderboard?type=watch">最多浏览</a>
        </div>
        <input type="text" class="nav-search" placeholder="输入搜索内容"
               onkeyup="if (event.keyCode===13){
                   window.sessionStorage.setItem('searchvalue',$(this).val());
                   window.open('searchview');
               } ">
        <div class="nav-item-right" style="background-color: #fb7299;border-radius: 4px;">
            <a class="nav-item-a" style="color: #fff;" onclick="openSubmission()">投稿</a>
        </div>
        <div class="nav-item-right" id="icon-div">
            <img class="nav-img" onclick="openUserInfo()" id="icon"/>
            <div class="show">
                <p style="cursor: pointer;" onclick="openUserInfo()">个人中心</p>
                <p style="cursor: pointer;"
                   onclick=" window.open('authorspace?id=' + window.sessionStorage.getItem('id'))">查看投稿</p>
                <p style="cursor: pointer;"
                   onclick="window.open('submission_m?id='+window.sessionStorage.getItem('id'),'投稿管理')">投稿管理</p>
                <p style="cursor: pointer;" onclick="signOut()">退出登录</p>
            </div>
        </div>
        <div class="nav-item-right">
            <a class="nav-item-a" onclick="window.open('sign_in', '登录')" id="login">登录</a>
        </div>
    </div>
</div>