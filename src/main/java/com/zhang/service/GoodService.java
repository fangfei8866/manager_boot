package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhang.base.BaseIService;
import com.zhang.po.Good;

import tk.mybatis.mapper.entity.Example;

/**
 * 
* @ClassName: GoodService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 1032543937@qq.com 
* @date 2017年4月6日 下午5:46:01
 */
public interface GoodService extends BaseIService<Good> {

	public int insertSelective(Good good);
	 
	public int updateByExampleSelective(Good good, Example example);

	public PageInfo<Good> selectByExampleAndPage(Example example, Page<Good> page);

	Map<Integer, Good> selectByExample(Example example);

	 
	

}
