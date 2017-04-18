<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="main/head.jsp"%>
    <title>Registration</title>
</head>
<body>
    <%@ include file="main/header.jsp"%>
    <main>
        <form class="registration" method="post" action="/testing_system/new_user">
            <div class="EnterData">
                <p>Login: </p>
                <input type="text" name="login">
                <span class="error"><c:out value="${login_exc}"/></span>

                <p>Password:</p>
                <input type="password" name="password">

                <p>Repeat password:</p>
                <input type="password" name="password_repeat">
                <span class="error"><c:out value="${password_exc}"/></span>
            </div>

            <p>Name: </p><input type="text" name="name">
            <p>Surname: </p><input type="text" name="surname">
            <p>Email: </p><input type="text" name="email">
            <span class="error"><c:out value="${email_exc}"/></span>
            <br>
            <input type="submit" value="Registration">
        </form>
    </main>
</body>
</html>


