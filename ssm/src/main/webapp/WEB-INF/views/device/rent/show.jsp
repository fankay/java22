<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>设备租赁合同显示</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper" id="app">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_device_rent"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <h3 style="text-align: center" class="visible-print-block">凯盛软件租赁合同清单</h3>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">租赁合同详情</h3>

                    <div class="box-tools pull-right hidden-print">
                        <button id="print" class="btn btn-default btn-sm"><i class="fa fa-print"></i>打印</button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>公司名称</label>
                                ${rent.companyName}
                            </div>
                            <div class="form-group">
                                <label>联系电话</label>
                                ${rent.tel}
                            </div>
                            <div class="form-group">
                                <label>租赁日期</label>
                                ${rent.rentDate}
                            </div>
                            <div class="form-group">
                                <label>总金额</label>
                                ${rent.totalPrice}
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>法人代表</label>
                                ${rent.linkMan}
                            </div>
                            <div class="form-group">
                                <label>地址</label>
                                ${rent.address}
                            </div>
                            <div class="form-group">
                                <label>归还日期</label>
                                ${rent.backDate}
                            </div>
                            <div class="form-group">
                                <label>预付款</label>
                                ${rent.preCost}
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>身份证号</label>
                                ${rent.cardNum}
                            </div>
                            <div class="form-group">
                                <label>传真</label>
                                ${rent.fax}
                            </div>
                            <div class="form-group">
                                <label>总天数</label>
                                ${rent.totalDay}
                            </div>
                            <div class="form-group">
                                <label>尾款</label>
                                ${rent.lastCost}
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">设备列表</h3>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>设备名称</th>
                                <th>单位</th>
                                <th>租赁单价</th>
                                <th>数量</th>
                                <th>总价</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${detailList}" var="d">
                            <tr>

                                <td>${d.deviceName}</td>
                                <td>${d.deviceUnit}</td>
                                <td>${d.devicePrice}</td>
                                <td>${d.num}</td>
                                <td>${d.totalPrice}</td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="box hidden-print">
                <div class="box-header">
                    <h3 class="box-title">合同扫描件</h3>
                    <div class="box-tools pull-right">
                        <a href="/device/rent/doc/zip?id=${rent.id}" class="btn btn-sm btn-default">
                            <i class="fa fa-file-zip"></i> 打包下载
                        </a>
                    </div>
                </div>
                <div class="box-body">
                    <ul id="fileList">
                        <c:forEach items="${docList}" var="doc">
                            <li><a href="/device/rent/doc?id=${doc.id}">${doc.sourceName}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->


</div>

<%@include file="../../include/js.jsp"%>
<script>
    $(function () {
        $("#print").click(function () {
            window.print();
        });
    });
</script>
</body>
</html>
