<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<h3>使用JSP表达式：</h3>
用户姓名：<%= request.getAttribute("name") %><br/>

<h3>使用EL表达式语言：</h3>
用户姓名：${requestScope.name}<br/>

<h3>使用JSTL标签库：</h3>
用户姓名：<c:out value="${requestScope.name}" /><br/>
</body>
</html>