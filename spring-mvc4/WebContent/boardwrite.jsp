<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file = "contextPath.jsp" %>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function(){
	$("form").submit(function(){
		alert("submit");
		$.ajax({
			url : '../boardwrite',
			method:'post',
			data:$(this).serialize(),
			success:function(data){
				var jsonObj = JSON.parse(data);
	            if(jsonObj.status==1){
					alert(jsonObj.msg);
					location.href="layout.jsp";
				}else alert(jsonObj.msg);
		     }
	   });//end ajax
	      return false;
	});
});
</script>

<h2>게시물 쓰기</h2>
<form>
	<input type="text" name="board_subject" placeholder="제목을 입력하세요."><br>
	<input type="text" name="board_writer" placeholder="글쓴이를 입력하세요."><br>
	<input type="password" name="board_pwd" placeholder="비밀번호를 입력하세요."><br>
	<textarea rows="3" cols="20" name="board_content"></textarea>
	<br>
	<button type="submit">글쓰기</button>
	<button type="reset">비우기</button>
</form>