$(function(){

    var login = $("#isLogin").text();
    if(login == "1"){
        setInterval(loadNotify,10*1000);
    }
   var loadNotify = function(){
    $.post("/notify",function(json){
        if (json.state == "success" && json.data > 0){
            $("#unreadCount").text(json.data);
        }
    });
   };
   loadNotify();
});