<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title>test_result</title>
</head>
<body>
<%@ include file="../main/header.jsp"%>
    <main>
        <div class="content center">
            <h1 class="title_results big_margin_top">Your result</h1>
            <h2> <c:out value="${mark}"/>% right answers </h2>

            <c:if test="${mark > 99.99 }">
                <h3> Well done! </h3>
                <img src="${pageContext.request.contextPath}/img/well_done.png" alt="" >
            </c:if>
            <div class="center">
                <form method="get" action="/testing_system/">
                    <input type="submit" value="On main page" class="main_page">
                </form>
            </div>
        </div>
    </main>
</body>
</html>
