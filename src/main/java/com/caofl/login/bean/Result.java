package com.caofl.login.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

	private Integer code=200;

	private T data;

	private String desc = "success";
}
