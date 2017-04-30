<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><c:out value="${test.caption}"/></title>
    <script src="${pageContext.request.contextPath}/js/tutortest.js"></script>
    <%@ include file="../main/head.jsp"%>
</head>
<body>
    <%@ include file="../main/header.jsp"%>
    <form method="post" action="/testing_system/tutor/add_question" id="form_add_question">
        <h1><fmt:message key='question.question'/></h1>
        <textarea name="question" cols="40" rows="5"></textarea></p>
        <input type="checkbox" name="mult_choice" value="yes" onclick="changeType()">
        <span><fmt:message key='question.mult_choice'/></span> <br>
        <h3><fmt:message key='question.answers'/>:</h3>

        <input type="checkbox" class="type_answer" name="group" value="0">
        <input type="text" class="answer" name="0"/> <br>

        <input class="type_answer" name="group" value="1">
        <input type="text" class="answer"  name="1"/> <br>

        <div id = "other_answers"></div>

        <input type="button"  value="<fmt:message key='question.add'/>" onclick="add()">
        <input type="button"  value="<fmt:message key='question.remove'/>" onclick="remove()">
        <input type="hidden" name="number_answers" id="number_answers" value="">
        <input type="hidden" name="id_test" value="<c:out value="${test.id}"/>">
        <input type="button" value="<fmt:message key='question.add_question'/>" onclick="count_answers()">
    </form>

    <form method="get" action="/testing_system/tutor/test">
        <input type="hidden" name="id_test" value="<c:out value="${test.id}"/>">
        <input type="submit" value="<fmt:message key='question.back'/>">
    </form>
</body>
</html>
