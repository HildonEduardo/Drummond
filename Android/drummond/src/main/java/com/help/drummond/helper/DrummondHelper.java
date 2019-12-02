package com.help.drummond.helper;

import android.content.Context;

import com.help.drummond.Drummond;

/**
 * @author hildon.lima
 * @date 01/12/2019
 * hildon.p
 */
public class DrummondHelper {

    public static Drummond initFullDebugMode(Context  context) {
        final Drummond instance = Drummond.getInstance();
        instance.initialize(context);
        
        return instance;
    }


    public static Drummond initFileOnly(Context  context) {
        final Drummond instance = Drummond.getInstance();
        instance.initialize(context);
        instance.setUseLogFile(true);
        instance.setDebugMode(false);

        return instance;
    }

}
