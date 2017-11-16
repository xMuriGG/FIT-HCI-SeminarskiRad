package com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.muri.fit_hci_3086_seminarskirad.R;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.F;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.MyApp;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.Sesija;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojeSamPohadjao;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.Ocjena;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MojprofilListFragment extends Fragment {
LayoutInflater layoutInflater=null;
    private ListView listView;
    private List<GrupeKojeSamPohadjao> grupeKojeSamPohadjao;

    public MojprofilListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Boolean result = data.getBooleanExtra("result", false);

        if (result) {
          grupeKojeSamPohadjao=Sesija.getGrupeKojeSamPohadjao();
            listView.setAdapter(new CustomBaseAdapter(grupeKojeSamPohadjao));
        }
    }

    class CustomBaseAdapter extends BaseAdapter
    {
        List<GrupeKojeSamPohadjao> list=null;
        public CustomBaseAdapter(List<GrupeKojeSamPohadjao> l)
        {
            list=l;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return list.get(i).GrupaKandidatiID;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            GrupeKojeSamPohadjao g=list.get(i);
            if (view==null)
            {
                view=layoutInflater.inflate(R.layout.stavka_grupa_koju_sam_pohadjao,viewGroup,false);
            }

            if (g.CertifikatOdobren)
            {
                Drawable d= MyApp.getContext().getDrawable(R.drawable.list_success_selector);
                view.setBackground(d);
            }


            TextView tvGrupaNaziv = F.findView(view, R.id.tvGrupaNaziv, TextView.class);
            TextView tvKursNaziv = F.findView(view, R.id.tvKursNaziv, TextView.class);
            TextView tvKursTipNaziv = F.findView(view, R.id.tvKursTipNaziv, TextView.class);
            TextView tvImePrezimeZaposlenika = F.findView(view, R.id.tvImePrezimeZaposlenika, TextView.class);
            TextView tvUplaceno = F.findView(view, R.id.tvUplaceno, TextView.class);
            RatingBar rbOcjena=F.findView(view,R.id.rbOcjena,RatingBar.class);

            tvGrupaNaziv.setText(g.GrupaNaziv);
            tvKursNaziv.setText(g.KursNaziv);
            tvKursTipNaziv.setText(g.TipNaziv);
            tvImePrezimeZaposlenika.setText(g.Predavac);
            tvUplaceno.setText(g.Uplaceno);
            rbOcjena.setRating(g.Ocjena);
            LayerDrawable stars = (LayerDrawable) rbOcjena.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
            return view;
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layoutInflater=inflater;
        View view= inflater.inflate(R.layout.fragment_mojprofil_list, container, false);
        grupeKojeSamPohadjao = Sesija.getGrupeKojeSamPohadjao();
        listView = view.findViewById(R.id.fragmentListView);

        listView.setAdapter( new CustomBaseAdapter(grupeKojeSamPohadjao));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GrupeKojeSamPohadjao g= grupeKojeSamPohadjao.get(i);
                Ocjena ocjena=new Ocjena(g.GrupaKandidatiID,g.Ocjena,g.Opis);

                final FragmentManager fm=getFragmentManager();
                DialogFragment fragment=RateKursDialogFragment.newInstance(ocjena);
                fragment.setTargetFragment(MojprofilListFragment.this,1);
                fragment.show(fm,"rateKurs");
            }
        });



        return view;

    }

}
