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
        <a href="?type=view" class="user-type-link">View</a>
        <a href="?type=add" class="user-type-link">Add</a>
    </div>
</div>
    <c:if test="${type eq 'view'}">
        <div class="user-view">
            <table class="user-table">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>email</th>
                        <th>password</th>
                        <th>name</th>
                        <th>surname</th>
                        <th>userState</th>
                        <th>isAdmin</th>
                    </tr>
                </thead>
            <c:forEach var="userItems" items="${users}" varStatus="status">
                <tr class="user-item">
                    <td>1</td>
                    <td>2</td>
                    <td>3</td>
                    <td>4</td>
                    <td>5</td>
                    <td>6</td>
                    <td>7</td>
                </tr>
            </c:forEach>
            </table>
        </div>
    </c:if>
    <c:if test="${type eq 'add'}">
        <div class="user-add">
            <form class="user-add-form" method="post" action="/userAdd">
                <label>email</label>
                <input>
                <label>password</label>
                <input>
                <label>name</label>
                <input>
                <label>surname</label>
                <input>
                <label>userState</label>
                <input>
                <label>isAdmin</label>
                <input>
                <button type="submit">submit</button>
            </form>
        </div>
    </c:if>
</body>
</html>