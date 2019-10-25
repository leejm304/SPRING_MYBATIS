<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 외부 라이브러리 사용 할때 써야함-->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<style>
header>h1{
  text-align: center; 
}
header>nav>ul>li { 
  display:inline-block;
  margin: 10px;
}
header>nav>ul>li>a{ 
  text-decoration: none;
}
header>nav>ul>li>a:hover{
  background-color: yellow;
  font-weight:bold;
}

</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	//callback함수 사용하기
	var loadLogin = function(data) {
		$("section").empty();
		$("section").html(data);
	};

	var loadJoin = function(data) {
		$("section").empty();
		$("section").html(data);
	};
	
	var loadBoardList = function(data) {
		$("section").empty();
		$("section").html(data);
	};
	var loadLogout = function(data) {
		location.href="${pageContext.request.contextPath}/jq/layout.jsp";
	};
	
	var loadProductList = function(data) {
		$("section").empty();
		$("section").html(data);
	};
	var loadCartList = function(data) {
		$("section").empty();
		$("section").html(data);
	};
	var loadOrderList = function(data) {
		$("section").empty();
		$("section").html(data);
	};

	var loadMenu = function(u, callback) {
		$.ajax({
			url : u,
			method : 'GET',
			success : function(data) {
				//console.log("OK", data);
				callback(data);
			}
		});
	};

	$(function() {
		var $menuArr = $("header>nav>ul>li>a");
		$menuArr.click(function() { // jquery는 event매개변수 필요 x
			//menuObj.href값에 해당 URL을 요청해서 결과를 응답받은 후
			//응답내용을 각 영역에 보여준다.
			var url = $(this).attr('href');
			//callback함수 사용하기 
			if (url != -1) {
				switch (url) {
				case '${pageContext.request.contextPath}/jq/login.jsp':
					loadMenu(url, loadLogin);
					break;
				case '${pageContext.request.contextPath}/jq/join.jsp':
					loadMenu(url, loadJoin);
					break;
				case '${pageContext.request.contextPath}/boardlist':
					loadMenu(url, loadBoardList);
					break;
				case '${pageContext.request.contextPath}/logout':
					loadMenu(url, loadLogout);
					break;
				case '${pageContext.request.contextPath}/productlist':
					loadMenu(url, loadProductList);
					break;
				case '${pageContext.request.contextPath}/cartlist':
					loadMenu(url, loadCartList);
					break;
				case '${pageContext.request.contextPath}/orderlist':
					loadMenu(url, loadOrderList);
					break;
				case '${pageContext.request.contextPath}/jq/display.html':
					loadMenu(url, function(data) {
						$("section").empty();
						$("section").html(data);
					});
					break;
				}
			}
			return false; //기본이벤트핸들러 막기, 이벤트전달 중지
		});
	});
</script>

<ul>
	<li><a href="${pageContext.request.contextPath}/boardlist">게시판</a></li>
	<li><a href="#">공지사항</a></li>
	<c:choose>
	<c:when test = "${empty sessionScope.loginInfo}">
	<li><a href="${pageContext.request.contextPath}/jq/login.jsp">로그인</a></li>
	<li><a href="${pageContext.request.contextPath}/jq/join.jsp">가입</a></li>
	</c:when>
	<c:otherwise>
	<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
	<li><a href="${pageContext.request.contextPath}/orderlist">주문목록</a></li>
	</c:otherwise>
	</c:choose>
	<li><a href="${pageContext.request.contextPath}/productlist">상품목록</a></li>
	<li><a href="${pageContext.request.contextPath}/cartlist">장바구니</a></li>
	<li><a href="${pageContext.request.contextPath}/jq/display.html">display.html</a></li>
</ul>