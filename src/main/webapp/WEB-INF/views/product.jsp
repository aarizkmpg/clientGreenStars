<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="actionGetById" value="/product/getById" scope="request" />
<div class="form-detail">
	<form:form action="${action}" method="post" modelAttribute="product" class="form-left">
		<h2>${pageTitle}</h2>
		<input type="hidden" name="modelType" value="Product"/>
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
				<form:input path="unitPrice" placeholder="Unit Price" type="number" required="true" class="input-text" />
			</div>
		</div>
		<div class="form-row">
			<form:select path="company.id">
				<form:option class="option" value="0" label="--Please Select--"/>
				<form:options class="option" items="${companies}" itemValue="id" itemLabel="name"/>
			</form:select>
			<span class="select-btn"><i class="zmdi zmdi-chevron-down"></i></span>
		</div>
		<div class="form-row">
			<h3>Attribute</h3>
		</div>
		<div class="form-group attr_titles">
			<div class="form-row form-row-4">Name</div>
			<div class="form-row form-row-4">Desc</div>
			<div class="form-row form-row-4">Action</div>
		</div>
			<c:set var="attrIndex" value="0" />
			<c:if test="${not empty product.attributes}">
				<c:forEach items="${product.attributes}" var="attr" varStatus="tagStatus">
				<div class="form-group">
					<div class="form-row form-row-4">
						<form:hidden path="attributes[${tagStatus.index}].id" />
						<form:input path="attributes[${tagStatus.index}].name" value="${attr.name}"/>
					</div>
					<div class="form-row form-row-4">
						<form:input path="attributes[${tagStatus.index}].desc" value="${attr.desc}"/>
					</div>
					<div class="form-row form-row-4">
						<form:hidden path="attributes[${tagStatus.index}].deleting" value="${attr.deleting}"/>
						<input type="button" class="del_button register" value="Delete" /></td>
						<c:set var="attrIndex" value="${tagStatus.index + 1}" />
					</div>
				</div>
				</c:forEach>
			</c:if>
		<div class="form-group default-add">
			<div class="form-row form-row-4">
				<input name="attributes[${attrIndex}].name" value="${attr.name}"/>
			</div>
			<div class="form-row form-row-4">
				<input name="attributes[${attrIndex}].desc" value="${attr.desc}"/>
			</div>
			<div class="form-row form-row-4">
				<input type="button" class="del_button register" value="Delete" attr="${attrIndex}" />
			</div>
		</div>
		<div class="form-group">
			<input type="button" class="button_submit add_button register" value="Add" style="margin: auto;" />
		</div>
		</div>
		<div class="form-row-last">
			<input type="submit" class="button_submit register" value="Submit">
			<input type="button" class="button_revert register" style="display:none" value="Revert">
		</div>
	</form:form>

	<div class="form-right" style="display:none">
		<h2>${pageTitle} Versioning</h2>
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
				<input name="unitPrice" placeholder="Unit Price" type="number" required="true" class="input-text" />
			</div>
		</div>
		<div class="form-row">
			<select name="company.id" disabled>
				<c:forEach items="${companies}" var="comp">
					<option class="option" value="${comp.id}">${comp.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-row">
			<h3>Attribute</h3>
		</div>
		<div class="form-group attr_titles">
			<div class="form-row form-row-4">Name</div>
			<div class="form-row form-row-4">Desc</div>
			<div class="form-row form-row-4"></div>
		</div>
		<div class="attr_s">


		</div>

		<div class="form-row-last">
			<input type="submit" class="button_Reversion register" value="Reversion">
		</div>
	</div>
</div>

<c:if test="${not empty product.id}">
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
	$(".del_button").on( "click", function() {
		$(this).siblings().val("1");
		$(this).parents(".form-group").hide();
	});

	var itemIndex = parseInt($(".default-add .del_button").attr("attr"));

	$(".add_button").on("click",function(){
		itemIndex ++;
		$('<div class="form-group"><div class="form-row form-row-4"><input name="attributes['+itemIndex+'].name" value=""></div><div class="form-row form-row-4"><input name="attributes['+itemIndex+'].desc" value=""></div><div class="form-row form-row-4"><input type="button" class="del_button register" value="Delete"> </div></div>').insertBefore($(this).parents(".form-group"));
		$(".del_button").on( "click", function() {
			$(this).siblings().val("1");
			$(this).parents(".form-group").hide();
		});
	});

	$(".auditLogClick").on("click",function(event){
		event.preventDefault();
		$(".form-right").show();
		$(".form-right input").removeClass("checkmark");
		$.getJSON($(this).attr("href"), function( data ) {
			var $form2 = $(".form-right");
			$(':input:not(:hidden)[name]', $form2).val(function() {
				return data.object[this.name];
			});
			$('select', $form2).val(function() {
				return data.object.company.id;
			});
			var html = "";
			$(".attr_s").html("");
			$.each(data.object.attributes, function(idx, obj) {

				html += '<div class="form-group">';
				html += '<div class="form-row form-row-4">';
				html += '<input type="hidden" name="attributes['+idx+'].id" value="'+obj.id+'" />';
				html += '<input name="attributes['+idx+'].name" value="'+obj.name+'"/>';
				html += '</div>';
				html += '<div class="form-row form-row-4">';
				html += '<input name="attributes['+idx+'].desc" value="'+obj.desc+'"/>';
				html += '</div>';
				html += '<div class="form-row form-row-4">';
				html += '</div>';
				html += '</div>';
			});
			$(html).appendTo($(".attr_s"));
			checkForms($('.form-left'),$('.form-right'));
			$(".button_revert").show();

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
			$('select', $form2).val(function() {
				return data.company.id;
			});
			var html = "";
			$(".attr_s").html("");
			$.each(data.attributes, function(idx, obj) {

				html += '<div class="form-group">';
				html += '<div class="form-row form-row-4">';
				html += '<input type="hidden" name="attributes['+idx+'].id" value="'+obj.id+'" />';
				html += '<input name="attributes['+idx+'].name" value="'+obj.name+'"/>';
				html += '</div>';
				html += '<div class="form-row form-row-4">';
				html += '<input name="attributes['+idx+'].desc" value="'+obj.desc+'"/>';
				html += '</div>';
				html += '<div class="form-row form-row-4">';
				html += '</div>';
				html += '</div>';
			});
			$(html).appendTo($(".attr_s"));
			checkForms($('.form-left'),$('.form-right'));
			$(".button_revert").show();
	});


</script>