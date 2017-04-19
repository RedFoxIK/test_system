<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title>tests</title>
</head>
<body>
    <%@ include file="../main/header.jsp"%>
    <main>
        <div class="info">
            <p>
                Dear, student please choose!
            </p>
            <form class="results"  method = "get" action="/testing_system/results">
                <input type="submit" value="results">
            </form>
        </div>
            <div class="tests">
                <c:forEach items="${tests}" var="test">
                    <div class="test">
                        <p> <c:out value="${test.caption}"/> </p>
                        <div class="author">
                            <c:out value="${test.author.name}"/>
                            <c:out value="${test.author.surname}"/>
                        </div>
                    </div>
                </c:forEach>
            </div>
    </main>
</body>
</html>
