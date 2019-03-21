package com.cxy.security.cxysecuritycore.valid;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/***
 * Created by Caoxingyun on 2019/03/12
 */
public class EnumValid implements ConstraintValidator<EnumValidAnnotation, String> {

    // 枚举类
    Class<?>[] cls;

    @Override
    public void initialize(EnumValidAnnotation enumValidAnnotation) {
        cls = enumValidAnnotation.target();
    }

    //利用反射 执行getName方法验证传值是否正确
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        if (cls.length > 0) {
            for (Class<?> cl : cls) {
                try {
                    if (cl.isEnum()) {
                        // 返回枚举类的元素
                        Object[] objects = cl.getEnumConstants();
                        Method method = cl.getMethod("name");
                        for (Object object : objects) {
                            Object ret = method.invoke(object, null);
                            if (value.equals(ret.toString())) {
                                return true;
                            }
                        }
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
        return false;
    }
}
