<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.."></script>
    <%@ include file="main/head.jsp"%>
    <title><fmt:message key='title.registration'/></title>
    <style>
        .buttons {
            display: none;
        }
    </style>
</head>
<body>
<%@ include file="main/header.jsp"%>
<main>
    <form class="registration" method="post" action="/testing_system/registration">
        <div class="EnterData">
            <p><fmt:message key='registration.login'/>: </p>
            <input type="text" name="login" value="<c:out value="${login}"/>" maxlength="20" required>
            <span class="error"><c:out value="${login_exc}"/></span>

            <p><fmt:message key='registration.password'/></p>
            <input type="password" name="password" maxlength="10" required pattern="{4, }$" oninvalid="this.setCustomValidity('Invalid password')">

            <p><fmt:message key='registration.re_password'/>:</p>
            <input type="password" name="password_repeat" maxlength="10" required pattern="{4,}$">
            <span class="error"><c:out value="${password_exc}"/></span>
        </div>

        <p><fmt:message key='registration.name'/>: </p>
        <input type="text" name="name" value="<c:out value="${name}"/>" required maxlength="20">
        <p><fmt:message key='registration.surname'/>: </p>
        <input type="text" name="surname" value="<c:out value="${surname}"/>" required maxlength="20">
        <p><fmt:message key='registration.email'/>: </p>
        <input type="email" name="email" value="<c:out value="${email}"/>" maxlength="30">
        <span class="error"><c:out value="${email_exc}"/></span>
        <br>
        <input type="submit" value="<fmt:message key='registration.registration'/>">
    </form>
</main>
</body>
</html>