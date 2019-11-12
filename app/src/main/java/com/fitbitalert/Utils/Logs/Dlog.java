package com.fitbitalert.Utils.Logs;

import android.util.Log;

import com.fitbitalert.Common.BaseApplication;
import com.fitbitalert.Utils.Consts.AppConstants;

public class Dlog {

    /**
     * Log Level Error
     **/
    public static final void e (String message) {
        if (BaseApplication.DEBUG) Log.e(AppConstants.APP_NAME, buildLogMsg(message));
    }

    /**
     * Log Level Warning
     **/
    public static final void w (String message) {
        if (BaseApplication.DEBUG) Log.w(AppConstants.APP_NAME, buildLogMsg(message));
    }

    /**
     * Log Level Information
     **/
    public static final void i (String message) {
        if (BaseApplication.DEBUG) Log.i(AppConstants.APP_NAME, buildLogMsg(message));
    }

    /**
     * Log Level Debug
     **/
    public static final void d (String message) {
        if (BaseApplication.DEBUG) Log.d(AppConstants.APP_NAME, buildLogMsg(message));
    }

    /**
     * Log Level Verbose
     **/
    public static final void v (String message) {
        if (BaseApplication.DEBUG) Log.v(AppConstants.APP_NAME, buildLogMsg(message));
    }


    public static String buildLogMsg(String message) {

        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName());
        sb.append("]");
        sb.append(message);

        return sb.toString();

    }
}