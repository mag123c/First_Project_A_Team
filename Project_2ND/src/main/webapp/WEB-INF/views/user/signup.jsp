<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<form method="post">
	이메일 : <input type="email" name="email">
	<button type="button" id="btn">아이디 중복체크</button>
	<p id="idcheck"></p>
	비밀번호 : <input type="password" name="password">
	<p id="pwcheck"></p>
	닉네임 : <input type="text" name="nickname">
	<p id="nncheck"></p>
	<c:if test="${message=='fault'}">
		<p id="nncheck2">중복된 닉네임입니다.</p>
	</c:if>	
	휴대폰 번호 : <input type="text" name="phone_num">
	<p id="phcheck"></p>	
	<input type="button" value="가입하기">
	<c:if test="${message=='fault'}">
		<h1>
			가입 실패
		</h1>
	</c:if>
</form>
<script>
	//혹시모를 청개구리의 enter키 입력 방지
	document.addEventListener('keydown', function(e){
		if (e.key === 'Enter'){
			e.preventDefault();
		};
	}, true);
	
	var a=0, b=0, c=0, d=0;

	//가입 버튼이 가입 양식에 일치할 때 가입 받아주기
	$('input[type=button]').on('click',function(e){
		const password = $('input[name=password]').val().length;
		const nickname = $('input[name=nickname]').val().length;
		const phone_num = $('input[name=phone_num]').val().length;
		
		if(password < 4 || password > 20){
			e.preventDefault();
			$('#pwcheck').text('비밀번호는 4~20자로 입력 해 주세요');
		}else {
			a=1;
			$('#pwcheck').text('');
		}
		
		if(nickname < 4 || nickname > 20){
			e.preventDefault();
			$('#nncheck').text('닉네임은 4~20자로 입력 해 주세요');
		}else {
			b=1;
			$('#nncheck').text('');
		}
		
		if(phone_num < 4 || phone_num > 15){
			e.preventDefault();
			$('#phcheck').text('올바른 휴대폰 번호를 입력 해 주세요');
		}else {
			c=1;
			$('#phcheck').text('');		
		}
		
		if(a==1 && b==1 && c==1 && d==1){			
			$('input[type=button]').prop('type', 'submit');
		}
		
	});
	
	$('#btn').on('click',function(){
		let email = $('input[name=email]').val();
		//이메일 올바르게 쓰지 않았을 때 바로 리턴
		if(!email.includes('@')) return $('#idcheck').text('올바른 이메일을 입력 해 주세요');
		//ajax로 email보내기 -> emailCheck 매핑된 곳으로 보내서 중복 확인
		$.ajax({
			url : 'emailCheck',
			type : 'post',
			data : {'email' : email}, // 입력한 email data
			dataType : 'text', 
			success : function(result){
				//result가 text 형태로 와서 false가 text 형태로 비교
				if(result == 'false'){      //false = 사용가능
					$('#idcheck').text('사용 가능한 이메일입니다');
					d=1;
				}else{
					$('#idcheck').text('이미 사용중인 이메일입니다');
				}
			},
			error : function(){
				alert('에러발생 에러발생 ㅌㅌ');
			}
		});
	});
	

</script>
</body>
</html>