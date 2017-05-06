package com.zhang.config;

import org.springframework.scheduling.annotation.Scheduled;

//@Configuration
//@EnableScheduling
public class SchedulingConfig {
	
	@Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
	public void scheduler(){
		System.out.println("######################################################");
		
	}

}
