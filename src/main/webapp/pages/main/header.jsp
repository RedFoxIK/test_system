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
            <form method="get" action="/testing_system/user/my_profile" class="profile_button">
                <input type="submit" value="<fmt:message key='general.profile'/>">
            </form>
            <form method="get" action="/testing_system/user/sign_out" class="logout_button">
                <input type="submit" value="<fmt:message key='general.sign_out'/>">
            </form>
        </div>
    </div>

</header>
