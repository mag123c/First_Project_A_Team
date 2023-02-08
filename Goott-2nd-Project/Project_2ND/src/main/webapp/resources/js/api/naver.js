const naverLogin = new naver.LoginWithNaverId({
	clientId: "XMsvnx3R1_T_0aXB93xf",
	callbackUrl: "http://localhost:8080/naverLogin",
	loginButton: {color: "green", type: 2, height: 40}
});
 naverLogin.init(); // 로그인 설정
 
naverLogin.getLoginStatus(function (status) {
	if (status) {
		const email=naverLogin.user.getEmail();
		const nickName=naverLogin.user.getNickName();
		const age=naverLogin.user.getAge();
		const birthday=naverLogin.user.getBirthday();
	
		//닉네임을 선택하지 않으면 선택창으로 돌아갑니다.
		if(nickName===null||nickName===undefined ){ 
			alert("별명이 필요합니다. 정보제공을 동의해주세요.");
			naverLogin.reprompt(); 
			return ;  
		}else{
			setLoginStatus(); //모든 필수 정보 제공 동의하면 실행하는 함수
			
			$.ajax({
				  type : 'post',
				  url : '/naverLogin',
				  data : {"email" : email, "nickname" : nickName},
				  dataType : 'text'
			})
			
		}
	}
});

console.log(naverLogin);

function setLoginStatus(){

	
	const message_area=document.getElementById('message');
	message_area.innerHTML=`
		<h3> Login 성공 </h3>
		<div>user EMAIL : ${naverLogin.user.email}</div>
		<div>user Nickname : ${naverLogin.user.nickname}</div>
		<div>user Age(범위) : ${naverLogin.user.age}</div>
		<div>user Birthday : ${naverLogin.user.birthday}</div>
		
		`;
	

	const button_area=document.getElementById('button_area');
	button_area.innerHTML="<button id='btn_logout'>로그아웃</button>";

	const logout=document.getElementById('btn_logout');
	logout.addEventListener('click',(e)=>{
		naverLogin.logout();
		location.replace("http://localhost:8080/user/signin");
	})
}