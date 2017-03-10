<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <%@include file="../../include/css.jsp"%>
    <title>我的运行流程</title>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper" id="app">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="process_runninglist"/>
    </jsp:include>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box" id="running">
                <div class="box-header">
                    <h3 class="box-title">我的运行流程</h3>
                </div>
                <div class="box-body">
                    <table width="100%" class="table table-bordered table-hover table-condensed">
                        <thead>
                        <tr>
                            <th>流程实例ID</th>
                            <th>所属流程</th>
                            <th>启动时间</th>
                            <th>流程启动人</th>
                            <th>当前节点</th>
                            <th>办理人</th>
                            <th>分配时间</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${processList}" var="process">
                            <tr>
                                <td><a href="">${process.processInstanceId}</a></td>
                                <td>${process.processDefinitionName}</td>
                                <td>${process.applyTime}</td>
                                <td>${process.userName}</td>
                                <td>${process.task.name}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty process.task.assignee}">
                                            ${process.task.assignee}

                                        </c:when>
                                        <c:otherwise>
                                            未签收
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${process.task.createTime}</td>
                            </tr>

                        </c:forEach>



                        </tbody>
                    </table>
                </div>
            </div>



        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>
<%@include file="../../include/js.jsp"%>
</body>

</html>