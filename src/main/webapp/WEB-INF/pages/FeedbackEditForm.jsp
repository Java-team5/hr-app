<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script type="text/javascript">
    function changeValue(id, value) {
        document.getElementById("id").value = value;
    }
</script>
<div class="item-add">
    <form class="item-add-form" name="editFeedback" method="Post" action="/feedback/edit">
        <h1>Edit</h1>

        <div class="question">
            <input class="item-add-form-input" name="idInterview" value="${feedback.idInterview}" type="text"
                   onchange="changeValue(this.id, this.value)" required/>
            <label class="item-add-form-label"><spring:message code="feedback.idInterview"/></label>
        </div>


        <div class="question">
            <input class="item-add-form-input" name="idInterviewer" value="${feedback.idInterviewer}" type="text"
                   onchange="changeValue(this.id, this.value)" required/>
            <label class="item-add-form-label"><spring:message code="feedback.idInterviewer"/></label>
        </div>


        <div class="question">
            <input list="states" class="item-add-form-input" name="feedbackState" value="${feedback.feedbackState}"
                   onchange="changeValue(this.id, this.value)" type="text" required>
            <datalist id="states">
                <option value="JobOffer">
                <option value="NonJobOffer">
            </datalist>
            <label class="item-add-form-label"><spring:message code="feedback.feedbackState"/></label>
        </div>

        <div class="question">
            <input class="item-add-form-input" name="reason" value="${feedback.reason}" type="text"
                   onchange="changeValue(this.id, this.value)" required/>
            <label class="item-add-form-label"><spring:message code="feedback.reason"/></label>
        </div>

        <button type="submit"><spring:message code="menu.saveEdit"/></button>
    </form>
</div>
</html>
