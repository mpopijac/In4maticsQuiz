package hr.foi.air.in4maticsquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
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

import hr.foi.air.in4maticsquiz.AsyncTaskClass.AddUpdateDeletePoglavlja;
import hr.foi.air.in4maticsquiz.adapters.PoglavljaListaAdapter;
import hr.foi.air.in4maticsquiz.core.DataLoader;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.db.Poglavlje;
import hr.foi.air.in4maticsquiz.loaders.WebServiceDataLoader;
import hr.foi.air.in4maticsquiz.singletons.Azuriranje;
import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;

public class PoglavljaActivity extends AppCompatActivity {

    private List<Poglavlje> poglavljeArrayList = new ArrayList<Poglavlje>();
    private List<Pitanja> pitanjasArrayList = new ArrayList<Pitanja>();
    ArrayList<Poglavlje> listaZaPrikazArrayList = new ArrayList<Poglavlje>();
    ArrayList<Poglavlje> kopija = new ArrayList<Poglavlje>();
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
            public void onClick(final View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(PoglavljaActivity.this);
                final View promptView = layoutInflater.inflate(R.layout.dodajpoglavlje, null);

                alertDodaj = new AlertDialog.Builder(PoglavljaActivity.this).create();

                imePoglavlja = (EditText) promptView.findViewById(R.id.imePoglavlja);


                btnDodaj = (Button) promptView.findViewById(R.id.btnDodajPoglavlje);


                btnDodaj.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        alertDodaj.dismiss();
                        Poglavlje p = new Poglavlje();
                        p.setNaziv(imePoglavlja.getText().toString());
                        p.setUkljuceno(0);
                        p.setObrisano(0);
                        listaZaPrikazArrayList.add(p);
                        poglavljaAdapter.notifyDataSetChanged();

                        //slanje zahtjeva na web server s podacima
                        addToBase(p.getIDpoglavlje(), p.getNaziv(), p.getUkljuceno());

                        Poglavlje poSave = new Poglavlje();
                        Log.i("sranje:",Long.toString(Azuriranje.getInstance().getZadnjeDodanoPoglavljeId()));
                        poSave.setIDpoglavlje(Azuriranje.getInstance().getZadnjeDodanoPoglavljeId());
                        poSave.setNaziv(p.getNaziv());
                        poSave.setUkljuceno(p.getUkljuceno());
                        poSave.setObrisano(p.getObrisano());
                        poSave.save();


                        Snackbar.make(view, "Poglavlje je dodano!! " + "\nTrebate dodati pitanje!!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                });

                alertDodaj.setView(promptView);
                alertDodaj.show();


            }
        });

        //prikaži listu
        prikaziListu();
    }


    private void addToBase(Long id, String naziv, Integer ukljuceno){
        new AddUpdateDeletePoglavlja(this, 0, "").execute(Long.toString(id), naziv, Integer.toString(ukljuceno));
        //trebalo dodati za sinkronizaciju podataka

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

        ///adapter za prikaz liste poglavlja
        poglavljaAdapter = new PoglavljaListaAdapter(this, R.layout.fragment_poglavlja_lista, listaZaPrikazArrayList);
        ListView listView = (ListView)findViewById(R.id.list);


        listView.setAdapter(poglavljaAdapter);


    }

    @Override
    public void onBackPressed(){
        /*
            promjenjauje trenutnu aktivnost na prethodnu aktivnost
         */
        Intent intent = new Intent(PoglavljaActivity.this, MenuActivity.class);
        startActivity(intent);

        //poslati promjene na web server, azuriranje
        ArrayList<Poglavlje> poglavlja = new ArrayList<Poglavlje>();
        poglavlja=Azuriranje.getInstance().getPoglavljeLista();
        for(Poglavlje po: poglavlja){
            new AddUpdateDeletePoglavlja(this,1, Integer.toString(po.getObrisano())).execute(Long.toString(po.getIDpoglavlje()), po.getNaziv(), Integer.toString(po.getUkljuceno()));
        }
        poglavlja.clear();



    }
}
