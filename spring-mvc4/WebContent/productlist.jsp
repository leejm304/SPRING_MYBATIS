<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="contextPath.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div.product_list>ul>li {
	width: 260px;
	float: left;
	margin: 10px;
	position: relative;
}

div.product_list>ul>li>a {
	margin: 0;
	padding: 0;
	font-size: 100%;
	text-decoration: none;
	vertical-align: baseline;
	color: #666;
	background: transparent;
	cursor: pointer;
}

div.product_list>ul>li  a>img {
	vertical-align: top;
	max-width: 100%;
}

@media screen and (max-width: 1099px) and (min-width: 641px) {
	div.product_list>ul>li {
		width: 31%;
		margin: 0.65%;
	}
	div.product_list>ul>li  a>img {
		height: auto;
		width: 100%;
	}
}

@media screen and (max-width: 1099px) and (min-width: 961px) {
	div.product_list>ul>li {
		width: 24%;
		margin: 0.5%;
	}
	div.product_list>ul>li  a>img {
		height: auto;
		width: 100%;
	}
	@media screen and (max-width: 640px) {
		div.product_list>ul>li {
			width: 49%;
			margin: 0.5%;
		}
		div.product_list>ul>li  a>img {
			height: auto;
			width: 100%;
		}
	}
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(function() {

		//상세 정보 조회
		var $menuArr = $(".product_list a");
		$menuArr.click(function() {
			var url = $(this).attr('href');
			$.ajax({
				url : url,
				method : 'GET',
				success : function(data) {
					$("section").empty();
					$("section").html(data);
				}
			});//end ajax
			return false;
		});//end click

	});
</script>
</head>
<body>
	<div class=product_list style="display: block;">
		<ul>
			<c:forEach var="list" items="${requestScope.list}">
				<li class="menuDataSet"
					style="list-style: none; float: left; position: relative;">
					<dl>
						<dt>
							<a href="${contextPath}/productdetail?prod_no=${list.prod_no}">
								<img src="${contextPath}/imgs/${list.prod_no}.jpg"
								alt="${list.prod_name}">
							</a>
						</dt>
						<dd>${list.prod_name}</dd>
					</dl>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>