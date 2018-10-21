<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head></head>
<style>
    <%@include file="../styles/user.css"%>
</style>
<body>
    <div class="user-type">
        <a href="?type=view" class="user-type-link">View</a>
        <a href="?type=add" class="user-type-link">Add</a>
    </div>
    <c:if test="${type eq 'view'}">
        <div class="user-view">Hi, i'm view</div>
    </c:if>
    <c:if test="${type eq 'add'}">
        <div class="user-view">Hi, i'm add</div>
    </c:if>
</body>
</html>