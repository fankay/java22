<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>

<button id="btn">Button</button>
<div id="result"></div>

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/handlebars-v4.0.5.js"></script>
<script type="text/handlebars" id="temp1">
    <div class="entry">
        {{!-- 模板注释 --}}
        <h1>{{title}}</h1>
        <div class="body">
            {{{body}}}
        </div>
        <ul>
            {{#each data}}
                <li>{{name}}</li>
            {{/each}}
        </ul>
        {{#if vip}}
            <div>Welcome!VIP</div>
        {{/if}}
    </div>
</script>
<script>
    $(function(){
        $("#btn").click(function(){
            var source = $("#temp1").html();
            var template = Handlebars.compile(source);

            var data = {
                "title":"模板的使用方式",
                "body":"<span style='color:red'>原来是这样的</span>",
                "data" : [
                    {"name":"jack"},
                    {"name":"李四"},
                    {"name":"王五"}
                ],
                "vip" : true
            };
            var html = template(data);

            $(html).appendTo($("#result"));
        });
    });
</script>

</body>
</html>