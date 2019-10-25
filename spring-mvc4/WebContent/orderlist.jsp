<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="contextPath.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.orderlist>table {
	width: 100%;
	text-align: left;
}

.orderlist {
	margin: 20px;
	width: 90%;
}

.orderlist #order {
	float: right;
}
.orderlist tr.order_detail{
	border-top: solid 1px green;
	border-bottom: solid 1px green;
}
</style>
<body>
	<c:set var="result" value="${requestScope.result}" />
	<c:if test="${result==1}">
		<c:set var="list" value="${requestScope.list}" />
		<div class=orderlist>
			<table>
				<th>주문번호</th>
				<th>주문시간</th>
				<th>상품번호</th>
				<th>상품명</th>
				<th>가격</th>
				<th>주문수량</th>
				<c:forEach var="orderinfo" items="${list}">
					<tr class="order_detail">
						<td>${orderinfo.order_no}</td>
						<td>${orderinfo.order_time}</td>
						<td colspan="4"></td>
					</tr>
					<c:forEach var="orderline" items="${orderinfo.orderLines}">
						<tr >
							<td colspan="2"></td>
							<td>${orderline.product.prod_no}</td>
							<td>${orderline.product.prod_name}</td>
							<td>${orderline.product.prod_price}</td>
							<td>${orderline.order_quantity}</td>
						</tr>
					</c:forEach>
				</c:forEach>
			</table>
		</div>
	</c:if>

</body>
</html>