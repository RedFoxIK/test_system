<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="main/head.jsp"%>
    <title>My profile</title>
    <style>
        .profile_button {
            display: none;
        }
    </style>
</head>
<body>
    <%@ include file="main/header.jsp"%>
    <div class="main_info">
        <div class="image">
            <img src="/img/user.png">
        </div>
        <div class="name_surname">
            <h2><c:out value="${user.name}"/></h2>
            <h2><c:out value="${user.surname}"/></h2>
        </div>
    </div>
    <div class="other_info" method="post">
        <form method="post">
            <input type="text" value="${user.email}">
            <input type="submit" value="change" name="email">
        </form>
        <form method="post">
            <input type="password" value="${user.password}">
            <input type="submit" value="change" name="password">
        </form>
    </div>
    <div>
        <form method="post" action="/testing_system/">
            <input type="submit" value="On main page">
        </form>
    </div>

</body>
</html>
