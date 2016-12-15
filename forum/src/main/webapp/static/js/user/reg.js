$(function(){

    $("#regBtn").click(function () {
        $("#regForm").submit();
    });

    $("#regForm").validate({
        errorElement:'span',
        errorClass:'text-error',
        rules:{
            username:{
                required:true,
                minlength:3,
                remote:"/validate/user"
            },
            password:{
                required:true,
                rangelength:[6,18]
            },
            repassword:{
                required:true,
                rangelength:[6,18],
                equalTo:"#password"
            },
            email:{
                required:true,
                email:true,
                remote:"/validate/email"
            },
            phone:{
                required:true,
                rangelength:[11,11],
                digits:true
            }
        },
        messages:{
            username:{
                required:"请输入账号",
                minlength:"账号最少3个字符",
                remote:"账号已被占用"
            },
            password:{
                required:"请输入密码",
                rangelength:"密码长度6-18个字符"
            },
            repassword:{
                required:"请输入确认密码",
                rangelength:"密码长度6-18个字符",
                equalTo:"两次密码不一致"
            },
            email:{
                required:"请输入电子邮件",
                email:"邮件格式错误",
                remote:"电子邮件已被占用"
            },
            phone:{
                required:"请输入手机号码",
                rangelength:"手机号码格式错误",
                digits:"手机号码格式错误"
            }
        },
        submitHandler:function () {
            $.ajax({
                url:"/reg",
                type:"post",
                data:$("#regForm").serialize(),
                beforeSend:function(){
                    $("#regBtn").text("注册中...").attr("disabled","disabled");
                },
                success:function(data){
                    if(data.state == 'success') {
                        alert("注册成功，请登录");
                        window.location.href = "/login";
                    } else {
                        alert(data.message);
                    }
                },
                error:function(){
                    alert("服务器错误");
                },
                complete:function(){
                    $("#regBtn").text("注册").removeAttr("disabled");
                }
            });
        }
    });


});
