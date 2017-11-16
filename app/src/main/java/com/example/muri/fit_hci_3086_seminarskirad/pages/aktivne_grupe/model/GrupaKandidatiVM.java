package com.example.muri.fit_hci_3086_seminarskirad.pages.aktivne_grupe.model;

/**
 * Created by Muri on 10.09.2017..
 */

public class GrupaKandidatiVM {
    public int GrupaKandidatiID ;
    public int KandidatID ;
    public int GrupaID ;
    public Boolean Otplaceno ;

    public GrupaKandidatiVM(int kandidatId,int grupaId)
    {
        KandidatID = kandidatId;
        GrupaID = grupaId;
        Otplaceno = false;
    }
}
