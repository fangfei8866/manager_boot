package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhang.base.BaseIService;
import com.zhang.po.Resources;

import tk.mybatis.mapper.entity.Example;

/**
 * 
* @ClassName: ResourcesService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 1032543937@qq.com 
* @date 2017年4月6日 下午5:46:01
 */
public interface ResourcesService extends BaseIService<Resources> {

	public int insertSelective(Resources resources);
	 
	public int updateByExampleSelective(Resources resources, Example example);

	public PageInfo<Resources> selectByExampleAndPage(Example example, Page<Resources> page);

	 List<Resources> selectByExample(Example example);

	List<Resources> selectAll();

	 
	

}
