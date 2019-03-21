package com.cxy.security.cxysecuritydemo.model;

import com.cxy.security.cxysecuritydemo.valid.MyAnnotation;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/***
 * Created by Caoxingyun on 2019/03/11
 */
@Data
public class User {

    public interface UserSimpleView {
    }

    public interface UserDetailView extends UserSimpleView {
    }

    @ApiModelProperty(value = "id")
    private String id;

    @JsonView(UserSimpleView.class)
    @NotBlank(message = "用户名不允许为空!")
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    @JsonView(UserSimpleView.class)
    @NotBlank(message = "密码不允许为空!")
    @ApiModelProperty(value = "密码", required = true)
    private String passWord;

    //JsonView 可以控制在返回相同对象的时候显示那些字段
    @JsonView(UserDetailView.class)
    @NotNull(message = "年龄不允许为空!")
    @ApiModelProperty(value = "年龄", required = true)
    private Integer age;

    @JsonView(UserDetailView.class)
    //必须是过去的时间
    //@Past
    //必须是未来的时间
    @Past(message = "生日必须是过去的时间!")
    private Date birthDay;

    @MyAnnotation(message = "手机号不能为空!")
    private String phone;
}
