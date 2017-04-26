<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><c:out value="${test.caption}"/></title>
    <script src="${pageContext.request.contextPath}/js/test2.js"></script>
    <%@ include file="../main/head.jsp"%>
</head>
<body>
    <%@ include file="../main/header.jsp"%>
    <form method="post" action="/testing_system/tutor/add_question" id="form_add_question">
        <h1>Question</h1>
        <input type="text" name="question"> <br>

        <input type="checkbox" name="mult_choice" value="yes" onclick="changeType()"> <span>multiple choice</span> <br>
        <h3>Answers:</h3>

        <input class="type_answer" name="group" value="0">
        <input type="text" class="answer" name="0"/> <br>

        <input class="type_answer" name="group" value="1">
        <input type="text" class="answer"  name="1"/> <br>

        <div id = "other_answers"></div>

        <input type="button"  value="add" onclick="add()">
        <input type="button"  value="remove" onclick="remove()">
        <input type="hidden" name="number_answers" id="number_answers" value="">
        <input type="hidden" name="id_test" value="<c:out value="${test.id}"/>">
        <input type="button" value="ADD QUESTION" onclick="count_answers()">
    </form>

    <form method="get" action="/testing_system/test_edit">
        <input type="hidden" name="id_test" value="<c:out value="${test.id}"/>">
        <input type="submit" value="BACK">
    </form>
</body>
</html>
