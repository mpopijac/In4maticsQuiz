package in4matics_team.in4maticsquiz;

import android.app.Activity;

import java.util.ArrayList;

import in4matics_team_local.db.Korisnik;
import in4matics_team_local.db.Odgovor;
import in4matics_team_local.db.Pitanja;
import in4matics_team_local.db.Poglavlje;
import in4matics_team_local.db.Razred;
import in4matics_team_local.db.Rezultat;
import in4matics_team_local.db.Tip_korisnika;


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
