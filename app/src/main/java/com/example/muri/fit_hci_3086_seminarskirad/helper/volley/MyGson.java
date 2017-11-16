package com.example.muri.fit_hci_3086_seminarskirad.helper.volley;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Adil on 11.5.2015.
 */
public class MyGson
{
    public static Gson build()
    {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return builder.create();
    }
}
