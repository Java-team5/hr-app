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
        <a href="/user/view/1" class="user-type-link"><spring:message code="user.view"/></a>
        <a href="/user/add" class="user-type-link"><spring:message code="user.add"/></a>
    </div>
</div>
    <c:if test="${type eq 'view'}">
        <div class="user-view">
            <table class="user-table">
                <thead>
                    <tr>
                        <th>id</th>
                        <th><spring:message code="user.email"/></th>
                        <th><spring:message code="user.password"/></th>
                        <th><spring:message code="user.name"/></th>
                        <th><spring:message code="user.surname"/></th>
                        <th><spring:message code="user.userState"/></th>
                        <th><spring:message code="user.isAdmin"/></th>
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
            <div class="user-sort">
                <form name='sort'>
                    Sort by:
                    <select name='sortBy'>
                        <option value="none">none</option>
                        <option value='email'>email</option>
                        <option value='name'>name</option>
                        <option value='surname'>surname</option>
                    </select>
                    <button type='submit'>SORT</button>
                </form>
            </div>
        </div>
    </c:if>
    <c:if test="${type eq 'add'}">
        <div class="user-add">
            <form class="user-add-form" name="newUser" method="Post" action="/user/add">
                <h1><spring:message code="user.addUserTitle"/></h1>
                <div class="question">
                    <input class="user-add-form-input" name="email" type="text" required />
                    <label class="user-add-form-label"><spring:message code="user.email"/></label>
                </div>
                <div class="question">
                    <input class="user-add-form-input" name="password" type="text" required/>
                    <label class="user-add-form-label"><spring:message code="user.password"/></label>
                </div>
                <div class="question">
                    <input class="user-add-form-input" name="name" type="text" required/>
                    <label class="user-add-form-label"><spring:message code="user.name"/></label>
                </div>
                <div class="question">
                    <input class="user-add-form-input" name="surname" type="text" required/>
                    <label class="user-add-form-label"><spring:message code="user.surname"/></label>
                </div>
                <button type="submit"><spring:message code="user.addUser"/></button>
            </form>

        </div>
    </c:if>
</body>
</html>