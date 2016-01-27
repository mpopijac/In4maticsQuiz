package hr.foi.air.in4maticsquiz.AsyncTaskClass;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hr.foi.air.in4maticsquiz.PoglavljaActivity;
import hr.foi.air.in4maticsquiz.core.DataLoader;
import hr.foi.air.in4maticsquiz.db.Poglavlje;
import hr.foi.air.in4maticsquiz.loaders.WebServiceDataLoader;

/**
 * Created by Matija Popijač on 27.1.2016..
 */
public class AddUpdateDeletePoglavlja extends AsyncTask<String,String, String> {

    private Context context;

    private String link, data, nazivPoglavlja, ukljuceno, operacija, idPoglavlja,obrisano;

    public AddUpdateDeletePoglavlja(Context context, Integer operacija, String obrisano){
        this.context=context;
        this.operacija=operacija.toString();
        this.obrisano = obrisano;
    }

    protected void onPreExecute(){

    }


    @Override
    protected String doInBackground(String... arg0) {
        try{
            //dohvačanje argumenata poziva funkcije
            idPoglavlja=(String)arg0[0];
            nazivPoglavlja=(String)arg0[1];
            ukljuceno=(String)arg0[2];

            Log.i("vrijednosti:",idPoglavlja+" " + operacija + " "+nazivPoglavlja+" "+ukljuceno);

            //dodajvanje
            if(Integer.parseInt(operacija)==0){
                //slanje zahtjeva na web servis
                link="http://www.in4maticsquiz.16mb.com/AddUpdateDeletePoglavlja.php";
                data  = URLEncoder.encode("idPoglavlje", "UTF-8") + "=" + URLEncoder.encode(idPoglavlja, "UTF-8");
                data += "&" + URLEncoder.encode("operacija", "UTF-8") + "=" + URLEncoder.encode(operacija, "UTF-8");
                data += "&" + URLEncoder.encode("nazivPoglavlja","UTF-8") + "=" + URLEncoder.encode(nazivPoglavlja, "UTF-8");
                data += "&" + URLEncoder.encode("ukljuceno","UTF-8") + "=" + URLEncoder.encode(ukljuceno, "UTF-8");
            }

            //azuriranje
            if(Integer.parseInt(operacija)==1){
                //slanje zahtjeva na web servis
                link="http://www.in4maticsquiz.16mb.com/AddUpdateDeletePoglavlja.php";
                data  = URLEncoder.encode("idPoglavlje", "UTF-8") + "=" + URLEncoder.encode(idPoglavlja, "UTF-8");
                data += "&" + URLEncoder.encode("operacija", "UTF-8") + "=" + URLEncoder.encode(operacija, "UTF-8");
                data += "&" + URLEncoder.encode("nazivPoglavlja","UTF-8") + "=" + URLEncoder.encode(nazivPoglavlja, "UTF-8");
                data += "&" + URLEncoder.encode("ukljuceno","UTF-8") + "=" + URLEncoder.encode(ukljuceno, "UTF-8");
                data += "&" + URLEncoder.encode("obrisano","UTF-8") + "=" + URLEncoder.encode(obrisano, "UTF-8");
            }


            //instanca URL-a i otvaranje veze prema webserveru
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


        }catch (Exception e){
            return new String("Exception: " + e.getMessage());
        }


    }


    /*
        metoda koja se poziva nakon pozadinskog izvršavanja(doInBackground) metode koja ovoj metodi proslijeđuje
        vrijednost koju vraća web servis

        TREBA DOVRŠITI
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
            Log.i("Uspješno: idPog ", result);

            //pokreni sinkronizaciju tamo gdje se i poziva klasa

        }
   }


}
