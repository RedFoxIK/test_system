<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title><fmt:message key='title.tests'/></title>
</head>
<body>
    <%@ include file="../main/header.jsp"%>
    <main>
        <div class="info">
            <p>
                <fmt:message key='tests.tutor.title'/>
            </p>
            </div>
            <div class="tests">
                <c:forEach items="${tests}" var="test">

                    <c:set var="not_activated" value=""/>
                    <c:if test="${not test.activated}">
                        <c:set var="not_activated" value="not_activated"/>
                    </c:if>

                    <div class="test ${not_activated}" id="<c:out value='${test.id}'/>" onclick="showTest(this.id)">
                        <p> <c:out value="${test.caption}"/> </p>
                        <div class="author">
                            <c:out value="${test.author.name}"/>
                            <c:out value="${test.author.surname}"/>
                        </div>
                    </div>
                </c:forEach>
                <br>
                <input type="button" value="<fmt:message key='tests.tutor.add_test'/>" onclick="showDiv()" id="add_button">

                <div id="new_test">
                    <h2><fmt:message key='tests.tutor.new_test'/></h2>
                    <form method="post" action="/testing_system/tutor/add_test">
                        <span><fmt:message key='tests.tutor.caption'/>: </span><input type="text" name="caption"> <br>
                        <span><fmt:message key='tests.tutor.description'/>: </span><input type="text" name="description"> <br>
                        <span><fmt:message key='tests.tutor.questions'/>: </span> <input type="number" name="size" min="1" max="100"> <br>
                        <input type="reset" value="<fmt:message key='tests.tutor.cancel'/>" onclick="hideDiv()">
                        <input type="submit" value="<fmt:message key='tests.tutor.add'/>">
                    </form>
                </div>
            </div>


        <form id="hidden_form" method="get" action="/testing_system/tutor/test">
            <input type="hidden" name="id_test"  value="<c:out value="${test.id}"/>" id="id_test"/>
        </form>
    </main>
</body>
</html>


