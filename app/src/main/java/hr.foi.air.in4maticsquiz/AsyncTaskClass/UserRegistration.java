package hr.foi.air.in4maticsquiz.AsyncTaskClass;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import hr.foi.air.in4maticsquiz.MainActivity;
import hr.foi.air.in4maticsquiz.R;
import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;

/**
 * Created by Matija Popijač on 15.11.2015..
 *
 * klasa za registraciju korisnika, slanjem zahtijeva na web servis
 * u pozivu klase/metode , posljednje dvije varijable se ne koriste ( dfs i dfsfsd)
 */
public class UserRegistration extends AsyncTask<String, String, String> {

    private Context context;
    private String ime, prezime, korisnickoIme, email, lozinka, link, data;


    public UserRegistration(Context context, String dfs, String dfsfsd) {
        this.context = context;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {

        try {
                /*
                    dohvaćanje argumenata poziva funkcije
                */
            ime = (String) arg0[0];
            prezime = (String) arg0[1];
            korisnickoIme = (String) arg0[2];
            email = (String) arg0[3];
            lozinka = (String) arg0[4];

                /*
                    Slanje zahtjeva na web servis putem POST metode
                */
            link = "http://www.in4maticsquiz.16mb.com/post_registracija.php";
            data = URLEncoder.encode("ime", "UTF-8") + "=" + URLEncoder.encode(ime, "UTF-8");
            data += "&" + URLEncoder.encode("prezime", "UTF-8") + "=" + URLEncoder.encode(prezime, "UTF-8");
            data += "&" + URLEncoder.encode("korisnickoIme", "UTF-8") + "=" + URLEncoder.encode(korisnickoIme, "UTF-8");
            data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
            data += "&" + URLEncoder.encode("lozinka", "UTF-8") + "=" + URLEncoder.encode(lozinka, "UTF-8");


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
            wr.write(data);

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
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }
            return sb.toString();
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }


    /*
        metoda koja se poziva nakon pozadinskog izvršavanja(doInBackground) metode koja ovoj metodi proslijeđuje
        vrijednost koju vraća web servis
    */
    @Override
    protected void onPostExecute(String result) {
        /*
            ako vraća 0 tada je došlo do greške prilikom registracije
         */
        if (result.equals("0")) {
            Toast toast = Toast.makeText(context, "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT);
            toast.show();
            /*
                postavljanjem setClicked na false omogućavamo ponovno slanje zahtijeva za registraciju
             */
            PrijavljeniKorisnik.getInstance().setClicked(false);

        } else {
            Toast.makeText(context, context.getString(R.string.registracijaUspjesna), Toast.LENGTH_LONG).show();
            /*
                postavljanjem setClicked na false omogućavamo ponovno slanje zahtijeva za registraciju
                te se otvara nova aktivnost Prijava
            */
            PrijavljeniKorisnik.getInstance().setClicked(false);
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }

    }

}
