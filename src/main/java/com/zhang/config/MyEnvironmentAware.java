package com.zhang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zhang.base.MySwitch;

@Configuration
public class MyEnvironmentAware implements EnvironmentAware{

	@Value("${system.readWrite.enable}")
	private Boolean readWriteEnable;
	
	@Value("${system.cache.enable}")
	private Boolean isCacheEnable;
	
	@Value("${system.cache.expire.default.enable}")
	private Boolean isCacheExpireEnable;
	
	@Value("${system.cache.expire.default.seconds}")
	private Integer cacheExpireSeconds;
	
	@Override
	public void setEnvironment(Environment environment) {
		//由于boot目前不能初始化设置MySwitch 姑在此设置
		MySwitch.readWriteEnable = readWriteEnable;
		MySwitch.isCacheEnable = isCacheEnable;
		MySwitch.isCacheExpireEnable = isCacheExpireEnable;
		MySwitch.cacheExpireSeconds = cacheExpireSeconds;
		
		
	}

}
