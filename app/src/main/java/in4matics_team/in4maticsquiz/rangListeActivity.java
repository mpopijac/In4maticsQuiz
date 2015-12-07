package in4matics_team.in4maticsquiz;

import android.app.ListActivity;
import android.os.Bundle;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import in4matics_team_local.db.Rezultat;

public class rangListeActivity extends ListActivity {

    private List<Rezultat> rezultatArrayList = new ArrayList<Rezultat>();
    private RangListaAdapter m_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rang_liste);

        rezultatArrayList = new Select().all().from(Rezultat.class).execute();


        m_adapter = new RangListaAdapter(this, R.layout.rang_liste_fragment, rezultatArrayList);
        setListAdapter(m_adapter);

        //setListAdapter(new ArrayAdapter<String>(this, R.layout.rang_liste_fragment, PENS));

    }
}
