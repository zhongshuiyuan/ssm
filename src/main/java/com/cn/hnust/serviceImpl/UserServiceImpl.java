package com.cn.hnust.serviceImpl;


import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.UserMapper;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	@Cacheable("userCache")//redis保存的key默认为传递的参数，即id
	public User getUserById(int id) {
		
		return this.userMapper.selectByPrimaryKey(id);
	}
	
	@CacheEvict(value="userCache",key="#root.args[0].id")//自定义缓存key为id(root.args形式为数组）
	public void updateUser(User user){
		this.userMapper.updateByPrimaryKey(user);
	}
	
	@CacheEvict("userCache")//userCache缓存名称。被删除的条目的key与传递进来的id参数值相等
	public void deleteUser(int id){
		this.userMapper.deleteByPrimaryKey(id);
	}

	public void saveUser(User user) {
		this.userMapper.insert(user);
	}
	
	

}
