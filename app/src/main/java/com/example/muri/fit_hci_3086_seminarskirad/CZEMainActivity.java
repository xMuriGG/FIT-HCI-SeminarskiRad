package com.example.muri.fit_hci_3086_seminarskirad;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.muri.fit_hci_3086_seminarskirad.api.GrupaKandidati;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.MyApp;
import com.example.muri.fit_hci_3086_seminarskirad.helper.config.Sesija;
import com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe.AktivneGrupeMainFragment;
import com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe.model.AktivnaGrupaGByKursKategorija;
import com.example.muri.fit_hci_3086_seminarskirad.api.Grupe;
import com.example.muri.fit_hci_3086_seminarskirad.helper.volley.MyRunnable;
import com.example.muri.fit_hci_3086_seminarskirad.pages.autentifikacija.LoginFragment;
import com.example.muri.fit_hci_3086_seminarskirad.pages.autentifikacija.model.LogiraniKorisnik;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.MojprofilMainFragment;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojePohadjam;
import com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojeSamPohadjao;

import java.util.ArrayList;
import java.util.List;

public class CZEMainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView left_drawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence drawerNaslov;
    private CharSequence activityNaslov;
    List<AktivnaGrupaGByKursKategorija> list;

    //region Actionbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cze_main, menu);
        if (Sesija.getLogiraniKorisnik() != null)
            menu.getItem(0).setTitle("Logout");
        else
            menu.getItem(0).setTitle("Login");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_login) {
            selectItem(0);
            return true;
        }

        if (itemId == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czemain);
        pripremi_drawer();
        ucitajAktivneGrupe();
    }

    public void ucitajAktivneGrupe() {
        Grupe.aktivneGrupeFragmentGet(CZEMainActivity.this, new MyRunnable<List<AktivnaGrupaGByKursKategorija>>() {
            @Override
            public void run(List<AktivnaGrupaGByKursKategorija> result) {
                Toast.makeText(MyApp.getContext(), "Grupe učitane", Toast.LENGTH_LONG).show();
                list = result;
                FragmentManager fm = getSupportFragmentManager();
                Fragment f = AktivneGrupeMainFragment.newInstance(result);
//                Fragment f=new TestFragment();
                fm.beginTransaction().replace(R.id.CZEMainMjestoFragment, f, "AktivneGrupeF").commit();
            }
        });
    }

    public void selectItem(int position) {
        Fragment fragment = null;
        LogiraniKorisnik logiraniKorisnik = Sesija.getLogiraniKorisnik();
        Boolean logiran = logiraniKorisnik != null;

        if (position == 0) {
            if (logiran) {
                Sesija.setLogiraniKorisnik(null);
                Sesija.setGrupeKojePohadjam(null);
                Sesija.setGrupeKojeSamPohadjao(null);
                finish();
                startActivity(new Intent(this, CZEMainActivity.class));
                return;
            } else {
                fragment = new LoginFragment();
            }
        }
        if (position == 1) {
            ucitajAktivneGrupe();
            fragment = AktivneGrupeMainFragment.newInstance(list);
        }


        if (position == 2) {

            fragment = new MojprofilMainFragment();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.CZEMainMjestoFragment, fragment).commit();
        // update selected item and title, then close the drawer
        left_drawerList.setItemChecked(position, true);
        setTitle(getNaslovi()[position]);
        drawerLayout.closeDrawer(left_drawerList);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

        if (getSupportFragmentManager().findFragmentByTag("AktivneGrupeF") != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

    }


    private void pripremi_drawer() {
        activityNaslov = drawerNaslov = getTitle();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        left_drawerList = (ListView) findViewById(R.id.left_drawerList);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        left_drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.stavka_drawer_list, getNaslovi()));

        left_drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });


        mDrawerToggle = new ActionBarDrawerToggle(
                this,                       /* host Activity */
                drawerLayout,               /* DrawerLayout object */
                R.string.drawer_otvoreno,   /* "open drawer" description for accessibility */
                R.string.drawer_zatvoreno   /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(activityNaslov);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerNaslov);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
    }

    protected String[] getNaslovi() {
        Boolean logiran = Sesija.getLogiraniKorisnik() != null;
        ArrayList<String> meni = new ArrayList<>();

        String stavka1 = !logiran ? "Login" : "Logout";
        String stavka2 = "AktivneGrupe";
        String stavka3 = "Moj profil";

        meni.add(stavka1);
        meni.add(stavka2);
        if (logiran)
            meni.add(stavka3);

        return meni.toArray(new String[0]);
    }
}
