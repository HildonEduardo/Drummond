package com.help.drummond.app;

import android.app.Application;

import com.help.drummond.Drummond;
import com.help.drummond.helper.DrummondHelper;

public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DrummondHelper.initFullDebugMode(this);
        Drummond.getInstance().setSymbol("###");
    }
}
