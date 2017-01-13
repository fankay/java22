<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Blank Page</title>
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="sys_accounts"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-search"></i> 搜索</h3>
                </div>
                <div class="box-body">
                    <form class="form-inline">
                        <div class="form-group">
                            <input type="text" name="q_name" placeholder="姓名" class="form-control">
                        </div>
                        <div class="form-group">
                            <select name="q_role"  class="form-control">
                                <option value="">--角色--</option>
                                <c:forEach items="${roleList}" var="role">
                                    <option value="${role.id}">${role.viewName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-list"></i> 账户管理</h3>
                    <div class="box-tools pull-right">
                        <a href="/user/new" class="btn"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">
                            ${message}
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        </div>
                    </c:if>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>角色</th>
                            <th width="100">#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.items}" var="user">
                            <tr>
                                <td>${user.userName}</td>
                                <td>${user.roleNames}</td>
                                <td>
                                    <a href="/user/${user.id}/edit">编辑</a>
                                    <a href="/user/${user.id}/del">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="box-footer">
                        <ul style="margin:5px 0px" id="pagination" class="pagination pull-right"></ul>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/jquery.twbsPagination.min.js"></script>
<script>
    $(function () {
//分页插件的使用
        $("#pagination").twbsPagination({
            totalPages:${page.totalPage},
            visiblePages:5,
            href:"/user?q_name=&q_role=&p={{number}}",
            first:"首页",
            prev:"上一页",
            next:"下一页",
            last:"末页"
        });
    });
</script>
</body>
</html>
