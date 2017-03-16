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
                <c:forEach items="${movieList}" var="movie">
                    <tr>
                        <td><a href="/movie/${movie.id}">${movie.title}</a></td>
                        <td>${movie.daoyan}</td>
                        <td>${movie.rate}</td>
                        <td>${movie.releaseyear}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>