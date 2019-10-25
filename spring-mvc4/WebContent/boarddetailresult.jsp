<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 외부 라이브러리 사용 할때 써야함-->

<%-- <c:set var="contextPath" value="${pageContext.request.contextPath}" /> --%>
<%@include file = "contextPath.jsp" %>

<style>
table {
	border-collapse: separate;
	border-spacing: 1px;
	text-align: left;
	line-height: 1.5;
	border-top: 1px solid #ccc;
	margin: 20px 10px;
	border: solid 1px #ccc;
}

table th {
	width: 150px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
}

table td {
	width: 350px;
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
}

span.underline {
	text-decoration: underline;
	font-weight: bold;
}

#pwdchkdiv {
	padding: 8px;
	height: auto;
	border: 1px solid transparent;
	display: none;
}
</style>

<script>
$(function(){
   var btedit = $("button#edit");//수정 버튼
   var btclear = $("button#clear");
   var pwdchkObj = $("#pwdchkdiv");//비밀번호 확인 div
   var btpwdchk = $("form#pwdchk>button");//비밀번호 확인 창의 확인 버튼
   var whatBtn=null;
   
   btedit.click(function(){//수정 버튼 클릭 -> 비밀번호 확인 창 보이도록
	   whatBtn = $(this);
      pwdchkObj.show();
   });//end click
   
   btclear.click(function(){//삭제 버튼 클릭 -> 비밀번호 확인 창 보이도록
	   whatBtn = $(this);
      pwdchkObj.show();
   });//end click
   
	btpwdchk.click(function(){//확인 버튼 클릭 \
		if (whatBtn ==btedit)//edit경우
		{
			
		}else{ //clear
		
		}
      $.ajax({
          url: "${contextPath}/boardpwdchk",
          method: 'post',
          data:'board_pwd='+$("input[name=iptPwd]").val() + "&" +
                'board_no='+ ${board.board_no},
          success:function(data){
            var jsonObj = JSON.parse(data);
            var msg = "";
            if(jsonObj.status ==1){
               msg += "비밀번호 일치! 수정 가능!";
            }else{
            	console.log(jsonObj.status);
               msg += "비밀번호 불일치! 수정 불가!";
            }
               alert(msg);
          }
       });//end ajax
      return false;
   });//end click 
   
   var btreply = $("button#reply");//답글쓰기 버튼
   btreply.click(function(){//답글쓰기 버튼 클릭 -> 글쓰기 화면
      $.ajax({
         url: "${contextPath}/boardreply.jsp",
          method: 'post',
          data:'board_no='+${param.board_no},
          success:function(data){
             $("section").empty();
             $("section").html(data);
          }
      });//end ajax
   });//end click
   
});
</script>

</head>
<body>
	<c:set var="board" value="${requestScope.board}" />
	<div>
		<h2>게시글</h2>
		<table>
			<tr>
				<td>글번호</td>
				<td>${board.board_no }</td>
			</tr>
			<c:if test="${board.level!=1}">
				<tr>
					<td>부모글번호</td>
					<td>${board.parent_no }</td>
				</tr>
			</c:if>
			<tr>
				<td>제목</td>
				<td>${board.board_subject}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${board.board_writer }</td>
			</tr>
			<tr>
				<td>작성시간</td>
				<td>${board.board_time }</td>
			</tr>
			<tr>
				<td>글내용</td>
				<td>${board.board_content }</td>
			</tr>

		</table>
		<button id="edit">수정</button>
		&nbsp;&nbsp;
		<button id="clear">삭제</button>
		&nbsp;&nbsp;
		<button id="reply">답글쓰기</button>

		<div id="pwdchkdiv">
			<form id="pwdchk">
				비밀번호 : <input type="password" name="iptPwd">
				<button>확인</button>
			</form>
		</div>
	</div>


</body>
</html>