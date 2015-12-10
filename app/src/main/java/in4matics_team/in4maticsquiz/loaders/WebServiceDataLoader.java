package in4matics_team.in4maticsquiz.loaders;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.activeandroid.query.Select;

import in4matics_team.in4maticsquiz.DataLoader;
import in4matics_team.in4maticsquiz.JsonAdapter;
import in4matics_team.in4maticsquiz.R;
import in4matics_team_local.db.Korisnik;
import in4matics_team_local.db.Odgovor;
import in4matics_team_local.db.Pitanja;
import in4matics_team_local.db.Poglavlje;
import in4matics_team_local.db.Razred;
import in4matics_team_local.db.Rezultat;
import in4matics_team_local.db.Tip_korisnika;
import in4matics_team_local.ws.WebServiceAsyncTask;
import in4matics_team_local.ws.WebServiceParams;
import in4matics_team_local.ws.WebServiceResultHandler;

/**
 * Created by Matija Popijač on 10.11.2015..
 */

public class WebServiceDataLoader extends DataLoader{

    Activity activity = null;
    private boolean tip_korisnikaLoaded=false;
    private boolean korisnikLoaded=false;
    private boolean rezultatLoaded=false;
    private boolean razredLoaded=false;
    private boolean pitanjaLoaded=false;
    private boolean poglavljaLoaded=false;
    private boolean odgovoriLoaded=false;

    private int brojRedova;
    private String json;


    public void LoadData(Activity activity){
        super.LoadData(activity);
        this.activity=activity;


        WebServiceAsyncTask atTip_korisnika= new WebServiceAsyncTask();
        WebServiceParams paramsTip_korisnika = new WebServiceParams();
        //paramsTip_korisnika.jsonParams = json; // posta $_POST['json'];
        paramsTip_korisnika.jsonParams = getXmlAzuriranjeJsonTime("tip_korisnika", Tip_korisnika.class);
        paramsTip_korisnika.methodName = "getAll"; // posta $_POST['method'];
        paramsTip_korisnika.serviceName = "tip_korisnika";
        paramsTip_korisnika.targetAttribute="items";
        paramsTip_korisnika.resultHandler=getAllTip_korisnikaHandler;
        atTip_korisnika.execute(new WebServiceParams[]{paramsTip_korisnika});



        WebServiceAsyncTask atkorisnik= new WebServiceAsyncTask();
        WebServiceParams paramskorisnik = new WebServiceParams();
        paramskorisnik.jsonParams = getXmlAzuriranjeJsonTime("korisnik",Korisnik.class);
        paramskorisnik.methodName = "getAll";
        paramskorisnik.serviceName = "korisnik";
        paramskorisnik.targetAttribute="items";
        paramskorisnik.resultHandler=getAllKorisnikHandler;
        atkorisnik.execute(new WebServiceParams[]{paramskorisnik});




        WebServiceAsyncTask atrezultat= new WebServiceAsyncTask();
        WebServiceParams paramsrezultat = new WebServiceParams();
        paramsrezultat.jsonParams = getXmlAzuriranjeJsonTime("rezultat",Rezultat.class);
        paramsrezultat.methodName = "getAll";
        paramsrezultat.serviceName = "rezultat";
        paramsrezultat.targetAttribute="items";
        paramsrezultat.resultHandler=getAllRezultatHandler;
        atrezultat.execute(new WebServiceParams[]{paramsrezultat});



        WebServiceAsyncTask atrazred= new WebServiceAsyncTask();
        WebServiceParams paramsrazred = new WebServiceParams();
        paramsrazred.jsonParams = getXmlAzuriranjeJsonTime("razred",Razred.class);
        paramsrazred.methodName = "getAll";
        paramsrazred.serviceName = "razred";
        paramsrazred.targetAttribute="items";
        paramsrazred.resultHandler=getAllRazredHandler;
        atrazred.execute(new WebServiceParams[]{paramsrazred});



        WebServiceAsyncTask atpitanja= new WebServiceAsyncTask();
        WebServiceParams paramspitanja = new WebServiceParams();
        paramspitanja.jsonParams = getXmlAzuriranjeJsonTime("pitanja",Pitanja.class);;
        paramspitanja.methodName = "getAll";
        paramspitanja.serviceName = "pitanja";
        paramspitanja.targetAttribute="items";
        paramspitanja.resultHandler=getAllPitanjaHandler;
        atpitanja.execute(new WebServiceParams[]{paramspitanja});




        WebServiceAsyncTask atpoglavlje= new WebServiceAsyncTask();
        WebServiceParams paramspoglavlje = new WebServiceParams();
        paramspoglavlje.jsonParams = getXmlAzuriranjeJsonTime("poglavlje",Poglavlje.class);;
        paramspoglavlje.methodName = "getAll";
        paramspoglavlje.serviceName = "poglavlje";
        paramspoglavlje.targetAttribute="items";
        paramspoglavlje.resultHandler=getAllPoglavljeHandler;
        atpoglavlje.execute(new WebServiceParams[]{paramspoglavlje});



        WebServiceAsyncTask atodgovor= new WebServiceAsyncTask();
        WebServiceParams paramsodgovor = new WebServiceParams();
        paramsodgovor.jsonParams = getXmlAzuriranjeJsonTime("odgovor",Odgovor.class);;
        paramsodgovor.methodName = "getAll";
        paramsodgovor.serviceName = "odgovor";
        paramsodgovor.targetAttribute="items";
        paramsodgovor.resultHandler=getAllOdgovorHandler;
        atodgovor.execute(new WebServiceParams[]{paramsodgovor});

    }


