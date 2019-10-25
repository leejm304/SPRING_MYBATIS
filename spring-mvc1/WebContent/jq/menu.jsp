<%@page contentType="text/html; charset=UTF-8" %>
<%
String contextPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
var loadLogin = function(data){
	//$("section>div>article:nth-child(1)").html(data);
	$("section").empty();
	$("section").html(data);
};

var loadJoin = function(data){
	//$("section>div>article:nth-child(2)").html(data);
	$("section").empty();
	$("section").html(data);
};

var loadBoardList = function(data){
	$("section").empty();
	$("section").html(data);
};

var loadLogout = function(data){
	location.href="${contextPath}/jq/layout.jsp";
};

var loadMenu = function(u, callback){
	$.ajax({
		url: u,
		method: 'GET',
		success: function(data){
			console.log("OK",data);
			callback(data);
		}
	});
};

$(function(){
	//var menuArr = document.querySelectorAll("header>nav>ul>li>a");
	var $menuArr = $("header>nav>ul>li>a");
	$menuArr.click(function(event){
		var url = $(this).attr('href'); //attr: 선택한 요소(this)의 (href)속성값
		
		switch(url){
		//case '../boardlist':
		case '${pageContext.request.contextPath}/boardlist':
			loadMenu(url, loadBoardList);
		case '<%=contextPath%>/jq/login.jsp':
			loadMenu(url, loadLogin);
			break;
		case '<%=contextPath%>/jq/join.jsp':
			loadMenu(url, loadJoin);
			break;
		case '<%=contextPath%>/logout':
			loadMenu(url, loadLogout);
			break;	
		case '<%=contextPath%>/jq/display.html':
			loadMenu(url, function(data){
				$("section").empty();
				$("section").html(data);
			});
		}
		return false; //기본이벤트핸들러 막기, 이벤트전달 중지
	});
});
</script>
<ul>
	<li><a href='<%=contextPath%>/boardlist'>게시판</a></li>
	<li><a href="#">공지사항</a></li>
	<%--로그인 안된 경우에 보일 메뉴 --%>
	<c:choose>
		<c:when test="${empty sessionScope.loginInfo}">
			<li><a href='${contextPath}/jq/login.jsp'>로그인</a></li>
			<li><a href='${contextPath}/jq/join.jsp'>가입</a></li>
		</c:when>
		<c:otherwise>
			<li><a href='${contextPath}/logout'>로그아웃</a></li>
		</c:otherwise>
	</c:choose>
	<li><a href='${contextPath}/jq/display.html'>display.html</a></li>
</ul>