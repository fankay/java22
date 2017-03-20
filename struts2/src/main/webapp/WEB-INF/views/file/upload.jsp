<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<form action="/file/upload" method="post" enctype="multipart/form-data">
    <input type="text" name="uName">
    <input type="file" name="doc">
    <input type="file" name="doc">
    <button>Upload</button>
</form>
<a href="/file/download">点此下载</a>
</body>
</html>