<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>

<input type="text" id="username">
<span id="loding" style="display: none"><img src="/static/img/loding.gif" alt=""></span>
<span id="text"></span>

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
    $(function () {
        $(document).ajaxSend(function(){
            $("#loding").show();
        });
        $(document).ajaxComplete(function(){
            $("#loding").hide();
        });

        $("#username").blur(function(){
            var name = $(this).val();

            $.get("/ajax",{"name":"ajax"},function(data){
                alert(data);
            });

            /*$.post("/ajaxs",{"name":name,"age":23})
                .done(function(data){
                    $("#text").text(data);
                })
                .error(function(){
                    alert("服务器端错误");
                });*/



            /*$.ajax({
                url : "/ajax",
                type : "post",
                data : {"name":name,"age":23},
                timeout : 15000,
                beforeSend : function(){
                    //请求发送之前做的函数
                    $("#loding").show();
                    $("#text").text("");
                },
                success : function(data){
                    $("#text").text(data);
                },
                error : function(){
                    alert("服务器异常");
                },
                complete : function(){
                    //无论success还是error都会执行
                    $("#loding").hide();
                }
            });*/


        });
    });
</script>
</body>
</html>