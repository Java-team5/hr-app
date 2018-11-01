<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="item-account-view">
    <table class="item-table">
        <tr>
            <th><spring:message code="feedback.idInterview"/></th>
            <td>${feedback.idInterview}</td>
        </tr>
        <tr>
            <th><spring:message code="feedback.idInterviewer"/></th>
            <td>${feedback.idInterviewer}</td>
        </tr>
        <tr>
            <th><spring:message code="feedback.feedbackState"/></th>
            <td>${feedback.feedbackState}</td>
        </tr>
        <tr>
            <th><spring:message code="feedback.reason"/></th>
            <td>${feedback.reason}</td>
        </tr>
    </table>
    <a class="item-sort-button item-edit-button-a" style="margin: 15px" href="/feedback/view/1"><spring:message
            code="menu.return"/></a>
</div>

