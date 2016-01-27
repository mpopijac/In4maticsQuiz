package hr.foi.air.in4maticsquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import hr.foi.air.in4maticsquiz.core.DataLoader;
import hr.foi.air.in4maticsquiz.loaders.WebServiceDataLoader;
import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;

/*
    Aktivnost odabira akcija za odabrani razred
    akcije: pregled rang liste, provjere znanja
 */

public class MenuActivity extends AppCompatActivity implements  View.OnClickListener {

    private Toolbar mToolbar;
    private Button provjeri, lista, poglavlja, pitanja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        provjeri = (Button)findViewById(R.id.btnProvjeri);
        provjeri.setOnClickListener(this);

        lista = (Button)findViewById(R.id.btnLista);
        lista.setOnClickListener(this);

        poglavlja = (Button) findViewById(R.id.poglavlja);
        pitanja = (Button) findViewById(R.id.pitanja);

        //za tipa korisnika ucitelj prikaži gumbove za ažuriranje
        if(PrijavljeniKorisnik.getInstance().getIDtip()==1) {

            poglavlja.setVisibility(View.VISIBLE);
            poglavlja.setOnClickListener(this);

            pitanja.setVisibility(View.VISIBLE);
            pitanja.setOnClickListener(this);

        }

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnProvjeri:
                intent= new Intent(MenuActivity.this, ProvjeriZnanjeActivity.class);
                startActivity(intent);
                break;

            case R.id.btnLista:
                intent=new Intent(MenuActivity.this, RangListeActivity.class);
                startActivity(intent);
                break;
            case R.id.poglavlja:
                intent = new Intent(MenuActivity.this, PoglavljaActivity.class);
                startActivity(intent);
                break;

            case R.id.pitanja:
                intent = new Intent(MenuActivity.this, PitanjaActivity.class);
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
                SharedPreferences korisnickiPodaci = MenuActivity.this.getSharedPreferences("korisnickiPodaci", MenuActivity.this.MODE_PRIVATE);
                SharedPreferences.Editor edit = korisnickiPodaci.edit();
                edit.clear();
                edit.commit();
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
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
    public void onBackPressed(){
        Intent intent = new Intent(MenuActivity.this, OdabirRazredaActivity.class);
        startActivity(intent);

    }

}