<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="actionGetById" value="/company/getById" scope="request" />
<div class="form-detail">
<form:form action="${action}" method="post" modelAttribute="company" class="form-left">
        <h2>${pageTitle}</h2>
        <input type="hidden" name="modelType" value="Company"/>
        <form:hidden path="id" />
    <div class="form-content">
        <div class="form-group">
            <div class="form-row">
                <form:input path="name" placeholder="Name" required="true" class="input-text" />
            </div>
        </div>
        <div class="form-group">
            <div class="form-row">
                <form:input path="desc" placeholder="Description" required="true" class="input-text" />
            </div>
        </div>
        <div class="form-group">
            <div class="form-row">
                <form:input path="address" placeholder="Address" required="true" class="input-text" />
            </div>
        </div>
    </div>
        <div class="form-row-last">
            <input type="submit" class="button_submit register" value="Submit">
            <input type="button" class="button_revert register" style="display:none" value="Revert">
        </div>
</form:form>
    <div class="form-right" style="display:none">
    <h2>${pageTitle} History</h2>
        <div class="form-content">
        <div class="form-group">
            <div class="form-row">
                <input name="name" placeholder="Name" required="true" class="input-text" />
            </div>
        </div>
        <div class="form-group">
            <div class="form-row">
                <input name="desc" placeholder="Description" required="true" class="input-text" />
            </div>
        </div>
        <div class="form-group">
            <div class="form-row">
                <input name="address" placeholder="Address" required="true" class="input-text" />
            </div>
        </div>
        </div>
        <div class="form-row-last">
            <input type="submit" class="button_Reversion register" value="Reversion">
        </div>
    </div>
</div>

<c:if test="${not empty company.id}">
<div class="page-content">
    <div class="form-v10-content">
        <div class="form-detail">
            <div class="form-left">
                <jsp:include page="listAuditLog.jsp" />
            </div>
        </div>
    </div>
</div>
</c:if>

<script>
    $(".auditLogClick").on("click",function(event){
        event.preventDefault();
        $(".form-right").show();
        $(".form-right input").removeClass("checkmark");
        $.getJSON($(this).attr("href"), function( data ) {
            var $form2 = $(".form-right");
            $(':input:not(:hidden)[name]', $form2).val(function() {
                return data.object[this.name];
            });
            checkForms($('.form-left'),$('.form-right'));
        });
    });

    $(".mongolog").on("click",function(event){
        event.preventDefault();
        $(".form-right").show();
        $(".form-right input").removeClass("checkmark");

        var data = JSON.parse($(this).html());

        var $form2 = $(".form-right");

        $(':input:not(:hidden)[name]', $form2).val(function() {

            return data[this.name];
        });
        checkForms($('.form-left'),$('.form-right'));
    });
</script>