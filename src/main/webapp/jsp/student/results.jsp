<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title>results</title>
</head>
<body>
    <%@ include file="../main/header.jsp"%>
    <main>
        <p> results </p>
        <c:set var="count" value="0" scope="page" />
        <table>
            <c:forEach items="${results}" var="result">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <tr>
                    <td><c:out value="${count}" /> </td>
                    <td><c:out value="${result.test.caption}"/></td>
                    <td><c:out value="${result.result}"/></td>
                    <td><c:out value="${result.date}"/></td>
                </tr>
            </c:forEach>
        </table>
    </main>
</body>
</html>