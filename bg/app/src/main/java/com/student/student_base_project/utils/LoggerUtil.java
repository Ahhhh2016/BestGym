package com.student.student_base_project.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

/**
 * @author zcq
 * @discrip log工具类
 */
@SuppressLint("NewApi")
public class LoggerUtil {

    public static String customTagPrefix = "Dong：";

    private static boolean isDebug = true;

    private LoggerUtil() {
    }

    /**
     * 是否显示log
     *
     * @param isShowLog true:显示log,false:不显示log
     * @return
     */
    public static void isDebug(boolean isShowLog) {
        isDebug = isShowLog;
    }

    private static String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "Dong:";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    public static void d(String content) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.d(tag, content);
    }

    public static void d(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.d(tag, content, tr);
    }

    public static void e(String content) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.e(tag, content);
    }

    public static void e(Class<?> tag, String content) {
        if (!isDebug) return;
        Log.e(tag.getName() + ">>>>>>", content);
    }

    public static void e(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.e(tag, content, tr);
    }

    public static void i(String content) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.i(tag, content);
    }

    public static void i(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.i(tag, content, tr);
    }

    public static void v(String content) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.v(tag, content);
    }

    public static void v(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.v(tag, content, tr);
    }

    public static void w(String content) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.w(tag, content);
    }

    public static void w(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.w(tag, content, tr);
    }

    public static void w(Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.w(tag, tr);
    }

    public static void wtf(String content) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.wtf(tag, content);
    }

    public static void wtf(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.wtf(tag, content, tr);
    }

    public static void wtf(Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.wtf(tag, tr);
    }

    /**
     * 截断输出日志
     * @param msg
     */
    public static void longlog(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        // 长度小于等于限制直接打印
        if (length <= segmentSize ) {
            Log.e(tag, msg);
        }else {
            // 循环分段打印日志
            while (msg.length() > segmentSize ) {
                String logContent = msg.substring(0, segmentSize );
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            // 打印剩余日志
            Log.e(tag, msg);
        }
    }

}
