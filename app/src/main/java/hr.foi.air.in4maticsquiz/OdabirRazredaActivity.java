package hr.foi.air.in4maticsquiz;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hr.foi.air.in4maticsquiz.core.DataLoader;
import hr.foi.air.in4maticsquiz.core.OnDataLoadedListener;
import hr.foi.air.in4maticsquiz.loaders.WebServiceDataLoader;
import hr.foi.air.in4maticsquiz.db.Korisnik;
import hr.foi.air.in4maticsquiz.db.Odgovor;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.db.Poglavlje;
import hr.foi.air.in4maticsquiz.db.Razred;
import hr.foi.air.in4maticsquiz.db.Rezultat;
import hr.foi.air.in4maticsquiz.db.Tip_korisnika;
import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;

public class OdabirRazredaActivity extends AppCompatActivity implements  View.OnClickListener, OnDataLoadedListener {


    private Toolbar mToolbar;
    private Button peti, sesti, sedmi, osmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabir_razreda);

        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        // poruka sa imenom i prezimenom
        CharSequence text;
        text = PrijavljeniKorisnik.getInstance().getIme() +" "+ PrijavljeniKorisnik.getInstance().getPrezime();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text + getString(R.string.odabirRazredaUspjesnaPrijava), duration);
        toast.show();


        DataLoader dataLoader = new WebServiceDataLoader();
        dataLoader.LoadData(this);


        String struser = PrijavljeniKorisnik.getInstance().getKorisnickoIme().toString();
        TextView txtuser = (TextView)findViewById(R.id.imePrijavljenog);
        txtuser.setText(getString(R.string.odabirRazredaPrijava) + " " + struser);


        peti = (Button)findViewById(R.id.peti_razred);
        peti.setOnClickListener(this);

        sesti = (Button)findViewById(R.id.sesti_razred);
        sesti.setOnClickListener(this);

        sedmi = (Button)findViewById(R.id.sedmi_razred);
        sedmi.setOnClickListener(this);

        osmi = (Button)findViewById(R.id.osmi_razred);
        osmi.setOnClickListener(this);

    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {

            case R.id.peti_razred:
                PrijavljeniKorisnik.getInstance().setOdabraniRazred(5);
                intent = new Intent(OdabirRazredaActivity.this, MenuActivity.class);
                startActivity(intent);
                break;

            case R.id.sesti_razred:
                PrijavljeniKorisnik.getInstance().setOdabraniRazred(6);
                intent = new Intent(OdabirRazredaActivity.this,MenuActivity.class);
                startActivity(intent);
                break;

            case R.id.sedmi_razred:
                PrijavljeniKorisnik.getInstance().setOdabraniRazred(7);
                intent = new Intent(OdabirRazredaActivity.this,MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.osmi_razred:
                PrijavljeniKorisnik.getInstance().setOdabraniRazred(8);
                intent = new Intent(OdabirRazredaActivity.this,MenuActivity.class);
                startActivity(intent);
                break;
        }
    }



    @Override

    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem actionViewItem = menu.findItem(R.id.miActionButton);
        View v = MenuItemCompat.getActionView(actionViewItem);
        ImageButton b = (ImageButton) v.findViewById(R.id.btnCustomAction);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences korisnickiPodaci = OdabirRazredaActivity.this.getSharedPreferences("korisnickiPodaci", OdabirRazredaActivity.this.MODE_PRIVATE);
                SharedPreferences.Editor edit = korisnickiPodaci.edit();
                edit.clear();
                edit.commit();
                Intent intent = new Intent(OdabirRazredaActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
        return super.onPrepareOptionsMenu(menu);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onDataLoaded(ArrayList<Tip_korisnika> tip_korisnikas, ArrayList<Korisnik> korisnici, ArrayList<Rezultat> rezultati, ArrayList<Razred> razredi, ArrayList<Pitanja> pitanjas, ArrayList<Poglavlje> poglavlja, ArrayList<Odgovor> odgovori) {

    }
}
