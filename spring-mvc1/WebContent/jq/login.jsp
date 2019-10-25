<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="UTF-8">
<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>-->
<script>
//window.addEventListener("load", function(){
//jQuery(document).ready(function(){
//$(document).ready(function(){
/* $(function(){
	alert("first")
});
window.addEventListener("load", function(){
	alert("second")
}); */
</script>
<script>
$(function(){
	var savedId = localStorage.getItem("savedId");
	if(savedId != null && savedId != ''){
			$("input[name=id]").val(savedId);
	}
	$("button[type=button]").click(function(){
		//alert("가입버튼이 클릭되었습니다.");
		//menu중 가입메뉴객체를 찾기
		var $menuArr = $("header > nav > ul > li > a"); //검사>element 선택 >copy selector 이용해서 가져오기
		for(var i=0; i<$menuArr.length; i++){
			var aObj = $menuArr[i];
			if($(aObj).attr('href') == 'join.html'){
				//alert($(aObj).html());
				//가입 메뉴 클릭해서 보여주길 원해 => click이벤트 강제로 발생시키기
				$(aObj).trigger("click"); 
				//jquery라이브러리를 layout에서도 선언, login에서도 선언하면 X
				break;
			}
		}
	});
	
	//$("button[type=submit]").click(function(){ //1
	//		alert("submit버튼에서 click이벤트 발생");
			/* $("form").attr('action', '../a.jsp');
			$("form").submit(); */
			//return false; // 지금 사용중인 페이지 요청 (기본 이벤트 핸들러) 막기
			//기본이벤트 핸들러가 할 일 : form의 submit이벤트를 강제 발생
	//});
	
	$("form").submit(function(){ //2
		/*아이디 기억*/
		//dom트리에서 name속성이 c인 객체 찾기
		$cObj = $("input[name=c]");
	
		//$cObj객체가 선택된 상태인가 확인
		if($cObj.prop("checked")){
			//id값을 storage에 기억
			var id = $("input[name=id]").val();
			localStorage.setItem("savedId", id);
		}else{//해제
			//id값을 storage에서 제거
			localStorage.removeItem("savedId");
		}
		$.ajax({ 
			//form으로 전송하면 name속성을 통해 알아서 전달되는데 ajax를 이용하면 data에 뭘 전송할지 적어줘야해
			url: '${contextPath}/login',
			method: 'post',
			data: /* 'id='+ $("input[name=id]").val() + "&" +
			      'pwd='+ $("input[name=pwd]").val(), */
			      $('form').serialize(),
			success: function(data){
				var jsonObj = JSON.parse(data);
				var msg = jsonObj.id + "님 로그인 ";
				if(jsonObj.status ==1){
					msg += "성공";
					alert(msg);
					location.href="${contextPath}/jq/layout.jsp";
				}else{
					msg += "실패";
					alert(msg);
				}
			}
		});
		return false;
	});
});

</script>
<style>

</style>
</head>
<body>
<%-- <%
//1)요청헤더에 쿠키를 얻기
Cookie[] cArr = request.getCookies();
String idValue = "";
if(cArr != null){
	//2)쿠키 중 이름이 savedId인 쿠키를 찾기, 쿠키값을 id입력란의 value속성으로 추가한다.
	for(Cookie c: cArr){
		if(c.getName().equals("savedId")){
			idValue = c.getValue();
			break;
		}
	}
}
%> --%>
<form>
	ID:	<input type="text" name="id" placeholder="아이디를 입력하세요"
		<%-- value="<%=idValue %>" --%>
		required
	 	><br>
	<!-- required="required" 속성명과 속성값이 같을 때는 속성명만 적어도 돼 -->
	비밀번호: 	<input type="password" name="pwd" required><br>
<!-- 	<input type="checkbox" name="c" value="1">one&nbsp;&nbsp;
	<input type="checkbox" name="c" value="2">two&nbsp;&nbsp;
	<input type="checkbox" name="c" value="3">three<br> -->
	<input type="checkbox" name="c" value="save">아이디 기억<br>
	<button type="submit">로그인</button>
	<button type="button">가입</button>
</form>
</body>
</html>