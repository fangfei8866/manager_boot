package com.zhang.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller("frameController")
@RequestMapping(value = "/admin")
public class FrameController{
	
	protected static final String ERROR_404 = "/error/404";
	
	
	/**
	 * 跳转到首页
	 * @param map
	 * @return
	 */
	@RequestMapping(value ={"","/","/index"})
	public String index(ModelMap map,HttpServletRequest request) {
		request.getSession().setAttribute("nana", "dsssssssssssssssss");
		return "index";
	} 
	/**
	 * 跳转到首页
	 * @param map
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value ={"test"})
	public  int test(ModelMap map) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return 1/0;
	} 
	
	
}
