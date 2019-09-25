package com.caofl.login.bean;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {

	private Integer id;

	private String name;

	private Date date;

	private String email;

	private String evaluate;

	private String title;

	private String state;
}
