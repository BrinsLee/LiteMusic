package com.brins.base.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * @author lipeilin
 * @date 2022/2/8
 * @desc
 */
public class Logz {

    //todo 全局常量初始化判断
    static {

    }

    private static final String TAG = "Logz";
    private static final String T_TAG = "T_Logz";

    // 展示行数
    private static boolean SHOWLINE = false;
    private static boolean isDebug = true;
    public static boolean DEBUG = isDebug;
    public static boolean isInitialTime=false;

/*    private static void appendLog(String filePath, String msg) {
        if (openOrCreateLogFile(filePath)) {
            FileUtil.appendData(filePath, (msg + "\r\n\r\n").getBytes());
        }
    }*/

    /**
     * 当前毫秒
     */
    public static void currentTime() {
        if (isDebug) {
            Log.i(T_TAG, String.valueOf(System.currentTimeMillis()));
        }
    }

    /**
     * 当前毫秒
     *
     * @param index 描述
     */
    public static void currentTime(int index) {
        if (isDebug) {
            StringBuilder builder = new StringBuilder();
            builder.append(index).append(":").append(String.valueOf(System.currentTimeMillis()));
            Log.i(T_TAG, builder.toString());
        }
    }

    private static String getCurLineForJump(int precount) {
        if (!SHOWLINE) {
            return "";
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace != null && precount >= 0 && precount < stackTrace.length) {
            return "\n==> at " + stackTrace[precount]
                //               待优化     + (precount + 1 < stackTrace.length ? "\n==> at " + stackTrace[precount + 1] : "")
                ;
        }
        return "";
    }

    /**
     * 常规日志打印
     *
     * @param msg
     */
    public static void d(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.d(TAG, msg);
        }
    }

    /**
     * 自定义TAG
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        //msg为null时会报java.lang.NullPointerException: println needs a message
        Log.d(tag, msg+""+ getCurLineForJump(4));
    }

    public static void d(String tag, String message, Object... args) {
        String s = (message == null) ? "" : String.format(message, args);
        Log.d(tag, s + getCurLineForJump(4));
    }

    public static void v(String tag, String msg) {
        Log.v(tag, msg + "" + getCurLineForJump(4));

    }

    public static void v(String tag, String message, Object... args) {
        String msg = (message == null) ? "" : String.format(message, args);
        Log.v(tag, msg + getCurLineForJump(4));
    }

    /**
     * 自定义TAG
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        Log.e(tag, msg == null ? "" : msg + getCurLineForJump(4));

    }

    public static void w(String tag, String msg) {
        Log.w(tag, msg == null ? "" : msg + getCurLineForJump(4));

    }

    public static void w(String tag, String message, Object... args) {
        String msg = (message == null) ? "" : String.format(message, args);
        Log.w(tag, msg + getCurLineForJump(4));
    }


    public static void e(Throwable e) {
        if (isDebug) {
            Logz.e("KGLog", Log.getStackTraceString(e));
        }
    }

    public static void e(String tag, Throwable e) {
        if (isDebug) {
            Logz.e(tag, Log.getStackTraceString(e));
        }
    }

    public static void e(String tag, Throwable e, String message, Object... args) {
        if (isDebug) {
            String s = (message == null) ? "" : String.format(message, args);
            Logz.e(tag, s + Log.getStackTraceString(e));
        }
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setDebug(boolean debug) {
        isDebug = debug;
    }
}
