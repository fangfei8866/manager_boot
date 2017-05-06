	package com.zhang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhang.po.User;
import com.zhang.service.UserService;

import tk.mybatis.mapper.entity.Example;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Override
	//@CachePut(region="user",key="#{id}",cacheType=CacheType.pojo,expire = 2000)
	public User selectByKey(Object id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	//@CachePut(region="user",key="#{account}")
	public User selectByAccout(String account) {
		User user = new User();
		return mapper.selectOne(user);
	}
	 
	@Override
	//@CachePut(region="user",cacheType=CacheType.page)
	public PageInfo<User> selectByExampleAndPage(Example example, Page<User> page){
		return  super.selectByExampleAndPage(example,page);
	}
	
	
	@Override
	//@CachePut(region="user")
	public Map<Integer,User> selectByExample(Example example){
		List<User> list = mapper.selectByExample(example);
		Map<Integer,User> res = new HashMap<Integer,User>();
		for(User g:list){
			res.put(g.getId(), g);
		}
		return  res;
	}
	
	 

	@Override
	//@CacheClear(region="user",clearType=ClearType.clearwithpojo,id="#{entity.id}")
	public int updateNotNull(User entity) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	//@CacheClear(region="user",clearType=ClearType.clearbutpojo)
	@Transactional
	public int insertSelective(User entity) {
		// TODO Auto-generated method stub
		//if(1==1){throw new RuntimeException(){};}
		return super.insertSelective(entity);
	}

	@Override
	//@CacheClear(region="user",clearType=ClearType.clearall)
	public int updateByExampleSelective(User user, Example example) {
		return super.updateByExampleSelective(user, example);
	}

	@Override
	public User findUserByName(String username) {
		if(StringUtils.isNotBlank(username)){
			Example example = new Example(User.class);
			example.createCriteria().andEqualTo("userName", username);
			List<User> list = mapper.selectByExample(example);
			if(list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
}
