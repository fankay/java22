<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.css">

     <title>请假申请</title>

</head>
<body class="hold-transition skin-blue sidebar-mini">

<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="process_apply"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper" >
        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">请假申请</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-5">
                            <form action="/leave/start" class="form-horizontal" method="post">

                                <fieldset>
                                    <div id="messageBox" class="alert alert-error input-large controls" style="display:none">输入有误，请先更正。</div>
                                    <div class="control-group">
                                        <label class="control-label">请假类型:</label>
                                        <div class="controls">
                                            <select id="leaveType" name="leaveType" class="required form-control">
                                                <option>公休</option>
                                                <option>病假</option>
                                                <option>调休</option>
                                                <option>事假</option>
                                                <option>婚假</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">开始时间:</label>
                                        <div class="controls">
                                            <input type="text" id="startTime" name="startTime" value="" readonly class="form_datetime input-small form-control">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">结束时间:</label>
                                        <div class="controls">
                                            <input type="text" id="endTime" name="endTime" value="" readonly class="form_datetime input-small form-control">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">请假原因:</label>
                                        <textarea name="reason" class="form-control"></textarea>
                                    </div>
                                    <div class="control-group" style="padding-top: 15px">

                                        <button type="submit" class="btn btn-primary">启动流程</button>
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