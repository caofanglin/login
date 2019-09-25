package com.caofl.login.service;

import com.caofl.login.bean.User;
import com.caofl.login.bean.UserInfo;
import com.caofl.login.filter.CommonException;
import com.caofl.login.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public User selectById(Integer id) {
		return userMapper.selectById(id);
	}

	@Override
	public void save(User user) {
		userMapper.save(user);
	}

	@Override
	public List<UserInfo> findAll() {
		return userMapper.findAll();
	}

	@Override
	public void register(User user) throws Exception {
		User exist = userMapper.selectByName(user.getUsername());
		if (exist != null){
			throw new CommonException("用户名已存在");
		}
		userMapper.save(user);
	}

	@Override
	public User selectByName(String username) {
		return userMapper.selectByName(username);
	}

	@Override
	public void saveInfo(UserInfo info) {
		userMapper.saveInfo(info);
	}

	@Override
	public void update(UserInfo info) {
		userMapper.update(info);
	}

	@Override
	public void deleteById(Integer id) {
		userMapper.deleteById(id);
	}
}
