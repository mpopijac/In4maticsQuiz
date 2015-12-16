package in4matics_team.in4maticsquiz.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import in4matics_team.in4maticsquiz.R;
import in4matics_team_local.db.Pitanja;

/**
 * Created by Dario on 15.12.2015..
 */
public class VisePonudenihOdgovora_fragment extends Fragment {
    private List<Pitanja> pitanja=new ArrayList<Pitanja>();
    TextView txtP;
    Pitanja trenutno;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_pitanje_vise_odaberi, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle data = getArguments();

        long id = data.getLong("pitanje_key");

        pitanja=new Select().from(Pitanja.class).where("IDpitanja==?",id).execute();
        trenutno=pitanja.get(0);

        txtP=(TextView)getView().findViewById(R.id.txtViseodg);
        txtP.setText(trenutno.getPitanje());

    }
}
