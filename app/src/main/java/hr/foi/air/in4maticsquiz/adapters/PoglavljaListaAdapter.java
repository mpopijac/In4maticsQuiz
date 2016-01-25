package hr.foi.air.in4maticsquiz.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.in4maticsquiz.PoglavljaActivity;
import hr.foi.air.in4maticsquiz.R;
import hr.foi.air.in4maticsquiz.db.Poglavlje;

/**
 * Created by Matija Popijač on 25.1.2016..
 */
public class PoglavljaListaAdapter extends ArrayAdapter<Poglavlje>{

    private ArrayList<Poglavlje> popisPogljavljaLista;


    public PoglavljaListaAdapter(Context context, int textViewResourceId, ArrayList<Poglavlje> lista) {
        super(context, textViewResourceId, lista);
        this.popisPogljavljaLista = lista;
    }

    private class ViewHolder{
        TextView poglavlje;
        Switch ukljuceno;
    }

    @Override
     public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));

        if(convertView ==null){
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.fragment_poglavlja_lista,null);

            holder =new ViewHolder();
            holder.poglavlje=(TextView) convertView.findViewById(R.id.poglavlje);
            holder.ukljuceno = (Switch) convertView.findViewById(R.id.ukljuceno);
            convertView.setTag(holder);

            holder.ukljuceno.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            Switch sw = (Switch) v;
                            Poglavlje poglavlje = (Poglavlje) sw.getTag();
                            Toast.makeText(getContext().getApplicationContext(), "Ukljuceno: " + sw.getText() + "je" + sw.isChecked(), Toast.LENGTH_LONG).show();
                            Log.i("Poglavlje x:", poglavlje.getNaziv());
                            Log.i("Poglavlje status:", Integer.toString(poglavlje.getUkljuceno()));
                            if (sw.isChecked() == true) {
                                poglavlje.setUkljuceno(1);
                            } else {
                                poglavlje.setUkljuceno(0);
                            }

                        }

                    }

            );

            holder.poglavlje.setOnLongClickListener(
                    new View.OnLongClickListener(){
                        public boolean onLongClick(View v) {
                            TextView tv = (TextView) v;
                            Poglavlje poglavlje = (Poglavlje) tv.getTag();
                            Log.i("LongClickActivated","jupi");
                            return false;
                        }
                    }
            );

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Poglavlje poglavlje = popisPogljavljaLista.get(position);
        holder.poglavlje.setText(poglavlje.getNaziv());
        if(poglavlje.getUkljuceno()==1){
            holder.ukljuceno.setChecked(true);
        }else{
            holder.ukljuceno.setChecked(false);
        }
        holder.ukljuceno.setTag(poglavlje);
        holder.poglavlje.setTag(poglavlje);

        return convertView;
     }
}
