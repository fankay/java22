<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>通知中心</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<%@include file="../include/navbar.jsp"%>
<!--header-bar end-->
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-bell"></i> 通知中心</span>
        </div>
        <button id="markBtn" style="margin-left: 8px;" disabled class="btn btn-mini">标记为已读</button>
        <table class="table">
            <thead>
            <tr>

                <th width="30"><c:if test="${not empty notifyList}"><input type="checkbox" id="ckFather"></c:if></th>

                <th width="200">发布日期</th>
                <th>内容</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
            <c:when test="${not empty notifyList}">
                <c:forEach items="${notifyList}" var="notify">
                    <c:choose>
                        <c:when test="${notify.state == 1}">
                            <tr class = "" style="text-decoration: line-through">
                                <td></td>
                                <td>${notify.createtime}</td>
                                <td>${notify.content}</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td><input value="${notify.id}" type="checkbox" class="ckSon"></td>
                                <td>${notify.createtime}</td>
                                <td>${notify.content}</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>


            </c:when>
            <c:otherwise>
                <tr><td colspan="3"><p>暂时没有任何消息</p></td></tr>
            </c:otherwise>
            </c:choose>

            </tbody>
        </table>


    </div>
    <!--box end-->
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.3.min.js"></script>

<script>
    $(function () {
        $("#ckFather").click(function(){
            var sons = $(".ckSon");
            for(var i = 0;i<sons.length;i++){
                sons[i].checked=$(this)[0].checked;
            }

            if ($(this)[0].checked == true){
                $("#markBtn").removeAttr("disabled");
            }else{
                $("#markBtn").attr("disabled","disabled");
            }
        });

        $(".ckSon").click(function () {
            var sons = $(".ckSon");
            var num = 0;
            for(var i = 0;i<sons.length;i++){
                if (sons[i].checked){
                    num++;
                }
            }
            if (num == sons.length){
                $("#ckFather")[0].checked = true;
            }else{
                $("#ckFather")[0].checked = false;
            }

            if (num > 0){
                $("#markBtn").removeAttr("disabled");
            }else{
                $("#markBtn").attr("disabled","disabled");
            }

        });

        $("#markBtn").click(function(){
            var ids = [];
            var sons = $(".ckSon");
            for(var i=0 ; i<sons.length;i++){
                if(sons[i].checked == true){
                    ids.push(sons[i].value);
                }
            }
            alert(ids.join(","));
            $.post("/notifyRead",{"ids":ids.join(",")},function(json){
                if (json == "success"){
                    window.history.go(0);
                }
            });
        });

    });
</script>
</body>
</html>
