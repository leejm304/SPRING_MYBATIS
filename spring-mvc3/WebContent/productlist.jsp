<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div.product{
	background-color: white;
	border: 2px solid black;
	padding: 3px;
	width: 250px;
	height: auto;
	display: inline-block;
}
</style>
<script>
$(function(){
	//상품 클릭했을 때 상세 페이지로 이동
	var $divArr = $("div.product");
    $divArr.click(function(){
    	var no = $(this).children("span:first").html();
    	alert(no+"번 상품");
    	$.ajax({
    		url: "${contextPath}/productdetail",
    		method: 'get',
    		data:'prod_no='+no,
    		success:function(data){
    			$("section").empty();
     		    $("section").html(data); 
    		}
    	});//end ajax  
    });//end click
	
});//end $(function(){
</script>
</head>
<body>
	<c:forEach var="product" items="${list}">
	<div class="product">
		<img src="images/${product.prod_no}.jpg">
		<span>${product.prod_no}</span>
		<span>${product.prod_name}</span>
	</div>
	</c:forEach> 
</body>
</html>