<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- Google -->
<meta name="google-signin-client_id" content="967788525797-huq32simk5h35o6faii7nlbi86822e6l.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
<!-- Google -->
<!-- kakao -->
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<!-- kakao -->
<!-- naver -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js"charset="utf-8"></script>
<link rel="stylesheet" href="/resources/css/api/naver.css">
<!-- naver -->
<body>
<form method="post" action="signin_check">
	이메일 : <input type="email" name="email">
	비밀번호 : <input type="password" name="password">
	<input type="submit" value="로그인">
</form>
	<!-- kakao -->
	<ul>
		<li onclick="kakaoLogin();">
			<a href="javascript:void(0)"><span>카카오 로그인</span></a>
		</li>
		<li onclick="kakaoLogout();">
			<a href="javascript:void(0)"><span>카카오 로그아웃</span></a>
		</li>
	</ul>

	<!-- google -->
	<ul>
		<li id="GgCustomLogin">
			<a href="javascript:void(0)"><span>Login with Google</span></a>
		</li>
	</ul>


    <!-- 네이버 로그인 버튼 노출 영역 -->
	<div class="container">
		<h1>Naver Login API 사용하기</h1>
			<div class="login-area">
				<div id="message">로그인 버튼을 눌러 로그인 해주세요.</div>
			<div id="button_area"> 
				<div id="naverIdLogin"></div>
			</div>
		</div>
	</div>
	
<script src="/resources/js/api/google.js"></script>
<script src="/resources/js/api/kakao.js"></script>
<script src="/resources/js/api/naver.js"></script>
</body>
</html>