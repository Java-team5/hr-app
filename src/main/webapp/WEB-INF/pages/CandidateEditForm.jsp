<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head></head>
<style>
    <%@include file="../styles/item.css"%>
</style>
<body>
<div class="item-add">
    <form:form modelAttribute="candidate" class="item-add-form"  method="post" action="/candidate/updateSaveCandidate">
        <div class="question">
            <form:hidden  path="id" />
            <form:input path="candidate" class="item-add-form-input"/>
            <label class="item-add-form-label"><spring:message code="menu.candidate"/></label>
        </div>
        <form:button type="submit"><spring:message code="menu.edit"/></form:button>
    </form:form>
</div>
</body>
</html>