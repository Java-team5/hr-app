<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<style>
    <%@include file="../../styles/item.css"%>
</style>
<body>
<script type="text/javascript">
    function changeValue(id, value){
        document.getElementById("id").value = value;
    }
</script>
<div class="item-add">
    <form class="item-add-form" name="editUser" method="Post" action="/vacancy/edit">
        <h1><spring:message code="vacancy.editVacancyTitle"/></h1>
        <input name="id" value="${user.id}" style="display: none"/>
        <div class="question">
            <input class="item-add-form-input" id="input1" name="idDeveloper" value="${vacancy.idDeveloper}" type="email" onchange="changeValue(this.id, this.value)" required />
        </div>
        <div class="question">
            <textarea class="item-add-form-input" name="position" wrap="soft" required>${vacancy.position}</textarea>
        </div>
        <div class="question">
            <input class="item-add-form-input" id="input3" name="salaryFrom" type="number" value="${vacancy.salaryFrom}" minlength="2" maxlength="20" onchange="changeValue(this.id, this.value)" required/>
        </div>
        <div class="question">
            <input class="item-add-form-input" id="input4" name="salaryTo" type="number" value="${vacancy.salaryTo}" minlength="2" maxlength="20" onchange="changeValue(this.id, this.value)" required/>
        </div>
        <div class="question">
            
        </div>
        <div class="question">
            <input class="item-add-form-input" id="input6" name="experiene" type="text" value="${user.surname}" minlength="2" maxlength="20" onchange="changeValue(this.id, this.value)" required/>
        </div>
        <button type="submit"><spring:message code="menu.saveEdit" /></button>
    </form>
</div>
</body>
</html>
