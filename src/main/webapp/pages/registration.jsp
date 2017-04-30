<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="test" />

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
        <div class="content">
        <form class="registration" method="post" action="/testing_system/registration">
            <div class="EnterData">
                <p><fmt:message key='registaration.registration'>: </p>
                <input type="text" name="login" value="<c:out value="${login}"/>" maxlength="20" required>
                <span class="error"><c:out value="${login_exc}"/></span>

                <p><fmt:message key='registaration.password'/>:</p>
                <input type="password" name="password" maxlength="10" required pattern="{4, }$" oninvalid="this.setCustomValidity('Invalid password')">

                <p><fmt:message key='registaration.re_password'/>:</p>
                <input type="password" name="password_repeat" maxlength="10" required pattern="{4,}$">
                <span class="error"><c:out value="${password_exc}"/></span>
            </div>

            <p><fmt:message key='registaration.name'/>: </p>
            <input type="text" name="name" value="<c:out value="${name}"/>" required title="First Name is Required!" maxlength="20">
            <p><fmt:message key='registaration.surname'/>: </p>
            <input type="text" name="surname" value="<c:out value="${surname}"/>" required title="Last Name is Required!" maxlength="20">
            <p><fmt:message key='registaration.email'/>: </p>
            <input type="email" name="email" value="<c:out value="${email}"/>" maxlength="30">
            <span class="error"><c:out value="${email_exc}"/></span>
            <br>
            <input type="submit" value="<fmt:message key='registaration.registration'/>">
        </form>
        </div>
    </main>
</body>
</html>


