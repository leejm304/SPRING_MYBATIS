<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
/*detail*/
div.product_view_wrap1 {
	padding: 20px;
	width: 1100px;
	position: relative;
	width: 1100px;
}

div.product_view_detail {
	width: 300px;
	display: inline-block;
	position: relative;
	margin-left: 20px;
}

div.product_view_detail div.product_info_detail {
	height: inherit;
	line-height: inherit;
	padding: 10px 0;
	height: 59px;
	line-height: 59px;
	position: relative;
	border-top: 1px solid #ddd;
}

div.product_view_pic {
	display: inline-block;
}

div.product_view_info button {
	float: right;
}
</style>
<script>
	$(function() {
		var jsonObj = ${requestScope.result};
		console.log(jsonObj);
		var status = jsonObj.status;
		var cate_name = jsonObj.product.cate_name;
		console.log(status + " " + cate_name);
		var prod_no = jsonObj.product.prod_no;
		var prod_name = jsonObj.product.prod_name;
		var prod_price = jsonObj.product.prod_price;
		var prod_detail = jsonObj.product.prod_detail;
		if (status == 1) {
			//데이타 채우기
			$("#prod_name").html(prod_name);
			$("#prod_no").html(prod_no);
			$("#prod_name").html(prod_name);
			$("#prod_price").html(prod_price);
			$("#prod_detail").html(prod_detail);
			$("#cate_name").html(cate_name);
			var url = "${contextPath}/imgs/" + jsonObj.product.prod_no + ".jpg";
			$("#prod_img").attr("src", url);
		} else {
			alert("정보가 없는 상품입니다.")
		}

		//장바구니담기
		$("button[type=submit]").click(function() {
			$.ajax({
				url : '${contextPath}/addcart',
				method : 'POST',
				data : {
					"prod_no" : $("#prod_no").html(),
					"prod_name" : $("#prod_name").html(),
					"prod_price" : $("#prod_price").html(),
					"quantity" : $("#quantity").val()
				},
				success : function(jsonObj) {
					var json = JSON.parse(jsonObj);
					if (json.status == 1) {
						alert("장바구니에 성공적으로 담겼습니다.");
					} else {
						alert("장바구니에 넣기에 실패했습니다.");
					}
				}
			});//end ajax
			return false;
		});//end click
	});
</script>
<div class="product_view_wrap1">
	<div class="product_view_pic">
		<div class="product_big_pic">
			<img id="prod_img" src="" alt="상세이미지">
		</div>
	</div>
	<div class="product_view_detail">
		<h4 id="prod_name"></h4>
		<span id="prod_detail"></span>
		<div class="product_view_info">
			<form method="post">
				<div class="product_info_detail">
					카테고리: <span id="cate_name"></span>
				</div>
				<div class="product_info_detail">
					상품번호: <span id="prod_no"></span>
				</div>
				<div class="product_info_detail">
					상품가격: <span id="prod_price"></span>
				</div>
				<div class="product_info_detail">
					상품수량: <input id="quantity" type="number" min="1" max="99" value="1"/>
				</div>
				<button type="submit">장바구니넣기</button>
			</form>
		</div>
	</div>
</div>