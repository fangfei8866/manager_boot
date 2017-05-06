package com.zhang.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zhang.curator.ReadWriteEnableListener;
import com.zhang.curator.RedisEnableListener;

@Configuration
public class CuratorConfig {
	
	@Bean
	public CuratorFramework curatorFramework(RetryPolicy retryPolicy,@Value("${system.zookeeper.connections}") String connections){
		CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(connections,5000,3000, retryPolicy);
		curatorFramework.start();
		return curatorFramework;
		
	}
	
	@Bean
	public RetryPolicy retryPolicy(){
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
		return retryPolicy;
	}
	
	//应用场景一：监控并同步所有redis开关的数据
	@Bean
	public RedisEnableListener redisEnableListener(@Value("${system.zookeeper.redisbathpath}") String path){
		RedisEnableListener redisEnableListener = new RedisEnableListener(path);
		return redisEnableListener;
	}
	
	//应用场景一：监控并同步所有redis开关的数据
	@Bean
	public ReadWriteEnableListener readWriteEnableListener(@Value("${system.zookeeper.readWrite}") String path){
		ReadWriteEnableListener readWriteEnableListener = new ReadWriteEnableListener(path);
		return readWriteEnableListener;
	}
	
	   

}
