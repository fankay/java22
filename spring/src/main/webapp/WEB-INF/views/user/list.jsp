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

        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>


        <a href="/user/new" class="btn btn-primary">添加</a>
        <table class="table">
            <tr>
                <th>姓名</th>
                <th>年龄</th>
            </tr>
            <c:forEach items="${accountList}" var="account">
            <tr>
                <td>${account.userName}</td>
                <td>${account.age}</td>
            </tr>
            </c:forEach>
        </table>


    </div>


</body>
</html>