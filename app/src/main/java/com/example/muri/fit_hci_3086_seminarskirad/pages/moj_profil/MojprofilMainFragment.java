package com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muri.fit_hci_3086_seminarskirad.CZEMainActivity;
import com.example.muri.fit_hci_3086_seminarskirad.R;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.Sesija;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojePohadjam;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojeSamPohadjao;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MojprofilMainFragment extends Fragment {

    private ViewPager viewPager;


    public MojprofilMainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_mojprofil_main, container, false);

        pripremi_viewPager(view);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        ActionBar actionBar=((CZEMainActivity)getActivity()).getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.removeAllTabs();
    }

    private void pripremi_viewPager(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        final FragmentManager fm=getChildFragmentManager();

        viewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position)
                {
                    case 0: fragment=new MojprofilExpandableListFragment();break;
                    case 1:fragment=new MojprofilListFragment();break;
                }
                return fragment;
            }
            @Override
            public int getCount() {
                return 2;
            }
        });

        final ActionBar actionBar=((CZEMainActivity)getActivity()).getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener=new ActionBar.TabListener(){
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                int pozicija=tab.getPosition();
                viewPager.setCurrentItem(pozicija);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };
        actionBar.addTab(actionBar.newTab().setText("Grupe koje pohađam").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("koje sam pohađao").setTabListener(tabListener));


        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position)
            {
                super.onPageSelected(position);
                actionBar.setSelectedNavigationItem(position);
            }
        });

    }

}
