package com.vicky.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vicky.binder.UserBinder;
import com.vicky.binder.UserLogin;
import com.vicky.constants.AppConstants;
import com.vicky.entity.User;
import com.vicky.repositary.UserRepositary;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private BCryptPasswordEncoder pzzwdEncoder;

	@Autowired
	private  UserRepositary userRepo;

	@Autowired
	private  HttpSession session;

	
	
	@Override
	public Boolean login(UserLogin login) {
 
		User user = userRepo.findByEmail(login.getEmail());
		if(user != null && pzzwdEncoder.matches(login.getPwd(), user.getPwd())) {
			session.setAttribute(AppConstants.USER_ID, user.getId());
			return true;
			
		}
		return false;
	}

	
	@Override
	public Boolean saveUser(UserBinder userBinder) {
		User user2 = userRepo.findByEmail(userBinder.getEmail());

		if (user2 != null) {
			return false;

		}
		User user = new User();
		BeanUtils.copyProperties(userBinder, user);
		user.setPwd(pzzwdEncoder.encode(userBinder.getPwd()));
		userRepo.save(user);
		return true;
	}

}
