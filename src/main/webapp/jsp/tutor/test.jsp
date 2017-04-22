<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/js/test2.js"></script>
    <title><c:out value="{$test.caption}"/></title>
</head>
<body>
    <form>
        <h1>Question</h1>
        <input type="text"> <br>

        <input type="checkbox" onclick="changeType()"> <span>multi choice</span> <br>
        <h3>Answers:</h3>

        <input class="type_answer" name="group">
        <input type="text" class="answer" /> <br>

        <input class="type_answer" name="group">
        <input type="text" class="answer" /> <br>

        <div id = "other_answers"></div>

        <input type="button"  id="b1"  value="add" onclick="add()">
        <input type="button"  value="remove" onclick="remove()">
    </form>
</body>
</html>
