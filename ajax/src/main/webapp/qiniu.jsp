<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<form action="http://up-z1.qiniu.com/" method="post" enctype="multipart/form-data">
    <input type="hidden" name="token" value="${token}">
    <input type="hidden" name="x:userid" value="1001">
    <input type="file" name="file">
    <button>上传</button>
</form>
<c:if test="${not empty fileName}">
    <img src="http://ohwnpkfcx.bkt.clouddn.com/${fileName}" alt="">
</c:if>
</body>
</html>