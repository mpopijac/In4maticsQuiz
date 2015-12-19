package in4matics_team.in4maticsquiz.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import in4matics_team.in4maticsquiz.R;
import in4matics_team_local.db.Odgovor;
import in4matics_team_local.db.Pitanja;

/**
 * Created by Dario on 15.12.2015..
 */
public class TocnoNetocno_fragment extends Fragment {

    private List<Pitanja> pitanja=new ArrayList<Pitanja>();
    private List<Odgovor> odgovori=new ArrayList<Odgovor>();
    TextView txtP;
    Pitanja trenutno;
    RadioButton rdTocno,rdNetocno;
    RadioGroup rg;
    private boolean tocno;
    Odgovor tocan=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_pitanje_t_n, container, false);

        return view;

    }
    @Override
    public void onStart() {
        super.onStart();

        Bundle data = getArguments();

        long id = data.getLong("pitanje_key");

        pitanja=new Select().from(Pitanja.class).where("IDpitanja==?",id).execute();
        trenutno=pitanja.get(0);

        txtP=(TextView)getView().findViewById(R.id.textTocnoNetocno);
        txtP.setText(trenutno.getPitanje());

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        rdTocno=(RadioButton)getView().findViewById(R.id.radioButtonTocno);
        rdNetocno=(RadioButton)getView().findViewById(R.id.radioButtonNetocno);
        final Bundle data = getArguments();
        long id = data.getLong("pitanje_key");
        odgovori=new Select().all().from(Odgovor.class).where("IDpitanja==?", id).execute();
        for(Odgovor odg:odgovori){
            if(odg.getTocan()==1){
                tocan=odg;
                rdTocno.setText(odg.getNaziv());
            }
            else {
                rdNetocno.setText(odg.getNaziv());
            }
        }
        rg = (RadioGroup)getView(). findViewById(R.id.rdgroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rdTocno = (RadioButton) group.findViewById(R.id.radioButtonTocno);
                rdNetocno = (RadioButton) group.findViewById(R.id.radioButtonNetocno);
                tocno = false;
                if (rdNetocno.isChecked()) {
                    if (tocan.getNaziv().equals(rdNetocno.getText())) {
                        tocno = true;
                    }

                }
                if (rdTocno.isChecked()) {
                    if (tocan.getNaziv().equals(rdTocno.getText())) {
                        tocno = true;
                    }
                }

                data.putBoolean("tocnost", tocno);
            }

        });
    }
}