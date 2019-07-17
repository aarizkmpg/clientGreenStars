<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<jsp:include page="header.jsp" />
	<body>
		<jsp:include page="head.jsp" />
		<div class="page-content">
			<div class="form-v10-content">
				<c:choose>
					<c:when test="${not empty company}">
						<c:url var="action" value="/company/add" scope="request" />
						<c:url var="actionPage" value="/company/list" scope="request" />
						<jsp:include page="company.jsp" />
					</c:when>
					<c:when test="${not empty product}">
						<c:url var="action" value="/product/add" scope="request" />
						<c:url var="actionPage" value="/product/list" scope="request" />
						<jsp:include page="product.jsp" />
					</c:when>
					<c:otherwise>

					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="page-content">
			<div class="form-v10-content">
				<div class="form-detail">
				<div class="form-left">
					<jsp:include page="list.jsp" />
				</div>
				</div>
			</div>
		</div>
	</body>
</html>