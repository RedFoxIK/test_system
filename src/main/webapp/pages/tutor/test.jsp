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
                <div class="test_text">
                    <span> <fmt:message key='test.tutor.caption'/>: </span> <input type="text" id="caption" name="caption"  value="${test.caption}"/> <br>
                    <span> <fmt:message key='test.tutor.description'/>: </span> <textarea id="description" rows="3" cols="30" name="description" value="${test.description}"></textarea> <br>
                    <span> <fmt:message key='test.tutor.size'/>: </span> <input type="number" name="test_size" max="100" min="1"  value="${test.size}"/>
                    <span> <fmt:message key='test.tutor.questions'/> </span> <br>
                    <span> <fmt:message key='tests.tutor.minutes'/> </span> <input type="number" name="minutes" max="100" min="1"  value="${test.minutes}"/>
                </div>
                <div class="test_change">
                    <input type="hidden" name="id_test" value="${test.id}"/>
                    <input class="change_test" type="submit" value="<fmt:message key='test.tutor.change'/>"/>
                </div>
            </form>
            <form class="activate center"  method = "get" action="/testing_system/tutor/change_test_state">
                <input type="hidden" name="id_test" value="<c:out value="${test.id}"/>">

                <fmt:message key="test.tutor.deactivate" var="activate" />
                <c:set var="disable" value=""/>
                <c:if test="${not test.activated}">
                    <fmt:message key="test.tutor.activate" var="activate" />
                    <c:if test="${fn:length(test.questions) < test.size}">
                        <c:set var="disabled" value="disabled"/>
                    </c:if>
                </c:if>
                <input class="activate_button" type="submit" value="<c:out value='${activate}'/>" <c:out value="${disabled}"/>>
            </form>
        </div>
        <div class="change_test_buttons">
            <form method="post" id="form_delete_test" action="/testing_system/tutor/delete_test">
                <input type="hidden" name="id_test" value="<c:out value="${test.id}"/>">
                <input class="delete_test" type="submit" value="<fmt:message key='test.tutor.delete_test'/>" >
            </form>
            <form class="results test_result"  method = "get" action="/testing_system/tutor/results_for_test">
                <input type="hidden" name="id_test" value="<c:out value="${test.id}"/>">
                <input type="submit" value="<fmt:message key='test.tutor.results'/>" class="button_results">
            </form>
        </div>

    </div>

    <c:forEach items="${test.questions}" var="question">
        <div class="content">
            <form method="post" action="/testing_system/tutor/delete_question">
                <div class="question tutor_question">
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
                <input type="submit" value="<fmt:message key='test.tutor.delete'/>" class="button_delete">
            </form>
            <hr>
        </div>

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
