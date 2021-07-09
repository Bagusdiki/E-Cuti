package com.cuti.online.panasonic.utils;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static synchronized Context getContext() {
        return context;
    }
}
