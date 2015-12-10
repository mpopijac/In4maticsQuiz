package in4matics_team.in4maticsquiz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import in4matics_team_local.db.Korisnik;
import in4matics_team_local.db.Odgovor;
import in4matics_team_local.db.Pitanja;
import in4matics_team_local.db.Poglavlje;
import in4matics_team_local.db.Razred;
import in4matics_team_local.db.Rezultat;
import in4matics_team_local.db.Tip_korisnika;

/**
 * Created by Anabel Li Kečkeš on 7.11.2015..
 */
public class JsonAdapter {


    public static ArrayList<Tip_korisnika> getTip_korisnika(String jsonString){

        ArrayList<Tip_korisnika> tip_korisnikas =new ArrayList<Tip_korisnika>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            int size = jsonArray.length();

            for (int i=0; i<size; i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Tip_korisnika tip_korisnika = new Tip_korisnika(
                        jsonObject.getLong("IDtip"),
                        jsonObject.getString("naziv")
                );
                tip_korisnikas.add(tip_korisnika);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return tip_korisnikas;
    }



    public static ArrayList<Korisnik> getKorisnik(String jsonString){

        ArrayList<Korisnik> korisnici =new ArrayList<Korisnik>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            int size = jsonArray.length();

            for (int i=0; i<size; i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Korisnik korisnik = new Korisnik(
                        jsonObject.getLong("IDkorisnik"),
                        jsonObject.getString("ime"),
                        jsonObject.getString("prezime"),
                        jsonObject.getString("korisnickoIme"),
                        jsonObject.getString("lozinka"),
                        jsonObject.getString("email"),
                        jsonObject.getLong("IDtip")
                );
                korisnici.add(korisnik);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return korisnici;
    }


    public static ArrayList<Rezultat> getRezultat(String jsonString) throws ParseException {

        ArrayList<Rezultat> rezultati =new ArrayList<Rezultat>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            int size = jsonArray.length();

            for (int i=0; i<size; i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);

                //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                Rezultat rezultat = new Rezultat(
                        jsonObject.getLong("IDrezultat"),
                        jsonObject.getLong("IDkorisnik"),
                        jsonObject.getLong("IDrazred"),
                        jsonObject.getLong("bodovi"),
                        jsonObject.getString("datum")
                       // sdf.parse(jsonObject.getString("datum"))
                );
                rezultati.add(rezultat);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return rezultati;
    }



    public static ArrayList<Odgovor> getOdgovor(String jsonString){

        ArrayList<Odgovor> odgovori =new ArrayList<Odgovor>();

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            int size = jsonArray.length();

            for (int i=0; i<size; i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Odgovor odgovor = new Odgovor(
                        jsonObject.getLong("IDodgovor"),
                        jsonObject.getString("naziv"),
                        jsonObject.getInt("tocan"),
                        jsonObject.getLong("IDpitanja")

                );
                odgovori.add(odgovor);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return odgovori;
    }



    public static ArrayList<Pitanja> getPitanja(String jsonString){

        ArrayList<Pitanja> pitanja =new ArrayList<Pitanja>();

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            int size = jsonArray.length();

            for (int i=0; i<size; i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Pitanja pitanje = new Pitanja(
                        jsonObject.getLong("IDpitanja"),
                        jsonObject.getString("pitanje"),
                        jsonObject.getLong("IDpoglavlje"),
                        jsonObject.getLong("IDrazred")
                );
                pitanja.add(pitanje);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return pitanja;
    }



    public static ArrayList<Poglavlje> getPoglavlje(String jsonString){

        ArrayList<Poglavlje> poglavlja =new ArrayList<Poglavlje>();

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            int size = jsonArray.length();

            for (int i=0; i<size; i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Poglavlje poglavlje = new Poglavlje(
                        jsonObject.getLong("IDpoglavlje"),
                        jsonObject.getString("naziv"),
                        jsonObject.getInt("ukljuceno")

                );
                poglavlja.add(poglavlje);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return poglavlja;
    }



    public static ArrayList<Razred> getRazred(String jsonString){

        ArrayList<Razred> razredi =new ArrayList<Razred>();

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            int size = jsonArray.length();

            for (int i=0; i<size; i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Razred razred = new Razred(
                        jsonObject.getLong("IDrazred"),
                        jsonObject.getString("naziv")

                );
                razredi.add(razred);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return razredi;
    }

}
