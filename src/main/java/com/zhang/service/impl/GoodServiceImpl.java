	package com.zhang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhang.po.Good;
import com.zhang.service.GoodService;

import tk.mybatis.mapper.entity.Example;

@Service("goodService")
public class GoodServiceImpl extends BaseServiceImpl<Good> implements GoodService {
	
	@Override
	//@CachePut(region="good",key="#{id}",cacheType=CacheType.pojo,expire = 2000)
	@Transactional(readOnly=true)
	public Good selectByKey(Object id) {
		return mapper.selectByPrimaryKey(id);
	}
	 
	@Override
	//@CachePut(region="good",cacheType=CacheType.page)
	@Transactional(readOnly=true)
	public PageInfo<Good> selectByExampleAndPage(Example example, Page<Good> page){
		PageInfo<Good> list = super.selectByExampleAndPage(example,page);
		return  list;
	}
	
	
	@Override
	//@CachePut(region="good")
	@Transactional(readOnly=true)
	public Map<Integer,Good> selectByExample(Example example){
		List<Good> list = mapper.selectByExample(example);
		Map<Integer,Good> res = new HashMap<Integer,Good>();
		for(Good g:list){
			res.put(g.getId(), g);
		}
		return  res;
	}
	
	 

	@Override
	//@CacheClear(region="good",clearType=ClearType.clearwithpojo,id="#{entity.id}")
	@Transactional
	public int updateNotNull(Good entity) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	//@CacheClear(region="good",clearType=ClearType.clearbutpojo)
	@Transactional
	public int insertSelective(Good entity) {
		// TODO Auto-generated method stub
		return super.insertSelective(entity);
	}

	@Override
	//@CacheClear(region="good",clearType=ClearType.clearall)
	@Transactional
	public int updateByExampleSelective(Good good, Example example) {
		return super.updateByExampleSelective(good, example);
	}
	
	
	
	
	
	
	
	
	
	
	
}
