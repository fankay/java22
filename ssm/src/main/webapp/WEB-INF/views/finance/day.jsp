<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>财务日报</title>
    <%@include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datatables1.10.13/media/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="finance_day"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-body">
                    <form class="form-inline">
                        <input type="text" class="form-control" id="date">
                    </form>
                </div>
            </div>
            <!-- Default box -->
            <div class="box box-primary box-solid">
                <div class="box-header with-border">
                    <h3 class="box-title">财务日报</h3>

                    <div class="box-tools pull-right">
                        <a href="javascript:;" id="exportCsv" class="btn btn-default"><i class="fa fa-file-o"></i> 导出Excel</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th></th>
                                <th>流水号</th>
                                <th>创建日期</th>
                                <th>类型</th>
                                <th>金额</th>
                                <th>业务模块</th>
                                <th>业务流水</th>
                                <th>状态</th>
                                <th>备注</th>
                                <th>#</th>
                            </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/datatables1.10.13/media/js/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables1.10.13/media/js/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script>
    $(function () {
        $("#date").val(moment().format("YYYY-MM-DD"));

        $("#date").datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true,
            endDate:moment().format("YYYY-MM-DD")
        }).on("changeDate",function(e){
            var today = e.format(0,'yyyy-mm-dd');
            //table.ajax.reload(false,null);
            table.ajax.reload();
        });

        var table = $(".table").DataTable({
            "lengthChange": false,
            "pageLength": 25,
            "serverSide": true,
            "ajax":{
                "url":"/finance/day/load",
                "type":"get",
                "data":function(obj){
                    obj.day = $("#date").val()
                }
            },
            "searching":false,//不使用自带的搜索
            "order":[[0,'desc']],//默认排序方式,
            "ordering": false,
            "columns":[
                {"data":"id","name":"id"},
                {"data":"serialNumber"},
                {"data":"createDate"},
                {"data":"type"},
                {"data":"money"},
                {"data":"module"},
                {"data":"moduleSerialNumber"},
                {"data":"state"},
                {"data":"mark"},
                {"data":function (row) {
                    if(row.state == "未确认") {
                        return "<a href='javascript:;' class='confirm_btn' rel='"+row.id+"'>确认</a>";
                    } else {
                        return "";
                    }
                }}
            ],
            "columnDefs":[
                {targets:[0],visible: false}
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

        $(document).delegate(".confirm_btn","click",function(){
            var id = $(this).attr("rel");
            layer.confirm("确认已收(付)款?",function(index){
                layer.close(index);
                $.post("/finance/confirm/"+id).done(function(result){
                    if(result.status == "success") {
                        layer.msg("确认成功");
                        table.ajax.reload(false, null);
                    } else {
                        layer.msg(message);
                    }
                }).error(function(){
                    layer.msg("服务器异常");
                });

            });
        });

        //导出Excel文件
        $("#exportCsv").click(function () {
            var day = $("#date").val();
            window.location.href = "/finance/day/"+day+"/data.xls";
        })

    });
</script>
</body>
</html>
