package com.example.muri.fit_hci_3086_seminarskirad.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.Config;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.MyApp;
import com.example.muri.fit_hci_3086_seminarskirad.helper.volley.MyRunnable;
import com.example.muri.fit_hci_3086_seminarskirad.helper.volley.MyVolley;
import com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe.model.AktivnaGrupaGByKursKategorija;


import java.util.Arrays;
import java.util.List;

/**
 * Created by Muri on 06.09.2017..
 */

public class Grupe {
    public static  void aktivneGrupeFragmentGet(Context context, final MyRunnable<List<AktivnaGrupaGByKursKategorija>> onSuccess)
    {
        String url = Config.strUrl+ "Grupa/VMGByKursKategorija";
        final ProgressDialog dialog = ProgressDialog.show(context, "Pristup podacima", "U toku");
        dialog.show();

        MyVolley.get(url, AktivnaGrupaGByKursKategorija[].class, new Response.Listener<AktivnaGrupaGByKursKategorija[]>() {
                    @Override
                    public void onResponse(AktivnaGrupaGByKursKategorija[] response) {
                        dialog.dismiss();
                        onSuccess.run(Arrays.asList(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        dialog.dismiss();
                        Toast.makeText(MyApp.getContext(), "Greška u komunikaciji serverom: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
