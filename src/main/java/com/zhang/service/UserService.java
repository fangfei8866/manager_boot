package com.zhang.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhang.base.BaseIService;
import com.zhang.po.User;

import tk.mybatis.mapper.entity.Example;

/**
 * 
* @ClassName: UserService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 1032543937@qq.com 
* @date 2017年4月6日 下午5:46:01
 */
public interface UserService extends BaseIService<User> {

	public int insertSelective(User user);
	 
	public int updateByExampleSelective(User user, Example example);

	public PageInfo<User> selectByExampleAndPage(Example example, Page<User> page);

	Map<Integer, User> selectByExample(Example example);

	User selectByAccout(String account);

	public User findUserByName(String username);

}
