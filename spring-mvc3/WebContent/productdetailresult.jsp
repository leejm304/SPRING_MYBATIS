<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="ContextPath.jsp" %>    
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<style>
span#str {
	display: none;
}
img {
	width: 200px;
	height: 200px;
}
</style>
<script>
$(function(){
	   var jsonStr = $("span#str").html();
	   obj = JSON.parse(jsonStr);
	   
	   var prod_no = obj.prod_no;
	   var prod_name = obj.prod_name;
	   var prod_price = obj.prod_price;
	   
	   $("#prod_no").text(prod_no);
	   $("#prod_name").text(prod_name);
	   $("#prod_price").text(prod_price);
	   $("img").attr("src", "images/"+prod_no+".jpg")
	   
	   if(prod_detail==null){
	      $("div.detail").hide();
	   }

	   $("button").click(function(){
	   var quantity = $("input[type=number]").val();	   
	      $.ajax({
	         url:'${contextPath}/addcart',
	         method:'POST',
	         data: {"prod_no":prod_no, "prod_name":prod_name, "prod_price":prod_price, "quantity":quantity},
	         success:function(data){
	        	var jsonObj = JSON.parse(data); 
	         	if(jsonObj.status == 1){
	         		alert("장바구니에 담겼습니다!");
	         	} else{
	         		alert("장바구니에 담기 실패!");
	         	}
	         }
	      });
	   });
	
	});

</script>
</head>
<body>
<c:set var="r" value="${requestScope.result}"/>
<span id="str">${r}</span>
<div>
<img src=""><br>
<span>상품 번호 : </span><span id="prod_no"></span><br>
<span>상품 이름 : </span><span id="prod_name"></span><br>
<span>상품 가격 : </span><span id="prod_price"></span><br>
<span>상품 설명 : </span><span id="prod_detail"></span><br>
<span>상품 수량 : </span><input type=number min="1" value="1" id="quantity"><br>
<button>장바구니에 담기</button>
</div>
</body>
