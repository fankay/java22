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
            <div class="form-group">
                <label>账号</label>
                <input type="text" name="userName" class="form-control">
            </div>
            <div class="form-group">
                <label>密码(默认000000)</label>
                <input type="password" name="password" value="000000" class="form-control">
            </div>
            <div class="form-group">
                <button class="btn btn-success">保存</button>
            </div>
        </form>
    </div>
</body>
</html>