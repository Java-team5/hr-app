<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head></head>
<style>
    <%@include file="../styles/skill.css"%>
</style>
<body>
<div class="skill">
    <div class="skill-type">
        <a href="/skill/view/1" class="skill-type-link"><spring:message code="menu.view"/></a>
        <a href="/skill/add" class="skill-type-link"><spring:message code="menu.add"/></a>
    </div>
</div>
    <c:if test="${type eq 'view'}">
        <div class="skill-view">
            <table class="skill-table">
                <thead>
                <tr>
                    <th><a href="/skill/addSorting/id/">id</a></th>
                    <th><a href="/skill/addSorting/skill/"><spring:message code="menu.skill"/></a></th>
                    <th><spring:message code="menu.edit"/></th>
                    <th><spring:message code="menu.delete"/></th>
                </tr>
                </thead>
                <c:forEach var="skill" items="${skill}">
                    <tr class="skill-item">
                        <td>${skill.id}</td>
                        <td>${skill.skill}</td>
                        <td><a href="/skill/updateSkill/${skill.id}">Edit</a></td>
                        <td><a href="/skill/deleteSkill/${skill.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <div class="skill-pages">
                <c:forEach var="pages" items="${pages}">
                    <a class="skill-page-link" href="/skill/view/${pages}">${pages}</a>
                </c:forEach>
            </div>
        </div>
    </c:if>

    <c:if test="${type eq 'add'}">
        <div class="skill-add">
            <form:form modelAttribute="addSkillForm" class="skill-add-form"  method="post" action="/skill/addSkill">
                <div class="question">
                    <form:input path="skill" class="skill-add-form-input"/>
                    <label class="skill-add-form-label"><spring:message code="menu.skill"/></label>
                </div>
                <form:button type="submit"><spring:message code="menu.add"/></form:button>
            </form:form>
        </div>
    </c:if>


</body>
</html>