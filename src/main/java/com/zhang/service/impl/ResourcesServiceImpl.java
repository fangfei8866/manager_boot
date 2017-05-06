	package com.zhang.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhang.po.Resources;
import com.zhang.service.ResourcesService;

import tk.mybatis.mapper.entity.Example;

@Service("resourcesService")
public class ResourcesServiceImpl extends BaseServiceImpl<Resources> implements ResourcesService {

	@Override
	//@CachePut(region="resources",key="#{id}",cacheType=CacheType.pojo,expire = 2000)
	public Resources selectByKey(Object id) {
		return mapper.selectByPrimaryKey(id);
	}
	 
	@Override
	//@CachePut(region="resources",cacheType=CacheType.page)
	public PageInfo<Resources> selectByExampleAndPage(Example example, Page<Resources> page){
		return  super.selectByExampleAndPage(example,page);
	}
	
	
	@Override
	//@CachePut(region="resources")
	public List<Resources> selectByExample(Example example){
		return mapper.selectByExample(example);
	}
	
	@Override
	public List<Resources> selectAll(){
		return mapper.selectAll();
	}
	
	 

	@Override
	//@CacheClear(region="resources",clearType=ClearType.clearwithpojo,id="#{entity.id}")
	public int updateNotNull(Resources entity) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	//@CacheClear(region="resources",clearType=ClearType.clearbutpojo)
	public int insertSelective(Resources entity) {
		// TODO Auto-generated method stub
		return super.insertSelective(entity);
	}

	@Override
	//@CacheClear(region="resources",clearType=ClearType.clearall)
	public int updateByExampleSelective(Resources resources, Example example) {
		return super.updateByExampleSelective(resources, example);
	}
	
	
	
	
	
	
	
	
	
	
	
}
