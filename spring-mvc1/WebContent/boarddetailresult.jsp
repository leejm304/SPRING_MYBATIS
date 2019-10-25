<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String contextPath = request.getContextPath();%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<style>
div.board {
padding: 10px;
}
div.board_content {
background-color: skyblue;
}
form#pwdchk{
border: 1px solid;
padding: 10px;
width: 400px;
margin-top: 20px;
display: none;
}
</style>
<script>
$(function(){
	var btedit = $("button#edit");//수정 버튼
	var btclear = $("button#clear");
	var pwdchkObj = $("form#pwdchk");//비밀번호 확인 창
	var btpwdchk = $("form#pwdchk>button");//비밀번호 확인 창의 확인 버튼
	
	btedit.click(function(){//수정 버튼 클릭 -> 비밀번호 확인 창 보이도록
		pwdchkObj.show();
	});//end click
	
	btclear.click(function(){//삭제 버튼 클릭 -> 비밀번호 확인 창 보이도록
		pwdchkObj.show();
	});//end click
	
	
 	btpwdchk.click(function(){//확인 버튼 클릭 
		$.ajax({
    		url: "${contextPath}/boardpwdchk",
    		method: 'post',
    		data:'iptPwd='+$("input[name=iptPwd]").val() + "&" +
		      	 'board_no='+${param.board_no},
    		success: function(data){
    			var jsonObj = JSON.parse(data);
				var msg = "";
				if(jsonObj.status == 1){
					msg += "비밀번호 일치!";
					alert(msg);
					
				}else{
					msg += "비밀번호 불일치!";
					alert(msg);
				}
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
<c:set var="status" value="${requestScope.status}"/>
<c:if test="${status != 1}">해당 게시글이 없습니다</c:if>
<c:set var="b" value="${requestScope.b}"/>
<c:set var="board_no" value="${b.board_no}"/>
<c:set var="parent_no" value="${b.parent_no}"/>
<c:set var="board_subject" value="${b.board_subject}"/>
<c:set var="board_writer" value="${b.board_writer}"/>
<c:set var="board_time" value="${b.board_time}"/>
<c:set var="board_content" value="${b.board_content}"/>
<h2>게시글</h2>
<div class="board">
<div class="board_no">글번호 : ${board_no}</div>
<div class="parent_no">부모글번호 : ${parent_no}</div>
<div class="board_subject">제목 : ${board_subject}</div>
<div class="board_writer">작성자 : ${board_writer}</div>
<div class="board_time">작성시간 : ${board_time}</div>
<hr>
<div class="board_content">내용 : ${board_content}</div>
</div>
<div>
<button id="edit">수정</button>&nbsp;&nbsp;<button id="clear">삭제</button>&nbsp;&nbsp;<button id="reply">답글쓰기</button>
<div>
	<form id="pwdchk">
		비밀번호 : <input type="password" name="iptPwd">
		<button>확인</button>
	</form>
</div>
</div>


