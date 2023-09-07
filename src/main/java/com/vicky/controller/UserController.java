package com.vicky.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vicky.binder.UserBinder;
import com.vicky.binder.UserLogin;
import com.vicky.constants.AppConstants;
import com.vicky.props.ApplicationProperties;
import com.vicky.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationProperties props;

	@GetMapping("/login")
	public String loadLoginPage(Model model) {

		UserLogin login = new UserLogin();
		model.addAttribute(AppConstants.LOGIN, login);

		return AppConstants.LOGIN_PAGE;

	}

	@PostMapping("/login")
	public String loginMethod(@ModelAttribute(AppConstants.LOGIN) UserLogin login, Model model) {

		Boolean save = userService.login(login);
		if (save) {
			return "redirect:/dashboard";
		} else {
			model.addAttribute(AppConstants.ERR_MSG, props.getMessages().get(AppConstants.LOGIN_INVALID_MSG));
			return AppConstants.LOGIN_PAGE;
		}

	}

	@GetMapping("/signup")
	public String loadSignupPage(Model model) {

		UserBinder signup = new UserBinder();
		model.addAttribute(AppConstants.SIGNUP, signup);

		return AppConstants.SIGNUP_PAGE;

	}

	@PostMapping("/signup")
	public String signupMethod(@ModelAttribute(AppConstants.SIGNUP) UserBinder signup, Model model) {

		boolean status = userService.saveUser(signup);

		if (status) {
			model.addAttribute(AppConstants.SUCC_MSG, props.getMessages().get(AppConstants.SIGNUP_SCC_MSG));
		} else {
			model.addAttribute(AppConstants.ERR_MSG, props.getMessages().get(AppConstants.SIGNUP_ERR_MSG));
		}

		return AppConstants.SIGNUP_PAGE;

	}

	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		UserLogin logout = new UserLogin();
		model.addAttribute(AppConstants.LOGOUT, logout);
		return "redirect:/login";
	}

}
