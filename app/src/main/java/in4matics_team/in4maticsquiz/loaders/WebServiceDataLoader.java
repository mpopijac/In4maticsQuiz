package in4matics_team.in4maticsquiz.loaders;

import android.app.Activity;
import android.widget.Toast;

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


    public void LoadData(Activity activity){
        this.activity=activity;

        WebServiceAsyncTask atTip_korisnika= new WebServiceAsyncTask();
        WebServiceParams paramsTip_korisnika = new WebServiceParams();
        paramsTip_korisnika.jsonParams = "";
        paramsTip_korisnika.methodName = "getAll";
        paramsTip_korisnika.serviceName = "tip_korisnika";
        paramsTip_korisnika.targetAttribute="items";
        paramsTip_korisnika.resultHandler=getAllTip_korisnikaHandler;
        atTip_korisnika.execute(new WebServiceParams[]{paramsTip_korisnika});



        WebServiceAsyncTask atkorisnik= new WebServiceAsyncTask();
        WebServiceParams paramskorisnik = new WebServiceParams();
        paramskorisnik.jsonParams = "";
        paramskorisnik.methodName = "getAll";
        paramskorisnik.serviceName = "korisnik";
        paramskorisnik.targetAttribute="items";
        paramskorisnik.resultHandler=getAllKorisnikHandler;
        atkorisnik.execute(new WebServiceParams[]{paramskorisnik});



        WebServiceAsyncTask atrezultat= new WebServiceAsyncTask();
        WebServiceParams paramsrezultat = new WebServiceParams();
        paramsrezultat.jsonParams = "";
        paramsrezultat.methodName = "getAll";
        paramsrezultat.serviceName = "rezultat";
        paramsrezultat.targetAttribute="items";
        paramsrezultat.resultHandler=getAllRezultatHandler;
        atrezultat.execute(new WebServiceParams[]{paramsrezultat});



        WebServiceAsyncTask atrazred= new WebServiceAsyncTask();
        WebServiceParams paramsrazred = new WebServiceParams();
        paramsrazred.jsonParams = "";
        paramsrazred.methodName = "getAll";
        paramsrazred.serviceName = "razred";
        paramsrazred.targetAttribute="items";
        paramsrazred.resultHandler=getAllRazredHandler;
        atrazred.execute(new WebServiceParams[]{paramsrazred});



        WebServiceAsyncTask atpitanja= new WebServiceAsyncTask();
        WebServiceParams paramspitanja = new WebServiceParams();
        paramspitanja.jsonParams = "";
        paramspitanja.methodName = "getAll";
        paramspitanja.serviceName = "pitanja";
        paramspitanja.targetAttribute="items";
        paramspitanja.resultHandler=getAllPitanjaHandler;
        atpitanja.execute(new WebServiceParams[]{paramspitanja});



        WebServiceAsyncTask atpoglavlje= new WebServiceAsyncTask();
        WebServiceParams paramspoglavlje = new WebServiceParams();
        paramspoglavlje.jsonParams = "";
        paramspoglavlje.methodName = "getAll";
        paramspoglavlje.serviceName = "poglavlje";
        paramspoglavlje.targetAttribute="items";
        paramspoglavlje.resultHandler=getAllPoglavljeHandler;
        atpoglavlje.execute(new WebServiceParams[]{paramspoglavlje});



        WebServiceAsyncTask atodgovor= new WebServiceAsyncTask();
        WebServiceParams paramsodgovor = new WebServiceParams();
        paramsodgovor.jsonParams = "";
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
                    tip_korisnikas = JsonAdapter.getTip_korisnika(result);
                    for (Tip_korisnika t : tip_korisnikas){
                        t.save();
                    }
                    /*
                    Toast.makeText(activity, R.string.data_loaded_tip_korisnika, Toast.LENGTH_SHORT).show();
                    tip_korisnikaLoaded=true;
                    */
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
                    korisnici=JsonAdapter.getKorisnik(result);
                    for (Korisnik k : korisnici){
                        k.save();
                    }
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
                    rezultati = JsonAdapter.getRezultat(result);
                    for (Rezultat r : rezultati){
                        r.save();
                    }
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
            if(ok){
                try {
                    for (Razred raz : razredi){
                        raz.save();
                    }
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
            if (ok){
                try {
                    for (Pitanja p : pitanjas){
                        p.save();
                    }
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
            if (ok){
                try {
                    for (Poglavlje po : poglavlja){
                        po.save();
                    }
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
            if (ok){
                try {
                    for (Odgovor o : odgovori){
                        o.save();
                    }
                    odgovoriLoaded=true;
                    bindTables();
                }catch (Exception e){
                    Toast.makeText(activity,R.string.data_error_odgovor, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private void bindTables(){
        if(tip_korisnikaLoaded && korisnikLoaded && rezultatLoaded && razredLoaded && pitanjaLoaded && poglavljaLoaded && odgovoriLoaded){

            // povezujemo tablicu tip_korisnika sa tablicom kotisnik
            for (Tip_korisnika t : tip_korisnikas){
                for (Korisnik k : korisnici){
                    if (k.getIDtip() == t.getIDtip()){
                        k.setTip_korisnika(t);
                        k.save();
                    }
                }
            }

            //povezujemo tablicu korisnik sa tablicom rezultat
            for (Korisnik k: korisnici){
                for (Rezultat r: rezultati){
                    if (r.getIDkorisnik()==k.getIDkorisnik()){
                        r.setKorisnik(k);
                        r.save();
                    }
                }
            }

            //povezujemo tablicu rezultati sa tablicom razred
            for (Razred r: razredi){
                for (Rezultat rz : rezultati){
                    if (rz.getIDrazred()==r.getIDrazred()){
                        rz.setRazred(r);
                        rz.save();
                    }
                }
                //povezujemo tablicu razred sa tablicom pitanja
                for (Pitanja p: pitanjas){
                    if(p.getIDrazred()==r.getIDrazred()){
                        p.setRazred(r);
                        p.save();
                    }
                }
            }

            //povezujemo tablicu poglavlje sa tablicom pitanja
            for (Poglavlje p : poglavlja){
                for (Pitanja pi : pitanjas){
                    if(p.getIDpoglavlje()==pi.getIDpoglavlje()){
                        pi.setPoglavlje(p);
                        pi.save();
                    }
                }
            }

            //povezujemo tablicu odgovor sa tablicom pitanja
            for (Pitanja p : pitanjas){
                for (Odgovor o: odgovori){
                    if(p.getIDpitanja()==o.getIDpitanja()){
                        o.setPitanje(p);
                        o.save();
                    }
                }
            }
            tip_korisnikaLoaded=false;
            korisnikLoaded=false;
            rezultatLoaded=false;
            razredLoaded=false;
            pitanjaLoaded=false;
            poglavljaLoaded=false;
            odgovoriLoaded=false;
        }
    }

}
