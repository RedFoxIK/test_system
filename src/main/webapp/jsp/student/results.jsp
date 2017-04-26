<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title>results</title>
</head>
<body>
    <%@ include file="../main/header.jsp"%>
    <main>

        <c:if test="${empty results}">
            <h2>You don't have any test results yet( </h2>
        </c:if>

        <c:set var="count" value="0" scope="page" />
        <table class="table_results">
            <tr>
                <th>№</th>
                <th>Name of the test</th>
                <th>Mark</th>
                <th>Date</th>
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

        <div class="center">
            <form method="get" action="/testing_system/">
                <input type="submit" value="On main page" class="main_page">
            </form>
        </div>

    </main>
</body>
</html>
