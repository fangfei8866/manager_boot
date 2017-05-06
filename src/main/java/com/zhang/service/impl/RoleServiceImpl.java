	package com.zhang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhang.po.Role;
import com.zhang.service.RoleService;

import tk.mybatis.mapper.entity.Example;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Override
	//@CachePut(region="role",key="#{id}",cacheType=CacheType.pojo,expire = 2000)
	public Role selectByKey(Object id) {
		return mapper.selectByPrimaryKey(id);
	}
	 
	@Override
	//@CachePut(region="role",cacheType=CacheType.page)
	public PageInfo<Role> selectByExampleAndPage(Example example, Page<Role> page){
		return  super.selectByExampleAndPage(example,page);
	}
	
	
	@Override
	//@CachePut(region="role",cacheType=CacheType.list)
	public Map<Integer,Role> selectByExample(Example example){
		List<Role> list = mapper.selectByExample(example);
		Map<Integer,Role> res = new HashMap<Integer,Role>();
		for(Role g:list){
			res.put(g.getId(), g);
		}
		return  res;
	}
	
	 

	@Override
	//@CacheClear(region="role",clearType=ClearType.clearwithpojo,id="#{entity.id}")
	public int updateNotNull(Role entity) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	//@CacheClear(region="role",clearType=ClearType.clearbutpojo)
	public int insertSelective(Role entity) {
		// TODO Auto-generated method stub
		return super.insertSelective(entity);
	}

	@Override
	//@CacheClear(region="role",clearType=ClearType.clearall)
	public int updateByExampleSelective(Role role, Example example) {
		return super.updateByExampleSelective(role, example);
	}
	
	
	
	
	
	
	
	
	
	
	
}
