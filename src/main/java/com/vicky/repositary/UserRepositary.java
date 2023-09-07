package com.vicky.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicky.entity.User;

public interface UserRepositary extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

	public User findByEmailAndPwd(String email, String pwd);
}
