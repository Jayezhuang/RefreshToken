package com.winchance.wechat.common.convertor;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonConvertor {
    private static final ThreadLocal<Gson> gsonLocal = new ThreadLocal<Gson>() {
        @Override
        protected Gson initialValue() {
            return new Gson();
        }
    };

    public static String toJson(Object object) {
        return gsonLocal.get().toJson(object);
    }

    public static <T> T fromJson(String jsonString, Class<T> classOfT)
            throws JsonSyntaxException {
        return gsonLocal.get().<T>fromJson(jsonString, classOfT);
    }
}
