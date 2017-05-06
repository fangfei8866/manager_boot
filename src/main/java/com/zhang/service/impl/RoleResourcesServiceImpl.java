package com.zhang.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import com.zhang.po.RoleResources;
import com.zhang.service.RoleResourcesService;

@Service("roleResourcesService")
public class RoleResourcesServiceImpl extends BaseServiceImpl<RoleResources> implements RoleResourcesService {

	@Override
	public Map<Integer, Set<Integer>> selectIdMaps() {
		List<RoleResources> list = mapper.selectAll();
		Map<Integer,Set<Integer>> res = new HashMap<Integer,Set<Integer>>();
		for(RoleResources rs:list){
			if(res.containsKey(rs.getResourceId())){
				Set<Integer> value = res.get(rs.getResourceId());
                value.add(rs.getRoleId());
                res.put(rs.getResourceId(),value);

            }else {
            	Set<Integer> value = new HashSet<Integer>();
                value.add(rs.getRoleId());
                res.put(rs.getResourceId(),value);
            }
		}
		return res;
	}

	 

}
