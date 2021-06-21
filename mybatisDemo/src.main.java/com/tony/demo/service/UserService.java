package com.tony.demo.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tony.demo.bean.User;
import com.tony.demo.mapper.UserMapper;

@Service("userService")
public class UserService  {
	@Autowired
	private UserMapper userMapper;

	public void createUser(User user) {
		userMapper.createUser(user);
	}
	public User getById(int id) {
		return userMapper.getById(id);
	}
	@Cacheable(value = "users", key = "{#root.targetClass, #id}")
	public User getCacheableById(int id) {
		return userMapper.getById(id);
	}
	public List<User> getAll() {
		return userMapper.getAll();
	}
	public List<User> getUsers(Map<String, Object> params, PageBounds pageBounds) {
		return userMapper.getUsers(params,pageBounds);
	}

	public void batchInsert(List<User> users) {
		userMapper.batchInsert(users);
	}
	
	public void createView() {
		String sql="CREATE VIEW item_view AS SELECT mi.resource_id,\r\n" + 
				"CONVERT(MAX(CASE rt.fieldname_en WHEN 'Title' THEN mi.text_value END),CHAR(50)) AS Title,\r\n" + 
				"CONVERT(MAX(CASE rt.fieldname_en WHEN 'Year' THEN mi.text_value END),UNSIGNED) AS YEAR,\r\n" + 
				"MAX(CASE rt.fieldname_en WHEN 'Title_alt' THEN mi.text_value END) AS Title_alt,\r\n" + 
				"MAX(CASE rt.fieldname_en WHEN 'Author' THEN mi.text_value END) AS Author,\r\n" + 
				"MAX(CASE rt.fieldname_en WHEN 'Affiliation' THEN mi.text_value END) AS Affiliation,\r\n" + 
				"MAX(CASE rt.fieldname_en WHEN 'Journal_en' THEN mi.text_value END) AS Journal_en\r\n" + 
				"FROM resource_template rt JOIN metadata_item mi ON rt.metadata_field_id = mi.metadata_field_id WHERE rt.resource_type_id=2 AND rt.contenttype_id=1\r\n" + 
				"GROUP BY mi.resource_id";
		userMapper.createView(sql);
	}
}
