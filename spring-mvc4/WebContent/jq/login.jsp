<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 외부 라이브러리 사용 할때 써야함-->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<!-- AJAX로 될때 한 소스코드에 ajax script선언 두번이라서 지움
 <script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<script>
$(function(){
	var savedId = localStorage.getItem("savedId");
	if(savedId != null && savedId != ''){
		$("input[name=id]").val(savedId);
	}
	$("button[type=button]").click(function(){
		var $menuArr = $("header > nav > ul > li > a");
		for(var i = 0 ; i <$menuArr.length;i++){
			var aObj = $menuArr[i];
			if($(aObj).attr('href') == 'join.html'){		
				//click이벤트 강제 발생 시키기
				$(aObj).trigger('click');
				break;
			}
		}
	});
	
	$("button[type=submit]").click(function(){
		alert("submit버튼에서 click이벤트 발생");		
		//$("form").attr('action', '../a.jsp');
		//$("form").submit(); //required점검 x
		//기본이벤트 핸들러 처리
	});
	
	$("form").submit(function(){
		/*아이디 기억*/
		//dom트리에서 name속성이 c인 객체찾기
		$cObj = $("input[name=c]");
		
		//$cObj객체가 선택된 상태인가 확인하기
		if($cObj.prop("checked")){//선택
		//id값을 storage에 기억
			var id = $("input[name=id]").val();
			localStorage.setItem("savedId",id);
		}else{//해제
			//id값을 storage에서 제거
			localStorage.removeItem("savedId");
		}
		
		alert("form에서 submit이벤트발생");
		//$("form").attr('method','post');
		//$("form").attr('action', '../first');
		//기본이벤트 핸들러가 할일: action속성값에 해당하는 URL을 요청한다.(default:현재의 URL)
		//return false; //기본이벤트 막기
		
		$.ajax({
			url:'${contextPath}/login',
			method:'POST',
			data: $('form').serialize(),
			success: function(data){		
				var msg = data.id + "님 로그인";
				if(data.status == 1){
					msg +="성공";
					location.href="${contextPath}/jq/layout.jsp";
				}else if(data.status == -1){
					msg +="실패";
				}
				alert(msg);
			}
		});
		return false;
	});
});

</script>
</head>
<body>
<form>
<%
/* //1)요청헤더의 쿠키를 얻기
Cookie[] cArr = request.getCookies();
String idValue = "";
if(cArr != null){
//2)쿠키중 이름이 savedId인 쿠키 찾기, 쿠키값을 id입력란의 id속성으로 추가
	for(Cookie c : cArr){
		if(c.getName().equals("savedId")){
			idValue = c.getValue();
			break;
		}
	}
}
 */
%>
	ID : <input type="text" name="id" placeholder="아이디를 입력하세요." 
	<%-- value = "<%=idValue%>" --%> required><br>
	비밀번호 : <input type="password" name="pwd" placeholder="비밀번호를 입력하세요." required><br>
	
	<input type = "checkbox" name="c" value="save">아이디기억<br>
	<button type="submit">로그인</button>
	<button type="button">가입</button>
</form>
</body>
</html>