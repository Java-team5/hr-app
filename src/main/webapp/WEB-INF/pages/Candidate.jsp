<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head></head>
<style>
    <%@include file="../styles/candidate.css"%>
</style>
<body>
<div class="candidate">
    <div class="candidate-type">
        <a href="?type=view" class="candidate-type-link">View</a>
        <a href="?type=add" class="candidate-type-link">Add</a>
    </div>
</div>
<div>
    <c:if test="${type eq 'view'}">
        <div class="candidate-view">
            <table class="candidate-table">
                <thead>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>surname</th>
                    <th>birthday</th>
                    <th>salary</th>
                </tr>
                </thead>
                <c:forEach var="candidateItems" items="${candidates}" varStatus="status">
                    <tr class="candidate-item">
                        <td>1</td>
                        <td>2</td>
                        <td>3</td>
                        <td>4</td>
                        <td>5</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
    <c:if test="${type eq 'add'}">
        <div class="candidate-add">
            <form class="candidate-add-form" method="post" action="/candidateAdd">
                <label>name</label>
                <input>
                <label>surname</label>
                <input>
                <label>birthday</label>
                <input>
                <label>salary</label>
                <input>
                <button type="submit">submit</button>
            </form>
        </div>
    </c:if>
</div>

</body>
</html>
