<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/common/table.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<td>idx</td>
		<td>이메일</td>
		<td>비밀번호</td>
		<td>닉네임</td>
		<td>휴대폰 번호</td>
		<td>구독여부</td>
		<td>삭제여부</td>
		<td>생성일자</td>
	</tr>
	<c:forEach var="list" items="${data}">
	<tr>
		<td>${list.USER_ID}</td>
		<td>${list.EMAIL}</td>
		<td>${list.PASSWORD}</td>
		<td>${list.NICKNAME}</td>
		<td>${list.PHONE_NUM}</td>
		<td>${list.SUBSCRIBE_YN}</td>
		<td>${list.DELETE_YN}</td>
		<td>${list.CREATE_DATE}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>