<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored ="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><c:out value="${test.caption}"/></title>
    <script src="${pageContext.request.contextPath}/js/test.js"> </script>
    <%@ include file="../main/head.jsp"%>
</head>
<body>
<%@ include file="../main/header.jsp"%>
<main>
    <c:set var="count" value="0" scope="page" />
    
    <div class="content">
        <form method="post" action="/testing_system/student/test_end">

            <div class="test_area">

                <div class="item_question">

                    <c:forEach items="${test.questions}" var="question">
                        <c:set var="count" value="${count + 1}" scope="page"/>

                        <div data-name="block${count}">
                            <h1><c:out value="${question.text}"/> </h1>

                            <c:set var="type" value="radio" scope="page" />
                            <c:if test="${question.multChoice}">
                                <c:set var="type" value="checkbox" scope="page" />
                            </c:if>

                            <c:forEach items="${question.answers}" var="answer">
                                <div class="item_answer"><input type="${type}" name="${question.id}" value="${answer.id}">
                                <c:out value="${answer.text}"/>
                                <br>
                                </div>
                            </c:forEach>
                        </div>
                    </c:forEach>

                </div>

                <c:set var="number_questions" value="${fn:length(test.questions)}"/>

                <div class="item_number_question">

                    <c:forEach begin="1" end="${number_questions}" varStatus="loop">
                        <input type="button" value="<c:out value="${loop.count}"/>" data-target="block${loop.count}" class="number_question">
                    </c:forEach>

                </div>

            </div>

            <input type="hidden" name="idQuestions" value="<c:out value="${idQuestions}"/>" />
            <input type="hidden" name="id_test" value="<c:out value="${test.id}"/>" />
            <div class="item_submit"><input type="submit" value="<fmt:message key='test.student.button'/>" class="button_send"/></div>
        </form>

    </div>

</main>
</body>
</html>