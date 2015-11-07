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
    public abstract void LoadData(Activity activity);
}
