package in4matics_team.in4maticsquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import in4matics_team_local.db.Korisnik;
import in4matics_team_local.db.Rezultat;

/**
 * Created by Matija Popijač on 3.12.2015..
 */
public class RangListaAdapter extends ArrayAdapter<Rezultat> {
    private List<Rezultat> rezultatArrayList;
    private int brojac=0;
    Korisnik korisnickoIme;


    public RangListaAdapter(Context context, int textViewResourceId, List<Rezultat> objects) {
        super(context, textViewResourceId, objects);
        this.rezultatArrayList = objects;
    }


    //ViewGroup parent -> viška

    public View getView(int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;
        Integer odabraniRazred = PrijavljeniKorisnik.getInstance().getOdabraniRazred();



        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.rang_liste_fragment, parent, false);
        }

        //   TextView txtuser = (TextView) v.findViewById(R.id.naslov);

        //     txtuser.setText(Integer.toString(odabraniRazred)+". razred");

        String[] korisnici= new String[rezultatArrayList.size()];
        Integer[] brojaci= new Integer[rezultatArrayList.size()];
        Long[] bodovi2= new Long[rezultatArrayList.size()];


        int z=0;
        int g=1;

        for (Rezultat rez: rezultatArrayList) {

            Korisnik korisnickoIme;
            korisnickoIme = new Select().from(Korisnik.class).where("IDkorisnik==?", rez.getIDkorisnik()).executeSingle();
            String username = korisnickoIme.getKorisnickoIme();

            korisnici[z]=username;
            brojaci[z]=g;
            bodovi2[z]=rez.getBodovi();


            z++;
            g++;

        }

        TextView rBroj = (TextView) v.findViewById(R.id.rBroj);
        TextView korIme = (TextView) v.findViewById(R.id.korisniko_ime);
        TextView bodovi = (TextView) v.findViewById(R.id.brBodova);


        //       System.out.println(Long.toString(brojac)+username+i.getBodovi()+"bla");

        rBroj.setText(Integer.toString(brojaci[position])+". ");
        korIme.setText(korisnici[position]);
        bodovi.setText(bodovi2[position].toString());



        // the view must be returned to our activity
        return v;

    }



}
