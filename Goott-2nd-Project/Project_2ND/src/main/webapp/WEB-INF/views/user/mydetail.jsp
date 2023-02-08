<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
	<form method="post" action="modify_detail">
	<c:forEach var="list" items="${data}">
		<li>아이디 : <input type="text" name="id" value="${list.ID}" readonly></li>
		<li>이메일 : <input type="email" name="email" value="${list.EMAIL}" readonly></li>
		<li>비밀번호 : <input type="password" name="password" value="${list.PASSWORD}"></li>
		<li>닉네임 : <input type="text" name="nickname" value="${list.NICKNAME}"></li>
		<c:if test="${message=='samenick'}">
			<p>중복된 닉네임이 존재합니다.</p>
		</c:if>		
		<li>휴대전화 : <input type="text" name="phone_num" value="${list.PHONE_NUM}" readonly></li>
	</c:forEach>
	</ul>
	<input type="submit" value="변경하기">
	</form>
</body>
</html>