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
    <form class="item-add-form" name="editCandidate" method="Post" action="/candidate/edit">
        <h1><spring:message code="candidate.editCandidateTitle"/></h1>
        <input name="id" value="${candidate.id}" style="display: none"/>
        <div class="question">
            <input class="item-add-form-input" id="input1" name="name" type="text" value="${candidate.name}" onchange="changeValue(this.id, this.value)" required />
        </div>
        <div class="question">
            <input class="item-add-form-input" id="input2" name="surname" type="text" value="${candidate.surname}" onchange="changeValue(this.id, this.value)" required/>
        </div>
        <div class="question">
            <input class="item-add-form-input" id="input3" name="birthday" type="date" value="${candidate.birthday}" onchange="changeValue(this.id, this.value)" required/>
        </div>
        <div class="question">
            <input class="item-add-form-input" id="input4" name="salary" type="text" value="${candidate.salary}" onchange="changeValue(this.id, this.value)" required/>
        </div>
        <button type="submit"><spring:message code="menu.saveEdit" /></button>
    </form>
</div>
</body>
</html>