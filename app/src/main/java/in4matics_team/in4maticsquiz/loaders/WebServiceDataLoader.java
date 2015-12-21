package in4matics_team.in4maticsquiz.loaders;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

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

public class WebServiceDataLoader extends DataLoader {

    Activity activity = null;
    private boolean tip_korisnikaLoaded = false;
    private boolean korisnikLoaded = false;
    private boolean rezultatLoaded = false;
    private boolean razredLoaded = false;
    private boolean pitanjaLoaded = false;
    private boolean poglavljaLoaded = false;
    private boolean odgovoriLoaded = false;

    private int brojRedova;
    private String json;

    private int duplikat;


    public void LoadData(Activity activity) {
        super.LoadData(activity);
        this.activity = activity;


        WebServiceAsyncTask atTip_korisnika = new WebServiceAsyncTask();
        WebServiceParams paramsTip_korisnika = new WebServiceParams();
        //paramsTip_korisnika.jsonParams = json; // posta $_POST['json'];
        paramsTip_korisnika.jsonParams = getXmlAzuriranjeJsonTime("tip_korisnika", Tip_korisnika.class);
        paramsTip_korisnika.methodName = "getAll"; // posta $_POST['method'];
        paramsTip_korisnika.serviceName = "tip_korisnika";
        paramsTip_korisnika.targetAttribute = "items";
        paramsTip_korisnika.resultHandler = getAllTip_korisnikaHandler;
        atTip_korisnika.execute(new WebServiceParams[]{paramsTip_korisnika});


        WebServiceAsyncTask atkorisnik = new WebServiceAsyncTask();
        WebServiceParams paramskorisnik = new WebServiceParams();
        paramskorisnik.jsonParams = getXmlAzuriranjeJsonTime("korisnik", Korisnik.class);
        paramskorisnik.methodName = "getAll";
        paramskorisnik.serviceName = "korisnik";
        paramskorisnik.targetAttribute = "items";
        paramskorisnik.resultHandler = getAllKorisnikHandler;
        atkorisnik.execute(new WebServiceParams[]{paramskorisnik});


        WebServiceAsyncTask atrezultat = new WebServiceAsyncTask();
        WebServiceParams paramsrezultat = new WebServiceParams();
        paramsrezultat.jsonParams = getXmlAzuriranjeJsonTime("rezultat", Rezultat.class);
        paramsrezultat.methodName = "getAll";
        paramsrezultat.serviceName = "rezultat";
        paramsrezultat.targetAttribute = "items";
        paramsrezultat.resultHandler = getAllRezultatHandler;
        atrezultat.execute(new WebServiceParams[]{paramsrezultat});


        WebServiceAsyncTask atrazred = new WebServiceAsyncTask();
        WebServiceParams paramsrazred = new WebServiceParams();
        paramsrazred.jsonParams = getXmlAzuriranjeJsonTime("razred", Razred.class);
        paramsrazred.methodName = "getAll";
        paramsrazred.serviceName = "razred";
        paramsrazred.targetAttribute = "items";
        paramsrazred.resultHandler = getAllRazredHandler;
        atrazred.execute(new WebServiceParams[]{paramsrazred});


        WebServiceAsyncTask atpitanja = new WebServiceAsyncTask();
        WebServiceParams paramspitanja = new WebServiceParams();
        paramspitanja.jsonParams = getXmlAzuriranjeJsonTime("pitanja", Pitanja.class);
        ;
        paramspitanja.methodName = "getAll";
        paramspitanja.serviceName = "pitanja";
        paramspitanja.targetAttribute = "items";
        paramspitanja.resultHandler = getAllPitanjaHandler;
        atpitanja.execute(new WebServiceParams[]{paramspitanja});


        WebServiceAsyncTask atpoglavlje = new WebServiceAsyncTask();
        WebServiceParams paramspoglavlje = new WebServiceParams();
        paramspoglavlje.jsonParams = getXmlAzuriranjeJsonTime("poglavlje", Poglavlje.class);
        ;
        paramspoglavlje.methodName = "getAll";
        paramspoglavlje.serviceName = "poglavlje";
        paramspoglavlje.targetAttribute = "items";
        paramspoglavlje.resultHandler = getAllPoglavljeHandler;
        atpoglavlje.execute(new WebServiceParams[]{paramspoglavlje});


        WebServiceAsyncTask atodgovor = new WebServiceAsyncTask();
        WebServiceParams paramsodgovor = new WebServiceParams();
        paramsodgovor.jsonParams = getXmlAzuriranjeJsonTime("odgovor", Odgovor.class);
        ;
        paramsodgovor.methodName = "getAll";
        paramsodgovor.serviceName = "odgovor";
        paramsodgovor.targetAttribute = "items";
        paramsodgovor.resultHandler = getAllOdgovorHandler;
        atodgovor.execute(new WebServiceParams[]{paramsodgovor});

    }


