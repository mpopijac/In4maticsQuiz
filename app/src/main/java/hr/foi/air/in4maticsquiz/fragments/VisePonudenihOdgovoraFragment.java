package hr.foi.air.in4maticsquiz.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.in4maticsquiz.adapters.CheckboxOdgovoriAdapter;
import hr.foi.air.in4maticsquiz.R;
import hr.foi.air.in4maticsquiz.db.Odgovor;
import hr.foi.air.in4maticsquiz.db.Pitanja;

/**
 * Created by Dario on 15.12.2015..
 * Fragment koji prikazuje vrstu pitanja sa više ponuđenih odgovora.
 */
public class VisePonudenihOdgovoraFragment extends ListFragment{
    private List<Pitanja> pitanja=new ArrayList<Pitanja>();
    private List<Odgovor> odgovori=new ArrayList<Odgovor>();

    TextView txtP;
    Pitanja trenutno;
    private  boolean tocno,oznacentocan=false;
    private int brOz,brTocnoOz,brTocnihodgovora;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.fragment_pitanje_vise_odaberi, container, false);
        Bundle data = getArguments();

        long id = data.getLong("pitanje_key");

        pitanja = new Select().from(Pitanja.class).where("IDpitanja==?", id).execute();
        trenutno = pitanja.get(0);

        txtP = (TextView) myFragmentView.findViewById(R.id.txtViseodg);
        txtP.setText(trenutno.getPitanje());
        return myFragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        final Bundle da=getArguments();
        long id = da.getLong("pitanje_key");
        odgovori=new Select().all().from(Odgovor.class).where("IDpitanja==?", id).execute();
        CheckboxOdgovoriAdapter adapter = new CheckboxOdgovoriAdapter(getActivity(),R.layout.fragment_checkbox_layout, odgovori);
        brOz=0;
        brTocnoOz=0;
        setListAdapter(adapter);
        /*
        Postavljanje brojaca koji govori koliko točnih odgovora ima pitanje(ako ima više točnih)
         */
        for(Odgovor i:odgovori){
            if(i.getTocan()==1){
                brTocnihodgovora++;
            }
        }

        /*
        Klik na element liste, u ovom slučaju na checkbox, označi ili odznači checkbox ovisno o njegovom stanju,
        te provjeri da li je označeni točan odgovor.
         */
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view != null) {
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBoxOdg);
                    checkBox.setChecked(!checkBox.isChecked());
                    tocno = false;

                    /*
                    Ako je checkbox(kliknuti element liste) označeni povećaj brojač broj označenih, ako je označeni checkbox točan odgovor
                    postavi da je točan odgvor označen i povećaj broj točno označenih odgovora.
                     */
                    if (checkBox.isChecked()) {
                        brOz++;
                        for (Odgovor odg : odgovori) {
                            if(odg.getTocan()==1 && checkBox.getText().equals(odg.getNaziv()))
                            {
                                oznacentocan = true;
                                brTocnoOz++;
                            }
                        }
                        Log.i("Broj označenih: ", Long.toString(brOz));
                        Log.i("Broj točno označenih: ", Long.toString(brTocnoOz));

                        /*
                        U slučaju da checkbox(kliknuti element liste) nije označen smanji broj označenih, te ako je odznačeni checkbox bio točan smanji
                        broj broj točno označenih te postavi da nema točno označenih ukoliko je broj točno označenih jednak 0.
                         */
                    } else {
                        brOz--;
                        for (Odgovor odg : odgovori) {
                            if (odg.getTocan() == 1 && checkBox.getText().equals(odg.getNaziv()))
                            {
                                brTocnoOz--;
                                if(brTocnoOz>0)oznacentocan = true;
                                else oznacentocan = false;
                            }
                        }
                        Log.i("Broj označenih: ", Long.toString(brOz));
                        Log.i("Broj točno označenih: ", Long.toString(brTocnoOz));
                    }
                    Log.i("Označeni točni : ", Boolean.toString(oznacentocan));
                    /*
                    Provjerava se da li je označeni odgovor točan, te se u slučaju da su svi točni odgovori(ukoliko ih ima više)
                    označeni i ako je broj označenih odgovora jednak broju točnih odgovora šalje aktivnosti iz koje je pozvan fragment
                    rezultat true, odnosno false ako nisu zadovoljeni svi uvjeti.
                     */
                    if (oznacentocan == true && brOz == brTocnihodgovora && brTocnoOz==brTocnihodgovora) tocno = true;
                    da.putBoolean("tocnost", tocno);
                    Log.i("Odgovorili ste: ", Boolean.toString(tocno));
                }
            }

        });


    }

}
