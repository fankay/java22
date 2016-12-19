$(function () {

    $("#basicBtn").click(function () {
        $("#basicForm").submit();
    });

    $("#basicForm").validate({
        errorElement:'span',
        errorClass:'text-error',
        rules:{
            email : {
                required:true,
                email:true,
                remote:'/validate/email?type=1'
            }
        },
        messages:{
            email : {
                required:"请输入电子邮件",
                email:"电子邮件格式错误",
                remote:'电子邮件已被占用'
            }
        },
        submitHandler:function(form){
            $.ajax({
                url:"/setting?action=profile",
                type:"post",
                data:$(form).serialize(),
                beforeSend:function(){
                    $("#basicBtn").text("保存中...").attr("disabled","disabled");
                },
                success:function(data){
                    if(data.state == "success") {
                        alert("修改成功");
                    }
                },
                error:function(){
                    alert("服务器错误");
                },
                complete:function(){
                    $("#basicBtn").text("保存").removeAttr("disabled");
                }
            });
        }
    });

    //密码修改
    $("#passwordBtn").click(function () {
        $("#passwordForm").submit();
    });

    $("#passwordForm").validate({
        errorClass:"text-error",
        errorElement:"span",
        rules:{
            oldpassword:{
                required:true,
                rangelength:[6,18]
            },
            newpassword:{
                required:true,
                rangelength:[6,18]
            },
            repassword:{
                required:true,
                rangelength:[6,18],
                equalTo:"#newpassword"
            }
        },
        messages:{
            oldpassword:{
                required:"请输入原始密码",
                rangelength:"密码长度6-18个字符"
            },
            newpassword:{
                required:"请输入新密码",
                rangelength:"密码长度6-18个字符"
            },
            repassword:{
                required:"请输入确认密码",
                rangelength:"密码长度6-18个字符",
                equalTo:"两次密码不一致"
            }
        },
        submitHandler:function(form){
            $.ajax({
                url:"/setting?action=password",
                type:"post",
                data:$(form).serialize(),
                beforeSend:function(){
                    $("#passwordBtn").text("保存中...").attr("disabled","disabled");
                },
                success:function(data){
                    if(data.state == "success") {
                        alert("密码修改成功，请重新登录");
                        window.location.href = "/login";
                    } else {
                        alert(data.message);
                    }
                },
                error:function(){
                    alert("服务器错误");
                },
                complete:function(){
                    $("#passwordBtn").text("保存").removeAttr("disabled");
                }
            });
        }
    });



});
