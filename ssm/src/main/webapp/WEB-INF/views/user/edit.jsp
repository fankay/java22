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
    <%@include file="../include/sider.jsp"%>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">账户管理</h3>
                    <div class="box-tools pull-right">
                        <a href="/user/new" class="btn"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post">
                        <input type="hidden" name="id" value="${user.id}">
                        <div class="form-group">
                            <label>账号</label>
                            <input type="text" name="userName" value="${user.userName}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>密码(如果不修改请留空)</label>
                            <input type="password" name="password" value="" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>角色</label>
                            <div>
                                <c:forEach items="${roleList}" var="role">
                                    <c:set var="flag" value="false" scope="page"/>
                                    <c:forEach items="${user.roleList}" var="userRole">
                                        <c:if test="${role.id == userRole.id}">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" checked name="roleIds" value="${role.id}"> ${role.viewName}
                                            </label>
                                            <c:set var="flag" value="true"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${not flag}">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" name="roleIds" value="${role.id}"> ${role.viewName}
                                        </label>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-success">保存</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../include/js.jsp"%>
</body>
</html>
