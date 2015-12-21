package hr.foi.air.in4maticsquiz.core;

import android.app.Activity;

import java.util.ArrayList;

import hr.foi.air.in4maticsquiz.db.Korisnik;
import hr.foi.air.in4maticsquiz.db.Odgovor;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.db.Poglavlje;
import hr.foi.air.in4maticsquiz.db.Razred;
import hr.foi.air.in4maticsquiz.db.Rezultat;
import hr.foi.air.in4maticsquiz.db.Tip_korisnika;


/**
 * Created by Matija Popijač on 7.11.2015..
 */
public abstract class DataLoader {
    public ArrayList<Tip_korisnika> tip_korisnikas;
    public ArrayList<Korisnik> korisnici;
    public ArrayList<Rezultat> rezultati;
    public ArrayList<Razred> razredi;
    public ArrayList<Pitanja> pitanjas;
    public ArrayList<Poglavlje> poglavlja;
    public ArrayList<Odgovor> odgovori;
    OnDataLoadedListener dataLoadedListener;

    public void LoadData(Activity activity){
        if(dataLoadedListener == null){
            dataLoadedListener = (OnDataLoadedListener) activity;
        }
    }

    public boolean dataLoaded(){
        if(tip_korisnikas==null || korisnici==null || rezultati==null || razredi==null || pitanjas==null || poglavlja==null || odgovori==null){
            return false;
        }
        else {
            dataLoadedListener.onDataLoaded(tip_korisnikas, korisnici, rezultati, razredi, pitanjas, poglavlja, odgovori);
            return true;
        }
    }
}
