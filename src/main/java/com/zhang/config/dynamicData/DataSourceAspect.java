package com.zhang.config.dynamicData;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zhang.base.MySwitch;
 
/**
 * 切换数据源Advice
 * @version v.0.1
 */
@Aspect
@Order(-10)//保证该AOP在@Transactional之前执行
@Component
public class DataSourceAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
    @Before("@annotation(transactional)")
	public void setReadDataSourceType(JoinPoint joinPoint,Transactional transactional) throws Throwable {
		if(MySwitch.readWriteEnable && transactional.readOnly()){
			DataSourceContextHolder.read();
			 logger.info("dataSource切换到：Read");
		}else{
			DataSourceContextHolder.write();
			logger.info("dataSource切换到：write");
		}
	}
}
