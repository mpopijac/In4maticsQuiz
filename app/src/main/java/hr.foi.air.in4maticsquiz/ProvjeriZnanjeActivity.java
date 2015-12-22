package hr.foi.air.in4maticsquiz;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import hr.foi.air.in4maticsquiz.AsyncTaskClass.UserQuizResultPushOnWebService;
import hr.foi.air.in4maticsquiz.core.IzracunBodovaKviza;
import hr.foi.air.in4maticsquiz.fragments.quiz.TocnoNetocnoFragment;
import hr.foi.air.in4maticsquiz.fragments.quiz.UnesiTocanPojamFragment;
import hr.foi.air.in4maticsquiz.fragments.quiz.VisePonudenihOdgovoraFragment;
import hr.foi.air.in4maticsquiz.db.Odgovor;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;

public class ProvjeriZnanjeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSljedece;
    private List<Pitanja> pitanja=new ArrayList<Pitanja>();
    private List<Odgovor> odgovoriTrenutno=new ArrayList<Odgovor>();
    private Pitanja trenutno;
    String vrijeme="";
    private int brojPitanja=10,idPit,zadnjePitanje=0,ukupnoBodova;;
    TextView prikazTimer;
    CountDownTimer mCountDownTimer;
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
        //maknuti trenutno pitanje da se ne bi ponavljalo.
        pitanja.remove(idPit);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        btnSljedece=(Button)findViewById(R.id.btnSljedeceP);
        btnSljedece.setOnClickListener(this);


        prikazTimer=(TextView)findViewById(R.id.vrijemeTimer);

        mCountDownTimer=new CountDownTimer(600000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                prikazTimer.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                vrijeme=(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                prikazTimer.setText("done!");
                gotovKviz();
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

    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem actionViewItem = menu.findItem(R.id.miActionButton);
        View v = MenuItemCompat.getActionView(actionViewItem);
        ImageButton b = (ImageButton) v.findViewById(R.id.btnCustomAction);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences korisnickiPodaci = ProvjeriZnanjeActivity.this.getSharedPreferences("korisnickiPodaci", ProvjeriZnanjeActivity.this.MODE_PRIVATE);
                SharedPreferences.Editor edit = korisnickiPodaci.edit();
                edit.clear();
                edit.commit();
                Intent intent = new Intent(ProvjeriZnanjeActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
        return super.onPrepareOptionsMenu(menu);

    }

    public void prikaziFragment(Fragment f){

        Bundle bundle = new Bundle();
        long id=trenutno.getIDpitanja();
        //prosljedivanje id pitanja fragmentu.
        bundle.putLong("pitanje_key", id);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        f.setArguments(bundle);

        ft.replace(R.id.your_placeholder, f);

        ft.commit();


    }
    public void vrstaPitanja(Pitanja p){

        odgovoriTrenutno=new Select().all().from(Odgovor.class).where("IDpitanja==?", p.getIDpitanja()).execute();

        if(odgovoriTrenutno.size()==2) prikaziFragment(new TocnoNetocnoFragment());
        else if(odgovoriTrenutno.size()>2) prikaziFragment(new VisePonudenihOdgovoraFragment());
        else if (odgovoriTrenutno.size()==1) prikaziFragment(new UnesiTocanPojamFragment());

    }
    @Override
    public void onClick(View v) {
        // vrijednost trenutnog fragmenta.
        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.your_placeholder);
        Bundle dat=new Bundle();
        dat = currentFragment.getArguments();
        //primljeni podaci od trenutnog fragmenta.
        boolean tocOdg = dat.getBoolean("tocnost");
        Log.i("Primljeno: ", Boolean.toString(tocOdg));
        if(tocOdg==true){
            ukupnoBodova++;
        }
        if (pitanja.size() > 0) {
            idPit = new Random().nextInt(pitanja.size());
            trenutno = pitanja.get(idPit);

            vrstaPitanja(trenutno);
            //promjena teksta buttona sljedece ukoliko je sljedece zadnje pitanje
            if (zadnjePitanje == (brojPitanja - 1)) {
                btnSljedece.setText("Završi test");
            }
            zadnjePitanje++;
            pitanja.remove(idPit);
        } else {
            mCountDownTimer.cancel();
            gotovKviz();
        }

    }

    private void gotovKviz(){

        IzracunBodovaKviza bod= new IzracunBodovaKviza(vrijeme, String.valueOf(ukupnoBodova));
        int bodovi2= bod.bodoviFormula();

        new UserQuizResultPushOnWebService(this, "", "").execute(String.valueOf(bodovi2), String.valueOf(PrijavljeniKorisnik.getInstance().getIDkorisnik()), String.valueOf(PrijavljeniKorisnik.getInstance().getOdabraniRazred()));


        new AlertDialog.Builder(ProvjeriZnanjeActivity.this)
                .setTitle("Kviz je završio")
                .setMessage("Broj bodova: "+bodovi2 + "\nTočni: "+ukupnoBodova)
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ProvjeriZnanjeActivity.this.finish();

                    }
                }).create().show();

    }


}
