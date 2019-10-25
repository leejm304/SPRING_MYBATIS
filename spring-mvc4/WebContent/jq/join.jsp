<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="UTF-8">
<style>
* {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

body {
	font-family: "Nanum Gothic", sans-serif;
	font-size: 14px;
	line-height: 1.428571429;
	color: #000;
	background-color: #fff;
	letter-spacing: -0.5px;
}

.mb-15 {
	margin-bottom: 15px !important;
}

.clearfix:before, .clearfix:after {
	content: " ";
	display: table;
}

h6 {
	font-size: 18px;
	letter-spacing: -1px;
	line-height: 24px;
}

h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6 {
	font-family: inherit;
	font-weight: 400;
	color: inherit;
}

.mb-0 {
	margin-bottom: 0px !important;
}

.mt-0 {
	margin-top: 0px !important;
}

.pull-left {
	float: left !important;
}

.pull-right {
	float: right !important;
}

.text-primary {
	color: #2BC63E;
	font-weight: bold;
}

.form-group-with-btn {
	padding-right: 110px;
	position: relative;
}
.form-group-with-btn2 {
	padding-right: 200px;
	position: relative;
}
.form-group-with-btn>.btn{
   position: absolute;
   top: 8px;
   right: 0;
}
.form-group-with-btn2>.btn{
    position: absolute;
    top: 8px;
    right: 0;
display: inline-block;
}
.form-control {
	display: block;
	width: 100%;
	height: 44px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.428571429;
	color: #000;
	letter-spacing: -0.5px;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 2px;
}

button, input, optgroup, select, textarea {
	color: inherit;
	font: inherit;
	margin: 0;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	margin-left: 0px;
}

.btn-primary.btn-line {
	color: #2BC63E;
	background-color: #fff;
	border-color: #2bc63e;
}

.btn-primary.btn-line:active:hover, .btn-primary.btn-line:active:focus,
	.btn-primary.btn-line:active.focus, .btn-primary.btn-line.active:hover,
	.btn-primary.btn-line.active:focus, .btn-primary.btn-line.active.focus,
	.open>.btn-primary.btn-line.dropdown-toggle:hover, .open>.btn-primary.btn-line.dropdown-toggle:focus,
	.open>.btn-primary.btn-line.dropdown-toggle.focus {
	color: #229c31;
	background-color: #fff;
	border-color: #229c31;
}

.btn-primary.btn-line:active, .btn-primary.btn-line.active, .open>.btn-primary.btn-line.dropdown-toggle
	{
	background-image: none;
}

.btn {
	display: inline-block;
	margin-bottom: 0;
	font-weight: normal;
	text-align: center;
	vertical-align: middle;
	touch-action: manipulation;
	cursor: pointer;
	background-image: none;
	border: 1px solid transparent;
	min-width: 180px;
	height: 44px;
	letter-spacing: -1px;
	font-weight: 700;
	white-space: nowrap;
	padding: 0px 18px;
	font-size: 14px;
	line-height: 42px;
	border-radius: 4px;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.form-group-with-btn>.btn {
	position: absolute;
	top: 0;
	right: 0;
}

.bold {
	font-weight: 700 !important;
}

.btn.btn-small {
	min-width: 100px;
}

/* 가입완료버튼 */
input[type=submit] {
	display: none;
}

#divSearchZip table {
	width: 90%;
	padding: 0px;
	margin: 0px, 0px, 20px, 0px;
	border-top: 2px solid #888;
	color: #707070;
}


table tr th, td {
    background-position: right top;
    background-repeat: no-repeat;
    font-size: 12px;
    color: #656565;
    line-height: 24px;
    padding: 8px 0px 8px 0px;
    margin: 0px;
    border-bottom: 1px solid #656565;
    text-align: center;
    }

#divSearchZip>div {
	overflow: auto;
	clear: both;
	height: 80%;
}
/* 우편번호 창 */
#divSearchZip {
	padding: 8px;
	width: 400px;
	height: 300px;
	position: absolute;
	top: 100px;
	left: 150px;
	border: 1px solid transparent;
	background-color: lightblue;
	display: none;
}

