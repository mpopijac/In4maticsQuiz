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

/**
 * Created by Matija Popijač on 10.11.2015..
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
            String username = (String)arg0[0];
            String password = (String)arg0[1];

            String link="http://www.in4maticsquiz.16mb.com/post_prijava.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
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

    @Override
    protected void onPostExecute(String result){

        if(result.equals("0")){

            Toast toast = Toast.makeText(context, "Pogrešno uneseni podaci. Pokušajte ponovo.", Toast.LENGTH_SHORT);
            toast.show();
            PrijavljeniKorisnik.getInstance().setClicked(false);

        }else{

            try {

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
                PrijavljeniKorisnik.getInstance().setClicked(false);
                Intent intent = new Intent(context,OdabirRazredaActivity.class);
                context.startActivity(intent);

            }catch (Exception e){
                CharSequence text = "Došlo je do greške. Pokušajte ponovo.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                PrijavljeniKorisnik.getInstance().setClicked(false);
            }


        }

    }


}
