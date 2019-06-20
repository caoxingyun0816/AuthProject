package com.cxy.security.cxysecuritydemo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/***
 * Class 测试类
 * Created by Caoxingyun on 2019/06/19
 */
public class ClassTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class studentClass = Class.forName("com.cxy.security.cxysecuritydemo.controller.Student");
        studentClass.getTypeName();
        studentClass.getName();
        studentClass.getClass();
        Object o = studentClass.newInstance(); //调用了Student的无参数构造方法.
        //构造方法
        //所有的公有构造方法
        Constructor[] constructors = studentClass.getConstructors();
        //所有的构造方法
        Constructor[] all = studentClass.getDeclaredConstructors();
        //根据参数类型获取公有构造方法
        Constructor a = studentClass.getConstructor(null);
        //根据参数类型获取构造方法
        Constructor constructor = studentClass.getDeclaredConstructor();
        constructor.setAccessible(true);//暴力访问 解除私有限定

        //属性
        //公有的字段
        Field[] fields =  studentClass.getFields();
        //所有的字段
        Field[] declaredFields =  studentClass.getDeclaredFields();
        Field field = studentClass.getField("name");
        field.getType();
        //调用构造方法
        Object obj = constructor.newInstance();// --> Student stu = new Student();
        field.set(obj,"cxyyy");
        Field declaredField = studentClass.getDeclaredField("phoneNum");
        declaredField.setAccessible(true);//解除私有限定
        declaredField.set(obj,"123456");// --> stu.phoneNum =  "123456";
        Student student = (Student) obj;

        //method
        //public 方法
        Method[] methods = studentClass.getMethods();

        //所有方法
        Method[] de = studentClass.getDeclaredMethods();
        //获取指定public方法
        //name 方法名 parameterType,参数类型
        Method method = studentClass.getMethod("show1", String.class);
        //调用方法 obj 对象实例 args 参数
        method.invoke(obj,"cxy");// --> obj.show1(cxy)
        Method m = studentClass.getDeclaredMethod("show4", int.class);
        m.setAccessible(true);
        m.invoke(obj,1);
        Method cxy = studentClass.getMethod("getCxy");//静态方法
        cxy.invoke(null);//静态方法可以直接调用，不需要实例

    }
}
