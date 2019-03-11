package com.cxy.security.cxysecuritydemo.controller;

import com.cxy.security.cxysecuritydemo.model.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by Caoxingyun on 2019/03/11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    //@PageableDefault spring data 默认的分页工具 Pageable 分页参数
    //@PathVariable 映射URL片段到java方法中的参数
    @JsonView(User.UserSimpleView.class)
    public List<User> query(@RequestParam("userName") String userName, @PageableDefault(page = 1, size = 10, sort = "age") Pageable pageable) {
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
    public User query(@PathVariable(name = "id") Integer id) {
        User user = new User();
        user.setUserName("cxy");
        user.setId(id+"");
        return user;
    }

    @PostMapping
    public User query(@RequestBody User user) {
        user.setId("111");
        return user;
    }
}
