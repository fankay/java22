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
        <form method="post">
            <input type="hidden" name="id" value="${user.id}">
            <div class="form-group">
                <label>账号</label>
                <input type="text" name="userName" value="${user.userName}" class="form-control">
            </div>
            <div class="form-group">
                <label>密码(如果不修改请留空)</label>
                <input type="password" name="password" value="" class="form-control">
            </div>
            <div class="form-group">
                <button class="btn btn-success">保存</button>
            </div>
        </form>
    </div>
</body>
</html>