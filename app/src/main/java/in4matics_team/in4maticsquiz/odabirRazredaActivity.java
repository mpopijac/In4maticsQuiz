package in4matics_team.in4maticsquiz;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in4matics_team.in4maticsquiz.R;
import in4matics_team.in4maticsquiz.fragments.rangListe;
import in4matics_team.in4maticsquiz.loaders.WebServiceDataLoader;
import in4matics_team_local.db.Tip_korisnika;

public class odabirRazredaActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener  {

    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager mFm;


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

        //displej provjereznanja

        in4matics_team.in4maticsquiz.fragments.provjeriZnanje dlf = new in4matics_team.in4maticsquiz.fragments.provjeriZnanje();
        FragmentTransaction fm = getFragmentManager().beginTransaction();
        fm.replace(R.id.fragment_container, dlf);
        fm.commit();

        //displej rangListe

        in4matics_team.in4maticsquiz.fragments.rangListe dlf2 = new in4matics_team.in4maticsquiz.fragments.rangListe();
        FragmentTransaction fm2 = getFragmentManager().beginTransaction();
        fm2.replace(R.id.fragment_container, dlf2);
        fm2.commit();

        NavigationManager nm = NavigationManager.getInstance();
        nm.setDependencies(this, mDrawer, (NavigationView) findViewById(R.id.nv_drawer));
        dlf.setPosition(0);
        nm.addItem(dlf);
        dlf2.setPosition(1);
        nm.addItem(dlf2);

        // poruka sa imenom i prezimenom
        CharSequence text;
        text = PrijavljeniKorisnik.getInstance().getIme() + PrijavljeniKorisnik.getInstance().getPrezime();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();


        //odjava

        TextView odjava;

        PrijavljeniKorisnik currentUser = PrijavljeniKorisnik.getInstance();
       String struser = currentUser.getKorisnickoIme().toString();
      TextView txtuser = (TextView)findViewById(R.id.imePrijavljenog);
       txtuser.setText("Prijavili ste se kao " + struser );

        odjava = (TextView)findViewById(R.id.odjava);

        //odjava.setOnClickListener(new View.OnClickListener() {
          //  public void onClick(View arg0) {
           //     PrijavljeniKorisnik.getInstance().setKorisnickoIme(null);
             //   finish();
           // }
        //});

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
                break;
            case R.id.action_settings:
                break;
        }

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


}
