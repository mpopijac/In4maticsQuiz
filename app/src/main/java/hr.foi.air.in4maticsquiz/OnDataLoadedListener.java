package hr.foi.air.in4maticsquiz;

import java.util.ArrayList;

import hr.foi.air.in4maticsquiz.db.Korisnik;
import hr.foi.air.in4maticsquiz.db.Odgovor;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.db.Poglavlje;
import hr.foi.air.in4maticsquiz.db.Razred;
import hr.foi.air.in4maticsquiz.db.Rezultat;
import hr.foi.air.in4maticsquiz.db.Tip_korisnika;

/**
 * Created by Matija Popijač on 1.12.2015..
 */
public interface OnDataLoadedListener {
    public void onDataLoaded(ArrayList<Tip_korisnika> tip_korisnikas, ArrayList<Korisnik> korisnici, ArrayList<Rezultat> rezultati, ArrayList<Razred> razredi, ArrayList<Pitanja> pitanjas, ArrayList<Poglavlje> poglavlja, ArrayList<Odgovor> odgovori);
}