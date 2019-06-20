package com.cxy.security.cxysecuritydemo.controller;

/***
 * Created by Caoxingyun on 2019/06/20
 */
public class ArrayTest {
    public static void main(String[] args) {
        int[] intArray = new int[]{1,2};
        Class intClass = intArray.getClass();
        intClass.getComponentType().getName();
        intClass.getComponentType().getTypeName();
        System.out.println(intClass.getClassLoader());
        System.out.println(intClass.getName());
        System.out.println(intClass.getTypeName());
        System.out.println(intClass.getTypeParameters());
        System.out.println(intClass.isAnnotation());
        System.out.println(intClass.isArray());
        System.out.println(intClass.isEnum());
        System.out.println(intClass.isInterface());
    }
}
