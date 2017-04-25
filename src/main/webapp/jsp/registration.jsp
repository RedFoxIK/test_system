<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <%@ include file="main/head.jsp"%>
    <title>Registration</title>
    <style>
        .buttons {
            display: none;
        }
    </style>
</head>
<body>
    <%@ include file="main/header.jsp"%>
    <main>
        <form class="registration" method="post" action="/testing_system/new_user">
            <div class="EnterData">
                <p>Login: </p>
                <input type="text" name="login" value="<c:out value="${login}"/>" maxlength="20" required>
                <span class="error"><c:out value="${login_exc}"/></span>

                <p>Password:</p>
                <input type="password" name="password" maxlength="10" required pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4, }$" oninvalid="this.setCustomValidity('Invalid password')">

                <p>Repeat password:</p>
                <input type="password" name="password_repeat" maxlength="10" required pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,}$">
                <span class="error"><c:out value="${password_exc}"/></span>
            </div>

            <p>Name: </p>
            <input type="text" name="name" value="<c:out value="${name}"/>" required title="First Name is Required!" maxlength="20">
            <p>Surname: </p>
            <input type="text" name="surname" value="<c:out value="${surname}"/>" required title="Last Name is Required!" maxlength="20">
            <p>Email: </p>
            <input type="email" name="email" value="<c:out value="${email}"/>" maxlength="30">
            <span class="error"><c:out value="${email_exc}"/></span>
            <br>
            <input type="submit" value="Registration">
        </form>
    </main>
</body>
</html>


