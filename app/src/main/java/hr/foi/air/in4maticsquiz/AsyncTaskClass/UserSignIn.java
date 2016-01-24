package hr.foi.air.in4maticsquiz.AsyncTaskClass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import hr.foi.air.in4maticsquiz.OdabirRazredaActivity;
import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;

/*
 * Created by Matija Popijač on 10.11.2015..
 *
  * Funkcija za prijavu korisnika u aplikaciju
  * u slučaju uspješne prijave prebacuje se na aktivnost odabira razreda
  *
 */
public class UserSignIn extends AsyncTask<String, String, String> {

    private Context context;
    private boolean clicked;
    private String z;

    public UserSignIn(Context context, boolean clicked, String zapamti){
        this.context=context;
        this.clicked=clicked;
        this.z=zapamti;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0){
        try{

            /*
                dohvaćanje argumenata poziva funkcije
             */
            String username = (String)arg0[0];
            String password = (String)arg0[1];

            /*
                Slanje zahtjeva na web servis putem POST metode
             */
            String link="http://www.in4maticsquiz.16mb.com/post_prijava.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            /*
                stvaranje istance URL sa navedenim linkom te otvaranje veze prema web servisu
             */
            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            /*
                otvaranje Stream-a za slanje podataka na web servis
             */
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            /*
                slanje podataka
             */
            wr.write( data );
            /*
                čiščenje, pražnjenje veze prema web servisu
             */
            wr.flush();

            /*
                Definiranje buffera za spremanje vračenih podataka sa web servisa
                Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
             */
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            /*
                Constructs a string builder with no characters in it and an initial capacity of 16 characters.
             */
            StringBuilder sb = new StringBuilder();
            String line = null;

            /*
             Read Server Response
             */
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            return sb.toString();
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    /*
        metoda koja se poziva nakon pozadinskog izvršavanja(doInBackground) metode koja ovoj metodi proslijeđuje
        vrijednost koju vraća web servis
     */
    @Override
    protected void onPostExecute(String result){

        /*
            ako vraća 0 tada ne postoji korisnik sa navedenim podacima
            inače postoji korisnik sa navedenim podacima
         */
        if(result.equals("0")){

            /*
                prikaz poruke na ekranu o grešci
             */
            Toast toast = Toast.makeText(context, "Pogrešno uneseni podaci. Pokušajte ponovo.", Toast.LENGTH_SHORT);
            toast.show();
            /*
                postavljanje setClicked varijable na false kako bi korisnik ponovo mogao poslati zahtjev na web servis, kliknuti gumb Prijava
             */
            PrijavljeniKorisnik.getInstance().setClicked(false);

        }else{

            try {
                /*
                    dekodiranje (JsonDecoding) vračenih podataka od web servisa te spremanje u singleton PrijavljeniKorisnik
                 */
                JSONArray jsonArray = new JSONArray(result);

                for (int i=0; i<jsonArray.length();i++){

                    JSONObject json_data = jsonArray.getJSONObject(i);
                    PrijavljeniKorisnik.getInstance().setIDkorisnik(json_data.getLong("IDkorisnik"));
                    PrijavljeniKorisnik.getInstance().setIme(json_data.getString("ime"));
                    PrijavljeniKorisnik.getInstance().setPrezime(json_data.getString("prezime"));
                    PrijavljeniKorisnik.getInstance().setKorisnickoIme(json_data.getString("korisnickoIme"));
                    PrijavljeniKorisnik.getInstance().setEmail(json_data.getString("email"));
                    PrijavljeniKorisnik.getInstance().setIDtip(json_data.getLong("IDtip"));

                }
                /*
                    ako je označeno zapamti prijavu tada se podaci dobiveni podaci spremaju u korisnickiPodaci.xml
                 */
                if(z.equals("true")){

                    SharedPreferences korisnickiPodaci = context.getSharedPreferences("korisnickiPodaci", context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = korisnickiPodaci.edit();
                    edit.clear();
                    edit.putLong("IDkorisnik", PrijavljeniKorisnik.getInstance().getIDkorisnik());
                    edit.putString("ime", PrijavljeniKorisnik.getInstance().getIme());
                    edit.putString("prezime",PrijavljeniKorisnik.getInstance().getPrezime());
                    edit.putString("korisnickoIme", PrijavljeniKorisnik.getInstance().getKorisnickoIme());
                    edit.putString("email", PrijavljeniKorisnik.getInstance().getEmail());
                    edit.putLong("IDtip", PrijavljeniKorisnik.getInstance().getIDtip());
                    edit.commit();

                }
                 /*
                postavljanje setClicked varijable na false kako bi korisnik ponovo mogao poslati zahtjev na web servis, kliknuti gumb Prijava
                te prebacivanje na aktivnost odaberi razred
                */
                PrijavljeniKorisnik.getInstance().setClicked(false);
                Intent intent = new Intent(context,OdabirRazredaActivity.class);
                context.startActivity(intent);

            }catch (Exception e){
                /*
                    U sluačju da je došlo do greške u json parsiranju(jsonDecoding-u) ispisuje se poruka da je došlo do greške
                 */
                Toast toast = Toast.makeText(context, "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT);
                toast.show();
                /*
                    postavljanje setClicked varijable na false kako bi korisnik ponovo mogao poslati zahtjev na web servis, kliknuti gumb Prijava
                */
                PrijavljeniKorisnik.getInstance().setClicked(false);
            }


        }

    }


}
