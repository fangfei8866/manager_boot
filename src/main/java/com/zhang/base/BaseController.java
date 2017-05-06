package com.zhang.base;

import com.github.pagehelper.Page;

public class BaseController {
	
	protected static final String ERROR_404 = "/error/404";
	
	
	//获取page
	public Page getPage(Integer curpage){
		return  new Page((curpage ==null || curpage == 0)?1:curpage,10);
	}
	
	//获取page
	public Page getPage(Integer curpage,Integer pagesize){
		return new Page((curpage ==null || curpage == 0)?1:curpage,pagesize);
	}
}
