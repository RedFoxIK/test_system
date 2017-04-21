<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@ include file="main/head.jsp"%>
        <title>Login</title>
        <style>
            .profile_button, .logout_button {
                display: none;
            }
        </style>
    </head>
    <body>
        <%@ include file="main/header.jsp"%>
        <main>
            <div class="content">
                <div class="log_in">
                    <form class="form_login" action="/testing_system/tests" method="post">
                        <p class="text_log_in">PLEASE, SIGN IN!</p>
                        <label class="label" for="login" maxlength="16"><p>Login:</p></label>
                        <input type="text" id="login" name="login" class="login" required/>
                        <br>
                        <label class="label" for="password"><p>Password:</p></label>
                        <input type="password" id="password" name="password" class="password" maxlength="10" required/>
                        <br>
                        <input type="submit" class="submit" value="Enter"/>
                    </form>
                    <form class="registration" action="${pageContext.request.contextPath}/jsp/registration.jsp" method="get">
                        <input type="submit"  value="Registration"/>
                    </form>
                </div>
                <p class="error"> <c:out value="${error}"/> </p>
            </div>
        </main>
    </body>
</html>
