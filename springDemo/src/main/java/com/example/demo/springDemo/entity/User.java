package com.example.demo.springDemo.entity;

import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class User implements BeanNameAware {
	private long id;
	private String name;
	private String beanName;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", beanName='" + beanName + '\'' +
				'}';
	}

	public static User createUser(){
		User user=new User();
		user.setName("user-by-static-method");
		return user;
	}

	public static User createUser2(){
		User user=new User();
		user.setName("user-by-static-method-2");
		return user;
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	@PostConstruct
	public void init(){
		System.out.println("PostConstruct:"+name);
	}

	@PreDestroy
	public void destroy(){
		System.out.println("PreDestroy:"+name);
	}
}
