package com.cxy.security.cxysecuritydemo.valid;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/***
 * Created by Caoxingyun on 2019/03/12
 * MyAnnotation 要验证的注解
 * Object 注解的对象的类型
 * 该类中可以注入其他的服务
 */
public class MyConstraintValidator implements ConstraintValidator<MyAnnotation,Object> {

    //初始化
    /***
     *
     * @param myAnnotation 自定义注解
     */
    @Override
    public void initialize(MyAnnotation myAnnotation) {
        System.out.println(" MyAnnotation init");
    }

    /***
     *
     * @param o 被注解注释的类型的实际的值
     * @param constraintValidatorContext 上下文
     * @return
     */
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (ObjectUtils.isEmpty(o)) {
            System.out.println("空空如也");
            return false;
        }
        return true;
    }

}
