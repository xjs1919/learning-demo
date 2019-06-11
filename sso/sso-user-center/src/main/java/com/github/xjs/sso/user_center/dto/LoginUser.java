package com.github.xjs.sso.user_center.dto;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年6月11日 上午10:27:46<br/>
 */
public class LoginUser {
	private String username;
	private String password;
	
	public LoginUser() {
	}
	
	public LoginUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
