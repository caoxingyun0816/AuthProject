package com.cxy.security.cxysecuritydemo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/***
 * Created by Caoxingyun on 2019/03/26
 */
@Component//自动注入
public class MyUserDetailServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    自定义用户服务
//    @Autowired
//    private UserService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //根据用户名查找用户
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("username is ：" + username);
        logger.info("数据库 password is ：" + passwordEncoder.encode("123456"));
        //return new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return new User(username, passwordEncoder.encode("123456"), true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        //        Collection<? extends GrantedAuthority>
//        继承GrantedAuthority 的集合 ,例如List<GrantedAuthority> authorities;
//        authorities 是用户的权限
//         public User(String username, String password, Collection<? extends GrantedAuthority > authorities) {
//            this(username, password, true, true, true, true, authorities);
//        }
//        UserDetails 七个参数
//        Collection<? extends GrantedAuthority> getAuthorities(); // 权限
//
//        String getPassword();// 密码
//
//        String getUsername(); // 用户名
//
//        boolean isAccountNonExpired(); //账户是够过期
//
//        boolean isAccountNonLocked(); //账户是否锁定
//
//        boolean isCredentialsNonExpired(); //密码是否过期
//
//        boolean isEnabled(); //账户是否被冻结
//        可以自己也user实现userdetail 然后自己校验逻辑
    }
}
