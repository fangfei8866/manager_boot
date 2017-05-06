package com.zhang.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zhang.BaseTest;
import com.zhang.po.Good;

import tk.mybatis.mapper.entity.Example;

public class GoodServiceTest extends BaseTest{

	@Autowired
	private GoodService goodService;
	
	@Autowired
	protected PasswordEncoder passwordEncoder;
	
	@Test
	public void testSelectByExampleAndPage() {
		goodService.selectByExample(new Example(Good.class));
	}

	@Test
	public void testSelectByExampleExample() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPasswordEncoder() {
		 System.out.println(passwordEncoder.encode("000000"));
		 
		 
	}

}
