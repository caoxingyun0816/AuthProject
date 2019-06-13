package com.cxy.security.cxysecuritydemo.controller;

import com.cxy.security.cxysecuritydemo.model.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/***
 * Created by Caoxingyun on 2019/03/11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @GetMapping("/getUserInfo")
    public Object getUserInfo(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/getUserInfo/detail")
    public Object getUserInfo(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }

    @GetMapping
    //@PageableDefault spring data 默认的分页工具 Pageable 分页参数
    //@PathVariable 映射URL片段到java方法中的参数
    @JsonView(User.UserSimpleView.class)
    public List<User> queryAll(@RequestParam("userName") String userName, @PageableDefault(page = 1, size = 10, sort = "age") Pageable pageable) {
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            User user = new User();
            user.setId(i + "");
            user.setAge(i);
            user.setUserName("cxy" + i);
            user.setPassWord(user.getUserName());
            users.add(user);
        }
        return users;
    }

    // 路径中可以使用正则表达式来匹配URL并指定参数类型
    @GetMapping(value = "/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User queryOne(@PathVariable(name = "id") Integer id) {
        User user = new User();
        user.setUserName("cxy");
        user.setId(id + "");
        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        user.setId("111");
        return user;
    }

    // 路径中可以使用正则表达式来匹配URL并指定参数类型

    /***
     * 当校验返回失败的时候会将错误信息返回到BindingResult中
     * @param user
     * @param result
     * @return
     */
    @PutMapping(value = "/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().stream().forEach(error -> {
//                FieldError fieldError = (FieldError) error;
//                String message = fieldError.getField() + " " + fieldError.getDefaultMessage();
                System.out.println(error.getDefaultMessage());
            });
        }
        return user;
    }

    @DeleteMapping(value = "/{id:\\d+}")
    public void delete(@PathVariable(name = "id") Integer id) {
        System.out.println("delete start");
        System.out.println(id);
    }

    @PostMapping(value = "/register")
    public void register(User user, HttpServletRequest request){
        //不管是注册用户还是绑定用户都会拿到用户的唯一标识userId;
        String userId = user.getUserName();
        //userId绑定第三方信息，放到数据库中
        providerSignInUtils.doPostSignUp(userId,new ServletWebRequest(request));

    }
}
