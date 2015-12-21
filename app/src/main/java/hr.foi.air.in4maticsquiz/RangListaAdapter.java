package hr.foi.air.in4maticsquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import hr.foi.air.in4maticsquiz.db.Korisnik;
import hr.foi.air.in4maticsquiz.db.Rezultat;

/**
 * Created by Matija Popijač on 3.12.2015..
 */
public class RangListaAdapter extends ArrayAdapter<rangListeActivity.rangLista> {
    private List<rangListeActivity.rangLista> rezultatArrayList;
    private int brojac=0;
    Korisnik korisnickoIme;


    public RangListaAdapter(Context context, int textViewResourceId, List<rangListeActivity.rangLista> objects) {
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
        String[] datumi = new String[rezultatArrayList.size()];

        int z=0;
        int g=1;

        for (rangListeActivity.rangLista pojedinac : rezultatArrayList) {
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


        //       System.out.println(Long.toString(brojac)+username+i.getBodovi()+"bla");

        rBroj.setText(Integer.toString(brojaci[position])+". ");

        korIme.setText(korisnici[position]);

        bodovi.setText(bodovi2[position].toString());

        datum.setText(datumi[position]);





        // the view must be returned to our activity
        return v;



    }




}
