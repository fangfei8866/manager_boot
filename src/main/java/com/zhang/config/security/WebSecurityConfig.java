package com.zhang.config.security;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Athos on 2016-10-16.
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private UserDetailsService userDetailsService;
	
	@Resource
	private MySecurityMetadataSource mySecurityMetadataSource;
	
	@Autowired
	private MyAccessDecisionManager myAccessDecisionManager;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**","/denied","/favicon.ico");
		web.debug(true);
	}
 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(new MyAuthenticationProvider(userDetailsService, passwordEncoder()));
		// auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(4));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//设置能够加载iframe
		http.headers().frameOptions().disable();
		//http.antMatcher("/static/**").anonymous();
		http.authorizeRequests().anyRequest().authenticated();
		
		//在FilterSecurityInterceptor 之前添加过滤器
		http.addFilterBefore(new MySecurityFilter(mySecurityMetadataSource,myAccessDecisionManager),FilterSecurityInterceptor.class);
		
		// 自定义登录页面
		http.formLogin()
		//.apply(new DefaultFormLoginConfigurer<HttpSecurity>())
		.loginPage("/login").successHandler(new MyLoginSuccessHandler())
				.defaultSuccessUrl("/loginsuccess", true)// login
												// success
				.failureUrl("/loginfail")
				.permitAll();
		// 自定义注销
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true).permitAll();
		
		// 自定义accessDecisionManager访问控制器
		http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler("/denied"));

		http.sessionManagement().sessionFixation().migrateSession().invalidSessionUrl("/invalidSessionUrl")
		.maximumSessions(20);

		// RemeberMe
		 http.rememberMe().userDetailsService(userDetailsService).rememberMeParameter("rememberMe");

	}
	
	


	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() {
		AuthenticationManager authenticationManager = null;
		try {
			authenticationManager = super.authenticationManagerBean();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authenticationManager;
	}

}
