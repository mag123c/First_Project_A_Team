<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/common/table.css">
<link rel="stylesheet" href="/resources/css/board/qna_list.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/common/navbar.jsp" %>
<div id="navArea"></div>


<div id="main_Con">
	<form name="form1">
		<input type="button" value="수정하기" onclick="location.href='/qna/modify/${question_id}'">
		<input type="button" value="삭제하기" onclick="qnaDelete()">
		<input type="hidden" name="question_id" value="${question_id}">
	</form>		
		
	<table>
		<tr id="tHead">
			<td>작성자</td>
			<td>제목</td>
			<td>내용</td>
			<td>답변</td>
			<td>답변일자</td>
		</tr>
		<tr>
			<td>${data.nickname}</td>
			<td>${data.q_subject}</td>
			<td>${data.q_content}</td>
			<c:if test="${data.answer != null}">
				<td>${data.answer}</td>
			</c:if>
			<c:if test="${data.answer == null}">
				<td>답변 준비중</td>
			</c:if>
			<c:if test="${data.a_create_date != null}">
				<td>${data.a_create_date}</td>
			</c:if>
			<td>&nbsp;</td>
		</tr>
	</table>
</div>	
<script>
function qnaDelete(){	
	if(confirm("삭제하시겠습니까?")){		
		document.form1.action="qnaDelete"
		document.form1.submit();
	}
}
</script>
</body>
</html>