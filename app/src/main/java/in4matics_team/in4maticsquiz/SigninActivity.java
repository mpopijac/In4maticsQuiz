package in4matics_team.in4maticsquiz;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Matija Popijač on 10.11.2015..
 */
public class SigninActivity extends AsyncTask<String, String, String> {

    private Context context;
    private EditText s;




    public SigninActivity(Context context, EditText lozinka, String prava){
        this.context=context;
        this.s=lozinka;

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
            CharSequence text = "Pogrešno uneseni podaci. Pokušajte ponovo.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

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


                Intent intent = new Intent(context,odabirRazredaActivity.class);
                context.startActivity(intent);

            }catch (Exception e){
                CharSequence text = "Došlo je do greške. Pokušajte ponovo.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }


        }

    }


}
