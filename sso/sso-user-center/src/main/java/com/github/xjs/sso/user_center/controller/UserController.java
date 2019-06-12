package com.github.xjs.sso.user_center.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	private static final String PARAM_NAME = "user_center_tk";
	private static final String COOKIE_NAME = "user_center_tk";
	
	@GetMapping(value="/login")
	public String to_login(HttpServletRequest request, Model model)throws Exception {
		String redir_url = request.getParameter("redir_url");
		String tk = getFromCookie(request);
		if(tk == null) {//去登陆
			model.addAttribute("redir_url", redir_url);
			return "login";
		}else {//check是否有效
			String username = getByToken(tk);
			if(username == null) {
				model.addAttribute("redir_url", redir_url);
				return "login";
			}else {//如果有效，直接跳回去
				redir_url = addTokenToUrl(redir_url, tk);
				return "redirect:"+redir_url;
			}
		}
	}
	
	@PostMapping(value="/login")
	public String do_login(LoginUser loginUser, String redirect_url, HttpServletResponse res, Model model)throws Exception {
		String username = loginUser.getUsername();
		LoginUser userDB = getFromDB(username);
		if(userDB == null) {
			model.addAttribute("errmsg", "用户不存在");
			model.addAttribute("redir_url", redirect_url);
			return "login";
		}
		String pwdDB = userDB.getPassword();
		if(!pwdDB.equals(loginUser.getPassword())) {
			model.addAttribute("errmsg", "密码错误");
			model.addAttribute("redir_url", redirect_url);
			return "login";
		}
		//存redis，注意要设置有效期
		String tk = UUID.randomUUID().toString();
		redis.put(tk, userDB);
		//生成用户中心的cookie
		Cookie cookie = new Cookie(COOKIE_NAME, tk);
		cookie.setDomain("www.usercenter.com");
		cookie.setPath("/");
		cookie.setMaxAge(Integer.MAX_VALUE);
		res.addCookie(cookie);
		//跳转回去
		redirect_url = addTokenToUrl(redirect_url, tk);
		return "redirect:"+redirect_url;
	}
	
	@GetMapping(value="/getByToken")
	@ResponseBody
	public String getByToken(String token)throws Exception {
		//这里注意：延长下redis的有效期
		LoginUser user = redis.get(token);
		if(user != null) {
			return user.getUsername();
		}else {
			return null;
		}
	}
	
	private String addTokenToUrl(String redirect_url, String tk) {
		if(redirect_url.indexOf("&") >=0 ) {
			return redirect_url + "&"+PARAM_NAME+"=" + tk;
		}else {
			return redirect_url + "?"+PARAM_NAME+"=" + tk;
		}
	}
	
	private String getFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		 if(cookies == null || cookies.length <= 0) {
			return null;
		 }else {
			 for(Cookie cookie : cookies) {
				 String name = cookie.getName();
				 if(name.equals(COOKIE_NAME)) {
					 return cookie.getValue();
				 }
			 }
		 }
		 return null;
	}
	
	private LoginUser getFromDB(String username) {
		return db.get(username);
	}
	
	/**模拟数据库*/
	private Map<String, LoginUser> db = new HashMap<String, LoginUser>();
	/**模拟redis*/
	private Map<String, LoginUser> redis = new HashMap<String, LoginUser>();
	/**模拟DB中的数据*/
	public void afterPropertiesSet() throws java.lang.Exception{
		db.put("user1", new LoginUser("user1", "123456"));
		db.put("user2", new LoginUser("user2", "123456"));
	}
	
}
