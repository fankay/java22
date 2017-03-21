<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/repo/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-5">
                <form action="/bank/save" method="post">
                    <div class="form-group">
                        <label>账号</label>
                        <input type="text" name="bank.username" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input type="password" name="bank.password" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>金额</label>
                        <input type="text" name="bank.money" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>状态</label>
                        <select name="bank.enable" class="form-control">
                            <option value="true">正常</option>
                            <option value="false">禁用</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-default">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>