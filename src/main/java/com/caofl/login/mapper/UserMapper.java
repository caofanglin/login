package com.caofl.login.mapper;

import com.caofl.login.bean.User;
import com.caofl.login.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

	User selectById(@Param("id") Integer id);

	void save(User user);

	List<UserInfo> findAll();

	User selectByName(@Param("username") String username);

	void saveInfo(UserInfo info);

	void update(UserInfo info);

	void deleteById(@Param("id") Integer id);
}
