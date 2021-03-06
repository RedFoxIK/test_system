<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title><fmt:message key='title.results'/></title>
</head>
<body>
    <%@ include file="../main/header.jsp"%>
    <main>
    <c:choose>
        <c:when test="${empty results}">
            <h2><fmt:message key='results.none'/>( </h2>
        </c:when>

        <c:otherwise>
            <c:set var="count" value="0" scope="page" />
            <table class="table_results">
                <tr>
                    <th>№</th>
                    <th><fmt:message key='results.test'/></th>
                    <th><fmt:message key='results.mark'/></th>
                    <th><fmt:message key='results.date'/></th>
                </tr>
                <c:forEach items="${results}" var="result">
                    <c:set var="count" value="${count + 1}" scope="page"/>

                    <tr>
                        <td><c:out value="${count}" /> </td>
                        <td><c:out value="${result.test.caption}"/></td>
                        <td><c:out value="${result.mark}"/></td>
                        <td><tags:datetime date="${result.dateTime}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

        <div class="center">
            <form method="get" action="/testing_system/">
                <input type="submit" value="<fmt:message key='general.main_page'/>" class="main_page">
            </form>
        </div>

    </main>
</body>
</html>
