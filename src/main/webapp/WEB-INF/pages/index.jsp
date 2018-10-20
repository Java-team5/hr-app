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
        Team-5
    </div>
    <div class="language">
        <a href="?lang=en">en</a>|<a href="?lang=ru">ru</a>
    </div>
</header>
<div class="main">
    <div class="menu">
        <div class="menu-item">
            <a class="menu-item-link" href="/user">User</a>
        </div>
        <div class="menu-item">
            <a class="menu-item-link" href="/candidate">Candidate</a>
        </div>
        <div class="menu-item">
            <a class="menu-item-link" href="/interview">Interview</a>
        </div>
        <div class="menu-item">
            <a class="menu-item-link" href="/skill">Skill</a>
        </div>
        <div class="menu-item">
            <a class="menu-item-link" href="/feedback">Feedback</a>
        </div>
        <div class="menu-item">
            <a class="menu-item-link" href="/vacancy">Vacancy</a>
        </div>
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