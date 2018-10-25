<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head></head>
<style>
    <%@include file="../styles/item.css"%>
</style>
<body>
<div class="item">
    <div class="item-type">
        <a href="/skill/view/1" class="item-type-link"><spring:message code="button.view"/></a>
        <a href="/skill/add" class="item-type-link"><spring:message code="button.add"/></a>
    </div>
</div>
<c:if test="${type eq 'view'}">
    <div class="item-view">
        <table class="item-table">
            <thead>
            <tr>
                <th>id</th>
                <th><spring:message code="skill"/></th>
            </tr>
            </thead>
            <c:forEach var="skill" items="${skills}">
                <tr class="user-item">
                    <td>${skill.id}</td>
                    <td>${skill.skill}</td>
                </tr>
            </c:forEach>
        </table>
        <div class="item-pages">
            <c:forEach var="pages" items="${pages}">
                <a class="item-page-link" href="/skill/view/${pages}">${pages}</a>
            </c:forEach>
        </div>
    </div>
</c:if>
<c:if test="${type eq 'add'}">
    <div class="item-add">
        <form class="item-add-form" method="post" action="/skill/add">
            <h1><spring:message code="skill.addSkill"/></h1>
            <div class="question">
                <input class="item-add-form-input" type="text" required/>
                <label class="item-add-form-label"><spring:message code="skill"/></label>
            </div>
            <button type="submit"><spring:message code="button.add"/></button>
        </form>

    </div>
</c:if>
</body>
</html>