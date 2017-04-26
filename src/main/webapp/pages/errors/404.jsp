<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title>404</title>
</head>
<body>
<%@ include file="../main/header.jsp"%>
<p>404</p>

<form method="get" action="/testing_system/">
    <input type="submit" value="On main page" class="main_page">
</form>
</body>
</html>