#divSearchZip span{
	display: none;
}

#divSearchZip .search_pop input[type=text] {
	width: 70%;
	height: 36px;
	font-size: 14px;
	line-height: 16px;
	padding: 8px;
	margin: 0px;
	border: 2px solid #ee2e24;
	box-sizing: border-box;
	float: left;
}

#divSearchZip .search_pop button {
	width: 30%;
	height: 36px;
	background-color: #ee2e24;
	font-size: 14px;
	color: #fff;
	font-weight: bold;
	text-align: center;
	line-height: 32px;
	display: block;
	padding: 0px;
	margin: 0px;
	border: 0px;
	border: 2px solid #ee2e24;
	box-sizing: border-box;
	float: left;
}
</style>
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<script>
	$(function() {

		//id입력시 submit버튼 숨김
		var $idObj = $("input[name=id]");
		var $submitObj = $("input[type=submit]");
		$idObj.focus(function() {
			$submitObj.css('display', 'none');
		});

		//중복확인버튼 click이벤트 감시
		var $dupchkObj = $("#dupchk");
		$dupchkObj.click(function() {
			if ($idObj.val().trim().length == 0) {
				$idObj.focus();
				return false; //이벤트 감시문 나간다.
			}
			$.ajax({
				url : '${contextPath}/dupchk',
				method : 'POST',
				data : 'id=' + $idObj.val(),
				success : function(data) {
					if (data.status == 1) {
						alert("이미 존재하는 아이디입니다.");
					} else if (data.status == -1) {//제대로 아이디 입력한 경우
						alert("가입 가능한 아이디입니다.");
						$submitObj.css('display', 'block');
					}
				}
			});
		});

		//우편번호 검색버튼
		var $searchZipBtnObj = $("#searchZip");
		$searchZipBtnObj.click(function() {
			$("#divSearchZip").show();
		});

		//우편번호창에서 행(tr) 클릭이벤트 감시 : 본창의 우편번호, 기본주소에 값 대입, 우편번호창 사라짐		
		$("#divSearchZip>div>table").on("click", "tr:not(:first-child)",
				function() {
					var children = $(this).children();//<td></td><td><span></span><div></div><div></div></td>
					console.log("child"+$(this).html());
					var zipcode = children.eq(0).html();
					$("input[name=zipcode]").val(zipcode);
					//var div1 = $(children).find("div:first-child");
					//var div1 = children.eq(1).children().eq(0).html();
					var juso = $(children).find("div:nth-child(2)");
					//#divSearchZip > div > table > tr:nth-child(3) > td:nth-child(2) > div:nth-child(2)
					$("input[name=addr1]").val(juso.html());
					console.log("juso"+juso.html());
					var bno = $(children).find("span"); //buildingno
					console.log("bno"+bno.html());
					$("input[name=buildingno]").val(bno.html());
					$("#divSearchZip").hide();
				});

		//#divSearch 하위요소로 form객체 찾기 -- 우편번호 검색 실행
		$("#divSearchZip form").submit(function() {
					
					var $doroObj = $("#divSearchZip form input");
					console.log("doroObj는 " + $doroObj.val());
					if ($doroObj.val().trim() == ''){
						$doroObj.focus();
						return false;
					}

					$.ajax({
						url : '${contextPath}/searchzip',
						method : 'POST',
						data : 'doro=' + $("#divSearchZip form input").val(),
						success : function(jsonObjArr) {
							jsonObjArr = JSON.parse(jsonObjArr);
							var str = "<tr><th>우편번호</th><th>주소</th></tr>";
							for (var i = 0; i < jsonObjArr.length; i++) {
								var jsonObj = jsonObjArr[i];
								str += "<tr><td>" + jsonObj.zipcode
								+ "</td><td><span>"+jsonObj.buildingno
								+ "</span><div>" + jsonObj.doro
								+ "</div><div>" + jsonObj.zibun
								+ "</div></td></tr>";
								/* str += "<tr><td>" + jsonObj.zipcode
										+ "</td><td><div>" + jsonObj.doro
										+ "<span>"+jsonObj.buildingno
										+ "</span></div><div>" + jsonObj.zibun
										+ "</div></td></tr>"; */
							}
							$("#divSearchZip>div>table").html(str);
						}
					});
					return false;
				});
		
		//가입완료 버튼 클릭
		$("#submitForm").submit(function(){
			var $pwObj = $("input[name=pwd]");
			var $repwObj = $("input[name=re_pwd]");
			if($pwObj.val() != $repwObj.val()){
				console.log("불일치");
				alert("비밀번호가 일치하지 않습니다!");
				return false;
			}	
			$.ajax({
				url : '${contextPath}/join',
				method : 'POST',
				data :  $("#submitForm").serialize(),
				success : function(data) {
					var msg = "가입";
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

	<form id="submitForm">
		<div style="height: 100%; width: 100%; border: 1px solid;">
			<div class="clearfix mb-15">
				<h6 class="mt-0 mb-0 pull-left">아이디 입력</h6>
				<div class="pull-right input_count" data-target="input_id"
					data-max="15">
					4자 이상의 영문 소문자 기준 <strong class="text-primary count">0</strong> /
					15자
				</div>
			</div>
			<div class="form-group form-group-with-btn mb-30">
				<input type="text" name="id" class="form-control"
					maxlength="15" placeholder="아이디를 입력하세요."><button type="button" id="dupchk"
					class="btn btn-primary btn-line btn-small bold">중복확인</button>
			</div>
			<div class="clearfix mb-15">
				<h6 class="mt-0 mb-0 pull-left">비밀번호</h6>
				<div class="pull-right">
					영문소문자, 숫자 포함 <strong class="text-primary">6</strong>자리 – <strong
						class="text-primary">12</strong>자리
				</div>
			</div>
			<div class="form-group mb-30">
				<input type="password" name="pwd" id="login_pwd"
					class="form-control" placeholder="사용할 비밀번호를 입력하세요." maxlength="12">
				<!-- <button type="button"  class="btn btn-primary btn-line btn-small bold">중복확인</butto\n> -->
			</div>

			<div class="clearfix mb-15">
				<h6 class="mt-0 mb-0 pull-left">비밀번호 확인</h6>
			</div>
			<div class="form-group mb-30">
				<input type="password" name="re_pwd" id="re_login_pwd"
					class="form-control" placeholder="사용할 비밀번호를 입력하세요." maxlength="12">
			</div>
			<div class="clearfix mb-15">
				<h6 class="mt-0 mb-0 pull-left">이름</h6>
			</div>
			<div class="form-group mb-30">
				<input type="text" name="name" id="login_name"
					class="form-control" placeholder="이름을 입력하세요.">
			</div>
			<div class="clearfix mb-15">
				<h6 class="mt-0 mb-0 pull-left">우편번호</h6>
			</div>			
			
			<div class="form-group form-group-with-btn2 mb-30">
				<input type="text" readonly name="zipcode" class="form-control"><button type="button" id="searchZip"
					class="btn btn-primary btn-line btn-small bold">우편번호찾기</button>
			</div>
			<div class="form-group mb-30">
				<input type="text" readonly name="addr1" class="form-control">
			</div>
			<div class="form-group mb-30">
				<input type="text" name="addr2" class="form-control">
			</div>
			<input type="hidden" name="buildingno">


			<div class="form-group mb-30"
				style="margin-top: 10px; margin-bottom: 10px;">
				<input type="reset" value="취소"> <input type="submit"
					value="가입완료">
			</div>

		</div>
	</form>

	<!-- 우편번호 검색용 div -->
	<div id=divSearchZip>
		<form>
			<div class="search_pop">
				<input name="doro" type="text" placeholder="도로명+건물번호">
				<button>우편번호검색</button>
			</div>
		</form>
		<div>
			<table>
				<!-- 크롬브라우저는 tbody태그를 끼워넣음 -->
				<tr>
					<th>우편번호</th>
					<th>주소</th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
