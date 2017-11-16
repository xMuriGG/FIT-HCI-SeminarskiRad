package com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.muri.fit_hci_3086_seminarskirad.R;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.F;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.MyApp;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.Sesija;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojePohadjam;
import com.example.muri.fit_hci_3086_seminarskirad.pages.autentifikacija.model.UplataKandidataVM;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MojprofilExpandableListFragment extends Fragment {


    public MojprofilExpandableListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_mojprofil_expandable_list, container, false);
        final List<GrupeKojePohadjam> grupeKojePohadjam= Sesija.getGrupeKojePohadjam();
        ExpandableListView expandableListView=view.findViewById(R.id.expandableListView);
        if (grupeKojePohadjam!=null)
        {
            expandableListView.setAdapter(new BaseExpandableListAdapter() {
                @Override
                public int getGroupCount() {
                    return grupeKojePohadjam.size();
                }

                @Override
                public int getChildrenCount(int i) {
                    return grupeKojePohadjam.get(i).UplateKandidata.size();
                }

                @Override
                public Object getGroup(int i) {
                    return grupeKojePohadjam.get(i);
                }

                @Override
                public Object getChild(int i, int i1) {
                    return grupeKojePohadjam.get(i).UplateKandidata.get(i1);
                }

                @Override
                public long getGroupId(int i) {
                    return grupeKojePohadjam.get(i).GrupaKandidatiID;
                }

                @Override
                public long getChildId(int i, int i1) {
                    return grupeKojePohadjam.get(i).UplateKandidata.get(i1).UplataKandidataID;
                }

                @Override
                public boolean hasStableIds() {
                    return false;
                }

                @Override
                public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                    if (view==null)
                    {
                        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        view=inflater.inflate(R.layout.stavka_aktivne_grupe,viewGroup,false);
                    }
                    GrupeKojePohadjam g=grupeKojePohadjam.get(i);

                    if (g.UplateKandidata.size()>=1)
                    {
                        Drawable d= MyApp.getContext().getDrawable(R.drawable.list_success_light_selector);
                        view.setBackground(d);
                    }
                    else
                    {
                        Drawable d= MyApp.getContext().getDrawable(R.drawable.list_selector);
                        view.setBackground(d);
                    }
                    TextView tvDatum = view.findViewById(R.id.tvDatum);
                    TextView tvKursTipNaziv = view.findViewById(R.id.tvKursTipNaziv);
                    TextView tvKursNaziv = view.findViewById(R.id.tvKursNaziv);
                    TextView tvKursKategorijaNaziv = view.findViewById(R.id.tvKursKategorijaNaziv);
                    TextView tvKursOpis = view.findViewById(R.id.tvKursOpis);
                    TextView tvImePrezimeZaposlenika = view.findViewById(R.id.tvImePrezimeZaposlenika);
                    TextView tvPrijave = view.findViewById(R.id.tvPrijave);
                    TextView tvCijena = view.findViewById(R.id.tvCijena);

                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    tvDatum.setText(df.format(g.Pocetak));
                    tvKursTipNaziv.setText(g.KursTipNaziv);
                    tvKursNaziv.setText(g.KursNaziv);
                    tvKursKategorijaNaziv.setText(g.KursKategorijaNaziv);
                    tvKursOpis.setText(g.KursOpis);
                    tvImePrezimeZaposlenika.setText(g.ImePrezimeZaposlenika);
                    tvPrijave.setText(g.Prijava + "");
                    tvCijena.setText(g.KursTipCijena);

                    return view;
                }

                @Override
                public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                    if (view==null)
                    {
                        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        view = inflater.inflate(R.layout.stavka_uplata, viewGroup, false);
                    }
                    UplataKandidataVM u=grupeKojePohadjam.get(i).UplateKandidata.get(i1);

                    F.findView(view, R.id.lvSvrha, TextView.class).setText(u.Biljeske);
                    F.findView(view, R.id.lvIznos, TextView.class).setText(F.decimal_0_00(u.Kolicina)+" KM");
                    F.findView(view, R.id.lvDatum, TextView.class).setText(F.date_ddMMyyy(u.DatumUplate));

                    return view;
                }

                @Override
                public boolean isChildSelectable(int i, int i1) {
                    return false;
                }
            });
        }

        return view;
    }

}
