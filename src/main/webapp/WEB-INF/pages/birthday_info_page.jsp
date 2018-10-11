<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="page.title"/></title>
</head>
<body>
    <header>
        <h1><spring:message code="info.author"/></h1>
        <a href="?lang=en">en</a>|<a href="?lang=ru">ru</a>
    </header>

    <h1><spring:message code="output.age"/>: ${age}</h1>
    <h1><spring:message code="output.dayBeforeBirthday"/>: ${dayBeforeBirthday}</h1>

</body>
</html>
