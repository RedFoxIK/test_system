<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="test" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="${language}">
<head>
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
    <div class="content">
        <form class="registration_item" method="post" action="/testing_system/registration">
            <div class="registr_item">
                <p><fmt:message key='registration.login'/>: </p>
                <input type="text" name="login" value="<c:out value="${login}"/>" maxlength="20" required>
                <span class="registr_error"><c:out value="${login_exc}"/></span>
            </div>
            <div class="registr_item">
                <p><fmt:message key='registration.password'/></p>
                <input type="password" name="password" maxlength="10" required pattern="{4, }$">
            </div>
            <div class="registr_item">
                <p><fmt:message key='registration.re_password'/>:</p>
                <input type="password" name="password_repeat" maxlength="10" required pattern="{4,}$">
                <span class="registr_error"><c:out value="${password_exc}"/></span>
            </div>
            <div class="registr_item">
                <p><fmt:message key='registration.name'/>: </p>
                <input type="text" name="name" value="<c:out value="${name}"/>" required maxlength="20">
            </div>
            <div class="registr_item">
                <p><fmt:message key='registration.surname'/>: </p>
                <input type="text" name="surname" value="<c:out value="${surname}"/>" required maxlength="20">
            </div>
            <div class="registr_item">
                <p><fmt:message key='registration.email'/>: </p>
                <input type="email" name="email" value="<c:out value="${email}"/>" maxlength="30">
                <span class="registr_error"><c:out value="${email_exc}"/></span>
            </div>
            <br>
            <input class="registr_confirm" type="submit" value="<fmt:message key='registration.registration'/>">
        </form>
    </div>
</main>
</body>
</html>