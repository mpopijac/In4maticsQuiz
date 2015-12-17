package in4matics_team.in4maticsquiz;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provjeri_znanje);
        Integer odabraniRazred = PrijavljeniKorisnik.getInstance().getOdabraniRazred();

        pitanja=new Select().all().from(Pitanja.class).where("IDrazred==?", odabraniRazred).execute();
        trenutno=pitanja.get(new Random().nextInt(pitanja.size()));
        vrstaPitanja(trenutno);

        btnSljedece=(Button)findViewById(R.id.btnSljedeceP);
        btnSljedece.setOnClickListener(this);
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
        if(odgovoriTrenutno.size()==2) postaviPit(1);
        else if(odgovoriTrenutno.size()>2) postaviPit(2);
        else if (odgovoriTrenutno.size()==1) postaviPit(3);

    }
    public void postaviPit(int v){

        if(v==1){
            prikaziFragment(new TocnoNetocno_fragment());
        }
        else if(v==2){
            prikaziFragment(new VisePonudenihOdgovora_fragment());
        }
        else prikaziFragment(new UnesiTocanPojam_fragment());

    }

    @Override
    public void onClick(View v) {
            trenutno=pitanja.get(new Random().nextInt(pitanja.size()));
            vrstaPitanja(trenutno);

    }
}
