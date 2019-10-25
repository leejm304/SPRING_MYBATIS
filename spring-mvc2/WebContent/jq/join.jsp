<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
<style>
#dupchkid {
	color: #2BC63E;
	background-color: #fff;
	border-color: #2bc63e;
	border-radius: 4px;
}

#divSearchZip, #joinComplete {
	display: none;
}

#divSearchZip>div {
	overflow: auto;
	height: 80%;
}
span{
	display: none;
}
</style>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<script>
$(function(){ //id입력란에 focus이벤트감시, 할일: 가입완료버튼화면에서 사라진다
	
	//아이디입력란
	var $idObj = $("input[name=id]");
	//가입완료 버튼
	var $submitObj = $("input[type=submit]");
	$idObj.focus(function(){
		$submitObj.css('display','none');
	});
	
	//중복확인 버튼
	var $dupchkObj = $("#dupchkid");
	$dupchkObj.click(function(){
		/* if($idObj.val()=="test") {
			 alert("이미 사용중인 ID입니다.");
		 } else if($idObj.val()=="") {
			 alert("ID를 입력하세요");
			 $idObj.focus();
		 } else {
			 alert("회원가입완료");
			 $submitObj.css("display","block");
		 } */
		 if($idObj.val().trim().length==0){
			 $idObj.focus();
			 return false;
		 }
		 $.ajax({ 
				//form으로 전송하면 name속성을 통해 알아서 전달되는데 ajax를 이용하면 data에 뭘 전송할지 적어줘야해
				url: '${contextPath}/dupchk',
				method: 'post',
				data: 'id='+ $("input[name=id]").val(),
				success: function(data){
					var jsonObj = JSON.parse(data);
					var msg = "";
					if(jsonObj.status == 1){
						msg += "이미 있는 아이디입니다!";
						alert(msg);
					}else if(jsonObj.status == -1){
						msg += "사용가능한 아이디입니다";
						alert(msg);
						$submitObj.show();
					}	
				}
			}); 
	});
	
	//우편번호검색 버튼
	var $searchZipObj=$("#searchZip");
	$searchZipObj.click(function(){
		$("#divSearchZip").css("display","block");
	});
	
	$("#divSearchZip>form>button").on("click", function(){
		//alert($("input[name=doro]").val());
		$.ajax({ 
			url: '${contextPath}/searchZip',
			method: 'post',
			data: 'doro='+ $("input[name=doro]").val(),
			success:function(data){ 
				var jsonObjArr = JSON.parse(data);
				var trs = "";
			    trs += "<tr><th>우편번호</th><th>주소</th></tr>";				
				for(var i=0; i<jsonObjArr.length; i++){
					var jsonObj = jsonObjArr[i];//객체{}
					trs += "<tr><td>"+jsonObj.zipcode+"</td><td><div>"+jsonObj.addrdoro+"</div><div>" + jsonObj.addrzibun+"</div><span>"+jsonObj.buildingno+"</span></td></tr>";
				}
				$("#divSearchZip>div>table").html(trs);
			}
		}); 
	});
	
	
	//우편번호창에서 행(tr) 클릭이벤트 감시 할일 : 본창의 우편번호, 기본주소에 값 대입
									//우편번호창 사라진다.
	$("#divSearchZip>div>table").on("click", "tr:not(:first-child)", function(){
		  var children = $(this).children();
		  var zipcode =children.eq(0).html();//<td></td>	  
		  $("input[name=zipcode]").val(zipcode);
		  //var addr1 = children.eq(1).html();
		  var addrdoro = children.find("div:first-child"); //<td><div></div><div></div></td>
		  var addrdoro = $(addrdoro).html(); //<div>
		  $("input[name=addr1]").val(addrdoro);
		  var buildingno = children.find("span");
		  var buildingno = $(buildingno).html();
		  $("input[name=buildingno]").val(buildingno);
		$("#divSearchZip").hide();	
	});
	
	$("#form1").submit(function(){
        var url = '../join';
        $.ajax({
        	url: url,
        	method: 'post',
        	data: $(this).serialize(),
        	success:function(data){
        		if($("input[name=pwd]").val() != $("input[name=pwdchk]").val()){
        			alert("비밀번호가 일치하지 않습니다");
        			return false;
        		}
        		var jsonObj = JSON.parse(data);
        		if(jsonObj.status == 1){ //가입성공
        			alert("가입성공");
        		    location.href='layout.jsp';
        		}else{
        			alert("가입실패");
        		}
        	}
        });
    	return false;
    });

});
</script>
</head>

<form id="form1">
<h3>아이디입력</h3>
<input type="text" name="id" placeholder="아이디를 입력하세요.">
<button id="dupchkid" type="button">중복확인</button>
<br>

<h3>비밀번호</h3>
<input type="password" name="pwd" placeholder="비밀번호를 입력하세요.">
<span></span>
<br>

<h3>비밀번호확인</h3>
<input type="password" name="pwdchk" placeholder="비밀번호를 입력하세요.">
<span></span>
<br>

<h3>이름</h3>
<input type="text" name="name" placeholder="이름을 입력하세요.">
<span></span>
<br>

<h3>주소</h3>
<input style="width: 80px" name="zipcode">
<button type="button" id="searchZip">우편번호검색</button>
<br>
<input style="width: 250px" name="addr1">
<br>
<input style="width: 300px" name="addr2">
<br>
<input type="hidden" name="buildingno">
<br>

<input type="reset" value="취소">
<input type="submit" value="가입완료">
</form>
<!-- 우편번호 검색용 -->
<div id="divSearchZip"
	style="width: 450px; height: 400px; position: absolute; top: 100px; left: 200px; background-color: lightgray">
	<form id="form2">
		<input type="text" placeholder="도로명 + 건물번호" name="doro">
		<button type="button">검색</button>
	</form>

	<div>
		<table>
			<!-- 크롬은 tbody태그를 자동끼워넣기를 하므로 tr객체찾기X tb후손으로 찾기O -->
			<tr>
				<th>우편번호</th>
				<th>주소</th>
			</tr>
			<tr>
	
			</tr>
		</table>
	</div>

</div>
</html>