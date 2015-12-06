package in4matics_team.in4maticsquiz;

import android.app.ListActivity;
import android.os.Bundle;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;


import in4matics_team_local.db.Korisnik;
import in4matics_team_local.db.Rezultat;

public class rangListeActivity extends ListActivity {

    private List<Rezultat> rezultatArrayList = new ArrayList<Rezultat>();
    private RangListaAdapter m_adapter;
    private String[] polja;
    private String[] polja1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Integer odabraniRazred = PrijavljeniKorisnik.getInstance().getOdabraniRazred();

        rezultatArrayList = new Select().from(Rezultat.class).where("razred==?", odabraniRazred).execute();

        m_adapter = new RangListaAdapter(this, R.layout.rang_liste_fragment, rezultatArrayList);

        setListAdapter(m_adapter);


    }
}
