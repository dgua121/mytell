package com.baidu.aip.asrwakeup3.uiasr.save;

import java.io.Serializable;
public class ObjectSeri implements Serializable{
	
	//成员变量写成static的话是不能被持久化的
	//private关键字是不能被持久化的，脱离了JVM，成员变量是不在JVM的安全机制之内
	private String name;
	private String sort;
	private String phone;
	private byte[] vido;
	public String getName() {
		return name;
	}
	public String getsort() {
		return sort;
	}
	public void setphone(String name) {
		this.phone = name;
	}
	public String getphone() {
		return phone;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setsort(String sort) {
		this.sort = sort;
	}
	public byte[] getvido() {
		return vido;
	}
	public void setvido(byte[] vido) {
		this.vido = vido;
	}
}
