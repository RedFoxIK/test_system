<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title>test_result</title>
</head>
<body>
    <%@ include file="../main/header.jsp"%>
    <h1>Your result</h1>
    <h2> <c:out value="${mark}"/>% right answers </h2>

    <c:if test="${mark > 99.99 }">
        <h3> Well done! ☺</h3>
    </c:if>
</body>
</html>
