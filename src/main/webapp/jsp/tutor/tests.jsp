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
                Dear tutor, good luck!
            </p>
            <form class="results"  method = "get" action="/testing_system/results">
                <input type="submit" value="results">
            </form>
            </div>
            <div class="tests">
                <c:forEach items="${tests}" var="test">
                    <div class="test" id="<c:out value='${test.id}'/>" onclick="showTest(this.id)">
                        <p> <c:out value="${test.caption}"/> </p>
                        <div class="author">
                            <c:out value="${test.author.name}"/>
                            <c:out value="${test.author.surname}"/>
                        </div>
                    </div>
                </c:forEach>
            </div>
        <form id="hidden_form" method="get" action="/testing_system/test_edit">
            <input name="id_test" type="hidden" value="" id="id_test"/>
        </form>
    </main>
</body>
</html>


