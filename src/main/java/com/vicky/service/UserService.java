package com.vicky.service;

import org.springframework.stereotype.Service;

import com.vicky.binder.UserBinder;
import com.vicky.binder.UserLogin;

@Service
public interface UserService {

	public Boolean login(UserLogin login);

	public Boolean saveUser(UserBinder userBinder);

}
