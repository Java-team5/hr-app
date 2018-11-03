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
                <c:forEach var="page" items="${pages}">
                    <a class="item-page-link" href="/vacancy/view/${page}">${page}</a>
                </c:forEach>
            </div>
            <div class="item-sort">
                <form name='sort'>
                    <spring:message code="sort.sortby"/>
                    <select class="item-sort-input" name='sortBy'>
                        <option value="none"><spring:message code="sort.none"/></option>
                        <option value="salaryFrom"><spring:message code="sort.salaryFrom"/></option>
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
            <%--@elvariable id="newVacancy" type="team5.models.Vacancy"--%>
            <form:form modelAttribute="newVacancy" class="item-add-form" method="post" action="/vacancy/add">
                <h1><spring:message code="vacancy.editVacancyTitle"/></h1>

                <div class="question">
                    <form:input path="idDeveloper" class="item-add-form-input"/>
                    <label class="item-add-form-label"><spring:message code="menu.vacancy.idDeveloper"/></label>
                </div>

                <div class="question">
                    <label class="item-add-form-label"><spring:message code="menu.vacancy.position"/></label>
                    <form:textarea path="position" wrap="soft" required="true"></form:textarea>
                </div>

                <div class="question">
                    <form:input class="item-add-form-input" path="salaryFrom" type="number" min="0" step="0.01"  minlength="1" maxlength="13" required="true"/>
                    <label class="item-add-form-label"><spring:message code="menu.vacancy.salaryFrom"/></label>
                </div>

                <div class="question">
                    <form:input class="item-add-form-input" path="salaryTo" type="number" min="0" step="0.01" minlength="0" maxlength="13"/>
                    <label class="item-add-form-label"><spring:message code="menu.vacancy.salaryTo"/></label>
                </div>

                <div>
                    <form:select path='vacancyState'>
                        <form:option value="active"><spring:message code="vacancy.state.active"/></form:option>
                        <form:option value="close"><spring:message code="vacancy.state.close"/></form:option>
                    </form:select>
                    <label class="item-add-form-label"><spring:message code="menu.vacancy.vacancyState"/></label>
                </div>

                <div class="question">
                    <form:input class="item-add-form-input" path="experienceYearsRequire" type="number" min="0" step="0.01" minlength="0" maxlength="5" />
                    <label class="item-add-form-label"><spring:message code="menu.vacancy.experienceYearsRequire"/></label>
                </div>

                <form:button type="submit"><spring:message code="menu.saveEdit" /></form:button>
            </form:form>
        </div>
    </c:if>
    <c:if test="${type eq 'edit'}">
        <jsp:include page="vacancy/VacancyEditForm.jsp" />
    </c:if>

</body>
</html>
