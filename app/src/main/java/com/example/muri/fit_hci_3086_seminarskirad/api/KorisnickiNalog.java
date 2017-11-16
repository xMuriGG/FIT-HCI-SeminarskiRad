package com.example.muri.fit_hci_3086_seminarskirad.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.Config;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.MyApp;
import com.example.muri.fit_hci_3086_seminarskirad.helper.volley.MyRunnable;
import com.example.muri.fit_hci_3086_seminarskirad.helper.volley.MyVolley;
import com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe.model.AktivnaGrupaGByKursKategorija;
import com.example.muri.fit_hci_3086_seminarskirad.pages.autentifikacija.model.LogiraniKorisnik;

import java.util.Arrays;

/**
 * Created by Muri on 08.09.2017..
 */

public class KorisnickiNalog {

    public static void Autentificiraj(final Context context,
                                              final String korisnickoIme,
                                              final String korisnickaLozinka,
                                              final MyRunnable<LogiraniKorisnik> onSuccess)
    {
        String url = Config.strUrl+ "KorisnickiNalog/GetKorisnikByLogInData/"+korisnickoIme+"/"+korisnickaLozinka;
        final ProgressDialog dialog = ProgressDialog.show(context, "Pristup podacima", "U toku");
        dialog.show();

        MyVolley.get(url, LogiraniKorisnik.class, new Response.Listener<LogiraniKorisnik>() {
                    @Override
                    public void onResponse(LogiraniKorisnik response) {
                        dialog.dismiss();
                        if (response != null) {
                            Toast.makeText(context, response.Ime, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Pogrešni login podaci.", Toast.LENGTH_LONG).show();
                        }
                        onSuccess.run(response);
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
