<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h1>New User</h1>
    <form action="/user/save" method="post">
        <input type="text" name="user.userName">
        <input type="text" name="user.address">
        <button>Save</button>
    </form>
    <ul>
        <c:forEach items="${names}" var="name">
            <li>${name}</li>
        </c:forEach>
    </ul>
</body>
</html>