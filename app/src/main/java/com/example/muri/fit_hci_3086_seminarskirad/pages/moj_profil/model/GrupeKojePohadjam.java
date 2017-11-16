package com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model;

import com.example.muri.fit_hci_3086_seminarskirad.pages.autentifikacija.model.UplataKandidataVM;

import java.util.Date;
import java.util.List;

/**
 * Created by Muri on 10.09.2017..
 */

public class GrupeKojePohadjam {
    public int Prijava ;
    public int GrupaKandidatiID ;
    //Grupa
    public int GrupaID ;
    public String GrupaNaziv ;
    public Date Pocetak ;
    public Date Kraj ;
    public Boolean Aktivna ;
    public Boolean UToku ;
    //Kurs
    public int KursID ;
    public String KursNaziv ;
    public String KursOpis ;
    //KursTip
    public int KursTipID ;
    public String KursTipNaziv ;
    public int KursTipTrajanje ;//u casovima
    public String KursTipCijena ;
    public String KursTipOpis ;
    //Kategorija
    public int KursKategorijaID ;
    public String KursKategorijaNaziv ;
    //Zaposlenik
    public String ImePrezimeZaposlenika ;

    public List<UplataKandidataVM> UplateKandidata ;
}
