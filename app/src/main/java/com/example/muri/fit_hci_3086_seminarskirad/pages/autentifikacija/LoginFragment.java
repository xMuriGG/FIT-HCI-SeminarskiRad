package com.example.muri.fit_hci_3086_seminarskirad.pages.autentifikacija;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.muri.fit_hci_3086_seminarskirad.CZEMainActivity;
import com.example.muri.fit_hci_3086_seminarskirad.R;
import com.example.muri.fit_hci_3086_seminarskirad.api.GrupaKandidati;
import com.example.muri.fit_hci_3086_seminarskirad.api.KorisnickiNalog;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.MyApp;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.Sesija;
import com.example.muri.fit_hci_3086_seminarskirad.helper.volley.MyRunnable;
import com.example.muri.fit_hci_3086_seminarskirad.pages.autentifikacija.model.LogiraniKorisnik;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojePohadjam;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojeSamPohadjao;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_login,container,false);
        ((EditText)view.findViewById(R.id.txt_kIme)).setText("test");
        ((EditText)view.findViewById(R.id.txt_lozinka)).setText("test123");

        Button btn_login =view.findViewById(R.id.btn_logIn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String korisnickoIme=((EditText)view.findViewById(R.id.txt_kIme)).getText().toString();
                String lozinka=((EditText)view.findViewById(R.id.txt_lozinka)).getText().toString();


                if (korisnickoIme.length()<3 || lozinka.length()<5)
                {
                    Toast.makeText(MyApp.getContext(), "Unesite validne vrijednosti.", Toast.LENGTH_LONG).show();
                    return;
                }

                KorisnickiNalog.Autentificiraj(getActivity(), korisnickoIme,lozinka, new MyRunnable<LogiraniKorisnik>() {
                    @Override
                    public void run(LogiraniKorisnik result) {
                        Sesija.setLogiraniKorisnik(result);
                        LogiraniKorisnik logiraniKorisnik=Sesija.getLogiraniKorisnik();
                        if (logiraniKorisnik!=null)
                        {

                            Sesija.setMojProfilData();

//                            ((CZEMainActivity)getActivity()).selectItem(1);
                            startActivity(new Intent(getActivity(),CZEMainActivity.class));
                        }
                    }
                });
            }
        });

        return view;
    }

}
