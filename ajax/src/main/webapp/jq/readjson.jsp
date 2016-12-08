<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <button id="btn">load JSON</button>
    <script src="/static/js/jquery-1.11.3.min.js"></script>
    <script>
        $(function(){
            $("#btn").click(function(){

                $.getJSON("/data.json").done(function(data){
                    for(var i = 0;i < data.length;i++) {
                        var user = data[i];
                        alert(user.username + " -> " + user.address);
                    }
                }).error(function(){
                    alert("服务器异常");
                });

                /*$.get("/data.json").done(function(data){
                    for(var i = 0;i < data.length;i++) {
                        var user = data[i];
                        alert(user.username + " -> " + user.address);
                    }
                }).error(function(){
                    alert("服务器异常");
                });*/
            });
        });
    </script>
</body>
</html>