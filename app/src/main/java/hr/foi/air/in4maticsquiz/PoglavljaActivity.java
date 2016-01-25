package hr.foi.air.in4maticsquiz;

import android.app.Activity;
import android.app.ListActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import hr.foi.air.in4maticsquiz.adapters.PoglavljaListaAdapter;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.db.Poglavlje;
import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;

public class PoglavljaActivity extends AppCompatActivity {

    private List<Poglavlje> poglavljeArrayList = new ArrayList<Poglavlje>();
    private List<Pitanja> pitanjasArrayList = new ArrayList<Pitanja>();
    ArrayList<Poglavlje> listaZaPrikazArrayList = new ArrayList<Poglavlje>();
    //List<poglavljeLista> listaZaPrikazArrayList = new CopyOnWriteArrayList<>();
    private PoglavljaListaAdapter poglavljaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poglavlja);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton dodaj = (FloatingActionButton) findViewById(R.id.dodaj);
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //prikaži listu
        prikaziListu();
    }

    private void prikaziListu() {

        //dohvačanje iz baze neobrisanih poglavlja
        poglavljeArrayList = new Select().from(Poglavlje.class).where("obrisano==?", 0).execute();
        //dohvačanje iz baze pitanja samo iz odabranog razreda
        pitanjasArrayList = new Select().from(Pitanja.class).where("IDrazred==?",PrijavljeniKorisnik.getInstance().getOdabraniRazred()).execute();
        

        //dodavanje elemenata u listu za prikaz
        for (Poglavlje poglavlje :poglavljeArrayList){
            for (Pitanja pitanje :pitanjasArrayList){
                if(poglavlje.getIDpoglavlje()==pitanje.getIDpoglavlje()){
                    listaZaPrikazArrayList.add(poglavlje);
                    break;
                }
            }
        }

        ///adapter za prikaz rezultata fali
        poglavljaAdapter = new PoglavljaListaAdapter(this, R.layout.fragment_poglavlja_lista, listaZaPrikazArrayList);

        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(poglavljaAdapter);

    }
}
