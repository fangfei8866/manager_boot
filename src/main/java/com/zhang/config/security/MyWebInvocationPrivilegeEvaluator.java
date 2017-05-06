package com.zhang.config.security;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 重写了url 标签的验证的决策器 
 * 
 * 在这里需要加入自己的元数据mySecurityMetadataSource 和 自己的访问控制权  myAccessDecisionManager
 * 不要使用 过滤器 因为如果使用了过滤器是产生拦截多次并且产生不必要的拦截
 * @author hadoop
 *
 */
@Component
public class MyWebInvocationPrivilegeEvaluator implements WebInvocationPrivilegeEvaluator {

	@Autowired
	private MySecurityMetadataSource mySecurityMetadataSource;
	
	@Autowired
	private MyAccessDecisionManager myAccessDecisionManager;
	
	Log logger = LogFactory.getLog(DefaultWebInvocationPrivilegeEvaluator.class);

	public MyWebInvocationPrivilegeEvaluator() {}
 
	public boolean isAllowed(String uri, Authentication authentication) {
		return isAllowed(null, uri, null, authentication);
	}
	 
	public boolean isAllowed(String contextPath, String uri, String method,
			Authentication authentication) {
		Assert.notNull(uri, "uri parameter is required");
		
		
		FilterInvocation fi = new FilterInvocation(contextPath, uri, method);
		Collection<ConfigAttribute> attrs = mySecurityMetadataSource.getAttributes(fi);
		if (attrs == null) {
			return false;
		}
		if (authentication == null) {
			return false;
		}
		try {
			myAccessDecisionManager.decide(authentication, fi,attrs);
		}
		catch (AccessDeniedException unauthorized) {
			return false;
		}
		return true;
	}
}
