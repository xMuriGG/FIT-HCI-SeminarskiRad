package com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.muri.fit_hci_3086_seminarskirad.R;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.F;
import com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe.model.AktivnaGrupaListVM;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AktivneGrupeListFragment extends Fragment {

    private static final String ARG_PARAM1 = "aktivneGrupe";

    public AktivneGrupeListFragment() {
        // Required empty public constructor
    }
    public static AktivneGrupeListFragment newInstance(List<AktivnaGrupaListVM> param1) {
        AktivneGrupeListFragment fragment = new AktivneGrupeListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aktivne_grupe_list, container, false);

        final List<AktivnaGrupaListVM> list=(List<AktivnaGrupaListVM>)getArguments().getSerializable(ARG_PARAM1);

        ListView myListView = (ListView) view.findViewById(R.id.fragmentListView);

        myListView.setAdapter(new BaseAdapter() {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public AktivnaGrupaListVM getItem(int i) {
                return list.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                final AktivnaGrupaListVM g = list.get(i);
                if (view==null)
                view = inflater.inflate(R.layout.stavka_aktivne_grupe_sa_slikom, viewGroup, false);

                TextView tvDatum = view.findViewById(R.id.tvDatum);
                TextView tvKursTipNaziv = view.findViewById(R.id.tvKursTipNaziv);
                TextView tvGrupaNaziv = view.findViewById(R.id.tvGrupaNaziv);
                TextView tvImePrezimeZaposlenika = view.findViewById(R.id.tvImePrezimeZaposlenika);
                TextView tvPrijave = view.findViewById(R.id.tvPrijave);
                TextView tvCijena = view.findViewById(R.id.tvCijena);
                ImageView ivSlika= F.findView(view,R.id.iv_slika,ImageView.class);

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                tvDatum.setText(df.format(g.Pocetak));
                tvKursTipNaziv.setText(g.KursTipNaziv+"-"+g.KursNaziv);
                tvGrupaNaziv.setText(g.GrupaNaziv);
                tvImePrezimeZaposlenika.setText(g.ImePrezimeZaposlenika);
                tvPrijave.setText(g.Prijava + "");
                tvCijena.setText(g.KursTipCijena);

                if (g.SlikaThumb!=null)
                {
                    byte[] decodedBytes= Base64.decode(g.SlikaThumb,Base64.DEFAULT);
                    Bitmap bitmap= BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.length);
                    ivSlika.setImageBitmap(bitmap);
                }
                else
                {
                    ivSlika.setImageResource(R.drawable.no_image);
                }


                return view;
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AktivnaGrupaListVM g=list.get(i);
                FragmentManager fm =getActivity().getSupportFragmentManager();
                Fragment f = AktivnaGrupaDetailsFragment.newInstance(g);
                fm.beginTransaction().replace(R.id.CZEMainMjestoFragment, f).addToBackStack("AGDetails").commit();
                return;
            }
        });

        return view;
    }

}
