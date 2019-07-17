<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Object List</h2>
<c:if test="${not empty objects}">
    <table class="table100">
        <c:if test="${not empty objects[0]['class'].declaredMethods}">
            <tr>
                <c:forEach var="method" items="${objects[0]['class'].declaredFields}">
                    <c:catch>
                        <c:choose>
                        <c:when test="${method.name != 'attributes'}">
                            <th><strong>${method.name}</strong></th>
                        </c:when>
                        </c:choose>
                    </c:catch>
                </c:forEach>
                <th><strong>create by</strong></th>
                <th><strong>create dt</strong></th>
                <th><strong>update by</strong></th>
                <th><strong>update dt</strong></th>
            </tr>
        </c:if>

        <c:forEach items="${objects}" var="object">
            <c:if test="${not empty object['class'].declaredFields}">
                <tr>
                    <c:forEach var="field" items="${object['class'].declaredFields}">
                        <c:catch>
                        <c:choose>
                            <c:when test="${field.name == 'id'}">
                                <td><a href="${actionGetById}?id=${object[field.name]}">${object[field.name]}</a></td>
                            </c:when>
                            <c:when test="${field.name == 'company'}">
                                <td>${object[field.name].name}</td>
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
    <table class="table100" style="text-align: center">
        <tr>
            <td><a class="button_submit" href="${actionPage}">First Page</a></td>
            <td><a class="button_submit"  href="${actionPage}/${pageIndex+1}">Next Page</a></td>
        </tr>
    </table>
</c:if>