package com.cxy.security.cxysecuritydemo.valid.annotation;

/***
 * Created by Caoxingyun on 2019/03/12
 */

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Create by HuangJin 0n 2018/10/9.
 */
//注解放在什么位置上 FIELD 字段 METHOD 方法 ANNOTATION_TYPE 注释
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
//注解在什么作用 RUNTIME 运行时 CLASS 注解保存在class文件中 SOURCE 编译时有效
@Retention(RetentionPolicy.RUNTIME)
//注解的逻辑处理 Constraint 约束条件 validatedBy 指定处理逻辑的类
@Constraint(validatedBy = {MyConstraintValidator.class})
@Documented
public @interface MyAnnotation {

    String message() default ""; //校验不过的时候的信息

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
