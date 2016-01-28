package hr.foi.air.in4maticsquiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.in4maticsquiz.R;
import hr.foi.air.in4maticsquiz.adapters.AdapterZaBrisanjeOdgvora;
import hr.foi.air.in4maticsquiz.adapters.PitanjaListaAdapter;
import hr.foi.air.in4maticsquiz.db.Odgovor;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.singletons.Azuriranje;
import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;

public class azuriraj extends AppCompatActivity {
    private List<Pitanja> pitanja=new ArrayList<Pitanja>();
    private List<Odgovor> odgovoriArrayList=new ArrayList<Odgovor>();
    private AdapterZaBrisanjeOdgvora odgovoriAdapter;
    ArrayList<Odgovor> listaZaPrikazArrayList = new ArrayList<Odgovor>();
    EditText txtPitanja;
    Pitanja trenutno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azuriraj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        long i= Azuriranje.getInstance().getIdPit();
        Log.i("idPitanja", String.valueOf(i));
        pitanja = new Select().from(Pitanja.class).where("IDpitanja==?", i).execute();
        trenutno = pitanja.get(0);

        txtPitanja = (EditText) findViewById(R.id.txtP);
        txtPitanja.setText(trenutno.getPitanje());

        //dohvačanje iz baze pitanja samo iz odabranog razreda
        odgovoriArrayList = new Select().from(Odgovor.class).where("IDpitanja==?", i).execute();


        for (Pitanja pitanje :pitanja){
            for (Odgovor o :odgovoriArrayList){
                if(o.getIDpitanja()==pitanje.getIDpitanja()){
                    listaZaPrikazArrayList.add(o);

                }
            }

        }


        ///adapter za prikaz rezultata fali
        odgovoriAdapter = new AdapterZaBrisanjeOdgvora(this, R.layout.listaodgovora, listaZaPrikazArrayList);
        ListView listView = (ListView)findViewById(R.id.listAzuriraj);


        listView.setAdapter(odgovoriAdapter);



    }

}
