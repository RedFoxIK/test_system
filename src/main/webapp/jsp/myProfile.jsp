<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="main/head.jsp"%>
    <title>My profile</title>
    <style>
        .buttons form.profile_button {
            display: none;
        }
    </style>
</head>
<body>
    <%@ include file="main/header.jsp"%>
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
        <form method="post" action="/testing_system/update_email">
            <input type="text" value="${user.email}" name="email">
            <input type="submit" value="change">  <span class="error"> <c:out value="${email_exc}"/></span>
        </form>
        <form method="post" action="/testing_system/update_pass">
            <input type="password" value="${user.password}" name="password">
            <span class="error">
            <input type="submit" value="change">
        </form>
    </div>
    <div>
        <form method="get" action="/testing_system/">
            <input type="submit" value="On main page">
        </form>
    </div>

</body>
</html>
