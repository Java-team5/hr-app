<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="app.title"/></title>
</head>
<body>
<header>
    <a href="?lang=en">en</a>|<a href="?lang=ru">ru</a>
</header>

<footer>
    <span><spring:message code="info.version"/>: ${version}.</span>
</footer>
</body>
</html>
