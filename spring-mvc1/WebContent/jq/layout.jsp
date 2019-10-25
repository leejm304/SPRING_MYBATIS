<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layout.jsp</title>
<style>
header>h1 {
  text-align: center;
}
header>nav>ul>li { 
  display:inline-block;
  margin: 10px;
}
header>nav>ul>li>a{ /*ë°ì¤ì ê±°*/
  text-decoration: none;
}
header>nav>ul>li>a:hover{
  background-color: lightgray;
  font-weight:bold;
}
section{
  width:100%;
  height: 300px;
  /* height:50%; */
  position: relative;
}
section>div{
  float:left;
  width:70%;
  height:100%;
}
article{
 /*display: inline-block;*/
 /* float:left; */
 border:1px solid;
 /* width:300px; */ 
 height:30%; 
 overflow: auto;
 margin-top:10px; 
}
aside{
  border:1px solid;
  background-color: lightgray;
  /*width: 200px;*/
  width: 20%;
  height: 30%;
  /*display: inline-block;*/
  float:right;
}
footer {
  background-color: gray;
  color: white;
  text-align: center;
  padding: 15px; /*ììª½ì¬ë°±*/
  position: absolute;
  left: 0px;
  right: 0px;
  bottom: 0px;
}
img{
width: 100%;
}
</style>

</head>
<body>
<header style="background-color: pink; border: 1px solid">
  <h1>KITRI</h1>
  <nav style="background-color: white;">
    <!-- <ul>
      <li><a href="#">게시판</a></li>
      <li><a href="#">공지사항</a></li>
      <li><a href="login.html">로그인</a></li>
      <li><a href="join.html">가입</a></li>
      <li><a href="display.html">display.html</a></li>
    </ul> -->
    <!-- menu.html 포함하려면  rd.include()가 서블릿이 되어야함
      MVC구조   :객체모델링 패턴
	  SERVLET : 컨트롤러 로직담당 - Control
      [요청(request.getParameter())받아
            비지니스로직호출( service.login())]
      SERVICE/DAO : ★비지니스 로직 담당 - Model
      JSP : 프리젠테이션 로직 담당 - View
    -->
    <jsp:include page="menu.jsp"/>
  </nav>
</header>
<section>
  <div>
    <article>
내용1<br>  내용1<br>
  내용1<br>  내용1<br>
  내용1<br>  내용1<br>
  내용1<br>  내용1<br>
  내용1<br>  내용1<br>
 내용1<br>  내용1<br>
 내용1<br>  내용1<br>  
    </article> 
    <article>내용2</article>
  </div>
  <aside><!-- <img src="../images/KDS.png">I'm K.D.S. --></aside>
</section>
<footer>
  주소:서울시 구로구 디지털로34길 
 연락처: 02-869-8301
</footer>
</body>
</html>