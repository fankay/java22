<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <form action="" class="form-inline">
            <input type="text" name="q_like_s_title_or_daoyan" value="${q_like_s_title_or_daoyan}" class="form-control" placeholder="影片名称或导演">
            <input type="text" name="q_eq_s_releaseyear" value="${q_eq_s_releaseyear}" class="form-control" placeholder="上映时间">
            <input type="text" name="q_eq_f_rate" value="${q_eq_f_rate}" class="form-control" placeholder="评分">
            <button class="btn btn-default">搜索</button>
        </form>
        <table class="table">
            <thead>
                <tr>
                    <th>名称</th>
                    <th>导演</th>
                    <th>评分</th>
                    <th>上映时间</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${page.items}" var="movie">
                    <tr>
                        <td><a href="/movie/${movie.id}">${movie.title}</a></td>
                        <td>${movie.daoyan}</td>
                        <td>${movie.rate}</td>
                        <td>${movie.releaseyear}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <ul class="pagination pull-right" id="page"></ul>
    </div>
    <script src="/static/js/jquery-1.11.3.min.js"></script>
    <script src="/static/js/jquery.twbsPagination.min.js"></script>
    <script>
        $(function () {
            $("#page").twbsPagination({
                totalPages:${page.totalPages},
                visiblePages:5,
                first:'首页',
                prev:'上一页',
                next:'下一页',
                last:'末页',
                href:'?p={{number}}&q_like_s_title_or_daoyan=${q_like_s_title_or_daoyan}&q_eq_s_releaseyear=${q_eq_s_releaseyear}&q_eq_f_rate=${q_eq_f_rate}'
            });
        });
    </script>
</body>
</html>