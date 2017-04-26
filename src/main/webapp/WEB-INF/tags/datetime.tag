<%@ tag body-content="empty" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="date" required="true" type="java.time.LocalDateTime" %>

<fmt:parseDate value="${date}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both" />
<fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedDateTime}"/>


