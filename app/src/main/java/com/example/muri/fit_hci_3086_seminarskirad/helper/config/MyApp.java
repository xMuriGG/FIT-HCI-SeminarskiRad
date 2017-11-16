package com.example.muri.fit_hci_3086_seminarskirad.helper.config;

import android.app.Application;
import android.content.Context;

/**
 * Created by Muri on 14.08.2017..
 */

public class MyApp extends Application {
    private static Context context;

    public static Context getContext()
    {
        return context;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
    }
}
