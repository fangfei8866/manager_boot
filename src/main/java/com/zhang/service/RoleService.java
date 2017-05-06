package com.zhang.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhang.base.BaseIService;
import com.zhang.po.Role;

import tk.mybatis.mapper.entity.Example;

/**
 * 
* @ClassName: RoleService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 1032543937@qq.com 
* @date 2017年4月6日 下午5:46:01
 */
public interface RoleService extends BaseIService<Role> {

	public int insertSelective(Role role);
	 
	public int updateByExampleSelective(Role role, Example example);

	public PageInfo<Role> selectByExampleAndPage(Example example, Page<Role> page);

	Map<Integer, Role> selectByExample(Example example);

	 
	

}
