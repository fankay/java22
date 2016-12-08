<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<button id="btn">Click</button>
<div id="result"></div>

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script type="text/template" id="template">
    <div id="num-{{id}}">
        <h3>{{title}}</h3>
        <p>{{content}}</p>
    </div>
</script>
<script>
    $(function () {
        $("#btn").click(function () {
            var html = $("#template").html();
            html = html.replace("{{id}}",Math.random());
            html = html.replace("{{title}}","Hello," + new Date().getTime());
            html = html.replace("{{content}}","你好," + new Date().getTime());
            $(html).appendTo($("#result"));
        });
    });
</script>


</body>
</html>