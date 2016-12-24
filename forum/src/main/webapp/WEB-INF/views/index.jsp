<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<%@ include file="include/navbar.jsp" %>
<div class="container">
    <div class="box">
        <div class="talk-item">
            <ul class="topic-type unstyled inline" style="margin-bottom:0px;">
                <li class="${empty param.nodeid?'active':''}"><a href="/home">全部</a></li>
                <c:forEach items="${nodeList}" var="node">
                    <li class="${node.id == param.nodeid?'active':'' }"><a href="/home?nodeid=${node.id}">${node.nodename}</a></li>
                </c:forEach>

            </ul>
        </div>

        <c:forEach items="${page.items}" var="topic">
        <div class="talk-item">
            <table class="talk-table">
                <tr>
                    <td width="50">
                        <img class="avatar" src="${topic.user.avatar}?imageView2/1/w/40/h/40" alt="">
                    </td>
                    <td width="80">
                        <a href="">${topic.user.username}</a>
                    </td>
                    <td width="auto">
                        <a href="/topicDetail?topicid=${topic.id}">${topic.title}</a>
                    </td>
                    <td width="50" align="center">
                        <span class="badge">${topic.replynum}</span>
                    </td>
                </tr>
            </table>
        </div>
        </c:forEach>

        <div class="pagination pagination-mini pagination-centered">
            <ul id="pagination" style="margin-bottom:20px;"></ul>
        </div>
    </div>
    <!--box end-->
</div>
<!--container end-->
<div class="footer">
    <div class="container">
        Copyright © 2016 kaishengit
    </div>
</div>

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>

<script>
    $(function(){
        $("#pagination").twbsPagination({
            totalPages:${page.totalPage},
            visiblePages:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href: '?p={{number}}&node=${param.node}'
        });

    });
</script>
</body>
</html>
