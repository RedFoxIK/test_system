<%@ tag body-content="empty" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="date" required="true" type="java.time.LocalDateTime" %>

<c:catch var ="catchException">
    <fmt:parseDate value="${date}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both" />
</c:catch>
<c:if test = "${catchException != null}">
    <fmt:parseDate value="${date}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
</c:if>

<fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedDateTime}"/>


