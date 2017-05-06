package com.zhang.web;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zhang.base.BaseController;
import com.zhang.po.Good;
import com.zhang.resultdata.AjaxResultData;
import com.zhang.service.GoodService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller("goodController")
@RequestMapping(value = "/admin/good")
public class GoodController extends BaseController{
	
	@Autowired
	private GoodService goodService;
	
	/**
	 * 跳转到列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(ModelMap map) {
		return "good/list";
	} 
	
	
	/**
	 * 列表数据
	 * @param limit
	 * @param offset
	 * @param response
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/list_")
	public String list_(ModelMap map,Integer pageNum,Good good,String startDate,String endDate) throws ParseException{
		@SuppressWarnings("unchecked")
		Page<Good> page = this.getPage(pageNum);
		Example example = new Example(Good.class);
		example.setOrderByClause("id desc");
		Criteria criteria = example.createCriteria();
		criteria.andBetween("status", 1, 3);
		if(good.getStatus() != null && good.getStatus() != -1){
			criteria.andEqualTo("status", good.getStatus());
		}
		if(good.getShowStatus() != null && good.getShowStatus() != -1){
			criteria.andEqualTo("showStatus", good.getShowStatus());
		}
		if(StringUtils.isNotBlank(startDate)){
			criteria.andGreaterThanOrEqualTo("enableTime", DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
		}
		if(StringUtils.isNotBlank(endDate)){
			criteria.andLessThanOrEqualTo("enableTime", DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
		}
		map.put("pageInfo", goodService.selectByExampleAndPage(example, page));
		return "good/list_";
		 
		
	} 
	
	/**
	 * test
	 * @param good
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/test")
	public @ResponseBody int test(Good good,ModelMap map) {
		
		Example example = new Example(Good.class);
		example.setOrderByClause("id desc");
		Criteria criteria = example.createCriteria();
		criteria.andBetween("status", 1, 3);
		criteria.andEqualTo("status", 1);
		criteria.andEqualTo("showStatus", 1);
		return goodService.updateByExampleSelective(good,example);
	} 
	
	/**
	 * 跳转到新建
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(ModelMap map) {
		return "good/add";
	} 
	
	/**
	 * 保存商品
	 * @param good
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/save")
	public @ResponseBody String save(Good good,ModelMap map) {
		good.setCreateTime(new Date());
		good.setStatus(1);
		good.setShowStatus(0);
		goodService.insertSelective(good);
		return AjaxResultData.ok(true);
	} 
	
	 
	/**
	 * 编辑商品
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}")
	public String edit(ModelMap map,@PathVariable Integer id) {
		Good good = goodService.selectByKey(id);
		if(good != null){
			map.put("good", good);
			
			return "good/edit";
		}
		return ERROR_404;
	} 
	
	/**
	 * 更新
	 * @param good
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/update")
	public @ResponseBody String update(Good good,ModelMap map) {
		goodService.updateNotNull(good);
		return AjaxResultData.ok(true);
	} 
	
	/**
	 * 更新
	 * @param good
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/update/status")
	public @ResponseBody String updateStatus(Integer[] ids ,Good good,ModelMap map) {
		System.out.println(ids);
		Example example = new Example(Good.class);
		example.createCriteria().andIn("id", Arrays.asList(ids));
		good.setModifyTime(new Date());
		if(good.getStatus()!= null && good.getStatus() ==2){
			good.setEnableTime(new Date());
		}
		goodService.updateByExampleSelective(good, example);
		
		return AjaxResultData.ok(true);
	} 
	
}
