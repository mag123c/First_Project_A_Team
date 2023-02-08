<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/common/table.css">
<link rel="stylesheet" href="/resources/css/board/qna_list.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/navbar.jsp" %>
<div id="navArea"></div>

<div id="main_Con">
	<table>
		<tr id="tHead">
			<td>질문번호</td>
			<td>작성자</td>
			<td>제목</td>
			<td>답변여부</td>
			<td>생성일자</td>
		</tr>
		<c:forEach var="list" items="${data}">
		<tr>
			<td>${list.QUESTION_ID}</td>
			<td><a href="/qna/list/${list.QUESTION_ID}">${list.NICKNAME}</a></td>
			<td>${list.Q_SUBJECT}</td>
			<c:if test="${list.ANSWER!=null}">
				<td>완료</td>
			</c:if>
			<td>진행중</td>		
			<td>${list.Q_CREATE_DATE}</td>
		</tr>
		</c:forEach>
		<input type="button" value="질문등록" onclick="location.href='/qna/create'">
	</table>
</div>	
</body>
</html>