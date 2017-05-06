package com.zhang.config.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Created by Athos on 2016-10-16.
 */
@Component
public class MyAccessDecisionManager  implements AccessDecisionManager {
	@Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
	
		System.out.println("++++++++++++++++url"+object);
		// 对应url没有权限时，直接跳出方法  
		if(configAttributes == null){   
			throw new AccessDeniedException("没有权限,拒绝访问!");
	       }  
        Iterator<ConfigAttribute> ite = configAttributes.iterator();
      //判断用户所拥有的权限，是否符合对应的Url权限，如果实现了UserDetailsService，则用户权限是loadUserByUsername返回用户所对应的权限 
        while(ite.hasNext()){
            ConfigAttribute ca = ite.next();
            String needRole = (ca).getAttribute();
            System.out.println(authentication.getAuthorities().toString());
            for (GrantedAuthority ga : authentication.getAuthorities()){
            	System.out.println(ga.getAuthority());
                if (needRole.equals(ga.getAuthority())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("没有权限,拒绝访问!");
    }
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
