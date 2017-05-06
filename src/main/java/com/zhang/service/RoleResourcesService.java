package com.zhang.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zhang.base.BaseIService;
import com.zhang.po.RoleResources;

/**
 * 
* @ClassName: RolereSourcesService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 1032543937@qq.com 
* @date 2017年4月6日 下午5:46:01
 */
public interface RoleResourcesService extends BaseIService<RoleResources> {

	
	Map<Integer, Set<Integer>> selectIdMaps();
	

	 

	 
	

}
