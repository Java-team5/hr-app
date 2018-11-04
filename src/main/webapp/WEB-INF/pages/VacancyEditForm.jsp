<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<style>
    <%@include file="../styles/item.css"%>
</style>
<body>
<div class="item-add">
    <%--@elvariable id="vacancy" type="team5.models.Vacancy"--%>
    <form:form modelAttribute="vacancy" class="item-add-form" method="post" action="/vacancy/${type}">
        <h1><spring:message code="vacancy.editVacancyTitle"/></h1>
        <form:input path="id" type="number" cssStyle="display: none"/>
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
                <form:option value="Active"><spring:message code="vacancy.state.active"/></form:option>
                <form:option value="Closed"><spring:message code="vacancy.state.close"/></form:option>
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
</body>
</html>
