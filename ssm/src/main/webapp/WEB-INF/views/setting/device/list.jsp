<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Blank Page</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datatables/jquery.dataTables.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="sys_device"/>
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
                            <input type="text" id="q_device_name" placeholder="设备名称" value="${queryName}" class="form-control">
                        </div>
                        <button type="button" id="searchBtn" class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-list"></i> 设备管理</h3>
                    <div class="box-tools pull-right">
                        <a href="/setting/device/new" class="btn"><i class="fa fa-plus"></i></a>
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
                            <th>id</th>
                            <th>名称</th>
                            <th>单位</th>
                            <th>总数</th>
                            <th>库存</th>
                            <th>价格</th>
                            <th width="100">#</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script>
    $(function () {
        var table = $(".table").DataTable({
            "lengthMenu": [10,50,100],
            "serverSide": true,
            "ajax":{
                "url":"/setting/device/load",
                "type":"post",
                "data":function(obj){
                    obj.deviceName= $("#q_device_name").val();
                }
            },
            "searching":false,//不使用自带的搜索
            "order":[[0,'desc']],//默认排序方式
            "columns":[
                {"data":"id","name":"id"},
                {"data":"name"},
                {"data":"unit"},
                {"data":"totalNum","name":"total_num"},
                {"data":"currentNum","name":"current_num"},
                {"data":"price","name":"price"},
                {"data":function(obj){
                    return "<a href='javascript:;' rel='"+obj.id+"' class='delLink'>删除</a>";
                }}

            ],
            "columnDefs":[
                {targets:[0],visible: false},
                {targets:[1,2,6],orderable:false}
            ],
            "language":{ //定义中文
                "search": "搜索:",
                "zeroRecords":    "没有匹配的数据",
                "lengthMenu":     "显示 _MENU_ 条数据",
                "info":           "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered":   "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载中...",
                "processing":     "处理中...",
                "paginate": {
                    "first":      "首页",
                    "last":       "末页",
                    "next":       "下一页",
                    "previous":   "上一页"
                }
            }
        });

        $(document).delegate(".delLink","click",function(){
            if(confirm("确定要删除吗?")) {
                var id = $(this).attr("rel");
                $.get("/setting/device/"+id+"/del").done(function(data){
                    if(data == "success") {
                        alert("删除成功");

                        //dataTables重新加载
                        table.ajax.reload();
                    }
                }).error(function(){
                    alert("服务器异常");
                });
            }
        });

        //自定义搜索
        $("#searchBtn").click(function () {
            table.draw(); //dataTables发出请求
        });


    });
</script>
</body>
</html>
