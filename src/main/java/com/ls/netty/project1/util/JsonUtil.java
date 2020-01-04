package com.ls.netty.project1.util;

import com.google.gson.Gson;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 12:57
 * 借助于gson实现对象到字符串以及字符串到对象之间的转化
 */
public class JsonUtil {

    private static final Gson gson = new Gson();

    private JsonUtil() {}//不能被实例化

    //把对象转化为字符串

    public static String toJson(Object object) {
        return gson.toJson(object);
    }


    //根据json字符串和对象的类型，把字符串转化为对象
    public static <T>T fromJson(String content,Class<T> clazz) {
        return gson.fromJson(content,clazz);
    }
}
