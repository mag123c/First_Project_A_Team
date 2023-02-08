package com.test.test1.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.test1.user.dao.UserDao;
import com.test.test1.user.dto.UserDto;
import com.test.test1.user.service.UserService;

@Controller
@RequestMapping("/user/**")
public class UserController {

	@Autowired
	UserService userService;	
	UserDao userDao;
	BCryptPasswordEncoder encoder;
	
	//로그인 페이지 이동 - 01.31 장재호
	@RequestMapping("signin")
	public String login() {
	    return "user/signin";
	}
	
	//로그인 기능  - 01.31 장재호
	//PW -> DB 전송 시 암호화 추가 - 02.06 장재호
	@RequestMapping("signin_check")
	public ModelAndView signin_check(UserDto userDto, HttpSession session, ModelAndView mv, BCryptPasswordEncoder encoder) {
		String str = userService.login(userDto, encoder);   //str : 유저닉네임(email, pw 일치 시 존재) 
		if(str != null) {                          //로그인 성공(세션에 로그인 정보 추가)
			session.setAttribute("user_email", userDto.getEmail());
			session.setAttribute("nickname", str);
			session.setMaxInactiveInterval(60*30); //세션 유지기간 : 30분
			mv.setViewName("redirect:/");
		}else {                                    //로그인 실패
			mv.setViewName("user/signin");
		}
		return mv;
	}
	
	//로그아웃 - 01.31 장재호
	@RequestMapping("sign_out")
	public String logout(HttpSession session) {
		session.invalidate(); // 로그아웃 시 세션 초기화
		return "redirect:/";
	}
	
	//회원가입 페이지 이동 - 01.31 장재호
	@RequestMapping("/signup")
	public String create() {
		return "user/signup";
	}
	
	//이메일 중복 확인 버튼 기능 - 01.31 장재호
	@RequestMapping("emailCheck")
	@ResponseBody //ajax 요청에 담긴 값을 자바 객체로 변환시켜 인스턴스(boolean)에 저장 -> illegalargumentException 방지
	public boolean emailCheck(@RequestParam("email") String email) {
		//DB 들어가서 email 중복값이 있나 들고나옴
		String check = null;
		check = userService.emailCheck(email); //check : email파라미터로 DB조회 결과
		if(check != null) return true;         //중복없음
		else return false;		
	}

	//회원가입 기능 - 01.31 장재호
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public ModelAndView createPost(UserDto userDto, BCryptPasswordEncoder encoder) {
	    //암호화하여 DB에 암호 저장
	    userDto.setPassword(encoder.encode(userDto.getPassword()));
	    
	    boolean tf = userService.create(userDto); //tf : 닉네임 중복여부 boolean
	    ModelAndView mav = new ModelAndView();

	    if (!tf) { //가입 실패
	        mav.addObject("message", "fault");
	        mav.setViewName("user/signup");
	    }else {   //가입 성공
	        mav.addObject("message", "success");
	        mav.setViewName("common/main");      
	    }
	    return mav;
	}
	
	
	//개인 상세 정보 조회 - 01.31 장재호
	@RequestMapping("mydetail")
	public ModelAndView mydetail(HttpSession session, ModelAndView mv) {		
		String user_email = session.getAttribute("user_email").toString();
		List<UserDto> list = userService.mydetail(user_email); //list : 로그인 된 user_email값에 해당하는 개인 정보

		mv.setViewName("user/mydetail");
		mv.addObject("data", list);
		return mv;
	}
	
	//개인 정보 수정 - 01.31 장재호
	@RequestMapping("modify_detail")
	public ModelAndView modify_detail(HttpSession session, UserDto userDto) {

		int modifyTF = userService.modifyDetail(userDto);
		ModelAndView mv = new ModelAndView();
		
		if(modifyTF == -1) {                                         //실패
			mv.addObject("message", "samenick");                     //오류 메세지를 위한 Obj 추가
			return mydetail(session, mv);
		} else {                                                     //성공
			mv.setViewName("common/main");
			mv.addObject("message", "oknick");
			session.setAttribute("nickname", userDto.getNickname()); //성공했으므로 세션 값 변경
		}
		return mv;
	}
	
	//전체조회 - 01.31 장재호
	@RequestMapping("list")
	public ModelAndView list(ModelAndView mv) {
		System.out.println(userService.list().toString());
		mv.addObject("data", userService.list());
		mv.setViewName("/user/list");
		return mv;
	}
}