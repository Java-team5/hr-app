<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head></head>
<style>
    <%@include file="../styles/item.css"%>
</style>
<body>
<div class="item">
    <div class="item-type">
        <a href="/skill/view/1" class="item-type-link"><spring:message code="menu.view"/></a>
        <a href="/skill/add" class="item-type-link"><spring:message code="menu.add"/></a>
    </div>
</div>
    <c:if test="${type eq 'view'}">
        <div class="item-view">
            <table class="item-table">
                <thead>
                <tr>
                    <th><a href="/skill/addSorting/id/">id</a></th>
                    <th><a href="/skill/addSorting/skill/"><spring:message code="menu.skill"/></a></th>
                    <th><spring:message code="menu.edit"/></th>
                    <th><spring:message code="menu.delete"/></th>
                </tr>
                </thead>
                <c:forEach var="skill" items="${skill}">
                    <tr class="item-item">
                        <td>${skill.id}</td>
                        <td>${skill.skill}</td>
                        <td><a href="/skill/updateSkill/${skill.id}"><spring:message code="menu.edit"/></a></td>
                        <td><a href="/skill/deleteSkill/${skill.id}"><spring:message code="menu.delete"/></a></td>
                    </tr>
                </c:forEach>
            </table>
            <div class="item-pages">
                <c:forEach var="pages" items="${pages}">
                    <a class="item-page-link" href="/skill/view/${pages}">${pages}</a>
                </c:forEach>
            </div>
        </div>
        <form:form modelAttribute="filterInput" class="item-add-form"  method="post" action="/skill/filter">
            <form:input path="value" onchange="this.submit();" class="item-add-form-input"/>
            <label class="item-add-form-label"><spring:message code="menu.find"/> <spring:message code="menu.skill"/></label>
        </form:form>

    </c:if>

    <c:if test="${type eq 'add'}">
        <div class="item-add">
            <form:form modelAttribute="addSkillForm" class="item-add-form"  method="post" action="/skill/addSkill">
                <div class="question">
                    <form:input path="skill" class="item-add-form-input"/>
                    <label class="item-add-form-label"><spring:message code="menu.skill"/></label>
                </div>
                <form:button type="submit"><spring:message code="menu.add"/></form:button>
            </form:form>
        </div>
    </c:if>


</body>
</html>