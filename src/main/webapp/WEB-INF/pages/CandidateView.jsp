<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<style>
    <%@include file="../styles/item.css"%>
</style>
<body>
<div class="item-account-view">
    <h2><spring:message code="candidate.account" /></h2>
    <table class="item-table">
        <tr>
            <th>id</th>
            <td>${candidate.id}</td>
        </tr>
        <tr>
            <th><spring:message code="candidate.name" /></th>
            <td>${candidate.name}</td>
        </tr>
        <tr>
            <th><spring:message code="candidate.surname" /></th>
            <td>${candidate.surname}</td>
        </tr>
        <tr>
            <th><spring:message code="candidate.birthday" /></th>
            <td>${candidate.birthday}</td>
        </tr>
        <tr>
            <th><spring:message code="candidate.salary" /></th>
            <td>${candidate.salary}</td>
        </tr>
    </table>
    <a class="item-sort-button item-edit-button-a" style="margin: 15px" href="/candidate/view/1"><spring:message code="menu.return" /></a>
</div>
</body>
</html>