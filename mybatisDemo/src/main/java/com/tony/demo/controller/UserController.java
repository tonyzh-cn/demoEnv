package com.tony.demo.controller;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.tony.demo.util.AsyncSupport;
import com.tony.demo.util.ThreadPoolSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tony.demo.bean.User;
import com.tony.demo.service.UserService;
import com.tony.demo.util.NumberUtil;
import com.tony.demo.util.StringUtil;

import static com.tony.demo.util.ThreadUtil.sleep;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/future")
	@ResponseBody
	public String future() throws Exception {
		long begin = System.currentTimeMillis();
		AsyncSupport support = AsyncSupport.begin("future");

		support.addTask(()->{
			long start = System.currentTimeMillis();
			sleep(1000);
			System.out.println("step1 took "+(System.currentTimeMillis()-start)+"ms");
			return true;
		});

		support.addTask(()->{
			long start = System.currentTimeMillis();
			sleep(1000);
			System.out.println("step2 took "+(System.currentTimeMillis()-start)+"ms");

			return true;
		});

		support.complete();
		return "success";
	}
	@RequestMapping("/future2")
	@ResponseBody
	public String future2() throws Exception {
		List<String> names = new ArrayList<>(Arrays.asList("a","b","c"));

		long begin = System.currentTimeMillis();

		List<CompletableFuture> futures = new ArrayList<>();
		ExecutorService executorService = ThreadPoolSupport.getExecutor("IndexPage");

		futures.add(CompletableFuture.runAsync(() -> {
			long start = System.currentTimeMillis();
			sleep(1000);
			System.out.println("step1 took " + (System.currentTimeMillis() - start) + "ms");

		}, executorService));

		futures.add(CompletableFuture.runAsync(() -> {
			long start = System.currentTimeMillis();
			sleep(2000);
			System.out.println("step2 took " + (System.currentTimeMillis() - start) + "ms");

		}, executorService));

		futures.add(CompletableFuture.runAsync(() -> {
			long start = System.currentTimeMillis();
			sleep(3000);
			System.out.println("step3 took " + (System.currentTimeMillis() - start) + "ms");

		}, executorService));

		CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
		for (CompletableFuture future : futures) {
			future.get();
		}
		System.out.println("total took " + (System.currentTimeMillis() - begin) + "ms");

		return "success";
	}

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
	public String getUser(@RequestParam(required = false) Integer name,@RequestParam(required = false) Integer age) {
//		Map<String,Object> params=new HashMap<String,Object>();
//		params.put("name", name);
//		params.put("age", age);
//		List<User> users = userService.getUsers(params,new PageBounds(1,1));
//		System.out.println(users.toString());
//		return users.toString();

		System.out.println(userService.count());

		return "s";
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
