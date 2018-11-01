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
        <a href="/vacancy/view/1" class="item-type-link"><spring:message code="button.view"/></a>
        <a href="/vacancy/add" class="item-type-link"><spring:message code="button.add"/></a>
    </div>
</div>
    <c:if test="${type eq 'view'}">
        <div class="item-view">
            <table class="item-table">
                <thead>
                <tr>
                    <th><spring:message code="menu.vacancy.id"/></th>
                    <th><spring:message code="menu.vacancy.idDeveloper"/></th>
                    <th><spring:message code="menu.vacancy.position"/></th>
                    <th><spring:message code="menu.vacancy.salaryFrom"/></th>
                    <th><spring:message code="menu.vacancy.salaryTo"/></th>
                    <th><spring:message code="menu.vacancy.vacancyState"/></th>
                    <th><spring:message code="menu.vacancy.experienceYearsRequire"/></th>
                </tr>
                </thead>
                <c:forEach var="vacancy" items="${vacancies}">
                    <tr>
                        <td>${vacancy.id}</td>
                        <td>${vacancy.idDeveloper}</td>
                        <td>${vacancy.position}</td>
                        <td>${vacancy.salaryFrom}</td>
                        <td>${vacancy.salaryTo}</td>
                        <td>${vacancy.vacancyState}</td>
                        <td>${vacancy.experienceYearsRequire}</td>
                    </tr>
                </c:forEach>
            </table>
            <div class="item-pages">
                <c:forEach var="pages" items="${pages}">
                    <a class="item-page-link" href="/vacancy/view/${pages}">${pages}</a>
                </c:forEach>
            </div>
            <div class="item-sort">
                <form name='sort'>
                    <spring:message code="sort.sortby"/>
                    <select class="item-sort-input" name='sortBy'>
                        <option value="none"><spring:message code="sort.none"/></option>
                        <option value='position'><spring:message code="sort.byposition"/></option>
                        <option value='experience'><spring:message code="sort.byexperience"/></option>
                    </select>
                    <button class="item-sort-button" type='submit'><spring:message code="sort"/></button>
                </form>
            </div>
        </div>
    </c:if>
    <c:if test="${type eq 'add'}">
        <div class="item-add">
            <form class="item-add-form" method="post" action="/vacancyadd">
                <label class="item-add-form-label"><spring:message code="menu.vacancy.idDeveloper"/></label>
                <input class="item-add-form-input">
                <label class="item-add-form-label"><spring:message code="menu.vacancy.position"/></label>
                <input class="item-add-form-input">
                <label class="item-add-form-label"><spring:message code="menu.vacancy.salaryFrom"/></label>
                <input class="item-add-form-input">
                <label class="item-add-form-label"><spring:message code="menu.vacancy.salaryTo"/></label>
                <input class="item-add-form-input">
                <label class="item-add-form-label"><spring:message code="menu.vacancy.vacancyState"/></label>
                <input class="item-add-form-input">
                <label class="item-add-form-label"><spring:message code="menu.vacancy.experienceYearsRequire"/></label>
                <input class="item-add-form-input">
                <button type="submit">submit</button>
            </form>
        </div>
    </c:if>

</body>
</html>
