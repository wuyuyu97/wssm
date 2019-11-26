<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>${topic.title}</title>
</head>
<body>

标题:
<h1>${topic.title}</h1>
作者:
    <a href="/topic/user_index/${user.id}">${user.username} </a>
<br>
内容:
<p>${topic.content}</p>

<div>
    <h3>评论</h3>
    <#list topic.commentList as c>
        <div>
            ${c.id} ${c.user.username} : ${c.content}
        </div>
    </#list>
</div>



</body>
</html>
