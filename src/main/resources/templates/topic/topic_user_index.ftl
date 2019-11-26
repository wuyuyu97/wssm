<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <!-- style -->
    <link rel="stylesheet" href="//static2.cnodejs.org/public/stylesheets/index.min.23a5b1ca.min.css" media="all">
</head>
<body>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <ul class="nav pull-right">
                <li><a href='/login'>登录</a></li>
                <li><a href='/todo'>待办事项</a></li>
                <li>    <a href='/topic'>板块总</a></li>
                <li>    <a href='/topic/board/1'>板块1</a></li>
                <li><a href='/topic/board/2'>板块2</a></li>
<#--                <li><a href='/message'>留言板</a></li>-->
<#--                <li><a href='/weibo/index'>微博</a></li>-->
                <li><a href='/'>主页</a></li>

            </ul>
        </div>
    </div>
</div>
<br>

<!--<li>  {username} 的 HOME PAGE</li>-->

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
                        <img src="${user.avatar}" height="100px" width="100px" title=${user.username}>
                        <!--                    </a>-->
                        <br>
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
<#--                            <a href="/mail/index">邮件</a>-->
    </span>
                    </div>

                </div>
            </div>

        </div>
    </div>
</div>
    用户注册时间${user.createdTime}
<h3>最近创建的话题</h3>
<#list topics as t >

    ${user.username}:${t.title} &nbsp;&nbsp;&nbsp;&nbsp;时间:${t.updatedTime}
    <br>
</#list>
    <h3>最近参与的话题</h3>
<#list title as t1 >
    ${user.username}:
    ${t1.title}
    &nbsp;&nbsp;&nbsp;&nbsp;时间:${t1.updatedTime}
    <br>
<#--    ${u.comment_createdTime}-->
</#list>


</body>

</html>