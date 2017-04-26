<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title> <c:out value="${test.id}"/> results </title>
</head>
<body>
    <%@ include file="../main/header.jsp"%>

    <main>

        <c:set var="count" value="0" scope="page" />
        <table class="table_results">
            <tr>
                <th>№</th>
                <th>User login</th>
                <th>Mark</th>
                <th>Time</th>
            </tr>
            <c:forEach items="${results}" var="result">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <tr>
                    <td><c:out value="${count}" /> </td>
                    <td><c:out value="${result.user.login}"/></td>
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
