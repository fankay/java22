var ajax = {};
ajax.sendPost = function(obj){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("post",obj.url);
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState == 4) {
            if(xmlHttp.status == 200) {
                var result = xmlHttp.responseText;
                obj.success(result);
            } else {
                obj.error();
            }
        }
    };
    xmlHttp.send(obj.data);
};