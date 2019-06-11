package com.github.xjs.sso.user_center.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.xjs.sso.user_center.dto.LoginUser;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年6月11日 上午10:26:07<br/>
 */
@Controller
@RequestMapping("/user_center")
public class UserController implements InitializingBean{
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	private Map<String, LoginUser> db = new HashMap<String, LoginUser>();
	
	private Map<String, LoginUser> redis = new HashMap<String, LoginUser>();
	
	@GetMapping(value="/login")
	public String to_login(HttpServletRequest request)throws Exception {
		String redir_url = request.getParameter("redir_url");
		String tk = getFromCookie(request);
		if(tk == null) {//去登陆
			return "redirect:/login.html?redir_url="+redir_url;
		}else {//check是否有效
			String username = getByToken(tk);
			if(username == null) {
				return "redirect:/login.html?redir_url="+redir_url;
			}else {
				redir_url = addTokenToUrl(redir_url, tk);
				return "redirect:"+redir_url;
			}
		}
	}
	
	@PostMapping(value="/login")
	public String do_login(LoginUser loginUser, String redirect_url, HttpServletResponse res)throws Exception {
		String username = loginUser.getUsername();
		LoginUser userDB = getFromDB(username);
		if(userDB == null) {
			log.error("用户不存在");
			return "redirect:/login.html?errmsg=user_not_exist&redirect_url="+URLEncoder.encode(redirect_url,"UTF-8");
		}
		String pwdDB = userDB.getPassword();
		if(!pwdDB.equals(loginUser.getPassword())) {
			log.error("密码错误");
			return "redirect:/login.html?errmsg=password_error&redirect_url="+URLEncoder.encode(redirect_url,"UTF-8");
		}
		String tk = UUID.randomUUID().toString();
		redis.put(tk, userDB);
		
		Cookie cookie = new Cookie("user_center_tk", tk);
		cookie.setDomain("www.usercenter.com");
		cookie.setPath("/");
		cookie.setMaxAge(3600);
		res.addCookie(cookie);
		
		redirect_url = addTokenToUrl(redirect_url, tk);
		return "redirect:"+redirect_url;
	}
	
	@GetMapping(value="/getByToken")
	@ResponseBody
	public String getByToken(String token)throws Exception {
		LoginUser user = redis.get(token);
		if(user != null) {
			return user.getUsername();
		}else {
			return null;
		}
	}
	
	
	private String addTokenToUrl(String redirect_url, String tk) {
		if(redirect_url.indexOf("&") >=0 ) {
			return redirect_url + "&user_center_tk=" + tk;
		}else {
			return redirect_url + "?user_center_tk=" + tk;
		}
	}

	private LoginUser getFromDB(String username) {
		return db.get(username);
	}
	
	/**模拟DB中的数据*/
	public void afterPropertiesSet() throws java.lang.Exception{
		db.put("user1", new LoginUser("user1", "123456"));
		db.put("user2", new LoginUser("user2", "123456"));
	}
	
	private String getFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		 if(cookies == null || cookies.length <= 0) {
			return null;
		 }else {
			 for(Cookie cookie : cookies) {
				 String name = cookie.getName();
				 if(name.equals("user_center_tk")) {
					 return cookie.getValue();
				 }
			 }
		 }
		 return null;
	}
}
