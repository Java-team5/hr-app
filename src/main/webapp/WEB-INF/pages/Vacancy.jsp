<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head></head>
<style>
    <%@include file="../styles/user.css"%>
</style>
<body>
<div class="user">
    <div class="user-type">
        <a href="/vacancy/view/1" class="user-type-link">View</a>
        <a href="/vacancy/add" class="user-type-link">Add</a>
    </div>
</div>
<div>
    <c:if test="${type eq 'view'}">
        <div class="user-view">
            <table class="user-table">
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
                    <tr class="user-item">
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
            <div class="user-pages">
                <c:forEach var="pages" items="${pages}">
                    <a class="user-page-link" href="/vacancy/view/${pages}">${pages}</a>
                </c:forEach>
            </div>
        </div>
    </c:if>
    <c:if test="${type eq 'add'}">
        <div class="user-add">
            <form class="user-add-form" method="post" action="/vacancyAdd">
                <label>idDeveloper</label>
                <input>
                <label>Position</label>
                <input>
                <label>Salary From</label>
                <input>
                <label>Salary To</label>
                <input>
                <label>State</label>
                <input>
                <label>Experience Years Require</label>
                <input>

                <button type="submit">submit</button>
            </form>
        </div>
    </c:if>
</div>

</body>
</html>
