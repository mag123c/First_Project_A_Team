<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/board/qna_list.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- **스트랩** -->
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<!-- ******* -->
</head>
<body>
<%@ include file="/WEB-INF/views/common/navbar.jsp" %>

<div id="navArea"></div>

<div class="container" id="main_Con">
	<div class="row">
		<form name="form1">
			<select id="selectBox" onChange="getSelect(this)">
			  <option value="NICKNAME">닉네임</option>
			  <option value="SUBJECT">제목</option>
			  <option value="CONTENT">내용</option>
			  <option value="SUBJECT,CONTENT">제목+내용</option>	  
			</select>
			 <input id="optionV" type="hidden" name="option" value="NICKNAME">			
			<input type="text" placeholder="검색" name="keyword" value="${keyword}" />  
			<input id="searchBtn"type="button" value="검색" />  
		</form>
		<div class="col-md-9">
			<table class="table table-list-search">
				<tr id="tHead">
					<td>질문번호</td>
					<td>작성자</td>
					<td>제목</td>
					<td>답변여부</td>
					<td>생성일자</td>
				</tr>
				<c:forEach var="list" items="${data}">
				<tr>
					<td>${list.question_id}</td>
					<td><a href="/qna/list/${list.question_id}">${list.nickname}</a></td>
					<td>${list.q_subject}</td>
					<c:if test="${list.answer!=null}">
						<td>완료</td>
					</c:if>
					<td>진행중</td>		
					<td>${list.q_create_date}</td>
				</tr>
				</c:forEach>
			</table>
			<input type="button" value="질문등록" onclick="location.href='/qna/create'">
		</div>
	</div>
</div>
<script>
//selectbox 선택한 값만 검색 - 02.07 장재호
const hidden = document.querySelector('#optionV');
function getSelect(e){
	const val = e.value;
	hidden.value = val;
}
$('#searchBtn').click(function(){
	console.log($('input[type=text]').val());
	if($('input[type=text]').val() == 'undefined' || $('input[type=text]').val() == ''){		
		alert("검색어를 입력하세요");
		return;
	}
	document.form1.submit();
});
</script>
<footer>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</footer>
</body>
</html>