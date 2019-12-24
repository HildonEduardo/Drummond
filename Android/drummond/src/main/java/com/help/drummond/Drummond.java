package com.help.drummond;

import android.content.Context;
import android.content.pm.ApplicationInfo;
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

    private Context context;

    private boolean useLogFile;

    private boolean debugMode = true;

    private String appTag = "";

    private String symbol = Constant.DEFAULT_SYMBOL;

    public static Drummond getInstance() {
        if (instance == null) {
            instance = new Drummond();
        }
        return instance;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol + " ";
    }

    public void setUseLogFile(boolean useLogFile) {
        this.useLogFile = useLogFile;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void initialize(final Context context) {
        this.context = context;
        this.useLogFile = false;
        this.debugMode = true;

        appTag = getApplicationName();

        ExceptionHandlerManager.getInstance().initHandling(this);
    }

    private String getApplicationName() {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    // begin region not default tag
    public void verbose(String tag, String message) {
        Log.v(tag, prepareMessage(message));
    }

    public void debug(String tag, String message) {
        Log.d(tag, prepareMessage(message));
    }

    public void error(String tag, String message) {
        Log.e(tag, prepareMessage(message));
    }

    public void info(String tag, String message) {
        Log.i(tag, prepareMessage(message));
    }

    public void warn(String tag, String message) {
        Log.w(tag, prepareMessage(message));
    }
    // end region not default tag

    public void v(String message) {
        verbose(appTag, message);
    }

    public void d(String message) {
        debug(appTag, message);
    }

    public void e(String message) {
        error(appTag, message);
    }

    public void i(String message) {
        info(appTag, message);
    }

    public void w(String message) {
        warn(appTag, message);
    }

    private String prepareMessage(String message) {
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
