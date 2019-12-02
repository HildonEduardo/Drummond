package com.help.drummond;

import android.content.Context;

/**
 * @author hildon.lima
 *
 * @date 01/12/2019
 * hildon.p
 */
public class Drummond {
    private static Drummond instance;

    private Context context;

    private boolean useLogFile;

    private boolean debugMode = true;

    public static Drummond getInstance() {
        if (instance == null) {
            instance = new Drummond();
        }
        return instance;
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
    }

}
