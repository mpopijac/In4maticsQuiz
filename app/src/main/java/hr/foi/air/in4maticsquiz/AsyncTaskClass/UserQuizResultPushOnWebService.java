package hr.foi.air.in4maticsquiz.AsyncTaskClass;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import hr.foi.air.in4maticsquiz.loaders.WebServiceDataLoader;
import hr.foi.air.in4maticsquiz.db.Rezultat;

/**
 * Created by Anabel Li on 20.12.2015..
 */
public class UserQuizResultPushOnWebService extends AsyncTask<String, String, String> {

    private Context context;
    private String  link,  data, bodovi,IDkorisnik, IDrazred;
   // private int bodovi,IDkorisnik, IDrazred;


    public UserQuizResultPushOnWebService(Context context, String dfs, String dfsfsd){
        this.context=context;
    }



    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0){

        try{
             /*
                    dohvaćanje argumenata poziva funkcije
                */
            bodovi = (String)arg0[0];
            IDkorisnik = (String)arg0[1];
            IDrazred = (String)arg0[2];

             /*
                    Slanje zahtjeva na web servis putem POST metode
                */


            link="http://www.in4maticsquiz.16mb.com/post_rezultat.php";
            data  = URLEncoder.encode("bodovi", "UTF-8") + "=" + URLEncoder.encode(bodovi, "UTF-8");
            data += "&" + URLEncoder.encode("IDkorisnik", "UTF-8") + "=" + URLEncoder.encode(IDkorisnik, "UTF-8");
            data += "&" + URLEncoder.encode("IDrazred", "UTF-8") + "=" + URLEncoder.encode(IDrazred, "UTF-8");

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
            ako vraća 0 tada je došlo do greške prilikom registracije
         */
        if(result.equals("0")){
            Toast toast = Toast.makeText(context, "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT);
            toast.show();

        }else{
            Log.i("Uspješno:", result);
            //tu dodaj u lokalnu bazu -> pokrenuti sinkronizaciju

            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
            String dan = simpleDateFormat.format(date).toUpperCase();

            simpleDateFormat = new SimpleDateFormat("MM");
            String mjesec = simpleDateFormat.format(date).toUpperCase();

            simpleDateFormat = new SimpleDateFormat("yyyy");
            String godina = simpleDateFormat.format(date).toUpperCase();

            Rezultat rezultat = new Rezultat();
            rezultat.setIDrezultat(Long.valueOf(result).longValue());
            rezultat.setIDkorisnik(Long.valueOf(IDkorisnik).longValue());
            rezultat.setIDrazred(Long.valueOf(IDrazred).longValue());
            rezultat.setBodovi(Long.valueOf(bodovi).longValue());
            rezultat.setDatum(dan + "." + mjesec + "." + godina);
            rezultat.save();

        }
    }

}
