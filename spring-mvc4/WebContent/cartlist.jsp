<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="cart" value="${sessionScope.cart}" />
<style>
.cartlist>table {
	width: 100%;
	text-align: left;
	border: solid 1px green;
}

.cartlist>table td {
	border-top: solid 1px green;
}

.cartlist {
	margin: 20px;
	width: 50%;
}

.orderdiv {
	float: right;
}
</style>
<script>
	$(function() {
		$("#order").click(function() {
			$.ajax({
				url : '${contextPath}/addorder',
				method : 'POST',
				success : function(data) {
					alert(data);
					var jsonObj = JSON.parse(data);
					if (jsonObj.status == 1) {
						alert("성공적으로 주문되었습니다.");
					} else if (jsonObj.status == 0) {
						alert("로그인 후 주문이 가능합니다.");
						//location.href="${contextPath}/jq/login.jsp";
					} else {
						alert("주문에 실패했습니다.");
					}

				}//end success
			});//end ajax
			return false;
		});//end click

		var sum = 0;
		$(".cartlist .prod_price").each(function() {
			sum += parseInt($(this).html());
		});
		console.log(sum);
		$("#sum").html(sum);
	});
</script>
<div class="cartlist">
	<table>
		<th>상품번호</th>
		<th>상품이름</th>
		<th>상품가격</th>
		<th>수량</th>
		<c:forEach items="${cart}" var="m">
			<c:set var="p" value="${m.key}" />
			<tr>
				<td>${p.prod_no}</td>
				<td>${p.prod_name}</td>
				<td class="prod_price">${p.prod_price}</td>
				<td>${m.value}</td>
			</tr>
		</c:forEach>
	</table>
<br>
<c:if test="${cart!=null}">
	<div class="orderdiv">
		합계: 총 <span id="sum"></span>원
		<button id="order">주문하기</button>
	</div>
</c:if>
</div>

