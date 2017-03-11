<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <%@include file="../../include/css.jsp"%>
    <title>流程信息--${process.historicProcessInstance.processInstanceId}</title>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<%@include file="../../include/header.jsp"%>
<jsp:include page="../../include/sider.jsp">
    <jsp:param name="menu" value="process_historylist"/>
</jsp:include>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" style=" min-height: 761px;">
    <!-- Main content -->
    <section class="content">

        <div class="box">
            <div class="box-header">
                <h3 class="box-title">已归档流程实例信息<a class="btn" href="" onclick="history.go(-1);">返回列表</a></h3>
            </div>
            <div class="box-body">
                <legend>流程综合信息-【请假流程】-${process.historicProcessInstance.processInstanceId}</legend>
                <table class="table table-bordered table-hover table-condensed">
                    <tbody><tr>
                        <th width="100">流程ID</th>
                        <td>${process.historicProcessInstance.processInstanceId}</td>
                        <th width="100">流程名称</th>
                        <td>${process.processDefinition.name}</td>
                        <th width="100">业务KEY</th>
                        <td>${process.historicProcessInstance.businessKey}</td>
                    </tr>
                    <tr>

                        <th width="100">流程启动时间</th>
                        <td><fmt:formatDate value="${process.historicProcessInstance.startTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                        <th width="100">流程结束时间</th>
                        <td><fmt:formatDate value="${process.historicProcessInstance.endTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                        <th width="100">流程状态</th>
                        <td>${process.historicProcessInstance.endTime == null ? '未结束' : '已结束'}</td>
                    </tr>
                    </tbody></table>

                <fieldset style="margin-top: 20px">
                    <legend>活动记录</legend>
                    <table width="100%" class="table table-bordered table-hover table-condensed">
                        <thead>
                        <tr>
                            <th>活动ID</th>
                            <th>活动名称</th>
                            <th>活动类型</th>
                            <th>任务ID</th>
                            <th>办理人</th>
                            <th>活动开始时间</th>
                            <td>活动结束时间</td>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${process.actList}" var="act">

                            <tr>
                                <td>${act.id}</td>
                                <td>${act.activityName}</td>
                                <td>${act.activityType}</td>
                                <td>${act.taskId}</td>
                                <td>${act.assignee}</td>
                                <td><fmt:formatDate value="${act.startTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                                <td><fmt:formatDate value="${act.endTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                            </tr>

                        </c:forEach>

                        </tbody>
                    </table>
                </fieldset>


                <div>
                    <img id="processDiagram" src="/process/resource/${process.processDefinition.deploymentId}?resourceName=${process.processDefinition.diagramResourceName}">
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

</body>

</html>

