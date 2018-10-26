<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<style>
    <%@include file="../styles/item.css"%>
</style>
<body>
<script type="text/javascript">
function changeValue(id, value){
    document.getElementById("id").value = value;
}
</script>
<div class="item-add">
    <form class="item-add-form" name="editUser" method="Post" action="/user/edit">
        <h1><spring:message code="user.editUserTitle"/></h1>
        <input name="id" value="${user.id}" style="display: none"/>
        <div class="question">
            <input class="item-add-form-input" id="input1" name="email" value="${user.email}" type="text" onchange="changeValue(this.id, this.value)" required />
        </div>
        <div class="question">
            <input class="item-add-form-input" id="input2" name="password" value="${user.password}" type="text" onchange="changeValue(this.id, this.value)" required/>
        </div>
        <div class="question">
            <input class="item-add-form-input" id="input3" name="name" type="text" value="${user.name}" onchange="changeValue(this.id, this.value)" required/>
        </div>
        <div class="question">
            <input class="item-add-form-input" id="input4" name="surname" type="text" value="${user.surname}" onchange="changeValue(this.id, this.value)" required/>
        </div>
        <button type="submit"><spring:message code="menu.saveEdit" /></button>
    </form>
</div>
</body>
</html>
