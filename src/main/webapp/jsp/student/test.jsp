<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page isELIgnored ="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <script src="${pageContext.request.contextPath}/js/test.js"></script>
    <title> <c:out value="${test.caption}"/> </title>
</head>
<body>
<%@ include file="../main/header.jsp"%>
<main>
    <c:set var="count" value="0" scope="page" />
    
    <div class="content">
        <form method="post" action="/testing_system/test_result">
            <c:forEach items="${questions}" var="question">
                <c:set var="count" value="${count + 1}" scope="page"/>

                <div data-name="block${count}">
                    <h1><c:out value="${question.text}"/> </h1>
                        <c:forEach items="${question.answers}" var="answer">
                                <input type="checkbox" name="${question.id}" value="${answer.id}">
                                <c:out value="${answer.text}"/>
                                <br>
                        </c:forEach>
                </div>
            </c:forEach>

            <c:set var="number_questions" value="${fn:length(questions)}"/>

            <c:forEach begin="1" end="${number_questions}" varStatus="loop">
                <input type="button" value="<c:out value="${loop.count}"/>" data-target="block${loop.count}">
            </c:forEach>

            <input type="hidden" name="idQuestions" value="<c:out value="${idQuestions}"/>" />
            <input type="hidden" name="test" value="<c:out value="${test.id}"/>" />
            <input type="submit" value="SEND"/>
        </form>

    </div>
</main>
</body>
</html>