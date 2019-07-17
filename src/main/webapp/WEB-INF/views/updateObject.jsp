<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body class="form-v10">
    <jsp:include page="header.jsp" />
    <div class="page-content">
        <div class="form-v10-content">
            <c:choose>
                <c:when test="${not empty company}">
                    <c:url var="action" value="/company/update" scope="request" />
                    <c:url var="getReversionUrl" value="/company/getReversion" scope="request" />
                    <jsp:include page="company.jsp" />
                </c:when>
                <c:when test="${not empty product}">
                    <c:url var="action" value="/product/update" scope="request" />
                    <c:url var="getReversionUrl" value="/product/getReversion" scope="request" />
                    <jsp:include page="product.jsp" />
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

</body>
</html>