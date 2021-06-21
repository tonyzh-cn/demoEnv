package com.tony.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tony.demo.bean.User;
import com.tony.demo.service.UserService;
import com.tony.demo.util.NumberUtil;
import com.tony.demo.util.StringUtil;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/batchInsert")
	@ResponseBody
	public String batchInsert() {
		List<User> users=new ArrayList<User>();
		for(int i=1;i<=100000;i++) {
			String name="name"+StringUtil.getRandomString(2);
			int age=NumberUtil.getRandomNumber(20, 30);
			users.add(new User(name,age));
		}
		long start=System.currentTimeMillis();
		userService.batchInsert(users);
		long end=System.currentTimeMillis();
		return String.valueOf(end-start);
	}
	
	@RequestMapping("/multiBatchInsert")
	@ResponseBody
	public String multiBatchInsert() {
		List<User> users=new ArrayList<User>();
		for(int i=1;i<=100000;i++) {
			String name="name"+StringUtil.getRandomString(2);
			int age=NumberUtil.getRandomNumber(20, 30);
			users.add(new User(name,age));
		}
		long start=System.currentTimeMillis();
		List<User> tempUsers=new ArrayList<User>();
		int limit=500;
		for(User user:users) {
			tempUsers.add(user);
			if(tempUsers.size()==limit) {
				userService.batchInsert(tempUsers);
				tempUsers.clear();
			}
		}
		if(tempUsers.size()>0) {
			userService.batchInsert(tempUsers);
		}
		long end=System.currentTimeMillis();
		return String.valueOf(end-start);
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public String create(@RequestParam String name,@RequestParam int age) {
		userService.createUser(new User(name,age));
		return "success";
	}
	@RequestMapping("/query")
	@ResponseBody
	public String getUser(@RequestParam(required = false) String name,@RequestParam(required = false) Integer age) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("name", name);
		params.put("age", age);
		List<User> users = userService.getUsers(params,new PageBounds(1,1));
		System.out.println(users.toString());
		return users.toString();
	}
	
	@RequestMapping("/byDao")
	@ResponseBody
	public String byDao() {
		List<Integer> ids=new ArrayList<Integer>(100000);
		for(int i=1;i<=100000;i++) {
			ids.add(i);
		}
		long start=System.currentTimeMillis();
		for(int id:ids) {
			userService.getById(id);
		}
		long end=System.currentTimeMillis();
		System.out.println(end-start);
		return String.valueOf(end-start);
	}
	
	@RequestMapping("/byMap")
	@ResponseBody
	public String byMap() {
		List<Integer> ids=new ArrayList<Integer>(100000);
		for(int i=1;i<=100000;i++) {
			ids.add(i);
		}
		long start=System.currentTimeMillis();
		List<User> users=userService.getAll();
		Map<Integer,User> userMap=new HashMap<Integer,User>();
		for(User user:users) {
			userMap.put(user.getId(), user);
		}
		for(int id:ids) {
			userMap.get(id);
		}
		long end=System.currentTimeMillis();
		System.out.println(end-start);
		return String.valueOf(end-start);
	}
	
	@RequestMapping("/cacheableQuery")
	@ResponseBody
	public String cacheableQuery() {
		long start=System.currentTimeMillis();
		for(int i=1;i<=100000;i++) {
			userService.getCacheableById(1);
		}
		long end =System.currentTimeMillis();
		return String.valueOf(end-start);
	}
	
	@RequestMapping("/nonCacheableQuery")
	@ResponseBody
	public String nonCacheableQuery() {
		long start=System.currentTimeMillis();
		for(int i=1;i<=100000;i++) {
			userService.getById(1);
		}
		long end =System.currentTimeMillis();
		return String.valueOf(end-start);
	}
	
	@RequestMapping("createView")
	@ResponseBody
	public String createView() {
		userService.createView();
		return "success";
		
	}
}
