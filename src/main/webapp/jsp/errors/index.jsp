<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@ include file="../main/head.jsp"%>
        <title>Login</title>
    </head>
    <body>
        <%@ include file="../main/header.jsp"%>
        <main>
            <div class="content">
                <div class="log_in">
                    <form class="form_login" action="/Servlet/tests">
                        <p class="text_log_in">PLEASE, LOG IN!</p>
                        <label class="label" for="login"><p>Login:</p></label>
                        <input type="text" id="login" class="login" />
                        <br>
                        <label class="label" for="password"><p>Password:</p></label>
                        <input type="password" id="password" class="password" />
                        <br>
                        <input type="submit" class="submit" value="Enter" method="post"/>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>
