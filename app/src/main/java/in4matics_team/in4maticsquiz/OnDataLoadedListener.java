package in4matics_team.in4maticsquiz;

import java.util.ArrayList;

import in4matics_team_local.db.Korisnik;
import in4matics_team_local.db.Odgovor;
import in4matics_team_local.db.Pitanja;
import in4matics_team_local.db.Poglavlje;
import in4matics_team_local.db.Razred;
import in4matics_team_local.db.Rezultat;
import in4matics_team_local.db.Tip_korisnika;

/**
 * Created by Matija Popijač on 1.12.2015..
 */
public interface OnDataLoadedListener {
    public void onDataLoaded(ArrayList<Tip_korisnika> tip_korisnikas, ArrayList<Korisnik> korisnici, ArrayList<Rezultat> rezultati, ArrayList<Razred> razredi, ArrayList<Pitanja> pitanjas, ArrayList<Poglavlje> poglavlja, ArrayList<Odgovor> odgovori);
}