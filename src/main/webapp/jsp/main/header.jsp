<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <div class="content">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/img/logo.jpg">
            <span>Test yourself!</span>
        </div>
        <div class="buttons">
            <form method="get" action="/testing_system/log_out" class="profile_button">
                <input type="submit" value="My profile">
            </form>
            <form method="button" action="/testing_system/log_out" class="profile_button">
                <input type="submit" value="Log out">
            </form>
        </div>
    </div>

</header>
