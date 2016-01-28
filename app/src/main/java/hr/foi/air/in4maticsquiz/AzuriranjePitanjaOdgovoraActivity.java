package hr.foi.air.in4maticsquiz;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.in4maticsquiz.AsyncTaskClass.AddUpdateDeleteOdgovora;
import hr.foi.air.in4maticsquiz.adapters.AdapterZaBrisanjeOdgvora;
import hr.foi.air.in4maticsquiz.db.Odgovor;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.singletons.Azuriranje;

public class AzuriranjePitanjaOdgovoraActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Pitanja> pitanja=new ArrayList<Pitanja>();
    private List<Odgovor> odgovoriArrayList=new ArrayList<Odgovor>();
    private List<Odgovor> odgovoriZazuriranjeArrayList=new ArrayList<Odgovor>();
    private AdapterZaBrisanjeOdgvora odgovoriAdapter;
    ArrayList<Odgovor> listaZaPrikazArrayList = new ArrayList<Odgovor>();
    EditText txtPitanja;
    Pitanja trenutno;
    Button azuriraj,dodajOdg,dodTocanOdg,dodNetOdg,btnPovratak;
    EditText txtodgovor;
    TextView odgNaPit;
    AlertDialog alertD;
    ArrayList<Odgovor> odgovorLista = new ArrayList<Odgovor>();
    long i;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azuriranje_pitanja_odgovora);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        i= Azuriranje.getInstance().getIdPit();
        Log.i("idPitanja", String.valueOf(i));
        dodajOdg=(Button)findViewById(R.id.btnDodOdgovor);
        azuriraj=(Button)findViewById(R.id.btnAzurirajOdgPit);
        dodajOdg.setOnClickListener(this);
        azuriraj.setOnClickListener(this);
        pitanja = new Select().from(Pitanja.class).where("IDpitanja==?", i).execute();
        trenutno = pitanja.get(0);

        txtPitanja = (EditText) findViewById(R.id.txtP);
        txtPitanja.setText(trenutno.getPitanje());

        //dohvačanje iz baze pitanja samo iz odabranog razreda
        odgovoriArrayList = new Select().from(Odgovor.class).where("IDpitanja==?", i).where("obrisano==?",0).execute();


        for (Pitanja pitanje :pitanja){
            for (Odgovor o :odgovoriArrayList){
                if(o.getIDpitanja()==pitanje.getIDpitanja()){
                    listaZaPrikazArrayList.add(o);

                }
            }

        }


        ///adapter za prikaz rezultata fali
        odgovoriAdapter = new AdapterZaBrisanjeOdgvora(this, R.layout.listaodgovora, listaZaPrikazArrayList);
        listView = (ListView)findViewById(R.id.listAzuriraj);


        listView.setAdapter(odgovoriAdapter);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAzurirajOdgPit:

                AzuriranjePitanjaOdgovoraActivity.this.finish();


                break;

            case R.id.btnDodOdgovor:
                LayoutInflater layoutInflater = LayoutInflater.from(AzuriranjePitanjaOdgovoraActivity.this);
                View promptView = layoutInflater.inflate(R.layout.dialog_dodaj_odgovor, null);
                alertD = new AlertDialog.Builder(AzuriranjePitanjaOdgovoraActivity.this).create();
                odgNaPit = (TextView) promptView.findViewById(R.id.txtDodajOdgNaPit);

                odgNaPit.setText(txtPitanja.getText());
                txtodgovor=(EditText)promptView.findViewById(R.id.prIme);
                dodTocanOdg = (Button) promptView.findViewById(R.id.btnDodTocanOdg);

                dodNetOdg = (Button) promptView.findViewById(R.id.btnDodNetocanOdg);
                btnPovratak=(Button)promptView.findViewById(R.id.btnPovratak);

                dodTocanOdg.setOnClickListener(this);
                dodNetOdg.setOnClickListener(this);
                btnPovratak.setOnClickListener(this);


                alertD.setView(promptView);
                alertD.setCancelable(false);
                alertD.show();
                break;

            case R.id.btnDodTocanOdg:
                Odgovor o = new Odgovor();
                o.setIDodgovor(-1);
                o.setNaziv(txtodgovor.getText().toString());
                o.setTocan(1);
                o.setIDpitanja(-1);
                o.setObrisano(0);

                odgovorLista.add(o);
                Toast.makeText(getApplicationContext(), R.string.potvrdaOdgovor, Toast.LENGTH_LONG).show();
                txtodgovor.getText().clear();

                break;
            case R.id.btnDodNetocanOdg:
                Odgovor on = new Odgovor();
                on.setIDodgovor(-1);
                on.setNaziv(txtodgovor.getText().toString());
                on.setTocan(0);
                on.setIDpitanja(-1);
                on.setObrisano(0);

                odgovorLista.add(on);
                Toast.makeText(getApplicationContext(), R.string.potvrdaOdgovor, Toast.LENGTH_LONG).show();
                txtodgovor.getText().clear();

                break;
            case R.id.btnPovratak:

                //ažurirati promjenu
                odgovoriAdapter.notifyDataSetChanged();
                alertD.dismiss();

                break;



        }
    }
}
