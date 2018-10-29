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
                    <th><spring:message code="candidate.id"/></th>
                    <th><spring:message code="candidate.name"/></th>
                    <th><spring:message code="candidate.surname"/></th>
                    <th><spring:message code="candidate.birthday"/></th>
                    <th><spring:message code="candidate.salary"/></th>
                </tr>
                </thead>
                <c:forEach var="candidate" items="${candidate}">
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
            <div class="item-sort">
                <form name='sort'>
                    <spring:message code="sort.sortby"/>
                    <select name='sortBy'>
                        <option value="none"><spring:message code="sort.none"/></option>
                        <option value='name'><spring:message code="sort.byname"/></option>
                        <option value='surname'><spring:message code="sort.bysurname"/></option>
                    </select>
                    <button type='submit'><spring:message code="sort"/></button>
                </form>
            </div>
        </div>
    </c:if>
    <c:if test="${type eq 'add'}">
        <div class="item-add">
            <form class="item-add-form" name="newCandidate" method="Post" action="/candidate/add">
                <h1><spring:message code="candidate.addCandidateTitle"/></h1>
                <div class="question">
                    <input class="item-add-form-input" name="name" type="text" required />
                    <label class="item-add-form-label"><spring:message code="candidate.name"/></label>
                </div>
                <div class="question">
                    <input class="item-add-form-input" name="surname" type="text" required/>
                    <label class="item-add-form-label"><spring:message code="candidate.surname"/></label>
                </div>
                <div class="question">
                    <input class="item-add-form-input" name="birthday" type="date" required/>
                    <label class="item-add-form-label"><spring:message code="candidate.birthday"/></label>
                </div>
                <div class="question">
                    <input class="item-add-form-input" name="salary" type="text" required/>
                    <label class="item-add-form-label"><spring:message code="candidate.salary"/></label>
                </div>
                <button type="submit"><spring:message code="candidate.addCandidate"/></button>
            </form>

        </div>
    </c:if>
</div>

</body>
</html>