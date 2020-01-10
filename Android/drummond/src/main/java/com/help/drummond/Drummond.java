package com.help.drummond;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.help.drummond.exceptions.CrashHandlingListener;
import com.help.drummond.exceptions.ExceptionHandlerManager;

/**
 * @author hildon.lima
 * @date 01/12/2019
 * hildon.p
 */
public class Drummond implements CrashHandlingListener {

    private static Drummond instance;

    private static Context context;

    private static boolean useLogFile;

    private static boolean debugMode = true;

    private static String appTag = "";

    private static String symbol = Constant.DEFAULT_SYMBOL;

    public static Drummond getInstance() {
        if (instance == null) {
            instance = new Drummond();
        }
        return instance;
    }

    public static void setSymbol(final String symb) {
        symbol = symb + " ";
    }

    public void setUseLogFile(boolean useLogFile) {
        this.useLogFile = useLogFile;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void initialize(final Context ctx) {
        context = ctx;
        useLogFile = false;

        ApplicationInfo ai = null;
        try {
            ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            debugMode = bundle.getBoolean("drummond.log.enabled");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            debugMode = true;
        }

        appTag = getApplicationName();

        ExceptionHandlerManager.getInstance().initHandling(this);
    }

    private static String getApplicationName() {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    // begin region not default tag
    public static void verbose(String tag, String message) {
        if (!debugMode) {
            return;
        }

        Log.v(tag, prepareMessage(message));
    }

    public static void debug(String tag, String message) {
        if (!debugMode) {
            return;
        }
        Log.d(tag, prepareMessage(message));
    }

    public static void error(String tag, String message) {
        Log.e(tag, prepareMessage(message));
    }

    public static void info(String tag, String message) {
        if (!debugMode) {
            return;
        }
        Log.i(tag, prepareMessage(message));
    }

    public static void warn(String tag, String message) {
        if (!debugMode) {
            return;
        }
        Log.w(tag, prepareMessage(message));
    }
    // end region not default tag

    public static void v(String message) {
        verbose(appTag, message);
    }

    public static void d(String message) {
        debug(appTag, message);
    }

    public static void e(String message) {
        error(appTag, message);
    }

    public static void i(String message) {
        info(appTag, message);
    }

    public static void w(String message) {
        warn(appTag, message);
    }

    private static String prepareMessage(String message) {
        String result = "";
        if (symbol != null) {
            result = symbol + message;
        }

        return result;
    }

    @Override
    public void onCrashHappened(String message) {
        e("Crash: " + message);
    }


}
