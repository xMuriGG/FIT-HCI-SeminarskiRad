package com.example.muri.fit_hci_3086_seminarskirad.pages.moj_profil.model;


import android.graphics.Path;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Muri on 12.09.2017..
 */

public class Ocjena implements Serializable {
    public int OcjeneID ;
    public int GrupaKandidatiID ;
    public Date Datum ;
    public int Ocjena ;
    public  String Opis;

    public Ocjena(int grupaKandidatiID,int ocjena,String opis)
    {
        OcjeneID=0;
        GrupaKandidatiID=grupaKandidatiID;
        Ocjena=ocjena;
        Opis=opis;

    }

}
