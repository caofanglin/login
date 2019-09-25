package com.caofl.login.service;

import com.caofl.login.bean.User;
import com.caofl.login.bean.UserInfo;

import java.util.List;

public interface UserService {
	User selectById(Integer id);

	void save(User user);

	List<UserInfo> findAll();

	void register(User user) throws Exception;

	User selectByName(String username);

	void saveInfo(UserInfo info);

	void update(UserInfo info);

	void deleteById(Integer id);
}
