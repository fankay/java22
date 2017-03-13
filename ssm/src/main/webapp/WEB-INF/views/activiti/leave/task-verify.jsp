<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.css">

    <title>请假--${leave.task.name}</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="process_list"/>
    </jsp:include>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper" >
        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-header">

                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-5">
                            <form action="/leave/task/complete/${leave.task.id}" class="form-horizontal" method="post">
                                <legend><small>${leave.task.name}</small></legend>
                                <input type="hidden" name = "defKey" value="${leave.task.taskDefinitionKey}">
                                <fieldset>
                                    <div class="control-group">
                                        <label class="control-label">申请人: ${leave.userName}</label>

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">申请时间: ${leave.applyTime}</label>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">请假类型: ${leave.leaveType}</label>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">开始时间: ${leave.startTime}</label>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">结束时间: ${leave.endTime}2</label>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">请假原因: ${leave.reason}</label>
                                    </div>
                                    <c:choose>
                                        <c:when test="${leave.task.taskDefinitionKey == 'leaveBack'}">

                                            <div class="control-group">
                                                <label class="control-label">实际开始时间:
                                                </label>
                                                <div class="controls">
                                                    <input type="text" id="realStartTime" name="realityStartTime" value="" readonly class="form_datetime input-small form-control">
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">实际结束时间:</label>
                                                <div class="controls">
                                                    <input type="text" id="realEndtTime" name="realityEndTime" value="" readonly class="form_datetime input-small form-control">
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="control-group">
                                                <label class="control-label">审批意见：</label>
                                                <input type="radio" name="verify" value="true" checked/>同意
                                                <input type="radio" name="verify" value="false"/>拒绝
                                            </div>


                                        </c:otherwise>

                                    </c:choose>
                                    <div class="form-actions control-group" style="padding-top: 7px;">
                                        <button type="submit" class="btn btn-primary">完成任务</button>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>
<%@include file="../../include/js.jsp"%>

<script type="text/javascript" src="/static/plugins/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="/static/plugins/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">
    $(function() {
        $(".form_datetime").datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            language:'zh-CN',
            autoclose: true,
            minuteStep: 30
        });
    });


</script>
</body>
</html>

