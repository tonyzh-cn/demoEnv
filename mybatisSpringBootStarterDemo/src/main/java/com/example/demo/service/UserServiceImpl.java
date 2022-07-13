package com.example.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.UserMapper;
import com.example.demo.po.User;
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	@Override
	public List<User> getUsers() {
		return userMapper.getUsers();
	}
	@Override
	@Transactional
	public void methodA() {
//		userMapper.create(new User("A"));
//		methodB();
	}
	private void methodB() {
		userMapper.create(new User("B"));
		throw new RuntimeException();
	}

}
