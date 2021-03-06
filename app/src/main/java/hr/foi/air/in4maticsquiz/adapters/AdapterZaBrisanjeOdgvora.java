package hr.foi.air.in4maticsquiz.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import hr.foi.air.in4maticsquiz.R;
import hr.foi.air.in4maticsquiz.db.Odgovor;

/**
 * Created by Dario on 28.1.2016..
 */
public class AdapterZaBrisanjeOdgvora extends ArrayAdapter<Odgovor> {

    private ArrayList<Odgovor> popisOdgovoraLista;
    Odgovor o;
    EditText pit;
    Odgovor odgovor;

    public AdapterZaBrisanjeOdgvora(Context context, int textViewResourceId, ArrayList<Odgovor> lista) {
        super(context, textViewResourceId, lista);
        this.popisOdgovoraLista = lista;
    }

    private class ViewHolder{
        EditText odg;
        Button btnIzbrisi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));

        if(convertView ==null){
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.listaodgovora,null);

            holder =new ViewHolder();
            holder.odg=(EditText) convertView.findViewById(R.id.txtOdgovor);
            holder.btnIzbrisi = (Button) convertView.findViewById(R.id.btnObrisi);
            convertView.setTag(holder);


        }else {
            holder = (ViewHolder) convertView.getTag();
        }
         odgovor = popisOdgovoraLista.get(position);
        holder.odg.setText(odgovor.getNaziv());
        holder.odg.setTag(odgovor);
        holder.btnIzbrisi.setTag(odgovor);

        holder.btnIzbrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popisOdgovoraLista.remove(odgovor);
                notifyDataSetChanged();
                odgovor.setObrisano(1);
                odgovor.updateOdgovor(odgovor);
                //fali update na web server

            }
        });

        return convertView;
    }


}
