package hr.foi.air.in4maticsquiz;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hr.foi.air.in4maticsquiz.AsyncTaskClass.AddUpdateDeleteOdgovora;
import hr.foi.air.in4maticsquiz.AsyncTaskClass.AddUpdateDeletePitanja;
import hr.foi.air.in4maticsquiz.AsyncTaskClass.AddUpdateDeletePitanja;
import hr.foi.air.in4maticsquiz.adapters.CheckboxPoglavljaAdapter;
import hr.foi.air.in4maticsquiz.adapters.PoglavljaListaAdapter;
import hr.foi.air.in4maticsquiz.db.Odgovor;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.db.Poglavlje;
import hr.foi.air.in4maticsquiz.singletons.Azuriranje;
import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;

public class DodajNovoPitanje extends AppCompatActivity implements View.OnClickListener {

    Button dodajPitanje,dodTocanOdg,dodNetOdg,btnPovratak;
    EditText tekstPitanja,txtodgovor;
    TextView tekstPoglavlja,odgNaPit;
    AlertDialog alertD;
    private CheckboxPoglavljaAdapter poglavljaAdapter;
    private List<Poglavlje> poglavljeArrayList = new ArrayList<Poglavlje>();
    private List<Pitanja> pitanjasArrayList = new ArrayList<Pitanja>();
    ArrayList<Poglavlje> listaZaPrikazArrayList = new ArrayList<Poglavlje>();
    Poglavlje po = new Poglavlje();
    List<Poglavlje> po2 = new ArrayList<Poglavlje>();
    ArrayList<Odgovor> odgovorLista = new ArrayList<Odgovor>();

    Boolean zastavica=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_novo_pitanje);

        tekstPitanja=(EditText)findViewById(R.id.tekstPitanja);
        tekstPoglavlja=(TextView)findViewById(R.id.odabir);
        dodajPitanje=(Button)findViewById(R.id.btnDodajPitanje);
        dodajPitanje.setOnClickListener(this);



        //dohvačanje iz baze neobrisanih poglavlja
        poglavljeArrayList = new Select().from(Poglavlje.class).where("obrisano==?", 0).execute();

        //dohvačanje iz baze pitanja samo iz odabranog razreda
        pitanjasArrayList = new Select().from(Pitanja.class).where("IDrazred==?", PrijavljeniKorisnik.getInstance().getOdabraniRazred()).execute();


        //dodavanje elemenata u listu za prikaz
        for (Poglavlje poglavlje :poglavljeArrayList){
            for (Pitanja pitanje :pitanjasArrayList){
                if(poglavlje.getIDpoglavlje()==pitanje.getIDpoglavlje()){
                    listaZaPrikazArrayList.add(poglavlje);
                    break;
                }
            }
        }

        pitanjasArrayList = new Select().from(Pitanja.class).execute();

        ArrayList<Long> idp = new ArrayList<Long>();
        for (Pitanja pitanje: pitanjasArrayList){
            idp.add(pitanje.getIDpoglavlje());
        }

        for (Long x:idp){
            for (Poglavlje poglavlje: poglavljeArrayList){
                if(x==poglavlje.getIDpoglavlje()){
                    po=poglavlje;
                    zastavica=true;
                }
            }
            if(zastavica){
                poglavljeArrayList.remove(po);
                zastavica=false;
            }
        }

        for(Poglavlje e:poglavljeArrayList){
            listaZaPrikazArrayList.add(e);
        }


        poglavljaAdapter = new CheckboxPoglavljaAdapter(this, R.layout.fragment_checkbox_layout, listaZaPrikazArrayList);
        ListView listView = (ListView)findViewById(R.id.listPoglavlje);


        listView.setAdapter(poglavljaAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view != null) {
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBoxOdg);
                    if(checkBox.isChecked()){

                        tekstPoglavlja.setText(checkBox.getText());
                    }


                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnDodajPitanje:

                if (TextUtils.isEmpty(tekstPitanja.getText().toString()) || TextUtils.isEmpty(tekstPoglavlja.getText().toString())) {

                    Toast.makeText(getApplicationContext(), R.string.greska_pitanje, Toast.LENGTH_LONG).show();

                } else {

                    LayoutInflater layoutInflater = LayoutInflater.from(DodajNovoPitanje.this);
                    View promptView = layoutInflater.inflate(R.layout.dialog_dodaj_odgovor, null);
                    alertD = new AlertDialog.Builder(DodajNovoPitanje.this).create();
                    odgNaPit = (TextView) promptView.findViewById(R.id.txtDodajOdgNaPit);

                    odgNaPit.setText(tekstPitanja.getText());
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
                }

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


                po2 = new Select().from(Poglavlje.class).where("naziv==?",tekstPoglavlja.getText().toString()).execute();

                //dodavanje this, 0 dodavanje, 0 obrisano
                new AddUpdateDeletePitanja(this, 0, "0").execute("0", tekstPitanja.getText().toString(), Long.toString(po2.get(0).getIDpoglavlje()), Long.toString(PrijavljeniKorisnik.getInstance().getOdabraniRazred()));

                Pitanja pi = new Pitanja();
                pi.setObrisano(0);
                pi.setIDrazred(PrijavljeniKorisnik.getInstance().getOdabraniRazred());
                pi.setIDpoglavlje(po2.get(0).getIDpoglavlje());
                pi.setPitanje(tekstPitanja.getText().toString());
                pi.setIDpitanja(Azuriranje.getInstance().getZadnjeDodanoPitanjeId());
                pi.save();

                Log.i("pitanje",tekstPitanja.getText().toString());
                for (Odgovor oi:odgovorLista){
                    Log.i("odgovori", oi.getNaziv());
                    oi.setIDpitanja(Azuriranje.getInstance().getZadnjeDodanoPitanjeId());
                    //dodavanje this, 0 dodavanje, 0 obrisano
                    new AddUpdateDeleteOdgovora(this, 0, "0").execute("0",oi.getNaziv(), Long.toString(oi.getTocan()), Long.toString(oi.getIDpitanja()));
                    oi.setIDodgovor(Azuriranje.getInstance().getZadnjiDodaniOdgovorId());
                    oi.save();
                }
                odgovorLista.clear();
                alertD.dismiss();
                DodajNovoPitanje.this.finish();

        }

    }
}
