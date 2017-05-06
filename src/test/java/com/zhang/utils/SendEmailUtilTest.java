package com.zhang.utils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhang.BaseTest;

public class SendEmailUtilTest extends BaseTest{

	@Autowired
	private SendEmailUtil sendEmailUtil;
	
	@Test
	public void testSendSimpleEmail() {
		sendEmailUtil.sendSimpleEmail();
	}
	
	@Test
	public void testSendInlineMail() {
		try {
			sendEmailUtil.sendInlineMail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSendTemplateMail() {
		try {
			sendEmailUtil.sendTemplateMail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
