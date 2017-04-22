<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title>${test.caption}</title>
</head>
<body>
    <%@ include file="../main/header.jsp"%>
    <c:forEach items="${questions}" var="question">
        <div class="question" id="<c:out value='${question.id}'/>" onclick="showTest(this.id)">
            <h3> <c:out value="${question.text}"/> </h3>
            <c:forEach items="${question.answers}" var="answer">

                <c:set var="checked" value="checked" scope="page"/>
                <c:set var="type" value="radio" scope="page" />

                <input type="<c:out value="${type}"/>" disabled="true" <c:out value="${checked}"/> ">
                <span> <c:out value="${answer.text}"/> </span> <br>
            </c:forEach>
        </div>
        <hr>
    </c:forEach>
</body>
</html>
