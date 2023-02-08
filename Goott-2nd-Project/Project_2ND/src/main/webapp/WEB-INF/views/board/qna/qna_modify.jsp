<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/board/qna_create.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/navbar.jsp" %>
	<!-- navArea : navbar 여백 잡는 공간 -->
	<div id="navArea"></div>
	
	<form name="form1" method="post">
		<!-- 전체 컨테이너 -->
		<div id="main_Con">
			<div id="title_Con"> 
				<h1>제목</h1>
				<textarea id="title" name="q_subject"></textarea>
			</div>
			<div id="content_Con">
				<h1>내용</h1>
				<textarea id="content" name="q_content"></textarea>
			</div>
		</div>
		<input type="button" value="등록" onclick="qModify()">
	</form>
</body>
<script>
const title = document.querySelector('textarea[id=title]');
const content = document.querySelector('textarea[id=content]');

// 등록 버튼 클릭 시 
function qModify(){
	if(title.value == "") {
		alert("제목을 입력하세요");
		document.form1.q_subject.focus();
		return;
	}
	if(content.value == ""){
		alert("내용을 입력하세요");
		document.form1.q_content.focus();
		return;
	}
	document.form1.submit();	
}
</script>
</html>