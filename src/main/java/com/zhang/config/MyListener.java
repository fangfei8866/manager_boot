package com.zhang.config;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import com.zhang.curator.RedisEnableListener;

@WebListener
public class MyListener implements ServletRequestListener{


	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("销毁");
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("过滤执行中");
		
	}

}
