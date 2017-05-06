package com.zhang.exception;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MyERRorPage implements ErrorPageRegistrar{

	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		ErrorPage e_403 = new ErrorPage(HttpStatus.FORBIDDEN,"/static/403.html");
		ErrorPage e_404 = new ErrorPage(HttpStatus.NOT_FOUND,"/static/404.html");
		ErrorPage e_500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/static/500.html");
		ErrorPage args = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/static/500.html");
		registry.addErrorPages(e_404,e_500,args);
		
	}

}
