package in4matics_team.in4maticsquiz;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.CountDownTimer;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import in4matics_team.in4maticsquiz.fragments.TocnoNetocno_fragment;
import in4matics_team.in4maticsquiz.fragments.UnesiTocanPojam_fragment;
import in4matics_team.in4maticsquiz.fragments.VisePonudenihOdgovora_fragment;
import in4matics_team_local.db.Odgovor;
import in4matics_team_local.db.Pitanja;

public class provjeriZnanje extends AppCompatActivity implements View.OnClickListener {

    private Button btnSljedece;
    private List<Pitanja> pitanja=new ArrayList<Pitanja>();
    private List<Odgovor> odgovoriTrenutno=new ArrayList<Odgovor>();
    private Pitanja trenutno;
    private int brojPitanja=10,idPit,zadnjePitanje=0;
    TextView prikazTimer;
    private static final String FORMAT = "%02d:%02d";



    private Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provjeri_znanje);
        Integer odabraniRazred = PrijavljeniKorisnik.getInstance().getOdabraniRazred();

        pitanja=new Select().from(Pitanja.class).where("IDrazred==?", odabraniRazred).orderBy("RANDOM()").limit(brojPitanja).execute();
        idPit=new Random().nextInt(pitanja.size());
        trenutno=pitanja.get(idPit);
        vrstaPitanja(trenutno);
        zadnjePitanje++;


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        btnSljedece=(Button)findViewById(R.id.btnSljedeceP);
        btnSljedece.setOnClickListener(this);


        prikazTimer=(TextView)findViewById(R.id.vrijemeTimer);

        new CountDownTimer(600000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                prikazTimer.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                prikazTimer.setText("done!");
            }
        }.start();


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

    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem actionViewItem = menu.findItem(R.id.miActionButton);
        View v = MenuItemCompat.getActionView(actionViewItem);
        ImageButton b = (ImageButton) v.findViewById(R.id.btnCustomAction);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences korisnickiPodaci = provjeriZnanje.this.getSharedPreferences("korisnickiPodaci", provjeriZnanje.this.MODE_PRIVATE);
                SharedPreferences.Editor edit = korisnickiPodaci.edit();
                edit.clear();
                edit.commit();
                Intent intent = new Intent(provjeriZnanje.this, MainActivity.class);
                startActivity(intent);


            }
        });
        return super.onPrepareOptionsMenu(menu);

    }



    public void prikaziFragment(Fragment f){


        Bundle bundle = new Bundle();
        long id=trenutno.getIDpitanja();

        bundle.putLong("pitanje_key", id);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        f.setArguments(bundle);

        ft.replace(R.id.your_placeholder, f);

        ft.commit();


    }
    public void vrstaPitanja(Pitanja p){

        odgovoriTrenutno=new Select().all().from(Odgovor.class).where("IDpitanja==?", p.getIDpitanja()).execute();
        if(odgovoriTrenutno.size()==2) prikaziFragment(new TocnoNetocno_fragment());
        else if(odgovoriTrenutno.size()>2) prikaziFragment(new VisePonudenihOdgovora_fragment());
        else if (odgovoriTrenutno.size()==1) prikaziFragment(new UnesiTocanPojam_fragment());

    }
    @Override
    public void onClick(View v) {
        if(pitanja.size()>0) {
            idPit = new Random().nextInt(pitanja.size());
            trenutno = pitanja.get(idPit);
            vrstaPitanja(trenutno);
            if(zadnjePitanje==(brojPitanja-1)){
                btnSljedece.setText("Završi test");
            }
            zadnjePitanje++;
            pitanja.remove(idPit);
        }
        else {

            new AlertDialog.Builder(provjeriZnanje.this)
                    .setTitle("Kviz je završio")
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           // Intent intent = new Intent(provjeriZnanje.this, menuActivity.class);
                           // startActivity(intent);
                            provjeriZnanje.this.finish();
                        }
                    }).create().show();
        }
    }


}
