<%@page import="com.my.vo.PageBean"%>
<%@page import="com.my.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath();%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<style>
button{
	width: 100px;
	padding: 10px;
	border: none;
	border-radius: 3px;
	background-color: #eee;
}
button.btWrite{
	float: right;
}
div.board>div.tr{ /*행 */
  width:100%;
  clear:both;
  float:left;
}
tr.origin{ /*원글 행 */
  border-top:1px solid;
  margin: 3px;
}
tr.rep{ /*답글 행*/
  border-top:1px dotted;
}
tr:not(:first-child):hover{ /*첫번째 행을 제외한 모든 행의 마우스올림*/ 
  background-color: skyblue;
  font-weight:bold;
}
div.pageGroup{ /*페이지 그룹 */
    text-align: center !important;
}
div.pageGroup>ul{ 
    text-align: center !important;
    display:inline-block;
    padding-left: 0;
    margin: 20px 0;
    border-radius: 2px;
}
div.pageGroup>ul>li{
    display: inline;
}
div.pageGroup>ul>li>a{
    text-decoration: none;
}
table.boardlist {
    border-collapse: separate;
    border-spacing: 0;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
    border-left: 1px solid #ccc;
  margin : 20px 10px;
}
table.boardlist th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    border-top: 1px solid #fff;
    border-left: 1px solid #fff;
    background: #eee;
}
table.boardlist td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function(){
	var $board_no = $("div.board>div.rep>div.board_no");
	$board_no.hover(function(){
		$(this).addClass("tooltip_rep");
	}, function(){
		$(this).removeClass("tooltip_rep");
	});
	
	
    //var $spanArr = $("div.pageGroup > span");
    //$spanArr.click(function(){
    //	var pageNum = $(this).html(); //1,2,3,4,5
    var $aArr = $("div.pageGroup > ul> li> a");	
    $aArr.click(function(){
    	var pageNum = $(this).attr("href"); //1,2,3,4,5
    	$.ajax({
    		url: "<%=contextPath%>/boardlist",
    		data:'currentPage='+pageNum,
    		success:function(data){
    		    $("section").empty();
    		    $("section").html(data);    		
    		}    		
    	});//end ajax  
    	return false;
    });//end click
    
    /*게시물 행 - 클릭 이벤트 연결 -> 글 상세보기*/
    var $trArr = $("tr");
    $trArr.click(function(){
    var $no = $(this).children("td.board_no").children("span").html();
    	$.ajax({
    		url: "${contextPath}/boarddetail",
    		method: 'get',
    		data:'board_no='+$no,
    		success:function(data){
    			$("section").empty();
     		    $("section").html(data); 
    		}
    	});//end ajax  
    });//end click
    
    /*글쓰기 버튼객체 찾기*/
    var $btWriteObj = $("#btWrite");
    $btWriteObj.click(function(){
    	$.ajax({
    		url: "${contextPath}/boardwrite.html",
    		method: 'post',
    		success:function(data){
    			$("section").empty();
     		    $("section").html(data); 
    		}
    	});//end ajax
    });//end click
    
});//end $(function(){
</script>
<c:set var="status" value="${requestScope.status}"/>
<c:if test="${status != 1}">게시물 목록이 없습니다</c:if>
<c:set var="pb" value="${requestScope.pb}"/>
<c:set var="currentPage" value="${pb.currentPage}"/>
<c:set var="maxPage" value="${pb.maxPage}"/>
<c:set var="list" value="${pb.list}"/>
<h2>게시판</h2>
<div>
<button id="btWrite">글쓰기</button>
<table class="boardlist">
	<tr>
		<th class="board_no">글 번호</th>
		<th class="parent_no">부모 글 번호</th>
		<th class="board_subject">제목</th>
		<th class="board_writer">작성자</th>
		<th class="board_time">작성시간</th>
	</tr>	
	<c:forEach var="b" items="${pb.list}">
	<tr class="
		<c:choose>
		<c:when test="${b.level}==1">origin</c:when>
		<c:otherwise>rep</c:otherwise>
		</c:choose>
	">
	<td class="board_no">
		<c:forEach var="i" begin="1" end="${b.level}">
		&nbsp;&nbsp;
		</c:forEach>
		<span>${b.board_no}</span>
	</td>
	<td class="parent_no">${b.parent_no}</td>
	<td class="board_subject">${fn:substring(b.board_subject, 0 , 5)}</td>
	<td class="board_writer">${b.board_writer}</td>
	<td class="board_time"><fmt:formatDate value="${b.board_time}" pattern="yy-MM-dd hh:mm:ss"/></td>  
	<tr>
	</c:forEach>
</table>
</div>
<div class="pageGroup">
<ul> 
<c:set var="startPage" value="${pb.startPage}"/>
<c:set var="endPage" value="${pb.endPage}"/> 
<c:if test="${startPage>1}">
	<li><a href="${startPage-1}"><span>PREV</span></a></li>
</c:if>
<c:forEach var="i" begin="${startPage}" end="${endPage}">
<c:choose>
	<c:when test="i==${currentPage}"><li><span>${i}</span></li></c:when>
	<c:otherwise><li><a href="${i}"><span>${i}</span></a></li></c:otherwise>
</c:choose>	
</c:forEach>
<c:if test="${endPage<pb.maxPage}">
	<li><a href="${endPage+1}"><span>NEXT</span></a></li>
</c:if>
</ul>
</div>
