<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head></head>
<style>
    <%@include file="../styles/user.css"%>
</style>
<body>
<div class="user">
    <div class="user-type">
        <a href="/feedback/view/1" class="user-type-link">View</a>
        <a href="/feedback/add" class="user-type-link">Add</a>
    </div>
</div>
    <c:if test="${type eq 'view'}">
        <div class="user-view">
            <table class="user-table">
                <thead>
                    <tr>
                        <th>idInterview</th>
                        <th>idInterviewer</th>
                        <th>State</th>
                        <th>Feedback</th>
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
            <div class="user-pages">
            <c:forEach var="pages" items="${pages}">
                <a class="user-page-link" href="/feedback/view/${pages}">${pages}</a>
            </c:forEach>
            </div>
        </div>
    </c:if>
    <c:if test="${type eq 'add'}">
        <div class="user-add">
            <form class="user-add-form" method="post" action="/feedbackAdd">
                <h1>Adding a new feedback</h1>
                <div class="question">
                    <input class="user-add-form-input" type="text" required />
                    <label class="user-add-form-label">idEnterview</label>
                </div>
                <div class="question">
                    <input class="user-add-form-input" type="text" required/>
                    <label class="user-add-form-label">idInterviewer</label>
                </div>
                <div class="question">
                    <input class="user-add-form-input" type="text" required/>
                    <label class="user-add-form-label">State</label>
                </div>
                <div class="question">
                    <input class="user-add-form-input" type="text" required/>
                    <label class="user-add-form-label">Feedback</label>
                </div>
                <button type="submit">Add</button>
            </form>

        </div>
    </c:if>
</body>
</html>