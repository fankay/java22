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
        <h3>${movie.title}</h3>
        <hr>
        ${movie.jianjie}
        <hr>
        ${movie.daoyan} | ${movie.rate} | ${movie.sendtime} | ${movie.releaseyear}
        <hr>
        <c:forEach items="${movie.categoryList}" var="cate">
            ${cate.name} |
        </c:forEach>
    </div>
</body>
</html>