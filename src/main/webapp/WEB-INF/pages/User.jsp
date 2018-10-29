<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head></head>
<style>
    <%@include file="../styles/item.css"%>
</style>
<body>
<div class="item">
    <div class="item-type">
        <a href="/user/view/1" class="item-type-link"><spring:message code="button.view"/></a>
        <a href="/user/add" class="item-type-link"><spring:message code="button.add"/></a>
    </div>
</div>
    <c:if test="${type eq 'view'}">
        <div class="item-view">
            <table class="item-table">
                <thead>
                    <tr>
                        <th>id</th>
                        <th><spring:message code="user.email"/></th>
                        <th><spring:message code="user.password"/></th>
                        <th><spring:message code="user.name"/></th>
                        <th><spring:message code="user.surname"/></th>
                        <th><spring:message code="user.userState"/></th>
                        <th><spring:message code="user.isAdmin"/></th>
                        <th><spring:message code="menu.edit" /></th>
                        <th><spring:message code="button.view" /></th>
                    </tr>
                </thead>
                <tr>
                    <form:form modelAttribute="filterInput"  method="post" action="/user/filter?sort=${sort}">
                        <td class="item-filter">filter:</td>
                        <td>
                            <form:input path="email" onchange="this.submit();"/>
                        </td>
                        <td>-</td>
                        <td>
                            <form:input path="name" onchange="this.submit();"/>
                        </td>
                        <td>
                            <form:input path="surname" onchange="this.submit();"/>
                        </td>
                        <td>-</td><td>-</td><td></td>
                        <td>
                            <input type="submit" value="Submit"/>
                        </td>
                    </form:form>
                </tr>
            <c:forEach var="user" items="${user}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.userState}</td>
                    <td>${user.isAdmin}</td>
                    <td class="item-edit-button">
                        <a class="item-edit-button-a" href="/user/edit/${user.id}">
                            <h3>
                                <spring:message code="user.edit" />
                            </h3>
                        </a>
                    </td>
                    <td class="item-edit-button">
                        <a class="item-edit-button-a" href="/user/account/${user.id}">
                            <h3>view</h3>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </table>
            <div class="item-pages">
            <c:forEach var="pages" items="${pages}">
                <a class="item-page-link" href="/user/view/${pages}?sort=${sort}">${pages}</a>
            </c:forEach>
            </div>
            <div class="item-sort">
                <form name='sort'>
                    <spring:message code="sort.sortby"/>
                    <select class="item-sort-input" name='sort'>
                        <option value="none"><spring:message code="sort.none"/></option>
                        <option value='email'><spring:message code="sort.byemail"/></option>
                        <option value='name'><spring:message code="sort.byname"/></option>
                        <option value='surname'><spring:message code="sort.bysurname"/></option>
                    </select>
                    <button class="item-sort-button" type='submit'><spring:message code="sort"/></button>
                </form>
            </div>
        </div>
    </c:if>
    <c:if test="${type eq 'add'}">
        <div class="item-add">
            <form class="item-add-form" name="newUser" method="Post" action="/user/add">
                <h1><spring:message code="user.addUserTitle"/></h1>
                <div class="question">
                    <input class="item-add-form-input" name="email" type="email" required />
                    <label class="item-add-form-label"><spring:message code="user.email"/></label>
                </div>
                <div class="question">
                    <input class="item-add-form-input" name="password" minlength="4" maxlength="40" type="password" required/>
                    <label class="item-add-form-label"><spring:message code="user.password"/></label>
                </div>
                <div class="question">
                    <input class="item-add-form-input" name="name" minlength="2" maxlength="20" type="text" required/>
                    <label class="item-add-form-label"><spring:message code="user.name"/></label>
                </div>
                <div class="question">
                    <input class="item-add-form-input" name="surname" minlength="2" maxlength="20" type="text" required/>
                    <label class="item-add-form-label"><spring:message code="user.surname"/></label>
                </div>
                <button type="submit"><spring:message code="user.addUser"/></button>
            </form>

        </div>
    </c:if>
    <c:if test="${type eq 'edit'}">
        <jsp:include page="UserEditForm.jsp" />
    </c:if>
    <c:if test="${type eq 'account'}">
        <jsp:include page="UserView.jsp" />
    </c:if>
</body>
</html>