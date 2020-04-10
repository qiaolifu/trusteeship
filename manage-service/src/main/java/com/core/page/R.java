package com.core.page;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果对象
 */
@Data
public class R implements Serializable {
	private static final long serialVersionUID = 1L;

	private int code;

	private String msg;

	private Map<String,Object> data;

	public static int OK = 0;

	public static int FAIL = -1;

	private R(){

	}

	private R(int code, String msg, Map<String, Object> data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static R ok(){
		return new R(OK,null,null);
	}

	public static R ok(String msg){
		return new R(OK,msg,null);
	}

	public static R ok(int code){
		return new R(code,null,null);
	}

	public static R ok(int code,String msg){
		return new R(code,msg,null);
	}
	public static R ok(int code,String msg,Map<String,Object> data){
		return new R(code,msg,data);
	}

	public static R fail(){
		return new R(FAIL,null,null);
	}
	public static R fail(String msg){
		return new R(FAIL,msg,null);
	}

	public static R fail(int code,String msg){
		return new R(code,msg,null);
	}
	public static R fail(int code,String msg,Map<String,Object> data){
		return new R(code,msg,data);
	}

	public R put(String key,Object value){
		if(null == data){
			data = new HashMap<>();
		}
		data.put(key,value);
		return this;
	}

	public R putAll(Map<String,Object> datas){
		if(null == data){
			data = new HashMap<>();
		}
		data.putAll(datas);
		return this;
	}



}