    WebServiceResultHandler getAllTip_korisnikaHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {
            if (ok) {
                try {

                    tip_korisnikas = JsonAdapter.getTip_korisnika(result);
                    for (Tip_korisnika t : tip_korisnikas) {

                        // provjera duplikata
                        duplikat = new Select().from(Tip_korisnika.class).where("IDtip==?", t.getIDtip()).count();
                        //ako nije
                        if (duplikat == 0 && t.getObrisano()==0) {
                            t.save();
                            tip_korisnikaLoaded = true;
                            bindTables();
                        } else {
                            //provjera dali je duplikat obrisani, obrisani unos
                            if (t.getObrisano() == 1) {
                                new Delete().from(Tip_korisnika.class).where("IDtip=?", t.getIDtip()).execute();
                                Log.i("Obrisano: ", t.getNaziv());
                            } else {
                                //ako nije, ažuriraj
                                Log.i("Ušo sam nutra:", Long.toString(t.getIDtip()));
                                Log.i("Update - naziv:", t.getNaziv());
                                new Update(Tip_korisnika.class).set("naziv=?", t.getNaziv()).where("IDtip=?", t.getIDtip()).execute();
                            }
                        }

                        Log.i("test", t.getNaziv());
                    }

                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("tip_korisnika", timestamp);

                } catch (Exception e) {
                    Toast.makeText(activity, R.string.data_error_tip_korisnika, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    WebServiceResultHandler getAllKorisnikHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {
            if (ok) {
                try {
                    //treba napraviti provjeru dali postoji već taj zapis u bazi ( slučaj ažuriranja )

                    korisnici = JsonAdapter.getKorisnik(result);
                    for (Korisnik k : korisnici) {

                        duplikat = new Select().from(Korisnik.class).where("IDkorisnik==?", k.getIDkorisnik()).count();

                        if (duplikat == 0 && k.getObrisano()==0) {
                            k.save();
                            korisnikLoaded = true;
                            bindTables();
                        } else {

                            if (k.getObrisano() == 1) {
                                new Delete().from(Korisnik.class).where("IDkorisnik=?", k.getIDkorisnik()).execute();
                                Log.i("Obrisano: ", k.getKorisnickoIme());
                            } else {
                                new Update(Korisnik.class).set("ime=?", k.getIme()).where("IDkorisnik=?", k.getIDkorisnik()).execute();
                                new Update(Korisnik.class).set("prezime=?", k.getPrezime()).where("IDkorisnik=?", k.getIDkorisnik()).execute();
                                new Update(Korisnik.class).set("korisnickoIme=?", k.getKorisnickoIme()).where("IDkorisnik=?", k.getIDkorisnik()).execute();
                                new Update(Korisnik.class).set("email=?", k.getEmail()).where("IDkorisnik=?", k.getIDkorisnik()).execute();
                                new Update(Korisnik.class).set("IDtip=?", k.getIDtip()).where("IDkorisnik=?", k.getIDkorisnik()).execute();

                                Log.i("Uspjelo?:", k.getKorisnickoIme());
                            }

                        }

                        Log.i("test", k.getKorisnickoIme());
                    }

                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("korisnik", timestamp);

                } catch (Exception e) {
                    Toast.makeText(activity, R.string.data_error_korisnik, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    WebServiceResultHandler getAllRezultatHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {
            if (ok) {
                try {
                    //treba napraviti provjeru dali postoji već taj zapis u bazi ( slučaj ažuriranja )

                    rezultati = JsonAdapter.getRezultat(result);
                    for (Rezultat r : rezultati) {

                        duplikat = new Select().from(Rezultat.class).where("IDrezultat==?", r.getIDrezultat()).count();
                        if (duplikat == 0 && r.getObrisano()==0) {
                            r.save();
                            rezultatLoaded = true;
                            bindTables();
                        } else {
                            if (r.getObrisano() == 1) {
                                new Delete().from(Rezultat.class).where("IDrezultat=?", r.getIDrezultat()).execute();
                                Log.i("Obrisano: ", Long.toString(r.getBodovi()));
                            } else {
                                new Update(Rezultat.class).set("IDkorisnik=?", r.getIDkorisnik()).where("IDrezultat=?", r.getIDrezultat()).execute();
                                new Update(Rezultat.class).set("IDrazred=?", r.getIDrazred()).where("IDrezultat=?", r.getIDrezultat()).execute();
                                new Update(Rezultat.class).set("bodovi=?", r.getBodovi()).where("IDrezultat=?", r.getIDrezultat()).execute();
                                new Update(Rezultat.class).set("datum=?", r.getDatum()).where("IDrezultat=?", r.getIDrezultat()).execute();
                            }

                            Log.i("Uspjelo?:", Long.toString(r.getBodovi()));
                        }
                        Log.i("test", Long.toString(r.getBodovi()));
                    }
                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("rezultat", timestamp);
                } catch (Exception e) {
                    Toast.makeText(activity, R.string.data_error_rezultat, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    WebServiceResultHandler getAllRazredHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {
            razredi = JsonAdapter.getRazred(result);
            if (ok) {
                try {

                    //treba napraviti provjeru dali postoji već taj zapis u bazi ( slučaj ažuriranja )

                    for (Razred raz : razredi) {

                        duplikat = new Select().from(Razred.class).where("IDrazred==?", raz.getIDrazred()).count();
                        if (duplikat == 0 && raz.getObrisano()==0) {
                            raz.save();
                            razredLoaded = true;
                            bindTables();
                        } else {
                            if (raz.getObrisano() == 1) {
                                new Delete().from(Razred.class).where("IDrazred=?", raz.getIDrazred()).execute();
                                Log.i("Obrisano: ", raz.getNaziv());
                            } else {
                                new Update(Razred.class).set("naziv=?", raz.getNaziv()).where("IDrazred=?", raz.getIDrazred()).execute();
                            }
                            Log.i("Uspjelo?:", raz.getNaziv());
                        }

                        Log.i("test", raz.getNaziv());
                    }


                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("razred", timestamp);

                    razredLoaded = true;

                } catch (Exception e) {
                    Toast.makeText(activity, R.string.data_error_razred, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    WebServiceResultHandler getAllPitanjaHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {
            pitanjas = JsonAdapter.getPitanja(result);
            if (ok) {
                try {
                    //treba napraviti provjeru dali postoji već taj zapis u bazi ( slučaj ažuriranja )

                    for (Pitanja p : pitanjas) {

                        duplikat = new Select().from(Pitanja.class).where("IDpitanja==?", p.getIDpitanja()).count();
                        if (duplikat == 0 && p.getObrisano()==0) {
                            p.save();
                            pitanjaLoaded = true;
                            bindTables();
                        } else {
                            if (p.getObrisano() == 1) {
                                new Delete().from(Pitanja.class).where("IDpitanja=?", p.getIDpitanja()).execute();
                                Log.i("Obrisano: ", p.getPitanje());
                            } else {
                                new Update(Pitanja.class).set("pitanje=?", p.getPitanje()).where("IDpitanja=?", p.getIDpitanja()).execute();
                                new Update(Pitanja.class).set("IDpoglavlje=?", p.getIDpoglavlje()).where("IDpitanja=?", p.getIDpitanja()).execute();
                                new Update(Pitanja.class).set("IDrazred=?", p.getIDrazred()).where("IDpitanja=?", p.getIDpitanja()).execute();
                            }
                            Log.i("Uspjelo?:", p.getPitanje());
                        }
                        Log.i("test", p.getPitanje());
                    }
                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("pitanja", timestamp);

                } catch (Exception e) {
                    Toast.makeText(activity, R.string.data_error_pitanja, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    WebServiceResultHandler getAllPoglavljeHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {
            poglavlja = JsonAdapter.getPoglavlje(result);
            if (ok) {
                try {
                    //treba napraviti provjeru dali postoji već taj zapis u bazi ( slučaj ažuriranja )
                    for (Poglavlje po : poglavlja) {
                        duplikat = new Select().from(Poglavlje.class).where("IDpoglavlje==?", po.getIDpoglavlje()).count();
                        if (duplikat == 0 && po.getObrisano()==0) {
                            po.save();
                            poglavljaLoaded = true;
                            bindTables();
                        } else {
                            if (po.getObrisano() == 1) {
                                new Delete().from(Poglavlje.class).where("IDpoglavlje=?", po.getIDpoglavlje()).execute();
                                Log.i("Obrisano: ", po.getNaziv());
                            } else {
                                new Update(Poglavlje.class).set("naziv=?", po.getNaziv()).where("IDpoglavlje=?", po.getIDpoglavlje()).execute();
                                new Update(Poglavlje.class).set("ukljuceno=?", po.getUkljuceno()).where("IDpoglavlje=?", po.getIDpoglavlje()).execute();
                            }
                            Log.i("Uspjelo?:", po.getNaziv());
                        }
                        Log.i("test", po.getNaziv());
                    }
                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("poglavlje", timestamp);

                } catch (Exception e) {
                    Toast.makeText(activity, R.string.data_error_poglavlja, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    WebServiceResultHandler getAllOdgovorHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {
            odgovori = JsonAdapter.getOdgovor(result);
            if (ok) {
                try {
                    //treba napraviti provjeru dali postoji već taj zapis u bazi ( slučaj ažuriranja )

                    for (Odgovor o : odgovori) {
                        duplikat = new Select().from(Odgovor.class).where("IDodgovor==?", o.getIDodgovor()).count();
                        if (duplikat == 0 && o.getObrisano()==0) {
                            o.save();
                            odgovoriLoaded = true;
                            bindTables();
                        } else {
                            if (o.getObrisano() == 1) {
                                new Delete().from(Odgovor.class).where("IDodgovor=?", o.getIDodgovor()).execute();
                                Log.i("Obrisano: ", o.getNaziv());
                            } else {
                                new Update(Odgovor.class).set("naziv=?", o.getNaziv()).where("IDodgovor=?", o.getIDodgovor()).execute();
                                new Update(Odgovor.class).set("tocan=?", o.getTocan()).where("IDodgovor=?", o.getIDodgovor()).execute();
                                new Update(Odgovor.class).set("IDpitanja=?", o.getIDpitanja()).where("IDodgovor=?", o.getIDodgovor()).execute();
                            }
                            Log.i("Uspjelo?:", o.getNaziv());
                        }
                        Log.i("test", o.getNaziv());
                    }
                    //postaviti vrijeme u xml dohvaćeno putem varijable timestamp
                    setXmalAzuriranjeJsonTime("odgovor", timestamp);
                } catch (Exception e) {
                    Toast.makeText(activity, R.string.data_error_odgovor, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private void bindTables() {

        if (tip_korisnikaLoaded && korisnikLoaded) {
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

        if (razredLoaded && rezultatLoaded && pitanjaLoaded) {
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

        if (pitanjaLoaded && odgovoriLoaded) {
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


    public String getXmlAzuriranjeJsonTime(String atribut, Class tablica) {
        brojRedova = new Select().from(tablica).count();
        Log.i("Broj redova:", String.valueOf(brojRedova));
        SharedPreferences baza = activity.getSharedPreferences("azuriranje", activity.MODE_PRIVATE);
        if (brojRedova != 0 && baza.contains(atribut)) {

            //dohvati vrijeme iz xml fajla
            json = Long.toString(baza.getLong(atribut, 0));
            Log.i("Json vrijeme iz xml-a :", json);

        } else {
            //proslijedi vrijednost 0
            json = "0";
            Log.i("Json vrijeme 0 :", json);
        }

        return json;
    }

    public void setXmalAzuriranjeJsonTime(String atribut, Long timestamp) {
        SharedPreferences baza = activity.getSharedPreferences("azuriranje", activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = baza.edit();
        edit.putLong(atribut, timestamp);
        edit.commit();
    }


}
