package hr.foi.air.in4maticsquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

    private EditText imePoglavlja;
    Button btnDodaj;
    AlertDialog alertDodaj;

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

                LayoutInflater layoutInflater = LayoutInflater.from(PoglavljaActivity.this);
                View promptView = layoutInflater.inflate(R.layout.dodajpoglavlje, null);

                alertDodaj = new AlertDialog.Builder(PoglavljaActivity.this).create();

                imePoglavlja = (EditText) promptView.findViewById(R.id.imePoglavlja);


                btnDodaj = (Button) promptView.findViewById(R.id.btnDodajPoglavlje);


                btnDodaj.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        alertDodaj.dismiss();
                        Poglavlje p=new Poglavlje();
                        p.setNaziv(imePoglavlja.getText().toString());
                        p.setUkljuceno(0);
                        listaZaPrikazArrayList.add(p);
                        poglavljaAdapter.notifyDataSetChanged();

                    }
                });

                alertDodaj.setView(promptView);
                alertDodaj.show();


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
