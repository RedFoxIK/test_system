<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="../main/head.jsp"%>
    <title>${test.caption}</title>
</head>
<body>
    <%@ include file="../main/header.jsp"%>
<main>
    <div class="content">
        <div class="test_data">
            <form id="test_data" method = "post" action="/testing_system/tutor/test_edit">
                <span><fmt:message key='test.tutor.caption'/>: </span> <input type="text" name="caption" value="${test.caption}"/> <br>
                <span> <fmt:message key='test.tutor.description'/>: </span> <input type="text" name="description" value="${test.description}"/> <br>
                <span> <fmt:message key='test.tutor.size'/>: </span> <input type="number" name="test_size" max="100" min="1"  value="${test.size}"/>
                <span> <fmt:message key='test.tutor.questions'/> </span> <br>
                <input type="hidden" name="id_test" value="${test.id}"/>
                <input type="submit" value="<fmt:message key='test.tutor.change'/>"/>
            </form>
        </div>
        <br> <br>
    <form method="post" id="form_delete_test" action="/testing_system/tutor/delete_test">
        <input type="hidden" name="id_test" value="<c:out value="${test.id}"/>">
        <input type="submit" value="<fmt:message key='test.tutor.delete_test'/>" >
    </form>
    <br>

    <form class="results"  method = "get" action="/testing_system/tutor/change_test_state">
        <input type="hidden" name="id_test" value="<c:out value="${test.id}"/>">

        <fmt:message key="test.tutor.deactivate" var="activate" />
        <c:set var="disable" value=""/>
        <c:if test="${not test.activated}">
            <fmt:message key="test.tutor.activate" var="activate" />
            <c:if test="${fn:length(test.questions) < test.size}">
                <c:set var="disabled" value="disabled"/>
            </c:if>
        </c:if>
        <input type="submit" value="<c:out value='${activate}'/>" <c:out value="${disabled}"/>>
    </form>

    <br>

    <form class="results test_result"  method = "get" action="/testing_system/tutor/results_for_test">
        <input type="hidden" name="id_test" value="<c:out value="${test.id}"/>">
        <input type="submit" value="<fmt:message key='test.tutor.results'/>" class="button_results">
    </form>

    <br>
    </div>

    <c:forEach items="${test.questions}" var="question">
    <div class="content">
        <form method="post" action="/testing_system/tutor/delete_question">
        <div class="question tutor_question">
            <h3><pre><c:out value="${question.text}"/></pre></h3>

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
                    <span><pre><c:out value="${answer.text}"/></pre></span> <br>
            </c:forEach>
        </div>
            <input type="hidden" name="id_question" value="<c:out value="${question.id}"/>">
            <input type="hidden" name="id_test"  value="<c:out value="${test.id}"/>"/>
            <input type="submit" value="<fmt:message key='test.tutor.delete'/>" class="button_delete">
        </form>
    </div>
        <hr>
    </c:forEach>
    <div class="content">

    <div>
        <form action="/testing_system/tutor/create_question" method="get">
            <input type="hidden" name="test" value="<c:out value="${test.id}"/>">
            <input type="submit" value="<fmt:message key='test.tutor.add_question'/>" class="add_question">
        </form>
    </div>

        <form method="get" action="/testing_system/" class="tutor_main">
            <input type="submit" value="<fmt:message key='general.main_page'/>" class="main_page">
        </form>
    </div>
</main>
</body>
</html>
