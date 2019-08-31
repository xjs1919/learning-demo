package com.github.xjs.sso.site1.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.github.xjs.sso.site1.vo.ResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年6月11日 上午10:26:07<br/>
 */
@RestController
@RequestMapping("/site1")
public class Site1Controller {
	
	private static final String USER_CENTER_LOGIN_URL = "http://www.usercenter.com/user_center/login?redir_url=%s";
	private static final String USER_CENTER_GET_TOKEN_URL = "http://www.usercenter.com/user_center/getByToken?token=%s";
	private static final String COOKIE_NAME = "site1_tk";
	private static final String PARAM_NAME = "user_center_tk";

	@GetMapping("/getUserInfo")
	public ResultVo getUserInfo(HttpServletRequest request, HttpServletResponse response)throws Exception{
		//首先从cookie中取token
		String tk = getFromCookie(request);
		if(!StringUtils.isEmpty(tk)){
			//验证token有效性
			String username = getFromUserCenter(tk);
			if(!StringUtils.isEmpty(username)){
				return ResultVo.success(username);
			}else{
				String url = createToUserCenterUrl(request);
				return ResultVo.fail(302, "跳转", url);
			}
		}else{
			String url = createToUserCenterUrl(request);
			return ResultVo.fail(302, "跳转", url);
		}
	}

	private String createToUserCenterUrl(HttpServletRequest request)throws Exception{
		String refer = request.getHeader("Referer");
		return String.format(USER_CENTER_LOGIN_URL, URLEncoder.encode(refer, "UTF-8"));
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
	
