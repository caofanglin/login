package com.caofl.login.controller;

import com.caofl.login.bean.Result;
import com.caofl.login.bean.User;
import com.caofl.login.bean.UserInfo;
import com.caofl.login.constant.Const;
import com.caofl.login.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public Result<User> get(@RequestBody User user){
		Result<User> result = new Result<>();
		String username = user.getUsername();
		String password = user.getPassword();
		System.out.println(username + "---" + password);
		if (StringUtils.isBlank(username)){
			result.setCode(Const.ERROR_CODE);
			result.setDesc("用户名不能为空");
			return result;
		}
		if (StringUtils.isBlank(password)){
			result.setCode(Const.ERROR_CODE);
			result.setDesc("密码不能为空");
			return result;
		}
		User loginUser = userService.selectByName(username);
		if (Objects.isNull(loginUser)){
			result.setCode(Const.ERROR_CODE);
			result.setDesc("账号不存在");
			return result;
		}
		if (!loginUser.getPassword().equals(password)){
			result.setCode(Const.ERROR_CODE);
			result.setDesc("密码不正确");
			return result;
		}
		return result;
	}

	@RequestMapping("/save")
	public Result<Void> save(@RequestBody User user){
		Result<Void> result = new Result<>();
		if (user.getUsername() == null || user.getAge() == null){
			result.setCode(Const.ERROR_CODE);
			result.setDesc("信息不能为空");
			return result;
		}
		userService.save(user);
		return result;
	}

	@RequestMapping("/register")
	public Result<Void> register(@RequestBody User user) throws Exception{
		Result<Void> result = new Result<>();
		String username = user.getUsername();
		String password = user.getPassword();
		if (StringUtils.isBlank(username)){
			result.setCode(Const.ERROR_CODE);
			result.setDesc("用户名不能为空");
			return result;
		}
		if (StringUtils.isBlank(password)){
			result.setCode(Const.ERROR_CODE);
			result.setDesc("密码不能为空");
			return result;
		}
		userService.register(user);
		return result;
	}

	@RequestMapping("/getInfo")
	public Result<List<UserInfo>> getInfo(){
		Result<List<UserInfo>> result = new Result<>();
		result.setData(userService.findAll());
		return result;
	}

	@RequestMapping("/saveInfo")
	public Result<Void> saveInfo(@RequestBody UserInfo info){
		Result<Void> result = new Result<>();
		userService.saveInfo(info);
		return result;
	}

	@RequestMapping("/update")
	public Result<Void> update(@RequestBody UserInfo info){
		Result<Void> result = new Result<>();
		userService.update(info);
		return result;
	}

	@RequestMapping("/deleteById")
	public Result<Void> deleteById(Integer id){
		Result<Void> result = new Result<>();
		userService.deleteById(id);
		return result;
	}


}
