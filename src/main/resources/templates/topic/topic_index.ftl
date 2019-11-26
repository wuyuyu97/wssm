<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Topic</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <!-- style -->
    <link rel="stylesheet" href="//static2.cnodejs.org/public/stylesheets/index.min.23a5b1ca.min.css" media="all">
</head>
<body>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <ul class="nav pull-right">
                <li>    <a href='/'>主页</a></li>
                <li> <a href='/login'>登录</a></li>
                <li><a href='/todo'>待办事项</a></li>
<#--                <li> <a href='/weibo/index'>微博</a></li>-->
                <li>    <a href='/topic'>板块总</a></li>
                <li>    <a href='/topic/board/1'>板块1</a></li>
                <li><a href='/topic/board/2'>板块2</a></li>
            </ul>
        </div>
    </div>
</div>
<br>
<!--<li>  {username} 的 HOME PAGE</li>-->
<form action="/topic/add" method="POST">
    <input type="id" name="boardId" value="${boardId}" hidden>
    <input type="text" name="title" placeholder="请输入 title">
    <br>
    <input type="text" name="content" placeholder="请输入 topic">
    <br>
    <button type="submit">添加</button>
</form>


<div id="main">
    <div id="sidebar">
        <div id="panel">
            <div class="header">
                <span class="col_fade">个人信息</span>
            </div>

            <div class="inner">
                <div class="user_card">
                    <div>
                        <!--                    <a class="user_avatar" href="/user/fumblegrowth">-->
                        <img src="${user.avatar}" title=${user.username}>
                        <!--                    </a>-->

                        <span class="user_name">用户名:${user.username}</span>

                        <div class="board clearfix">
                            <div class="floor">
                                <span class="big">积分: 0 </span>
                            </div>
                        </div>
                        <div class="space clearfix"></div>
                        <span class="signature">
        “
            这家伙很懒，什么个性签名都没有留下。
        ”
    </span>
                    </div>

                </div>
            </div>

        </div>
    </div>
</div>
<#list topics as t >
    <h3>
        <a href="/topic/detail/${t.id}">${t.id} : ${t.title}</a>
    </h3>
    <a href="/topic/commentedit?id=${t.id}">评论</a>

    <a href="/topic/edit?id=${t.id}">编辑</a>
    <a href="/topic/delete?id=${t.id}">删除</a>
    <br>
</#list>


</body>


</html>