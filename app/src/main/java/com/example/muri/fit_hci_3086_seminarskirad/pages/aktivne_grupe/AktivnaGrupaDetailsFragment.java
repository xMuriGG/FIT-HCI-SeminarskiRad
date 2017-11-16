package com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.muri.fit_hci_3086_seminarskirad.CZEMainActivity;
import com.example.muri.fit_hci_3086_seminarskirad.R;
import com.example.muri.fit_hci_3086_seminarskirad.api.GrupaKandidati;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.F;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.Sesija;
import com.example.muri.fit_hci_3086_seminarskirad.helper.volley.MyRunnable;
import com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe.model.AktivnaGrupaListVM;
import com.example.muri.fit_hci_3086_seminarskirad.pages.autentifikacija.model.LogiraniKorisnik;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojePohadjam;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojeSamPohadjao;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AktivnaGrupaDetailsFragment extends Fragment {

    private static final String ARG_PARAM1 = "aktivnaGrupaDetails";

    public AktivnaGrupaDetailsFragment() {
        // Required empty public constructor
    }

    public static AktivnaGrupaDetailsFragment newInstance(AktivnaGrupaListVM aktivnaGrupaDetails) {
        AktivnaGrupaDetailsFragment fragment = new AktivnaGrupaDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_PARAM1, aktivnaGrupaDetails);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CZEMainActivity activity = (CZEMainActivity) getActivity();

        if (activity != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aktivna_grupa_details, container, false);
        final AktivnaGrupaListVM g = (AktivnaGrupaListVM) getArguments().getSerializable(ARG_PARAM1);
        F.findView(view, R.id.tvGrupaNaziv, TextView.class).setText(g.GrupaNaziv);
        F.findView(view, R.id.tvDatumPocetka, TextView.class).setText(F.date_ddMMyyy(g.Pocetak));
        F.findView(view, R.id.tvDatumZavrsetka, TextView.class).setText(F.date_ddMMyyy(g.Kraj));
        F.findView(view, R.id.tvKursNaziv, TextView.class).setText(g.KursNaziv);
        F.findView(view, R.id.tvKursOpis, TextView.class).setText(g.KursOpis);
        F.findView(view, R.id.tvKursTipNaziv, TextView.class).setText(g.KursTipNaziv);
        F.findView(view, R.id.tvTrajanje, TextView.class).setText(g.KursTipTrajanje + " časova");
        F.findView(view, R.id.tvCijena, TextView.class).setText(g.KursTipCijena);
        F.findView(view, R.id.tvKursTipOpis, TextView.class).setText(g.KursTipOpis);
        F.findView(view, R.id.tvKursKategorijaNaziv, TextView.class).setText(g.KursKategorijaNaziv);
        F.findView(view, R.id.tvPredavac, TextView.class).setText(g.ImePrezimeZaposlenika);

        Button btn_prijaviSe = F.findView(view, R.id.btn_prijaviSe, Button.class);
        final LogiraniKorisnik l = Sesija.getLogiraniKorisnik();
        if (l != null)
            btn_prijaviSe.setVisibility(View.VISIBLE);
        else
            btn_prijaviSe.setVisibility(View.INVISIBLE);

        btn_prijaviSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrupaKandidati.prijaviKandidataUGrupu(l.KorisnickiNalogID, g.GrupaID);

                Sesija.setMojProfilData();
            }
        });

        return view;
    }

}
