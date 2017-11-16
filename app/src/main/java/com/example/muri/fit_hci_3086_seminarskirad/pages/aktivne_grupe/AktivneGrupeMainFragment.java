package com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muri.fit_hci_3086_seminarskirad.CZEMainActivity;
import com.example.muri.fit_hci_3086_seminarskirad.R;
import com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe.model.AktivnaGrupaGByKursKategorija;
import com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe.model.AktivnaGrupaListVM;

import java.io.Serializable;
import java.util.List;

public class AktivneGrupeMainFragment extends Fragment {
    private static final String ARG_PARAM1 = "aktivneGrupeGrouped";

    private ViewPager viewPager;

    public static AktivneGrupeMainFragment newInstance(List<AktivnaGrupaGByKursKategorija> param1) {
        AktivneGrupeMainFragment fragment = new AktivneGrupeMainFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_aktivne_grupe, container, false);
        List<AktivnaGrupaGByKursKategorija> list = (List<AktivnaGrupaGByKursKategorija>) getArguments().getSerializable(ARG_PARAM1);
        pripremi_viewPager(list, view);

        return view;
    }


    @Override
    public void onStop() {
        super.onStop();
        ActionBar actionBar = ((CZEMainActivity) getActivity()).getSupportActionBar();
//        actionBar.hide();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.removeAllTabs();
    }


    private void pripremi_viewPager(final List<AktivnaGrupaGByKursKategorija> result, View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);


//        final FragmentManager fm=getFragmentManager();
        final FragmentManager fm = getChildFragmentManager();

        viewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                List<AktivnaGrupaListVM> aktivneGrupe = result.get(position).AktivnaGrupaListVM;
                Fragment fragment = AktivneGrupeListFragment.newInstance(aktivneGrupe);
                return fragment;
            }

            @Override
            public int getCount() {
                return result.size();
            }
        });


        final ActionBar actionBar = ((CZEMainActivity) getActivity()).getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                int pozicija = tab.getPosition();
                viewPager.setCurrentItem(pozicija);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

//        for (TabSuperClass s:list)
//        {
//           actionBar.addTab(actionBar.newTab().setText(s.SuperNaziv).setTabListener(tabListener));
//        }
        for (AktivnaGrupaGByKursKategorija s : result) {
            actionBar.addTab(actionBar.newTab().setText(s.Naziv).setTabListener(tabListener));
        }


        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                actionBar.setSelectedNavigationItem(position);
            }
        });
    }

}
