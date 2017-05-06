package com.zhang.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class SendEmailUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	private String from="ccs163@ccs163.com";
	private String toAccount ="1032543937@qq.com";
	
	/**
	 * 发送简单邮件
	 */
	public void sendSimpleEmail(){
		 SimpleMailMessage message = new SimpleMailMessage();
		 message.setFrom("ccs163@ccs163.com");
		 message.setTo("1032543937@qq.com");//接收者.
		 message.setSubject("测试邮件（邮件主题）");//邮件主题.
		 message.setText("这是邮件内容");//邮件内容.
      
		 mailSender.send(message);//发送邮件
		
	}
	
	/**
	 * 发送附件
     * 发送附件主要通过MimeMessageHelper来进行操作的，实际中也很简单。
	 */
	
	/**
     * 测试发送附件.(这里发送图片.)
     * @throws MessagingException
     */
    public void sendAttachmentsEmail() throws MessagingException{
       //这个是javax.mail.internet.MimeMessage下的，不要搞错了。
       MimeMessage mimeMessage =  mailSender.createMimeMessage();
       MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
      
       //基本设置.
       helper.setFrom(from);//发送者.
       helper.setTo(toAccount);//接收者.
       helper.setSubject("测试附件（邮件主题）");//邮件主题.
       helper.setText("这是邮件内容（有附件哦.）");//邮件内容.
      
       //org.springframework.core.io.FileSystemResource下的:
       //附件1,获取文件对象.
       FileSystemResource file1 = new FileSystemResource(new File("E:/es.txt"));
       //添加附件，这里第一个参数是在邮件中显示的名称，也可以直接是head.jpg，但是一定要有文件后缀，不然就无法显示图片了。
       helper.addAttachment("es.txt", file1);
       //附件2
       FileSystemResource file2 = new FileSystemResource(new File("E:/ss.png"));
       helper.addAttachment("ss.png", file2);
       
        mailSender.send(mimeMessage);
    }
	
    
    /**
     * 除了发送附件之外，我们在邮件内容中可能希望通过嵌入图片等静态资源，让邮件获得更好的阅读体验，而不是从附件中查看具体图片，下面的测试用例演示了如何通过MimeMessageHelper实现在邮件正文中嵌入静态资源。
       	内嵌图片，给定一个CID值即可，增加附件，使用MimeMessageHelper的addAttachment即可现在一般不会做内嵌图片，因为这样邮件会很大，容易对服务器造成压力，一般做法是使用图片链接另外，如果要做内嵌或发送图片，你应该使用信用较高的邮箱帐户，否则会报错：554 DT:SPM 发送的邮件内容包含了未被许可的信息，或被系统识别为垃圾邮件。请检查是否有用户发送病毒或者垃圾邮件
     */
    
    /**
     * 邮件中使用静态资源.
     * @throws Exception
     */
    public void sendInlineMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
 
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
      //基本设置.
       helper.setFrom(from);//发送者.
       helper.setTo(toAccount);//接收者.
       helper.setSubject("测试静态资源（邮件主题）");//邮件主题.
       // 邮件内容，第二个参数指定发送的是HTML格式 
       //说明：嵌入图片<img src='cid:head'/>，其中cid:是固定的写法，而aaa是一个contentId。
        helper.setText("<body>这是图片：<img src='cid:head' /></body>", true);
       
        FileSystemResource file = new FileSystemResource(new File("E:/ss.png"));
        helper.addInline("head",file);
 
        mailSender.send(mimeMessage);
 
    }
    
    /**
     * 模板邮件；
     * @throws Exception
     */
    public void sendTemplateMail() throws Exception {
 
        MimeMessage mimeMessage = mailSender.createMimeMessage();
 
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //基本设置.
       helper.setFrom(from);//发送者.
       helper.setTo(toAccount);//接收者.
       helper.setSubject("模板邮件（邮件主题）");//邮件主题.
 
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("username", "林峰");
       
         Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);  
        // 设定去哪里读取相应的ftl模板  
        cfg.setClassForTemplateLoading(this.getClass(), "/ftl");  
        // 在模板文件目录中寻找名称为name的模板文件  
        Template template   = cfg.getTemplate("email.ftl");
       
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(html, true);
 
        mailSender.send(mimeMessage);
    }
	
	

}
