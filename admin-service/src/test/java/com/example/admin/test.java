package com.example.admin;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

public class test {

    public static void main(String[] args) {
        User<Com<List<String>>> u = new User<>();
        Class clz = u.getClass();
        TypeVariable<Class>[] parameters = clz.getTypeParameters();
        System.out.println(parameters);
    }


    public static class User<T> {

    }

    public static class Com<T> {

    }

}
