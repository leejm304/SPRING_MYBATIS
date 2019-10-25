<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<style>
input, textarea{
	width: 50%;
	padding: 10px;
	margin: 10px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function(){
	$("form").submit(function(){
		$.ajax({
			url: "${contextPath}/boardreply",
			method: 'post',
			data: $(this).serialize(),
			success: function(data){
				var jsonObj = JSON.parse(data);
				if(jsonObj.status==1){
					alert("답글 쓰기 성공");
				}else{
					alert(jsonObj.msg);//msg(예외 상세 메세지): 실패의 원인
				}
			}
		});
		return false;
	});
});
</script>
<h2>${param.board_no}글의 답글 쓰기</h2>
<form>
	<input type="hidden" name="parent_no" value="${param.board_no}"><br>
	<input type="text" name="board_subject" placeholder="글제목"><br>
	<input type="text" name="board_writer" placeholder="작성자"><br>
	<input type="password" name="board_pwd" placeholder="글비밀번호"><br>
	<textarea rows="5" cols="20" name="board_content" ></textarea><br>
	<button type="submit">작성</button>
	<button type="reset">CLEAR</button>
</form>