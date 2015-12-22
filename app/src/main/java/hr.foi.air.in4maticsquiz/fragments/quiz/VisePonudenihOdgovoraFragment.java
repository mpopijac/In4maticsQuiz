package hr.foi.air.in4maticsquiz.fragments.quiz;

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
 */
public class VisePonudenihOdgovoraFragment extends ListFragment{
    private List<Pitanja> pitanja=new ArrayList<Pitanja>();
    private List<Odgovor> odgovori=new ArrayList<Odgovor>();
    TextView txtP;
    Pitanja trenutno;
    private  boolean tocno;
    private int brOz;



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
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view != null) {
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBoxOdg);
                    checkBox.setChecked(!checkBox.isChecked());

                    Odgovor tocan = null;
                    for (Odgovor odg : odgovori) {
                        if (odg.getTocan() == 1) {
                            tocan = odg;
                        }
                    }
                    tocno = false;

                    if (checkBox.isChecked()) {
                        brOz++;
                        //provjera broja oznacenih checkboxa
                        Log.i("Broj označenih: ", Long.toString(brOz));
                    } else {
                        if (brOz > 0) brOz--;
                        else brOz = 0;
                        Log.i("Broj označenih: ", Long.toString(brOz));
                    }
                    if (checkBox.isChecked() && tocan.getNaziv().equals(checkBox.getText()) && brOz == 1) {
                        Log.i("Broj označenih: ", Long.toString(brOz));
                        tocno = true;
                        Log.i("Točan odgovor: ", Boolean.toString(tocno));

                    }
                    da.putBoolean("tocnost", tocno);
                }
            }
        });


    }


}
