package com.common.cklibrary.utils;


import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/5/27.
 */
public class JsonUtil {
    private static JsonUtil jsonUtil = null;
    private static Gson gson;


    private JsonUtil() {
        gson = new Gson();
    }

    public synchronized static JsonUtil getInstance() {
        if (jsonUtil == null) {
            jsonUtil = new JsonUtil();
        }
        return jsonUtil;
    }


    //    public static String obj2JsonString(Map<String, Object> map) {
//        return gson.toJson(map);
//
//    }
    @SuppressWarnings("unchecked")
    public static String obj2JsonString(Object obj) {
        try {
            return gson.toJson(obj);
        } catch (Exception e) {
            return "";
        }


    }

    @SuppressWarnings("unchecked")
    public static Object json2Obj(String jsonString, Class clzz) {
        try {
            Object obj = gson.fromJson(jsonString, clzz);
            return obj;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static Object json2ObjWithEx(String jsonString, Class clzz) throws Exception {
        Object obj = gson.fromJson(jsonString, clzz);
        return obj;
    }
}
