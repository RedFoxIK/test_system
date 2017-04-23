<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title>exception</title>
</head>
    <%@ include file="../main/header.jsp"%>
    <main>
        <div class="content center">
            <div class="image_position"><img src="${pageContext.request.contextPath}/img/oops.png" alt="" class="big_margin_top"></div>
            <p class="big_font_size">Something is wrong!</p>

            <form method="get" action="/testing_system/">
                <input type="button" value="On main page" class="main_page">
            </form>

        </div>
    </main>
</body>
</html>
