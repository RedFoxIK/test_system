<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@ include file="jsp/main/head.jsp"%>
        <title>Login</title>
    </head>
    <body>
        <%@ include file="jsp/main/header.jsp"%>
        <main>
            <div class="content">
                <div class="log_in">
                    <form class="form_login" action="/testing_system/tests" method="post">
                        <p class="text_log_in">PLEASE, LOG IN!</p>
                        <label class="label" for="login"><p>Login:</p></label>
                        <input type="text" id="login" name="login" class="login" />
                        <br>
                        <label class="label" for="password"><p>Password:</p></label>
                        <input type="password" id="password" name="password" class="password" />
                        <br>
                        <input type="submit" class="submit" value="Enter"/>
                    </form>
                    <form class="registration" action="/testing_system/registration" method="get">
                        <input type="submit"  value="Registration"/>
                    </form>
                </div>
                <p class="error"> <c:out value="${error}"/> </p>
            </div>
        </main>
    </body>
</html>
