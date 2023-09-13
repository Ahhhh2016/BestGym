package com.student.student_base_project.net;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.google.gson.Gson;
import com.student.student_base_project.utils.LoggerUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Administrator on 2016-10-book_defalut.
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    Gson gson;
    Type type;

    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        value.close();
        LoggerUtil.longlog("Dong", response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            boolean isExitCode = jsonObject.has("resultCode");
            if (isExitCode) {
                String code = jsonObject.getString("resultCode");
                if (!code.equals("1")) {
                    throw new RuntimeException("code" + "--" + jsonObject.getString("resultMsg") + "--" + code);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        T responseBody = getResponseBody(response);

        return responseBody;
    }

    private T getResponseBody(String response) {
        LoggerUtil.d(response);
        if (type instanceof Class) {
            Class clazz = (Class) type;

            if (clazz.equals(JSONObject.class)) {//如果返回类型 为JsonObject
                try {
                    return (T) new JSONObject(response);
                } catch (JSONException e) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        return (T) jsonObject.put("src", response);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if (clazz.equals(JSONArray.class)) {
                try {
                    return (T) new JSONArray(response);
                } catch (JSONException e) {
                }
            }
        }
        return gson.fromJson(response, type);
    }

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }

    }
}
