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

/*
    Aktivnost Prijava -> prvi ekran
 */

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
        /*
        *dohvaćanje podataka spremljenih u korisnickiPodaci.xml
        *u privatnom modu, što znači da su dostupni sam ovoj aplikaciji
        */
        SharedPreferences korisnickiPodaci = this.getSharedPreferences("korisnickiPodaci", MODE_PRIVATE);

        /*
        *dohvati ID od korisnika, ako ne postoji vrati nulu
        *provjerava dali su zapamčeni podaci za prijavu u aplikaciju, te ako jesu nije potrebno ponovo vršiti prijavu
        */
        if(korisnickiPodaci.getLong("IDkorisnik", 0)!=0){
            /*
            *ako su podaci zapamčeni, dohvati zapamčene podatke i spremi ih u singleton PrijavljeniKorisnik kako bi smo ih mogli dohvatiti
            *bilo gdje u aplikaciji
            */
            PrijavljeniKorisnik.getInstance().setIDkorisnik(korisnickiPodaci.getLong("IDkorisnik", 0));
            PrijavljeniKorisnik.getInstance().setIme(korisnickiPodaci.getString("ime", ""));
            PrijavljeniKorisnik.getInstance().setPrezime(korisnickiPodaci.getString("prezime", ""));
            PrijavljeniKorisnik.getInstance().setKorisnickoIme(korisnickiPodaci.getString("korisnickoIme", ""));
            PrijavljeniKorisnik.getInstance().setEmail(korisnickiPodaci.getString("email", ""));
            PrijavljeniKorisnik.getInstance().setIDtip(korisnickiPodaci.getLong("IDtip",0));

            /*
            *nakon dohvaćanja podatka prelazi se na sljedeću aktivnost odabri razreda
            */
            Intent intent = new Intent(this,OdabirRazredaActivity.class);
            this.startActivity(intent);
        }
    }

    /*
     *metoda koja se pokreće prilikom klika na ekranu, te ovisno na što je kliknuto obavlja
     *operacije(prijave, ili prebacivanje na aktivnost registracije)
     * varijabla clicked - spriječava višestruko slanje zahtjeva prijave
    */
    @Override
    public void onClick(View v){
        switch (v.getId()){

            case R.id.btnPrijava:

                if(clicked==false || PrijavljeniKorisnik.getInstance().isClicked()==false) {

                    korisnickoImeSt = korisnickoIme.getText().toString();
                    lozinkaSt = lozinka.getText().toString();
                    z = String.valueOf(zapamti.isChecked());

                    /*
                        poziv AsyncTask klase za provjeru ispravnosti podataka za prijavu
                     */
                    new UserSignIn(this, clicked, z).execute(korisnickoImeSt, lozinkaSt, z);

                    if (zapamti.isChecked() == true) {

                        /*
                            ako je korisnik odabrao da mu zapamti podatke, spremaju se podaci u korisnickiPodaci.xml
                            kako bi prilikom ponovnog pokretanja aplikacije izbjegao prijavljivanje
                         */
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

                    /*
                        clicked je postavljeni na true kako korisnik ne bi mogao poslati više zahtjeva za prijavom od jednom,
                        nego tek kada se prethodni završi
                     */
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


    /*
       metoda koja se pokreće prilikom pritiska Back gumba.
    */
    @Override
    public void onBackPressed(){
        /*
            promjenjauje trenutnu aktivnost na home aktivnost uređaja
         */
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        /*
            super.finish(); zatvara aplikaciju;
         */
        super.finish();

    }

}
