package com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Muri on 18.08.2017..
 */

public class AktivnaGrupaListVM implements Serializable{
    public int Prijava ;
    //Grupa
    public int GrupaID ;
    public String GrupaNaziv ;
    public Date Pocetak ;
    public Date Kraj ;
    public boolean Aktivna ;
    public boolean UToku ;
//    public byte[] Slika;
    public String SlikaThumb;

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
}
