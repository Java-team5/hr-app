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
        <a href="/user/view/1" class="user-type-link">View</a>
        <a href="/user/add" class="user-type-link">Add</a>
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
            <c:forEach var="user" items="${user}">
                <tr class="user-item">
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.userState}</td>
                    <td>${user.isAdmin}</td>
                </tr>
            </c:forEach>
            </table>
            <div class="user-pages">
            <c:forEach var="pages" items="${pages}">
                <a class="user-page-link" href="/user/view/${pages}">${pages}</a>
            </c:forEach>
            </div>
        </div>
    </c:if>
    <c:if test="${type eq 'add'}">
        <div class="user-add">
            <form class="user-add-form" method="post" action="/userAdd">
                <h1>Adding a new user</h1>
                <div class="question">
                    <input class="user-add-form-input" type="text" required />
                    <label class="user-add-form-label">Email</label>
                </div>
                <div class="question">
                    <input class="user-add-form-input" type="text" required/>
                    <label class="user-add-form-label">Password</label>
                </div>
                <div class="question">
                    <input class="user-add-form-input" type="text" required/>
                    <label class="user-add-form-label">Name</label>
                </div>
                <div class="question">
                    <input class="user-add-form-input" type="text" required/>
                    <label class="user-add-form-label">Surname</label>
                </div>
                <div class="question">
                    <input class="user-add-form-input" type="text" required/>
                    <label class="user-add-form-label">userState</label>
                </div>
                <div class="question">
                    <input class="user-add-form-input" type="text" required/>
                    <label class="user-add-form-label">isAdmin</label>
                </div>
                <button type="submit">Add</button>
            </form>

        </div>
    </c:if>
</body>
</html>