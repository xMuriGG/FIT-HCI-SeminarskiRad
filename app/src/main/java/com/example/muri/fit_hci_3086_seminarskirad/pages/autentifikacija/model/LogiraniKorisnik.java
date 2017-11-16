package com.example.muri.fit_hci_3086_seminarskirad.pages.autentifikacija.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Muri on 10.08.2017..
 */

public class LogiraniKorisnik {
    public int KorisnickiNalogID ;
    public String KorisnickoIme ;
    public String LozinkaHash ;
    public String LozinkaSalt ;
    public boolean Aktivan ;
    public UlogaKorisnika UlogaKorisnika ;

    public String Ime ;
    public String Prezime ;
    public Date DatumRodjenja ;
    public Spol Spol ;
    public String Email ;
    public String Adresa ;

    public boolean IsKandidat ;
    public boolean IsZaposlenik ;

//    public List<com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojePohadjam> GrupeKojePohadjam ;
//    public List<com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model.GrupeKojeSamPohadjao> GrupeKojeSamPohadjao ;

    public enum Spol { M,Z};
    public enum UlogaKorisnika { Administrator, Direktor, Administrativni_Radnik, Predavac, Kandidat }

}
