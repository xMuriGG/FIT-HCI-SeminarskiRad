package com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;


import com.example.muri.fit_hci_3086_seminarskirad.CZEMainActivity;
import com.example.muri.fit_hci_3086_seminarskirad.R;
import com.example.muri.fit_hci_3086_seminarskirad.api.GrupaKandidati;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.F;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.Sesija;
import com.example.muri.fit_hci_3086_seminarskirad.helper.volley.MyRunnable;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojeSamPohadjao;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.Ocjena;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Muri on 12.09.2017..
 */

public class RateKursDialogFragment extends DialogFragment {

    public static final String ARG_PARAM1 = "ocjena";

    public static RateKursDialogFragment newInstance(Ocjena ocjena) {
        RateKursDialogFragment fragment = new RateKursDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_PARAM1, ocjena);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final Ocjena o = (Ocjena) getArguments().getSerializable(ARG_PARAM1);

        getDialog().setTitle("Ocijeni kurs");
        final View view = inflater.inflate(R.layout.dialog_fragment_rate_kurs, container, false);

        final RatingBar rbOcjena = F.findView(view, R.id.rbOcjena, RatingBar.class);
        final EditText txtOpis=F.findView(view, R.id.txtOpis, EditText.class);
        Button btn_ocjeni = F.findView(view, R.id.btn_ocjeniKurs, Button.class);

        rbOcjena.setRating(o.Ocjena);
        txtOpis.setText(o.Opis);

        btn_ocjeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                o.Datum = Calendar.getInstance().getTime();
                o.Ocjena = Math.round(rbOcjena.getRating());
                o.Opis=txtOpis.getText().toString();
                GrupaKandidati.ocjeniKurs(o, getDialog(), new MyRunnable<Boolean>() {
                    @Override
                    public void run(Boolean result) {
                        if (result) {
                            GrupaKandidati.getGrupeKojeSamPohadjao(Sesija.getLogiraniKorisnikId(), new MyRunnable<List<GrupeKojeSamPohadjao>>() {
                                @Override
                                public void run(List<GrupeKojeSamPohadjao> result) {
                                    Intent intent=new Intent();
                                    if (result != null)
                                    {
                                        Sesija.setGrupeKojeSamPohadjao(result);
                                        intent.putExtra("result",true);
                                    }
                                    else
                                    {
                                        intent.putExtra("result",false);
                                    }
                                    getTargetFragment().onActivityResult(getTargetRequestCode(),1,intent);
                                }
                            });
                        }
                    }
                });
            }
        });

        return view;
    }
}
