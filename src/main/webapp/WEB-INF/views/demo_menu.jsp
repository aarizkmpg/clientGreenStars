<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Spring5 MVC Hibernate Demo</title>
		<style type="text/css">
			.error {
				color: red;
			}
			table {
				width: 50%;
				border-collapse: collapse;
				border-spacing: 0px;
			}
			table td {
				border: 1px solid #565454;
				padding: 20px;
			}
		</style>
	</head>
		<h1>Testing History and Audit using 1 table</h1>
		<h2>Using database client02</h2>
		<a href="<c:url value="/demo/2/listDemoProducts"/>">List Products</a>

		<h1>Testing History and Audit using mutiple tables</h1>
		<h2>Using database client03</h2>
		<p>List Products</p>
		<c:forEach items="${style3}" var="st">
			List Products from: <a href="<c:url value="demo/3/listDemoProducts?index=${st.indexId}"/>">${st.fromDt} - ${st.toDt}</a><br>
		</c:forEach>

		<h1>Testing History and Audit using 1 table multiple database </h1>
		<h2>Using database client04 client05 client06</h2>
		<p>List Products</p>
		<c:forEach items="${style4}" var="st">
			List Products from: <a href="<c:url value="demo/${st.databaseIndex}/listDemoProducts?index=1"/>">${st.fromDt} - ${st.toDt}</a><br>
		</c:forEach>

	</body>
</html>