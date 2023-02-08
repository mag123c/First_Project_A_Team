<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/common/common.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/navbar.jsp" %>
<div id="navArea"></div>


<div id="main_Con">
hello
<!-- 로그인 성공 시 -->
<c:if test="${sessionScope.nickname != null}">
	<h1 id="text">
		${sessionScope.nickname}님 환영합니다	
	</h1>
</c:if>

<c:if test="${sessionScope.user_email == null}">
<!-- 회원가입 -->
<input type="button" value="회원가입" onclick="location.href='user/signup'">
<!-- 로그인 로그아웃 -->
<input type="button" value="로그인" onclick="location.href='user/signin'">
</c:if>
<c:if test="${sessionScope.user_email != null}">
<form action="/user/sign_out">
	<input type="submit" value="로그아웃">
</form>
<input type="button" value="마이페이지" onclick="location.href='user/mydetail'">
<input type="button" value="동영상게시판" onclick="location.href='video/list'">
</c:if>
<c:if test="${message=='success'}">
	<h1 id="text">
		가입 성공
	</h1>
</c:if>
<c:if test="${message=='oknick'}">
	<h1 id="text">
		변경 완료
	</h1>
</c:if>
<input type="button" value="회원 조회" onclick="location.href='user/list'">

<c:if test="${sessionScope.user_email != null}">
<!-- QNA -->
<form action="/qna/list">
	<input type="button" value="QNA게시판" onclick="location.href='qna/list'">
</form>
</c:if>
</div>
<script>
	const timer = setTimeout(function(){
		$('#text').text('');
	}, 2000);
</script>
</body>

</html>