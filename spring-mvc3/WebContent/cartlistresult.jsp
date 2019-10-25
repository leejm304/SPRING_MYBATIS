<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="ContextPath.jsp"%>
<c:set var="cart" value="${sessionScope.cart}" />
<style>
table, td {
	border: solid 1px;
}
</style>
<script>
	var countChecked = function() {
		var n = $("input:checked").length;
		$("#info").text(n + "개의 상품을 주문합니다.");
	};

	$(function() {
		
		$("input[type=checkbox]").change(function(){
	        if($(this).is(":checked")){
	        	$(this).parent().parent().css("background-color", "yellow");
	        }else{
	        	$(this).parent().parent().css("background-color", "white");
	        }
   		});
		
		$("input[type=checkbox]").on("click", countChecked);
		
 		$("button").click(function() {
 			var prod_noArray = [];//주문할(체크된) 상품번호 담을 배열
 			$("input[type=checkbox]:checked").each(function(){
 				prod_noArray.push($(this).parent().next().html());
            });

 			    $.ajax({
				url : '${contextPath}/addorder',
				method : 'POST',
				data : "prod_noArray="+prod_noArray,
				success : function(data) {
					var jsonObj = JSON.parse(data); 
		         	if(jsonObj.status == 1){
		         		alert("주문 성공");
		         	} else if(jsonObj.status == 0){
		         		alert("로그인 후 주문가능합니다.");
		         	}
		         	else{
		         		alert("주문 실패");
		         	}
				}
			});//end ajax
		});//end click

	});//end $(function(){
</script>
<div class="cartList">
	<c:choose>
		<c:when test="${empty cart}">
			<h3>장바구니에 담긴 상품이 없습니다.</h3>
		</c:when>
		<c:otherwise>
			<table>
				<tr>
					<th>선택</th>
					<th>상품 번호</th>
					<th>상품 이름</th>
					<th>상품 가격</th>
					<th>개수</th>
				</tr>
				<c:forEach var="m" items="${cart}">
					<c:set var="p" value="${m.key}" />
					<tr>
						<td><input type="checkbox"></td>
						<td>${p.prod_no}</td>
						<td>${p.prod_name}</td>
						<td>${p.prod_price}</td>
						<td>${m.value}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<span id="info"></span><br>
	<button>주문하기</button>
</div>