<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <form class="item-add-form" name="editUser" method="Post" action="/vacancy/edit">
        <h1><spring:message code="vacancy.editVacancyTitle"/></h1>
        <input name="id" value="${vacancy.id}" style="display: none"/>
        <div class="question">
            <input class="item-add-form-input" id="input1" name="idDeveloper" value="${vacancy.idDeveloper}" type="number" min="0" onchange="changeValue(this.id, this.value)" required />
        </div>
        <div class="question">
            <textarea class="item-add-form-input" id="input2" name="position" wrap="soft" required>${vacancy.position}</textarea>
        </div>
        <div class="question">
            <input class="item-add-form-input" id="input3" name="salaryFrom" type="number" min="0" value="${vacancy.salaryFrom}" minlength="1" maxlength="13" onchange="changeValue(this.id, this.value)" required/>
        </div>
        <div class="question">
            <input class="item-add-form-input" id="input4" name="salaryTo" type="number" min="input3.value" value="${vacancy.salaryTo}" minlength="0" maxlength="13" onchange="changeValue(this.id, this.value)"/>
        </div>
        <div>
            <select id="input5" name='vacancyState' required>
                <option value="active"><spring:message code="vacancy.state.active"/></option>
                <option value="block"><spring:message code="vacancy.state.close"/></option>
            </select>
        </div>
        <div class="question">
            <input class="item-add-form-input" id="input6" name="experience" type="number" min="0" value="${vacancy.experienceYearsRequire}" minlength="0" maxlength="5" onchange="changeValue(this.id, this.value)"/>
        </div>
        <button type="submit"><spring:message code="menu.saveEdit" /></button>
    </form>
</div>
</body>
</html>
