package com.cn.hnust.service;

import com.cn.hnust.pojo.User;

public interface UserService {

	public User getUserById(int id);
	
	public void updateUser(User user);
	
	public void deleteUser(int id);
	
	public void saveUser(User user);
}
