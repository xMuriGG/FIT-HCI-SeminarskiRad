package com.example.muri.fit_hci_3086_seminarskirad.api;

import android.app.Dialog;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.Config;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.MyApp;
import com.example.muri.fit_hci_3086_seminarskirad.helper.volley.MyRunnable;
import com.example.muri.fit_hci_3086_seminarskirad.helper.volley.MyVolley;
import com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe.model.GrupaKandidatiVM;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojePohadjam;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojeSamPohadjao;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.Ocjena;

import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Muri on 10.09.2017..
 */

public class GrupaKandidati {

    public static void prijaviKandidataUGrupu(final int kandidatId, final int grupaId) {
        String url = Config.strUrl + "GrupaKandidati";

        GrupaKandidatiVM grupaKandidati = new GrupaKandidatiVM(kandidatId, grupaId);


        MyVolley.post(url, Object.class, new Response.Listener<Object>() {
            @Override
            public void onResponse(Object response) {
                Toast.makeText(MyApp.getContext(), "Uspješno ste prijavljeni u grupu.", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse.statusCode == HttpStatus.SC_CONFLICT)
                    Toast.makeText(MyApp.getContext(), "Korisnik već prijavljen u grupu.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MyApp.getContext(), "Greška u komunikaciji serverom: " + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }, grupaKandidati);
    }

    public static void ocjeniKurs(Ocjena ocjena, final Dialog dialog, final MyRunnable<Boolean> onSuccess) {
        String url = Config.strUrl +"Ocjena";

        MyVolley.post(url, Object.class, new Response.Listener<Object>() {
                    @Override
                    public void onResponse(Object response) {
                        Toast.makeText(MyApp.getContext(), "Uspješno ste ocjenili kurs.", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        onSuccess.run(true);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyApp.getContext(), "Greška u komunikaciji serverom: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
                , ocjena);
    }

    public static void getGrupeKojePohadjam(int korisnickiNalogId, final MyRunnable<List<GrupeKojePohadjam>> onSuccess)
    {
        String url=Config.strUrl +"GrupaKandidati/GrupeKojePohadjam/"+korisnickiNalogId;
        MyVolley.get(url, GrupeKojePohadjam[].class, new Response.Listener<GrupeKojePohadjam[]>() {
            @Override
            public void onResponse(GrupeKojePohadjam[] response) {
                    onSuccess.run(Arrays.asList(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyApp.getContext(), "Greška u komunikaciji serverom: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void getGrupeKojeSamPohadjao(int korisnickiNalogId, final MyRunnable<List<GrupeKojeSamPohadjao>> onSuccess)
    {
        String url=Config.strUrl +"GrupaKandidati/GrupeKojeSamPohadjao/"+korisnickiNalogId;
        MyVolley.get(url, GrupeKojeSamPohadjao[].class, new Response.Listener<GrupeKojeSamPohadjao[]>() {
            @Override
            public void onResponse(GrupeKojeSamPohadjao[] response) {
                onSuccess.run(Arrays.asList(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyApp.getContext(), "Greška u komunikaciji serverom: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
