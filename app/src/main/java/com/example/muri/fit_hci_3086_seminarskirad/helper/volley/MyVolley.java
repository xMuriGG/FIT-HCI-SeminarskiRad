package com.example.muri.fit_hci_3086_seminarskirad.helper.volley;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.example.muri.fit_hci_3086_seminarskirad.api.GrupaKandidati;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.MyApp;
import com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe.model.GrupaKandidatiVM;
import com.google.gson.Gson;


import java.util.Arrays;

/**
 * Created by Muri on 14.08.2017..
 */

public class MyVolley {
    public static <T>  void get(String urlStr, Class<T> responseType, Response.Listener<T> listener, Response.ErrorListener errorListener, String... inputParams)
    {
        String  urlParam="";
        for (String s:inputParams)
        {
            urlParam+="/"+s;
        }
        String url = urlStr + urlParam;
        GsonRequest<T> gsonRequest = new GsonRequest<>(url, responseType, null, null, listener, errorListener, Request.Method.GET);
        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }

    public static <T>  void post(String urlStr,  Class<T> responseType, final Response.Listener<T> listener, Response.ErrorListener errorListener, Object postObject)
    {
        String jsonStr = MyGson.build().toJson(postObject);
        GsonRequest<T> gsonRequest = new GsonRequest<>(urlStr, responseType, null, jsonStr, listener, errorListener, Request.Method.POST);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }
}
