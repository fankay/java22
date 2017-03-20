<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h1>save page2</h1>
    <h1>${user.userName}</h1>
    <h1><s:property value="name"/></h1>
    <h1><s:property value="user.userNamex" default="xxx"/> </h1>
    <s:if test="name == 'Jacks'">
        <h1>Hello,Jack</h1>
    </s:if>
    <s:elseif test="name == 'Jack'">
        <h1>Hi,Jack</h1>
    </s:elseif>
    <s:else>
        <h1>Hello,Other</h1>
    </s:else>
    <ul>
        <s:iterator value="nameList" var="n" status="st">
            <li>${st.index} : <s:property value="n"/> </li>
        </s:iterator>
    </ul>
</body>
</html>