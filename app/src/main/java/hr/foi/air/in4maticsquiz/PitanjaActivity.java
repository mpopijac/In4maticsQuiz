package hr.foi.air.in4maticsquiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.in4maticsquiz.R;
import hr.foi.air.in4maticsquiz.adapters.PitanjaListaAdapter;
import hr.foi.air.in4maticsquiz.adapters.PoglavljaListaAdapter;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.db.Poglavlje;
import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;

public class PitanjaActivity extends AppCompatActivity {

    private List<Pitanja> pitanjasArrayList = new ArrayList<Pitanja>();
    private PitanjaListaAdapter pitanjaAdapter;
    ArrayList<Pitanja> listaZaPrikazArrayList = new ArrayList<Pitanja>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitanja);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton dodaj = (FloatingActionButton) findViewById(R.id.dodajPit);
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        prikaziListu();
    }

    private void prikaziListu() {




        //dohvačanje iz baze pitanja samo iz odabranog razreda
        pitanjasArrayList = new Select().from(Pitanja.class).where("IDrazred==?", PrijavljeniKorisnik.getInstance().getOdabraniRazred()).execute();


        for (Pitanja pitanje :pitanjasArrayList){
                if(pitanje.getObrisano()==0){
                    listaZaPrikazArrayList.add(pitanje);
                    
                }
        }


        ///adapter za prikaz rezultata fali
        pitanjaAdapter = new PitanjaListaAdapter(this, R.layout.fragment_pitanja_lista, listaZaPrikazArrayList);
        ListView listView = (ListView)findViewById(R.id.listPitanja);


        listView.setAdapter(pitanjaAdapter);


    }

}
