<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title>404</title>
</head>
<body>
<%@ include file="../main/header.jsp"%>
<main>
    <div class="content error_page center">
        <div class="image_error">
            <p>* <fmt:message key='page_not_found'/></p>
            <img src="${pageContext.request.contextPath}/img/404.png">
        </div>
        <form method="get" action="/testing_system/">
            <input type="submit" value="On main page" class="main_page">
        </form>
    </div>
    </div>
</main>

</body>
</html>
