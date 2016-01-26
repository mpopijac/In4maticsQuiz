package hr.foi.air.in4maticsquiz.singletons;

import java.util.ArrayList;

import hr.foi.air.in4maticsquiz.db.Odgovor;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.db.Poglavlje;

/**
 * Created by Anabel Li on 26.1.2016..
 */
public class Azuriranje {

    public ArrayList<Poglavlje> poglavljeLista = new ArrayList<Poglavlje>();

    public void dodajPoglavlje(Poglavlje poglavljee){
        poglavljeLista.add(poglavljee);
    }

    private ArrayList<Pitanja> pitanje = new ArrayList<Pitanja>();

    private ArrayList<Odgovor> odgovor = new ArrayList<Odgovor>();



}
