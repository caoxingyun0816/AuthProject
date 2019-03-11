package com.cxy.security.cxysecuritydemo.model;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/***
 * Created by Caoxingyun on 2019/03/11
 */
@Data
public class User {

    public interface UserSimpleView {}

    public interface UserDetailView  extends UserSimpleView {}

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

//    JsonView 可以控制在返回相同对象的时候显示那些字段
    @JsonView(UserDetailView.class)
    @NotNull(message = "年龄不允许为空!")
    @ApiModelProperty(value = "年龄", required = true)
    private Integer age;

    @JsonView(UserDetailView.class)
    @NotNull(message = "生日不允许为空!")
    @ApiModelProperty(value = "生日", required = true)
    private Date birthDay;
}
