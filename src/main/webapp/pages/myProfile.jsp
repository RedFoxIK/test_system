<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <%@ include file="main/head.jsp"%>
    <title><fmt:message key='title.my_profile'/></title>
    <style>
        .buttons form.profile_button {
            display: none;
        }
    </style>
</head>
<body>
    <%@ include file="main/header.jsp"%>
    <div class="content center">
        <div class="main_info">
            <div class="profile_image">
                <img src="${pageContext.request.contextPath}/img/user.png">
            </div>
            <div class="name_surname">
                <h2><c:out value="${user.name}"/></h2>
                <h2><c:out value="${user.surname}"/></h2>
            </div>
        </div>
        <div class="other_info">
            <form method="post" action="/testing_system/user/update_email">
                <input type="text" value="${user.email}" name="email">
                <input type="submit" value="<fmt:message key='profile.button'/>">  <span class="error"> <c:out value="${email_exc}"/></span>
            </form>
            <form method="post" action="/testing_system/user/update_pass">
                <input type="password" value="${user.password}" name="password">
                <input type="submit" value="<fmt:message key='profile.button'/>">
            </form>
        </div>
        <div>
            <form method="get" action="/testing_system/">
                <input type="submit" value="<fmt:message key="general.main_page"/>" class="main_page">
            </form>
        </div>
    </div>
</body>
</html>
