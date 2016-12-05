<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>

<form action="" enctype="application/x-www-form-urlencoded"></form>

<input type="text" id="name">
<button id="btn">sendRequest</button>
<div id="result"></div>


<script>
    (function(){

        //创建XMLHttpRequest对象
        function createXmlHttp() {
            var xmlHttp = null;
            if(window.ActiveXObject) {
                //IE
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } else {
                xmlHttp = new XMLHttpRequest();
            }
            return xmlHttp;
        }

        document.querySelector("#btn").onclick = function(){
            var name = document.querySelector("#name").value;
            //sendGet(name);
            sendPost(name);
        };

        function sendGet(name) {
            //1. 获取Ajax引擎
            var xmlHttp = createXmlHttp();
            //2. 指定请求方式(GET|POST)和请求地址
            xmlHttp.open("get","/ajax?name="+name);
            //3. 发出请求
            xmlHttp.send();
        }

        function sendPost(name) {
            //1. 获取Ajax引擎
            var xmlHttp = createXmlHttp();
            //2. 指定请求方式和地址
            xmlHttp.open("post","/ajax");
            xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            //3. 配置回调函数
            xmlHttp.onreadystatechange = function(){
                var state = xmlHttp.readyState;
                if(state == 4) {
                    var httpState = xmlHttp.status; //获取HTTP状态码
                    if(httpState == 200) {
                        //获取服务端返回的字符串
                        var result = xmlHttp.responseText;

                        var div = document.querySelector("#result");
                        if(result == "能用") {
                            div.innerText = "可以使用该账号";
                        } else {
                            div.innerText = "该账号已被占用";
                        }
                    } else {
                        alert("服务器错误:" + httpState);
                    }
                }
            };
            //4. 发出请求
            xmlHttp.send("name="+name+"&age=23");
        }

    })();
</script>

</body>
</html>