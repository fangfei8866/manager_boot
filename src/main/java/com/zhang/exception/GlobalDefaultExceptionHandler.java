package com.zhang.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.zhang.base.Constants;


@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	  private Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultExceptionHandler(HttpServletRequest request,Exception exception) {
		logger.debug("execpiton"+exception.getMessage());
		/*
        * 返回视图：
        * 定义一个ModelAndView即可，
        * 然后return;
        * 定义视图文件(比如：error.html,error.ftl,error.jsp);
        *
        */
		ModelAndView mav = new ModelAndView();
       mav.addObject("exception", exception);
       mav.addObject("url", request.getRequestURL());
       mav.setViewName(Constants.DEFAULT_ERROR_VIEW);
       return mav;
	}

}
