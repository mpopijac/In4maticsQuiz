package in4matics_team.in4maticsquiz;

import android.app.FragmentManager;
import android.content.Context;
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

import in4matics_team.in4maticsquiz.loaders.WebServiceDataLoader;
import in4matics_team_local.db.Korisnik;
import in4matics_team_local.db.Odgovor;
import in4matics_team_local.db.Pitanja;
import in4matics_team_local.db.Poglavlje;
import in4matics_team_local.db.Razred;
import in4matics_team_local.db.Rezultat;
import in4matics_team_local.db.Tip_korisnika;

public class odabirRazredaActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, View.OnClickListener, OnDataLoadedListener {

    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager mFm;
    private Button peti, sesti, sedmi, osmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabir_razreda);

        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = setupDrawerToggle();
        mDrawer.setDrawerListener(mDrawerToggle);
        mFm = getFragmentManager();
        mFm.addOnBackStackChangedListener(this);
        mToolbar.setNavigationOnClickListener(navigationClick);

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
                intent = new Intent(odabirRazredaActivity.this, menuActivity.class);
                startActivity(intent);
                break;

            case R.id.sesti_razred:
                PrijavljeniKorisnik.getInstance().setOdabraniRazred(6);
                intent = new Intent(odabirRazredaActivity.this,menuActivity.class);
                startActivity(intent);
                break;

            case R.id.sedmi_razred:
                PrijavljeniKorisnik.getInstance().setOdabraniRazred(7);
                intent = new Intent(odabirRazredaActivity.this,menuActivity.class);
                startActivity(intent);
                break;
            case R.id.osmi_razred:
                PrijavljeniKorisnik.getInstance().setOdabraniRazred(8);
                intent = new Intent(odabirRazredaActivity.this,menuActivity.class);
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
                SharedPreferences korisnickiPodaci = odabirRazredaActivity.this.getSharedPreferences("korisnickiPodaci", odabirRazredaActivity.this.MODE_PRIVATE);
                SharedPreferences.Editor edit = korisnickiPodaci.edit();
                edit.clear();
                edit.commit();
                Intent intent = new Intent(odabirRazredaActivity.this, MainActivity.class);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        //Switch on item id.
        int id = item.getItemId();
        switch (id) {
            case R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                break;
            case R.id.action_search:
                SearchDialog sd = new SearchDialog(this);
                sd.show();
                break;
            case R.id.action_settings:
                Intent intent = new Intent(this, AppPreferenceActivity.class);
                startActivity(intent);
                break;
        }
        Toast.makeText(this, "Menu item " + item.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        return true;

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() != 0)
            if (mDrawer.isDrawerOpen(GravityCompat.START))
                mDrawer.closeDrawer(GravityCompat.START);
            else
                getFragmentManager().popBackStack();
        else {
            if (mDrawer.isDrawerOpen(GravityCompat.START))
                mDrawer.closeDrawer(GravityCompat.START);
            else
                super.onBackPressed();
        }
    }
    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackStackChanged() {
        mDrawerToggle.setDrawerIndicatorEnabled(mFm.getBackStackEntryCount() == 0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(mFm.getBackStackEntryCount() > 0);
        mDrawerToggle.syncState();
    }

    View.OnClickListener navigationClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (getFragmentManager().getBackStackEntryCount() == 0) {
                mDrawer.openDrawer(GravityCompat.START);
            } else {
                onBackPressed();
            }
        }
    };


    @Override
    public void onDataLoaded(ArrayList<Tip_korisnika> tip_korisnikas, ArrayList<Korisnik> korisnici, ArrayList<Rezultat> rezultati, ArrayList<Razred> razredi, ArrayList<Pitanja> pitanjas, ArrayList<Poglavlje> poglavlja, ArrayList<Odgovor> odgovori) {

    }
}