    WebServiceResultHandler getAllTip_korisnikaHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {

            if(ok){
                try {

                    //treba napraviti provjeru dali postoji već taj zapis u bazi ( slučaj ažuriranja )

                    tip_korisnikas = JsonAdapter.getTip_korisnika(result);
                    for (Tip_korisnika t : tip_korisnikas){
                        t.save();
                        Log.i("test", t.getNaziv());
                    }

                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("tip_korisnika",timestamp);

                    tip_korisnikaLoaded=true;
                    bindTables();
                }catch (Exception e){
                    Toast.makeText(activity,R.string.data_error_tip_korisnika, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    WebServiceResultHandler getAllKorisnikHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {

            if(ok){
                try {

                    //treba napraviti provjeru dali postoji već taj zapis u bazi ( slučaj ažuriranja )

                    korisnici=JsonAdapter.getKorisnik(result);
                    for (Korisnik k : korisnici){
                        k.save();
                        Log.i("test", k.getEmail());
                    }

                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("korisnik", timestamp);

                    korisnikLoaded=true;
                    bindTables();
                }catch (Exception e){
                    Toast.makeText(activity,R.string.data_error_korisnik, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    WebServiceResultHandler getAllRezultatHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {

            if(ok){
                try {

                    //treba napraviti provjeru dali postoji već taj zapis u bazi ( slučaj ažuriranja )

                    rezultati = JsonAdapter.getRezultat(result);
                    for (Rezultat r : rezultati){
                        r.save();
                        Log.i("test", Long.toString(r.getBodovi()));
                    }

                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("rezultat", timestamp);


                    rezultatLoaded=true;
                    bindTables();
                }catch (Exception e){
                    Toast.makeText(activity,R.string.data_error_rezultat, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    WebServiceResultHandler getAllRazredHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {
            razredi = JsonAdapter.getRazred(result);
            if(ok){
                try {

                    //treba napraviti provjeru dali postoji već taj zapis u bazi ( slučaj ažuriranja )

                    for (Razred raz : razredi){
                        raz.save();
                        Log.i("test", raz.getNaziv());
                    }


                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("razred", timestamp);

                    razredLoaded=true;
                    bindTables();
                }catch (Exception e){
                    Toast.makeText(activity,R.string.data_error_razred, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    WebServiceResultHandler getAllPitanjaHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {
            pitanjas = JsonAdapter.getPitanja(result);
            if (ok){
                try {

                    //treba napraviti provjeru dali postoji već taj zapis u bazi ( slučaj ažuriranja )

                    for (Pitanja p : pitanjas){
                        p.save();
                        Log.i("test", p.getPitanje());
                    }


                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("pitanja", timestamp);


                    pitanjaLoaded=true;
                    bindTables();
                }catch (Exception e){
                    Toast.makeText(activity,R.string.data_error_pitanja, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    WebServiceResultHandler getAllPoglavljeHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {
            poglavlja = JsonAdapter.getPoglavlje(result);
            if (ok){
                try {

                    //treba napraviti provjeru dali postoji već taj zapis u bazi ( slučaj ažuriranja )

                    for (Poglavlje po : poglavlja){
                        po.save();
                        Log.i("test", po.getNaziv());
                    }


                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("poglavlje", timestamp);


                    poglavljaLoaded=true;
                    bindTables();
                }catch (Exception e){
                    Toast.makeText(activity,R.string.data_error_poglavlja, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    WebServiceResultHandler getAllOdgovorHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {
            odgovori=JsonAdapter.getOdgovor(result);
            if (ok){
                try {

                    //treba napraviti provjeru dali postoji već taj zapis u bazi ( slučaj ažuriranja )

                    for (Odgovor o : odgovori){
                        o.save();
                        Log.i("test", o.getNaziv());
                    }


                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("odgovor",timestamp);


                    odgovoriLoaded=true;
                    bindTables();

                }catch (Exception e){
                    Toast.makeText(activity,R.string.data_error_odgovor, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private void bindTables(){

        if(tip_korisnikaLoaded && korisnikLoaded) {
            // povezujemo tablicu tip_korisnika sa tablicom kotisnik
            for (Tip_korisnika t : tip_korisnikas) {
                for (Korisnik k : korisnici) {
                    if (k.getIDtip() == t.getIDtip()) {
                        k.setTip_korisnika(t);
                        k.save();
                    }
                }
            }
            tip_korisnikaLoaded = false;
            //dataLoaded();
        }

        if (korisnikLoaded && rezultatLoaded) {
            //povezujemo tablicu korisnik sa tablicom rezultat
            for (Korisnik k : korisnici) {
                for (Rezultat r : rezultati) {
                    if (r.getIDkorisnik() == k.getIDkorisnik()) {
                        r.setKorisnik(k);
                        r.save();
                    }
                }
            }
            korisnikLoaded = false;
            //dataLoaded();
        }

        if(razredLoaded && rezultatLoaded && pitanjaLoaded) {
            //povezujemo tablicu rezultati sa tablicom razred
            for (Razred r : razredi) {
                for (Rezultat rz : rezultati) {
                    if (rz.getIDrazred() == r.getIDrazred()) {
                        rz.setRazred(r);
                        rz.save();
                    }
                }
                //povezujemo tablicu razred sa tablicom pitanja
                for (Pitanja p : pitanjas) {
                    if (p.getIDrazred() == r.getIDrazred()) {
                        p.setRazred(r);
                        p.save();
                    }
                }
            }
            rezultatLoaded = false;
            razredLoaded = false;
            //dataLoaded();
        }

        if (poglavljaLoaded && pitanjaLoaded) {
            //povezujemo tablicu poglavlje sa tablicom pitanja
            for (Poglavlje p : poglavlja) {
                for (Pitanja pi : pitanjas) {
                    if (p.getIDpoglavlje() == pi.getIDpoglavlje()) {
                        pi.setPoglavlje(p);
                        pi.save();
                    }
                }
            }
            poglavljaLoaded = false;
            //dataLoaded();
        }

        if(pitanjaLoaded && odgovoriLoaded) {
            //povezujemo tablicu odgovor sa tablicom pitanja
            for (Pitanja p : pitanjas) {
                for (Odgovor o : odgovori) {
                    if (p.getIDpitanja() == o.getIDpitanja()) {
                        o.setPitanje(p);
                        o.save();
                    }
                }
            }
            pitanjaLoaded = false;
            odgovoriLoaded = false;
            //dataLoaded();
        }

    }


    public String getXmlAzuriranjeJsonTime(String atribut, Class tablica ){
        brojRedova = new Select().from(tablica).count();
        Log.i("Broj redova:", String.valueOf(brojRedova));
        if(brojRedova!=0){

            //dohvati vrijeme iz xml fajla
            SharedPreferences baza = activity.getSharedPreferences("azuriranje", activity.MODE_PRIVATE);
            json=Long.toString(baza.getLong(atribut,0));
            Log.i("Json vrijeme iz xml-a :", json);

        }else{
            //proslijedi vrijednost 0
            json="0";
            Log.i("Json vrijeme 0 :", json);
        }

        return json;
    }

    public void setXmalAzuriranjeJsonTime(String atribut, Long timestamp){
        SharedPreferences baza = activity.getSharedPreferences("azuriranje", activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = baza.edit();
        edit.putLong(atribut,timestamp);
        edit.commit();
    }


}
