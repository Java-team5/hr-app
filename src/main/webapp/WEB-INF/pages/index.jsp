<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html>
<head>
    <title><spring:message code="app.title"/></title>
</head>
<style>
    <%@include file="../styles/styles.css"%>
</style>
<body>
<header>
    <div class="intro">
        <spring:message code="team.name"/>
    </div>
    <div class="language">
        <a class="menu-item" href="?lang=en">en</a>
        <a class="menu-item" href="?lang=ru">ru</a>
    </div>
</header>
<div class="main">
    <div class="menu">
        <a class="menu-item" href="/user">
            <spring:message code="menu.user"/>
        </a>
        <a class="menu-item" href="/candidate">
            <spring:message code="menu.candidate"/>
        </a>
        <a class="menu-item" href="/interview">
            <spring:message code="menu.interview"/>
        </a>
        <a class="menu-item" href="/skill">
            <spring:message code="menu.skill"/>
        </a>
        <a class="menu-item" href="/feedback">
            <spring:message code="menu.feedback"/>
        </a>
        <a class="menu-item" href="/vacancy">
            <spring:message code="menu.vacancy"/>
        </a>
    </div>
    <div class="result">
        <c:if test = "${not empty entity}">
            <jsp:include page="${entity}.jsp" />
        </c:if>
    </div>
</div>
<footer>
    <span><spring:message code="info.version"/>: ${version}.</span>
</footer>
</body>
</html>