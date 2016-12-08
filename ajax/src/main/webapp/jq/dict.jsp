<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<input type="text" id="word"> <button id="btn">翻译</button>
<div id="result"></div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
    $(function(){
        $("#btn").click(function () {
            $("#result").html("");

            var word = $("#word").val();
            var url = "http://fanyi.youdao.com/openapi.do?keyfrom=kaishengit&key=1587754017&type=data&doctype=jsonp&callback=?&version=1.1&q="+word;
            $.getJSON(url).done(function(data){
                var array = data.basic.explains;
                for(var i =0;i< array.length;i++) {
                    $("<p></p>").text(array[i]).appendTo($("#result"));
                }
            }).error(function(){
                alert("服务器异常");
            });

        });
    });
</script>
</body>
</html>