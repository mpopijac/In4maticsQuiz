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
    private long brojac=0;
    Korisnik korisnickoIme;

    public RangListaAdapter(Context context, int textViewResourceId, List<Rezultat> objects) {
        super(context, textViewResourceId, objects);
        this.rezultatArrayList = objects;
    }


        //ViewGroup parent -> viška

    public View getView(int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.rang_liste_fragment, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
        Rezultat i = rezultatArrayList.get(position);

        if (i != null) {

            brojac++;

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView rBroj = (TextView) v.findViewById(R.id.rBroj);
            TextView korIme = (TextView) v.findViewById(R.id.korisniko_ime);
            TextView bodovi = (TextView) v.findViewById(R.id.brBodova);



            korisnickoIme = new Select().from(Korisnik.class).where("IDkorisnik==?",i.getIDkorisnik()).executeSingle();
            String username = korisnickoIme.getKorisnickoIme();
            //rezultatArrayList = new Select().all().from(Rezultat.class).execute();


            // check to see if each individual textview is null.
            // if not, assign some text!
            if (rBroj != null){
                rBroj.setText(Long.toString(brojac));
            }


            if (korIme != null){
                korIme.setText(username);
            }
            if (bodovi != null){
                bodovi.setText(Long.toString(i.getBodovi()));
            }

        }

        // the view must be returned to our activity
        return v;

    }



}
