package com.tony.demo.mapper;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import com.tony.demo.bean.User;

public interface UserMapper {
	User getById(int id);

	List<User> getAll();

	void createUser(User user);

	List<User> getUsers(Map<String, Object> params, PageBounds pageBounds);

	void batchInsert(List<User> users);
	void createView(@Param(value = "sql") String sql);
}
