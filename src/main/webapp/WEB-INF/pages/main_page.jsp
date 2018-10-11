<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title><spring:message code="page.title"/></title>
</head>
<body>
    <header>
        <h1><spring:message code="info.author"/></h1>
        <a href="?lang=en">en</a>|<a href="?lang=ru">ru</a>
    </header>
    <form:form modelAttribute="dateFromInput" method="post" action="/dateInfo">
        <form:input path="date"/>
        <form:button><spring:message code="page.btn.sendDate"/></form:button>
    </form:form>

    <footer>
        <span><spring:message code="info.version"/>: ${version}.</span>
    </footer>
</body>
</html>
