package hr.foi.air.in4maticsquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.activeandroid.ActiveAndroid;

import hr.foi.air.in4maticsquiz.AsyncTaskClass.UserSignIn;
import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText korisnickoIme, lozinka;
    private Button bLogin;
    private TextView bRegistriraj;
    private CheckBox zapamti;
    private String korisnickoImeSt, lozinkaSt;
    private String z;
    private boolean clicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActiveAndroid.initialize(this);
        setContentView(R.layout.activity_main);

        korisnickoIme = (EditText)findViewById(R.id.korisnickoIme);
        lozinka = (EditText)findViewById(R.id.lozinka);
        zapamti = (CheckBox)findViewById(R.id.checkboxZapamti);

        bLogin = (Button)findViewById(R.id.btnPrijava);
        bRegistriraj = (TextView)findViewById(R.id.btnRegistriraj);

        bLogin.setOnClickListener(this);
        bRegistriraj.setOnClickListener(this);

        SharedPreferences korisnickiPodaci = this.getSharedPreferences("korisnickiPodaci", MODE_PRIVATE);

        if(korisnickiPodaci.getLong("IDkorisnik", 0)!=0){

            PrijavljeniKorisnik.getInstance().setIDkorisnik(korisnickiPodaci.getLong("IDkorisnik", 0));
            PrijavljeniKorisnik.getInstance().setIme(korisnickiPodaci.getString("ime", ""));
            PrijavljeniKorisnik.getInstance().setPrezime(korisnickiPodaci.getString("prezime", ""));
            PrijavljeniKorisnik.getInstance().setKorisnickoIme(korisnickiPodaci.getString("korisnickoIme", ""));
            PrijavljeniKorisnik.getInstance().setEmail(korisnickiPodaci.getString("email", ""));
            PrijavljeniKorisnik.getInstance().setIDtip(korisnickiPodaci.getLong("IDtip",0));

            Intent intent = new Intent(this,OdabirRazredaActivity.class);
            this.startActivity(intent);
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){

            case R.id.btnPrijava:

                if(clicked==false || PrijavljeniKorisnik.getInstance().isClicked()==false) {

                    korisnickoImeSt = korisnickoIme.getText().toString();
                    lozinkaSt = lozinka.getText().toString();
                    z = String.valueOf(zapamti.isChecked());

                    new UserSignIn(this, clicked, z).execute(korisnickoImeSt, lozinkaSt, z);

                    if (zapamti.isChecked() == true) {

                        SharedPreferences korisnickiPodaci = this.getSharedPreferences("korisnickiPodaci", MODE_PRIVATE);
                        SharedPreferences.Editor edit = korisnickiPodaci.edit();
                        edit.clear();
                        edit.putLong("IDkorisnik", PrijavljeniKorisnik.getInstance().getIDkorisnik());
                        edit.putString("ime", PrijavljeniKorisnik.getInstance().getIme());
                        edit.putString("prezime", PrijavljeniKorisnik.getInstance().getPrezime());
                        edit.putString("korisnickoIme", PrijavljeniKorisnik.getInstance().getKorisnickoIme());
                        edit.putString("email", PrijavljeniKorisnik.getInstance().getEmail());
                        edit.putLong("IDtip", PrijavljeniKorisnik.getInstance().getIDtip());
                        edit.commit();

                    }
                    clicked=true;
                    PrijavljeniKorisnik.getInstance().setClicked(true);
                }

                break;


            case R.id.btnRegistriraj:

                Intent intentR = new Intent(MainActivity.this,RegistracijaActivity.class);
                startActivity(intentR);

                break;
        }
    }

}
