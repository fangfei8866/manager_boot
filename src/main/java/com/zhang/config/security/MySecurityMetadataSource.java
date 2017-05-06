package com.zhang.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.zhang.po.Resources;
import com.zhang.service.ResourcesService;
import com.zhang.service.RoleResourcesService;

/**
 * Created by Athos on 2016-10-16.
 */
@Component("mySecurityMetadataSource")
public class MySecurityMetadataSource  implements FilterInvocationSecurityMetadataSource {

    private static Map<String,Collection<ConfigAttribute>> resourceMap = null;
    
    private ResourcesService resourcesService;
    
    private RoleResourcesService roleResourcesService;

    /**
     * 构造方法
     */
    //1
    public MySecurityMetadataSource(ResourcesService resourcesService,RoleResourcesService roleResourcesService){
        this.roleResourcesService=roleResourcesService;
        this.resourcesService=resourcesService;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)throws IllegalArgumentException{
    	
    	
    	System.out.println("##################################"+object.toString());
    	loadResourceDefine();
        HttpServletRequest request=((FilterInvocation)object).getRequest();
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()){
            String resURL = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if(requestMatcher.matches(request)){
                return resourceMap.get(resURL);
            }
        }
        return null;
    }
    //4
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        System.out.println("metadata : getAllConfigAttributes");
        return null;
    }
    //3
    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("metadata : supports");
        return true;
    }


    private void loadResourceDefine(){
        /**
         * 因为只有权限控制的资源才需要被拦截验证,所以只加载有权限控制的资源
         * 资源 ---角色 - 多对多的关系
         * 以资源为key  list<role> 为value的组装
         * 
         * 
         */
    	Map<Integer,Set<Integer>> idmaps = roleResourcesService.selectIdMaps();
        List<Resources> Resourceses = resourcesService.selectAll();
        resourceMap = new HashMap<>();
        for (Resources resources:Resourceses){
        	String url = resources.getUrl();
        	Set<Integer> roleids = idmaps.get(resources.getId());
        	//如果该url没有对应的角色 说明他还没分配出去 不允许访问
        	Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
        	if(roleids !=null && !roleids.isEmpty()){
        		for(Integer roleid:roleids){
        			ConfigAttribute ca = new SecurityConfig(roleid.toString());
        			atts.add(ca);
        		}
        	}
        	resourceMap.put(url,atts);
        }
    }
}
