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
        <title>Login</title>
        <style>
            div.buttons {
                display: none;
            }
        </style>
    </head>
    <body>
        <%@ include file="main/header.jsp"%>
        <main>
            <div class="content">
                <div class="log_in">

                    <form class="form_login" action="/testing_system/sign_in" method="post">
                        <p class="text_log_in"><fmt:message key="sign_in.title" />!</p>

                        <label class="label" for="login" maxlength="16">
                            <p><fmt:message key="sign_in.login"/>:</p></label>
                        <input type="text" id="login" name="login" class="login" required/>
                        <br>

                        <label class="label" for="password">
                            <p><fmt:message key="sign_in.password"/>:</p></label>

                        <input type="password" id="password" name="password" class="password" maxlength="10" required/>
                        <br>
                        <input type="submit" class="submit" value="Enter"/>
                    </form>

                    <form class="registration" action="${pageContext.request.contextPath}/pages/registration.jsp" method="get">
                        <input type="submit"  value="Registration"/>
                    </form>
                </div>
                <p class="error"> <c:out value="${error}"/> </p>
            </div>
        </main>
    </body>
</html>
