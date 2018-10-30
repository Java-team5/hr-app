<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<style>
    <%@include file="../styles/item.css"%>
</style>
<body>
<div class="item-account-view">
    <h2><spring:message code="user.account" /></h2>
    <table class="item-table">
        <tr>
            <th>id</th>
            <td>${user.id}</td>
        </tr>
        <tr>
            <th><spring:message code="user.email" /></th>
            <td>${user.email}</td>
        </tr>
        <tr>
            <th><spring:message code="user.password" /></th>
            <td>${user.password}</td>
        </tr>
        <tr>
            <th><spring:message code="user.name" /></th>
            <td>${user.name}</td>
        </tr>
        <tr>
            <th><spring:message code="user.surname" /></th>
            <td>${user.surname}</td>
        </tr>
        <tr>
            <th><spring:message code="user.userState" /></th>
            <td>${user.userState}</td>
        </tr>
        <tr>
            <th><spring:message code="user.isAdmin"/></th>
            <td>${user.isAdmin}</td>
        </tr>
    </table>
    <a class="item-sort-button item-edit-button-a" style="margin: 15px" href="/user/view/1"><spring:message code="menu.return" /></a>
</div>
</body>
</html>
