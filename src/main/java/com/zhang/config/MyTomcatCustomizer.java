package com.zhang.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Valve;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.valves.AccessLogValve;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.stereotype.Component;
/**
 * 自定义tomcat的调优
 * @author hadoop
 *
 */
//@Component
public class MyTomcatCustomizer implements EmbeddedServletContainerCustomizer{

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		TomcatEmbeddedServletContainerFactory factory = (TomcatEmbeddedServletContainerFactory)container;
		factory.setPort(8084);
		factory.setBaseDirectory(new File("e:/logs/tomcat"));
		factory.setContextValves(getAccessLogs());
		factory.addConnectorCustomizers(new MyTomcatConnectorCustomizer());
	}

	private List<Valve> getAccessLogs() {
		List<Valve> values = new ArrayList<>();
		AccessLogValve logs = new AccessLogValve();
		logs.setEnabled(true);
		logs.setPrefix("spring-boot");
		logs.setDirectory("e:/logs/log");
		logs.setSuffix(".log");
		values.add(logs);
		return values;
	}
	
	class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer{

		@Override
		public void customize(Connector connector) {
			System.out.println(connector.getProtocolHandlerClassName());
			Http11NioProtocol http = (Http11NioProtocol)connector.getProtocolHandler();
			http.setMaxConnections(2000);
			http.setMaxThreads(500);
			http.setMaxKeepAliveRequests(1);
		}
		
	}

}
