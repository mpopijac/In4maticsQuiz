package hr.foi.air.in4maticsquiz.fragments.quiz;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.in4maticsquiz.R;
import hr.foi.air.in4maticsquiz.db.Odgovor;
import hr.foi.air.in4maticsquiz.db.Pitanja;

/**
 * Created by Dario on 15.12.2015..
 */
public class UnesiTocanPojamFragment extends Fragment {

    private List<Pitanja> pitanja=new ArrayList<Pitanja>();
    private List<Odgovor> odgovori=new ArrayList<Odgovor>();
    TextView txtP;
    Pitanja trenutno;
    EditText odgnaPitanje;
    private boolean tocno;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_pitanje_odgovor, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle data = getArguments();

        long id = data.getLong("pitanje_key");

        pitanja=new Select().from(Pitanja.class).where("IDpitanja==?",id).execute();
        trenutno=pitanja.get(0);

        txtP=(TextView)getView().findViewById(R.id.txtPitanjeodg);
        txtP.setText(trenutno.getPitanje());


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        final Bundle data=getArguments();

        long id = data.getLong("pitanje_key");
        odgovori=new Select().all().from(Odgovor.class).where("IDpitanja==?", id).execute();

        odgnaPitanje=(EditText)getView().findViewById(R.id.odgovorNaPitanje);
        odgnaPitanje.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                Odgovor tocan = null;
                for (Odgovor odg : odgovori) {
                    if (odg.getTocan() == 1) {
                        tocan = odg;
                    }
                }
                tocno = false;

                if (tocan.getNaziv().toLowerCase().toString().equals(odgnaPitanje.getText().toString().toLowerCase())) {

                    tocno = true;
                }


                data.putBoolean("tocnost", tocno);

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
    }



}
