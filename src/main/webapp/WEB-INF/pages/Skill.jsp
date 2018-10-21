<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p><spring:message code="menu.skill"/></p>

<table border="2" width="90%" cellpadding="2">
    <tr>
        <th>Id</th>
        <th><spring:message code="menu.skill"/></th>
    </tr>
    <c:forEach var="skill" items="${list}">
        <tr>
            <td>${skill.id}</td>
            <td>${skill.skill}</td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="/skill/1">1</a>
<a href="/skill/2">2</a>
<a href="/skill/3">3</a>
