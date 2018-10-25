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
        <a href="/feedback/view/1" class="item-type-link"><spring:message code="button.view"/></a>
        <a href="/feedback/add" class="item-type-link"><spring:message code="button.add"/></a>
    </div>
</div>
    <c:if test="${type eq 'view'}">
        <div class="item-view">
            <table class="item-table">
                <thead>
                    <tr>
                        <th><spring:message code="feedback.idInterview"/></th>
                        <th><spring:message code="feedback.idInterviewer"/></th>
                        <th><spring:message code="feedback.feedbackState"/></th>
                        <th><spring:message code="feedback.reason"/></th>
                    </tr>
                </thead>
            <c:forEach var="feedback" items="${feedback}">
                <tr class="user-item">
                    <td>${feedback.idInterview}</td>
                    <td>${feedback.idInterviewer}</td>
                    <td>${feedback.feedbackState}</td>
                    <td>${feedback.reason}</td>
                </tr>
            </c:forEach>
            </table>
            <div class="item-pages">
                <c:forEach var="pages" items="${pages}">
                    <a class="item-page-link" href="/feedback/view/${pages}">${pages}</a>
                </c:forEach>
            </div>
        </div>
    </c:if>
    <c:if test="${type eq 'add'}">
        <div class="item-add">
            <form class="item-add-form" method="post" action="/feedback/add">
                <h1><spring:message code="feedback.addFeedback"/></h1>
                <div class="question">
                    <input class="item-add-form-input" type="text" required />
                    <label class="item-add-form-label"><spring:message code="feedback.idInterview"/></label>
                </div>
                <div class="question">
                    <input class="item-add-form-input" type="text" required/>
                    <label class="item-add-form-label"><spring:message code="feedback.idInterviewer"/></label>
                </div>
                <div class="question">
                    <input class="item-add-form-input" type="text" required/>
                    <label class="item-add-form-label"><spring:message code="feedback.feedbackState"/></label>
                </div>
                <div class="question">
                    <input class="item-add-form-input" type="text" required/>
                    <label class="item-add-form-label"><spring:message code="feedback.reason"/></label>
                </div>
                <button type="submit">Add</button>
            </form>

        </div>
    </c:if>
</body>
</html>