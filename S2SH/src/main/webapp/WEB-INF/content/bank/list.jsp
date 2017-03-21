<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/repo/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <a href="/bank/new">添加</a>
        <form action="/bank/list" method="get" class="form-inline">
            <input type="text" name="q_like_s_username"> <button class="btn btn-default">搜索</button>
        </form>
        <table class="table">
            <thead>
                <tr>
                    <th>账号</th>
                    <th>金额</th>
                    <th>状态</th>
                    <th>#</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="bankList" var="bank">
                    <tr>
                        <td><s:property value="#bank.username"/> </td>
                        <td>${bank.money}</td>
                        <td>${bank.enable}</td>
                        <td>
                            <a href="/bank/edit?id=${bank.id}">修改</a>
                            <a href="/bank/del?id=${bank.id}">删除</a>
                        </td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </div>
</body>
</html>