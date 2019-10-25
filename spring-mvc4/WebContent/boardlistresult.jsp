<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.my.vo.Board"%>
<%@ page import="com.my.vo.PageBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 외부 라이브러리 사용 할때 써야함-->
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<%-- <c:set var="contextPath" value="${pageContext.request.contextPath}" /> --%>
<%@include file = "contextPath.jsp" %>

<style>
h2 {
	text-align: center;
}

div.board {
	width: 100%;
	height: 50%;
}

div.board_no {
	text-align: center;
	width: 100%;
	height: 10%;
}

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

h1 {
	text-align: center;
}

span.underline {
	text-decoration: underline;
	font-weight: bold;
}

tr:not(.header):hover {
	background-color: #cce5ff;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(function() {
		//글 상세보기
		var $trArr = $("div.board tr:not(.header)");
		/* body > section > div.board > table > tbody > tr:nth-child(2) */
		$trArr.click(function() {
			var children = $(this).children();
			var board_no = children.eq(0).children("span").html()//${b.board_no}
			$.ajax({
				url : "${contextPath}/boarddetail",
				data : 'board_no=' + board_no,
				success : function(data) {
					$("section").empty();
					$("section").html(data);
				}
			}); //end ajax  			
		});//end click
		
		//페이지이동 
		var $spanArr = $("div.board_no > span");
		$spanArr.click(function() {
			var pageNum;
			if ($(this).html() == 'Next') {
				pageNum = parseInt($(this).prev().html()) + 1;
			} else if ($(this).html() == 'Prev') {
				pageNum = parseInt($(this).next().html()) - 1;
			} else {
				pageNum = $(this).html();
			}
			$.ajax({
				url : "${contextPath}/boardlist",
				data : 'currentPage=' + pageNum,
				success : function(data) {
					$("section").empty();
					$("section").html(data);
				}
			});//end ajax  
		});//end click
		
		var $btWriteObj = $("#btWrite");
		$btWriteObj.click(function(){
			$.ajax({
				url: '${contextPath}/boardwrite.jsp',
				success:function(data){
					$("section").empty();
					$("section").html(data);
				}
			});
		});
	});//end $(function(){
</script>
<body>
	<c:set var="status" value="${requestScope.status}" />
	<c:choose>
		<c:when test="${status}!=1">게시물 목록이 없습니다.</c:when>
		<c:otherwise>
			<c:set var="pb" value="${requestScope.pb}" />
			<c:set var="currentPage" value="${pb.currentPage}" />
			<c:set var="maxPage" value="${pb.maxPage}" />
			<c:set var="list" value="${pb.list}" />
			<h2>게시판</h2>
			<button id = "btWrite">글쓰기</button>
			<div class="board">
				<table>
					<tr class="header">
						<th>게시물번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>작성시간</th>
					</tr>
					<c:forEach var="b" items="${list}">
						<tr>
							<td><c:forEach begin="1" end="${b.level-1}">&nbsp;&nbsp;</c:forEach>							
							<span>${b.board_no}</span></td>							 
							<%-- <td>${b.board_subject}</td> --%>
							<td>${fn:substring(b.board_subject , 0, 5)}</td>
							<td>${b.board_writer}</td>
							<%-- <td>${b.board_time}</td> --%>
							<td><fmt:formatDate value="${b.board_time}" pattern="yy-MM-dd hh:mm:ss"/></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class='board_no'>
				<c:if test="${pb.startPage!=1}">
					<span>Prev</span>
				</c:if>
				<c:forEach var="i" begin="${pb.startPage}" end="${pb.endPage}">
					<c:choose>
						<c:when test="${i==currentPage}">
						[<span class=pageNum>${i}</span>]&nbsp;&nbsp;
					</c:when>
						<c:otherwise>
						[<span class="underline">${i}</span>]&nbsp;&nbsp;
					</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pb.endPage!=pb.maxPage}">
					<span>Next</span>
				</c:if>
			</div>
		</c:otherwise>
	</c:choose>
</body>