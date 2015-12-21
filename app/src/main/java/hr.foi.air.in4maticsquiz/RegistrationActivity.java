package hr.foi.air.in4maticsquiz;

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

/**
 * Created by Matija Popijač on 15.11.2015..
 */
public class RegistrationActivity extends AsyncTask<String, String, String> {

        private Context context;
        private String ime, prezime, korisnickoIme, email, lozinka, link, data;


        public RegistrationActivity(Context context, String dfs, String dfsfsd){
            this.context=context;
        }

        protected void onPreExecute(){

        }

        @Override
        protected String doInBackground(String... arg0){

            try{
                ime = (String)arg0[0];
                prezime = (String)arg0[1];
                korisnickoIme = (String)arg0[2];
                email = (String)arg0[3];
                lozinka = (String)arg0[4];


                link="http://www.in4maticsquiz.16mb.com/post_registracija.php";
                data  = URLEncoder.encode("ime", "UTF-8") + "=" + URLEncoder.encode(ime, "UTF-8");
                data += "&" + URLEncoder.encode("prezime", "UTF-8") + "=" + URLEncoder.encode(prezime, "UTF-8");
                data += "&" + URLEncoder.encode("korisnickoIme", "UTF-8") + "=" + URLEncoder.encode(korisnickoIme, "UTF-8");
                data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("lozinka", "UTF-8") + "=" + URLEncoder.encode(lozinka, "UTF-8");

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
                Toast toast = Toast.makeText(context, "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT);
                toast.show();
                PrijavljeniKorisnik.getInstance().setClicked(false);

            }else{
                PrijavljeniKorisnik.getInstance().setClicked(false);
                Intent intent = new Intent(context,MainActivity.class);
                context.startActivity(intent);
            }

        }

}
