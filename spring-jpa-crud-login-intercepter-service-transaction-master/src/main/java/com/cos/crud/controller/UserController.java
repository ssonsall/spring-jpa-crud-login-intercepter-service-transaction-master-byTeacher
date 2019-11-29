package com.cos.crud.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.crud.model.User;
import com.cos.crud.service.UserService;
import com.cos.crud.utils.Script;

@Controller
public class UserController {

	@Autowired
	private UserService mService;


	// 사용자가 원하는 데이터 = 나의 정보를 10번 찍어달라고함.
	// http://localhost:8080/user/1
	// form 이용 => name값에 id를 삽입
	// http://localhost:8080/user?id=1
	@GetMapping("/user/{id}")
	public @ResponseBody List<User> getUser(@PathVariable int id) {
		return mService.getUser(id);
	}

	@PostMapping("/user/login")
	public @ResponseBody String userLogin(User user, HttpSession session) {
		User u = mService.userLogin(user);
		if (u != null) {
			session.setAttribute("user", u);
			return Script.href("/board/list");
		} else {
			return Script.back("로그인 실패");
		}
	}

	@GetMapping("/user/loginForm")
	public String userLoginForm() {
		return "user/loginForm";
	}

	@PostMapping("/user/join")
	public String userJoin(User user) {
		int result = mService.userJoin(user);
		if (result == 1) {
			return "redirect:/board/list";
		} else {
			return "redirect:/user/joinForm";
		}
	}

	@GetMapping("/user/joinForm")
	public String userJoinForm() {
		return "user/joinForm";
	}

	@GetMapping("/user/logout")
	public String userLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/board/list";
	}
}
