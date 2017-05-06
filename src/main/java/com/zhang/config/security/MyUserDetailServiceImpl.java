package com.zhang.config.security;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zhang.po.User;
import com.zhang.service.ResourcesService;
import com.zhang.service.RoleResourcesService;
import com.zhang.service.UserService;

@Service("userDetailsService")
public class MyUserDetailServiceImpl implements UserDetailsService {

	@Resource
    private UserService userService;
    @Resource
    private RoleResourcesService roleResourcesService;
    @Resource
    private ResourcesService resourcesService;

	// 登录验证
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.findUserByName(username);

		if (user != null) {
			Set<GrantedAuthority> sga = obtionGrantedAuthorities(user);
			//使用MyUser包装User
			MyUser<User> duser = new MyUser<User>(user.getUserName(), user.getUserPwd(),true, sga, user.getId(),user);
			return duser;
		}
		return null;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		if(StringUtils.isNoneEmpty(user.getRoleIds())){
			String[]roleIds = user.getRoleIds().split(","); 
			for (String id : roleIds) {
				authSet.add(new SimpleGrantedAuthority(id));
			}
		}
		return authSet;
	}
}
