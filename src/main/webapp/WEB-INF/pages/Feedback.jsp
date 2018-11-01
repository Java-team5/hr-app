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
                        <th><spring:message code="menu.edit"/></th>
                        <th><spring:message code="button.view"/></th>
                    </tr>
                </thead>
            <c:forEach var="feedback" items="${feedback}">
                <tr class="user-item">
                    <td>${feedback.idInterview}</td>
                    <td>${feedback.idInterviewer}</td>
                    <td>${feedback.feedbackState}</td>
                    <td>${feedback.reason}</td>
                    <td class="item-edit-button">
                        <a class="item-edit-button-a"
                           href="/feedback/edit/${feedback.idInterview}|${feedback.idInterviewer}">
                            <h3>
                                <spring:message code="user.edit"/>
                            </h3>
                        </a>
                    </td>
                    <td class="item-edit-button">
                        <a class="item-edit-button-a"
                           href="/feedback/account/${feedback.idInterview}|${feedback.idInterviewer}">
                            <h3><spring:message code="user.view"/></h3>
                        </a>
                    </td>
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
            <form class="item-add-form" name="newFeedback" method="Post" action="/feedback/add">
                <h1><spring:message code="feedback.addFeedback"/></h1>
                <div class="question">
                    <input class="item-add-form-input" name="idInterview" type="text" required/>
                    <label class="item-add-form-label"><spring:message code="feedback.idInterview"/></label>
                </div>
                <div class="question">
                    <input class="item-add-form-input" name="idInterviewer" type="text" required/>
                    <label class="item-add-form-label"><spring:message code="feedback.idInterviewer"/></label>
                </div>
                <div class="question">
                    <input list="states" class="item-add-form-input" name="feedbackState" type="text" required>
                    <datalist id="states">
                        <option value="JobOffer">
                        <option value="NonJobOffer">
                    </datalist>
                    <label class="item-add-form-label"><spring:message code="feedback.feedbackState"/></label>
                </div>
                <div class="question">
                    <input class="item-add-form-input" name="reason" type="text" required/>
                    <label class="item-add-form-label"><spring:message code="feedback.reason"/></label>
                </div>
                <button type="submit">Add</button>
            </form>

        </div>
    </c:if>
<c:if test="${type eq 'edit'}">
    <jsp:include page="FeedbackEditForm.jsp"/>
</c:if>
<c:if test="${type eq 'account'}">
    <jsp:include page="FeedbackView.jsp"/>
</c:if>
</body>
</html>