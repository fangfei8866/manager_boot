package com.zhang.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="mypty")
public class MyProperities {
	
	private String xifu;

	public String getXifu() {
		return xifu;
	}

	public void setXifu(String xifu) {
		this.xifu = xifu;
	}
	
	

}
