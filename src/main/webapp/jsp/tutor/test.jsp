<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored ="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title>${test.caption}</title>
</head>
<body>
    <%@ include file="../main/header.jsp"%>

    <h1> ${test.caption}</h1>
    <h2> ${test.size} questions </h2>

    <form method="get" id="form_delete_test" action="/testing_system/delete_question">
        <iput type="hidden" name="id_test" value="<c:out value="${test.id}"/>"/>
        <input type="submit" value="delete test" >
               <%--onclick="deleteTest()">--%>
    </form>
    <br>

    <form class="results"  method = "get" action="/testing_system/change_test_state">
        <input type="hidden" name="id_test" value="<c:out value="${test.id}"/>">

        <c:set var="activate" value="deactivate"/>
        <c:set var="disable" value=""/>
        <c:if test="${not test.activated}">
            <c:set var="activate" value="activate"/>
            <c:if test="${fn:length(test.questions) < test.size}">
                <c:set var="disabled" value="disabled"/>
            </c:if>
        </c:if>
        <input type="submit" value="<c:out value='${activate}'/>" <c:out value="${disabled}"/>>
    </form>

    <br>

    <form class="results"  method = "get" action="/testing_system/results_for_test">
        <input type="hidden" name="id_test" value="<c:out value="${test.id}"/>">
        <input type="submit" value="results">
    </form>

    <br>

    <c:forEach items="${test.questions}" var="question">
        <form method="post" action="/testing_system/delete_question">
        <div class="question">
            <h3> <c:out value="${question.text}"/> </h3>

            <c:set var="type" value="radio" scope="page" />
            <c:if test="${question.multChoice}">
                <c:set var="type" value="checkbox" scope="page" />
            </c:if>

            <c:forEach items="${question.answers}" var="answer">
                    <c:set var="checked" value="" scope="page"/>
                    <c:if test="${answer.right}">
                        <c:set var="checked" value="checked" scope="page"/>
                    </c:if>

                    <input type="<c:out value="${type}"/>" disabled="true" <c:out value="${checked}"/> ">
                    <span> <c:out value="${answer.text}"/> </span> <br>

            </c:forEach>

        </div>
            <input type="hidden" name="id_question" value="<c:out value="${question.id}"/>">
            <input type="hidden" name="id_test"  value="<c:out value="${test.id}"/>"/>
            <input type="submit" value="delete">
        </form>
        <hr>
    </c:forEach>
    <div>
        <form action="/testing_system/create_question" method="get">
            <input type="hidden" name="test" value="<c:out value="${test.id}"/>">
            <input type="submit" value="Add question">
        </form>
    </div>

    <form method="get" action="/testing_system/">
        <input type="submit" value="On main page" class="main_page">
    </form>
</body>
</html>
