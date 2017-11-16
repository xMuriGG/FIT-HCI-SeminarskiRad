package com.example.muri.fit_hci_3086_seminarskirad.helper.config;

import android.content.SharedPreferences;

import com.example.muri.fit_hci_3086_seminarskirad.api.GrupaKandidati;
import com.example.muri.fit_hci_3086_seminarskirad.helper.volley.MyGson;
import com.example.muri.fit_hci_3086_seminarskirad.helper.volley.MyRunnable;
import com.example.muri.fit_hci_3086_seminarskirad.pages.autentifikacija.model.LogiraniKorisnik;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojePohadjam;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojeSamPohadjao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Muri on 17.08.2017..
 */

public class Sesija {
    private static final String PREFS_NAME ="SharedPreferenceMyData8" ;
    private static final String ARG_LOGK = "logiraniKorisnik";
    private static final String ARG_GRUPA1 = "grupeKojePohadjam";
    private static final String ARG_GRUPA2 = "grupeKojeSamPohadjao";

    public static LogiraniKorisnik getLogiraniKorisnik() {
        SharedPreferences settings= MyApp.getContext().getSharedPreferences(PREFS_NAME,0);
        String logiraniKorisnik = settings.getString(ARG_LOGK, null);
        if (logiraniKorisnik==null) return null;

        return MyGson.build().fromJson(logiraniKorisnik,LogiraniKorisnik.class);
    }

    public static void setLogiraniKorisnik(LogiraniKorisnik logiraniKorisnik) {
        SharedPreferences settings = MyApp.getContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        String strLogiraniKorisnik=MyGson.build().toJson(logiraniKorisnik);
        editor.putString(ARG_LOGK, strLogiraniKorisnik);

        // Commit the edits!
        editor.commit();
    }

    public static int getLogiraniKorisnikId()
    {
       return getLogiraniKorisnik().KorisnickiNalogID;
    }

    public static List<GrupeKojePohadjam> getGrupeKojePohadjam() {
        SharedPreferences settings= MyApp.getContext().getSharedPreferences(PREFS_NAME,0);
        String str_grupeKojePohadjam = settings.getString(ARG_GRUPA1, null);
        if (str_grupeKojePohadjam==null) return null;

        GrupeKojePohadjam[] grupeKojePohadjam= MyGson.build().fromJson(str_grupeKojePohadjam,GrupeKojePohadjam[].class);
        return Arrays.asList(grupeKojePohadjam);
    }

    public static void setGrupeKojePohadjam(List<GrupeKojePohadjam> grupeKojePohadjam) {
        SharedPreferences settings = MyApp.getContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        String str_grupeKojePohadjam=MyGson.build().toJson(grupeKojePohadjam);
        editor.putString(ARG_GRUPA1, str_grupeKojePohadjam);

        // Commit the edits!
        editor.commit();
    }

    public static List<GrupeKojeSamPohadjao> getGrupeKojeSamPohadjao() {
        SharedPreferences settings= MyApp.getContext().getSharedPreferences(PREFS_NAME,0);
        String str_grupeKojeSamPohadjao = settings.getString(ARG_GRUPA2, null);
        if (str_grupeKojeSamPohadjao==null) return null;

        GrupeKojeSamPohadjao[] grupeKojeSamPohadjao= MyGson.build().fromJson(str_grupeKojeSamPohadjao,GrupeKojeSamPohadjao[].class);
        return Arrays.asList(grupeKojeSamPohadjao);
    }

    public static void setGrupeKojeSamPohadjao(List<GrupeKojeSamPohadjao> grupeKojeSamPohadjao) {
        SharedPreferences settings = MyApp.getContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        String str_grupeKojeSamPohadjao=MyGson.build().toJson(grupeKojeSamPohadjao);
        editor.putString(ARG_GRUPA2, str_grupeKojeSamPohadjao);
        // Commit the edits!
        editor.commit();
    }


    public static void setMojProfilData()
    {
        LogiraniKorisnik l=getLogiraniKorisnik();
        GrupaKandidati.getGrupeKojePohadjam(l.KorisnickiNalogID, new MyRunnable<List<GrupeKojePohadjam>>() {
            @Override
            public void run(List<GrupeKojePohadjam> result) {
                Sesija.setGrupeKojePohadjam(result);
            }
        });
        GrupaKandidati.getGrupeKojeSamPohadjao(l.KorisnickiNalogID, new MyRunnable<List<GrupeKojeSamPohadjao>>() {
            @Override
            public void run(List<GrupeKojeSamPohadjao> result) {
                Sesija.setGrupeKojeSamPohadjao(result);
            }
        });
    }
}
