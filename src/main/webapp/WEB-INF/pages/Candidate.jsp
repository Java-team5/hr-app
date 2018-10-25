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
        <a href="/candidate/view/1" class="item-type-link"><spring:message code="button.view"/></a>
        <a href="/candidate/add" class="item-type-link"><spring:message code="button.add"/></a>
    </div>
</div>
<div>
    <c:if test="${type eq 'view'}">
        <div class="item-view">
            <table class="item-table">
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
                    <tr>
                        <td>${candidate.id}</td>
                        <td>${candidate.name}</td>
                        <td>${candidate.surname}</td>
                        <td>${candidate.birthday}</td>
                        <td>${candidate.salary}</td>
                    </tr>
                </c:forEach>
            </table>
            <div class="item-pages">
                <c:forEach var="pages" items="${pages}">
                    <a class="item-page-link" href="/candidate/view/${pages}">${pages}</a>
                </c:forEach>
            </div>
        </div>
    </c:if>
    <c:if test="${type eq 'add'}">
        <div class="item-add">
            <form class="item-add-form" method="post" action="/candidate/add">
                <label class="item-add-form-label"><spring:message code="menu.candidate.name"/></label>
                <input class="item-add-form-input">
                <label class="item-add-form-label"><spring:message code="menu.candidate.surname"/></label>
                <input class="item-add-form-input">
                <label class="item-add-form-label"><spring:message code="menu.candidate.birthday"/></label>
                <input class="item-add-form-input">
                <label class="item-add-form-label"><spring:message code="menu.candidate.salary"/></label>
                <input class="item-add-form-input">
                <button type="submit">submit</button>
            </form>
        </div>
    </c:if>
</div>

</body>
</html>
