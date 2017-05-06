package com.zhang.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhang.config.MyProperities;
import com.zhang.resultdata.RestResponseBody;

/**
 * @author ZHANGWEI5095@QQ.COM
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private MyProperities mypty;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		
		System.out.println(mypty.getXifu()+"#################################");
		return "login";
	}
	
	@RequestMapping("/loginsuccess")
	@ResponseBody
	public RestResponseBody loginsuccess(HttpServletRequest request){
		return RestResponseBody.success();
	}
	
	@RequestMapping("/loginfail")
	@ResponseBody
	public RestResponseBody loginfail(HttpServletRequest request){
		Exception error = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		System.out.println(request.getAttribute("username"));
		if (error != null){
			request.setAttribute("message", error.getMessage());
			return RestResponseBody.failure(error.getMessage());
		}else{
			return RestResponseBody.failure("请联系管理员");
		}
	}

	@RequestMapping({"/403","/denied"})
	public String unauthorizedRole(ModelMap map) {
		logger.info("------没有权限-------");
		map.addAttribute("exception", "------没有权限-------");
		return "/error";
	}
	
}