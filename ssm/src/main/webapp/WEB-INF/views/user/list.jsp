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
        <h3>SSM CRUD Demo</h3>

        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>

        <a href="/user/new" class="btn btn-primary">新增</a>
        <table class="table">
            <thead>
                <tr>
                    <th>姓名</th>
                    <th>#</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>${user.userName}</td>
                        <td>
                            <a href="/user/${user.id}/edit">编辑</a>
                            <a href="/user/${user.id}/del">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>