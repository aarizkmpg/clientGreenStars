<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="temp_details" style="display: none"></div>
<h2>Object Audit Logs</h2>
<c:if test="${not empty auditLogs}">
    <table class="table100">
        <c:if test="${not empty auditLogs[0]['class'].declaredMethods}">
            <tr>
                <th><strong>Version</strong></th>
                <c:forEach var="method" items="${auditLogs[0].object['class'].declaredFields}">
                    <c:catch>
                        <c:choose>
                        <c:when test="${method.name != 'attributes' && method.name != 'id' && method.name != 'company'}">
                            <th><strong>${method.name}</strong></th>
                        </c:when>
                        </c:choose>
                    </c:catch>
                </c:forEach>
                <th><strong>create by</strong></th>
                <th><strong>create dt (Audit Log)</strong></th>
                <th><strong>update by</strong></th>
                <th><strong>update dt</strong></th>
            </tr>
        </c:if>
        <c:forEach items="${auditLogs}" var="auditLog">
            <c:set var="object" value="${auditLog.object}" />
            <c:if test="${not empty object['class'].declaredFields}">
                <tr>
                    <td><a href="${getReversionUrl}?vid=${auditLog.revision}" class="auditLogClick" attr="${auditLog.revision}">${auditLog.revision}</a></td>
                    <c:forEach var="field" items="${object['class'].declaredFields}">
                        <c:catch>
                        <c:choose>
                            <c:when test="${field.name == 'id'}">

                            </c:when>
                            <c:when test="${field.name == 'company'}">

                            </c:when>
                            <c:when test="${field.name == 'attributes'}">

                            </c:when>
                            <c:otherwise>
                                <td>${object[field.name]}</td>
                            </c:otherwise>
                        </c:choose>
                        </c:catch>
                    </c:forEach>
                    <td>${object.createBy}</td>
                    <td>${object.createDt}</td>
                    <td>${object.lastupdBy}</td>
                    <td>${object.lastupdDt}</td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
    <table class="table100">
        <tr><td><h2>Audit logs from MongoDB, stored as JSON format, can be parse into other format to display</h2></td></tr>
        <c:forEach items="${mongoLogs}" var="mLog">
            <tr><td><a href="#" class="mongolog">${mLog}</a></td></tr>
        </c:forEach>

    </table>
    <table class="table100" style="text-align: center">
        <tr>
            <td><a class="button_submit" href="${actionPage}">First Page</a></td>
            <td><a class="button_submit"  href="${actionPage}/${pageIndex+1}">Next Page</a></td>
        </tr>
    </table>
</c:if>
<script>
    $(".temp_details").html($(".form-left .form-content").html());

    $(".button_Reversion").on("click",function(){
        copyForms($('.form-right') ,$('.form-left'));
        $(".button_revert").show();
    });

    $(".button_revert").on("click",function(){
        copyForms($('.temp_details') ,$('.form-left'));
        $(".button_revert").hide();
    });

    function copyForms($form1 , $form2) {
        $(':input:not(:hidden)[name]', $form2).val(function() {
            return $(':input[name="' + this.name + '"]', $form1).val();
        });
    }
    function checkForms($form1 , $form2){
        $(':input:not(:hidden)[name]', $form2).addClass(function() {
            console.log($(this).val() + "     " +$(':input[name="' + this.name + '"]', $form1).val());
            return ($(this).val() == $(':input[name="' + this.name + '"]', $form1).val())?"" : "checkmark";
        });
    }
</script>