<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p><spring:message code="menu.skill"/></p>

<table border="2" width="90%" cellpadding="2">
    <tr>
        <th>Id</th>
        <th><spring:message code="menu.skill"/></th>
    </tr>
    <c:forEach var="skill" items="${skill}">
        <tr>
            <td>${skill.id}</td>
            <td>${skill.skill}</td>
        </tr>
    </c:forEach>
</table>
<br/>
<c:forEach var="pages" items="${pages}">
    <tr>
        <td><a href="/skill/${pages}">${pages}</a></td>
    </tr>
</c:forEach>

<select>
    <option><spring:message code="sorting.withoutSorting"/></option>
    <option>ID</option>
    <option><spring:message code="menu.skill"/></option>
</select>
