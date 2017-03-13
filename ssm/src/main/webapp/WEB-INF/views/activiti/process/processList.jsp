<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发起流程</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/dist/css/style.css">
</head>
<body  class="hold-transition skin-blue sidebar-mini">

<%@include file="../../include/header.jsp"%>
<jsp:include page="../../include/sider.jsp">
    <jsp:param name="menu" value="process_apply"/>
</jsp:include>

<div class="content-wrapper">
    <section class="content">
        <div class="box">
            <div class="main"><!-- 人力资源开始 -->
                <div class="big-title bg0">人力资源管理类</div>
                <span class="arrow-left"></span>
                <div class="content">
                    <div class="small-title">考勤类</div>
                    <ul id="nav_type">
                        <li><a class="color_A new" href="/leave/apply/">请假</a></li>
                        <li><a class="color_A" href="#">报销</a></li>
                        <li><a class="color_A" href="#">请假单</a></li>
                        <li><a class="color_A" href="#">出差申请单</a></li>
                        <li><a class="color_A" href="#">外训申请</a></li>
                        <li><a class="color_A" href="#">出差变更</a></li>
                    </ul>
                </div>
                <div class="content">
                    <div class="small-title">招聘类</div>
                    <ul>
                        <li><a class="color_A" href="#">员工转正</a></li>
                        <li><a class="color_A" href="#">实习结束</a></li>
                    </ul>
                </div>
                <div class="content">
                    <div class="small-title">人事类</div>
                    <ul>
                        <li><a class="color_A" href="#">离职</a></li>
                        <li><a class="color_A" href="#">员工信息更新</a></li>
                    </ul>
                </div>
                <div class="content">
                    <div class="small-title">任职考核类</div>
                    <ul>
                        <li><a class="color_A" href="#">任职资格</a></li>
                        <li><a class="color_A" href="#">考核评估</a></li>
                        <li><a class="color_A" href="#">考核计划变更</a></li>
                        <li><a class="color_A" href="#">考核计划制定</a></li>
                        <li><a class="color_A" href="#">专业成果</a></li>
                    </ul>
                </div>
            </div><!-- 人力资源结束 -->
            <div class="main"><!-- 财务管理类开始-->
                <div class="big-title bg1">财务管理类</div>
                <span class="arrow-left"></span>
                <div class="content">
                    <div class="small-title">财务管理</div>
                    <ul>
                        <li><a class="color_A" href="#">清理</a></li>
                        <li><a class="color_A" href="#">领用</a></li>
                        <li><a class="color_A" href="#">归还</a></li>
                        <li><a class="color_A" href="#">报销</a></li>
                        <li><a class="color_A" href="#">租房变更</a></li>
                        <li><a class="color_A" href="#">租房登记</a></li>
                        <li><a class="color_A" href="#">借款</a></li>
                    </ul>
                </div>
            </div><!-- 财务管理类结束 -->
            <div class="main"><!-- 合同管理类开始-->
                <div class="big-title bg2">财务管理类</div>
                <span class="arrow-left"></span>
                <div class="content">
                    <div class="small-title">合同管理</div>
                    <ul>
                        <li><a class="color_A" href="#">采购申请</a></li>
                        <li><a class="color_A" href="#">项目售前询价</a></li>
                    </ul>
                </div>
            </div><!-- 合同管理类结束 -->

        </div>

    </section>
</div>
</body>
</html>
