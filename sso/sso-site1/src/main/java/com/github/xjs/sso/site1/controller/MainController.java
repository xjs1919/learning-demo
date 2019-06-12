package com.github.xjs.sso.site1.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年6月11日 上午10:26:07<br/>
 */
@Controller
@RequestMapping("/site1")
public class MainController{
	
	private static final String USER_CENTER_LOGIN_URL = "http://www.usercenter.com/user_center/login?redir_url=%s";
	private static final String USER_CENTER_GET_TOKEN_URL = "http://www.usercenter.com/user_center/getByToken?token=%s";
	private static final String COOKIE_NAME = "site1_tk";
	private static final String PARAM_NAME = "user_center_tk";
	
	@GetMapping("/main")
	public String main(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		//首先从cookie中取token
		String tk = getFromCookie(request);
		if(tk != null) {
			//验证token有效性
			String username = getFromUserCenter(tk);
			if(username != null) {
				model.addAttribute("username", username);
				return "main";
			}else {
				//如果失效，重新登录
				String url = getFullUrl(request);
				return "redirect:"+String.format(USER_CENTER_LOGIN_URL, URLEncoder.encode(url, "UTF-8"));
			}
		}else {//从参数中取，如果从用户中心跳转回来，会在参数中传递token
			tk = getFromParam(request);
			if(tk != null) {
				String username = getFromUserCenter(tk);
				if(username != null) {
					//生成自己域下面的cookie
					Cookie cookie = new Cookie(COOKIE_NAME, tk);
					cookie.setMaxAge(Integer.MAX_VALUE);
					response.addCookie(cookie);
					//进入主页
					model.addAttribute("username", username);
					return "main";
				}else {//说明token已经过期，重新登录
					String url = getFullUrl(request);
					return "redirect:"+String.format(USER_CENTER_LOGIN_URL, URLEncoder.encode(url, "UTF-8"));
				}
			}else {
				String url = getFullUrl(request);
				return "redirect:"+String.format(USER_CENTER_LOGIN_URL, URLEncoder.encode(url, "UTF-8"));
			}
		}
	}
	
	private String getFromUserCenter(String tk) {
		String url = String.format(USER_CENTER_GET_TOKEN_URL, tk);
		try {
			HttpURLConnection conn =	(HttpURLConnection)((new URL(url).openConnection()));
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = conn.getInputStream();
			int len = 0;
			byte buff[] = new byte[1024];
			while((len = in.read(buff)) >= 0) {
				out.write(buff,0,len);
			}
			out.close();
			in.close();
			return new String(out.toByteArray(), "UTF-8");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getFullUrl(HttpServletRequest request) {
		String reqUrl = request.getRequestURL().toString();
		String query = request.getQueryString();
		String url = reqUrl;
		if (query != null && query.length() > 0) {
			url = url + "?" + query;
		}
		return url;
	}

	private String getFromParam(HttpServletRequest request) {
		return request.getParameter(PARAM_NAME);
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
	
}
	
