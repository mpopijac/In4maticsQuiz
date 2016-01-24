package hr.foi.air.in4maticsquiz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;
import hr.foi.air.in4maticsquiz.R;
import hr.foi.air.in4maticsquiz.db.Korisnik;
import hr.foi.air.in4maticsquiz.RangListeActivity;

/**
 * Created by Matija Popijač on 3.12.2015..
 *
 * Adapter koji služi za prikaz korisnika i njihovih najboljih rezultata pomoću fragmenata
 * koja prima parametre: context, fragment kojeg punimo s podacima i listu koju treba prikazati
 *
 */
public class RangListaAdapter extends ArrayAdapter<RangListeActivity.rangLista> {
    private List<RangListeActivity.rangLista> rezultatArrayList;
    private int brojac=0;
    Korisnik korisnickoIme;


    public RangListaAdapter(Context context, int textViewResourceId, List<RangListeActivity.rangLista> objects) {
        super(context, textViewResourceId, objects);
        this.rezultatArrayList = objects;

    }



    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;
        Integer odabraniRazred = PrijavljeniKorisnik.getInstance().getOdabraniRazred();


        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.fragment_rang_lista, parent, false);
        }


        String[] korisnici= new String[rezultatArrayList.size()];
        Integer[] brojaci= new Integer[rezultatArrayList.size()];
        Long[] bodovi2= new Long[rezultatArrayList.size()];
        String[] datumi = new String[rezultatArrayList.size()];

        int z=0;
        int g=1;

        // Punjenjenje polja korisnici, brojaci, bodovi2 i datumi iz liste koja je proslijeđena.

        for (RangListeActivity.rangLista pojedinac : rezultatArrayList) {
            korisnici[z]=pojedinac.getKorisnici();
            brojaci[z]=g;
            bodovi2[z]=pojedinac.getBodovi2();
            datumi[z]=pojedinac.getDatumi();

            z++;
            g++;

        }


        TextView rBroj = (TextView) v.findViewById(R.id.rBroj);
        TextView korIme = (TextView) v.findViewById(R.id.korisniko_ime);
        TextView bodovi = (TextView) v.findViewById(R.id.brBodova);
        TextView datum = (TextView) v.findViewById(R.id.datum);



        rBroj.setText(Integer.toString(brojaci[position])+". ");

        korIme.setText(korisnici[position]);

        bodovi.setText(bodovi2[position].toString());

        datum.setText(datumi[position]);


        return v;

    }

}

