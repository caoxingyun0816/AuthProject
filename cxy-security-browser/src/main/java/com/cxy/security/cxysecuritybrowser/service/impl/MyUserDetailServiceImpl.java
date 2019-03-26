package com.cxy.security.cxysecuritybrowser.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

/***
 * Created by Caoxingyun on 2019/03/26
 */
@Component//自动注入
public class MyUserDetailServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    自定义用户服务
//    @Autowired
//    private UserService;

    //根据用户名查找用户
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("username is ：" + username);
        return new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        Collection<? extends GrantedAuthority>
//        继承GrantedAuthority 的集合 ,例如List<GrantedAuthority> authorities;
//        authorities 是用户的权限
//         public User(String username, String password, Collection<? extends GrantedAuthority > authorities) {
//            this(username, password, true, true, true, true, authorities);
//        }
    }
}
