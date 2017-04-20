<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title> <c:out value="${test_name}"/> </title>
</head>
<body>
<%@ include file="../main/header.jsp"%>
<main>
    <c:forEach items="${questions}" var="question">
        <p> <c:out value="${question.text}"/> </p>
    </c:forEach>
</main>
</body>
</html>