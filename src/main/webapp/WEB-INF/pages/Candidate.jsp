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
        <a href="/candidate/view/1" class="user-type-link">View</a>
        <a href="/candidate/add" class="user-type-link">Add</a>
    </div>
</div>
<div>
    <c:if test="${type eq 'view'}">
        <div class="user-view">
            <table class="user-table">
                <thead>
                <tr>
                    <th><spring:message code="menu.candidate.id"/></th>
                    <th><spring:message code="menu.candidate.name"/></th>
                    <th><spring:message code="menu.candidate.surname"/></th>
                    <th><spring:message code="menu.candidate.birthday"/></th>
                    <th><spring:message code="menu.candidate.salary"/></th>
                </tr>
                </thead>
                <c:forEach var="candidate" items="${candidates}">
                    <tr class="user-item">
                        <td>${candidate.id}</td>
                        <td>${candidate.name}</td>
                        <td>${candidate.surname}</td>
                        <td>${candidate.birthday}</td>
                        <td>${candidate.salary}</td>
                    </tr>
                </c:forEach>
            </table>
            <div class="user-pages">
                <c:forEach var="pages" items="${pages}">
                    <a class="user-page-link" href="/candidate/view/${pages}">${pages}</a>
                </c:forEach>
            </div>
        </div>
    </c:if>
    <c:if test="${type eq 'add'}">
        <div class="user-add">
            <form class="user-add-form" method="post" action="/candidateAdd">
                <label>name</label>
                <input>
                <label>surname</label>
                <input>
                <label>birthday</label>
                <input>
                <label>salary</label>
                <input>
                <button type="submit">submit</button>
            </form>
        </div>
    </c:if>
</div>

</body>
</html>
