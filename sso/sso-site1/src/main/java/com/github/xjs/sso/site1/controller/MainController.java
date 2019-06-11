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
	
	@GetMapping("/main")
	public String main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tk = getFromCookie(request);
		if(tk != null) {
			return "redirect:/main.html";
		}else {
			tk = getFromParam(request);
			if(tk != null) {
				String username = getFromUserCenter(tk);
				if(username != null) {
					Cookie cookie = new Cookie("user_center_tk", tk);
					response.addCookie(cookie);
					return "redirect:/main.html";
				}else {//说明token已经过期，重新登录
					String url = "http://www.site1.com/site1/main";
					return "redirect:http://www.usercenter.com/user_center/login?redir_url="+URLEncoder.encode(url, "UTF-8");
				}
			}else {
				String url = "http://www.site1.com/site1/main";
				return "redirect:http://www.usercenter.com/user_center/login?redir_url="+URLEncoder.encode(url, "UTF-8");
			}
		}
	}
	
	private String getFromUserCenter(String tk) {
		String url = "http://www.usercenter.com/user_center/getByToken?token="+tk;
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

	private String getFromParam(HttpServletRequest request) {
		return request.getParameter("user_center_tk");
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
	
