package com.zhang.config;

import org.apache.catalina.valves.AccessLogValve;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/**
 * 配置tomcat 
 * @author hadoop
 * 
 * 2种方法同MyTomcatCustomizer.class 选一种即可
 *
 */
@SpringBootConfiguration
public class MyTomcatConfiguration {

	@Bean
	public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory(){
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
		factory.addInitializers((servletContext) -> {
			System.out.println("=========servletContext startup==========");
			servletContext.setAttribute("startup", "true");
		});
		factory.addContextValves(getLogAccessLogValve());
		return factory;
	}
	
	private AccessLogValve getLogAccessLogValve(){
		AccessLogValve log = new AccessLogValve();
		log.setDirectory("e:/tmp/logs");
		log.setEnabled(true);
		log.setPattern("%h %l %u %t \"%r\" %s %b");
		log.setPrefix("springboot-access-log");
		log.setSuffix(".txt");
		return log;
	}
}
