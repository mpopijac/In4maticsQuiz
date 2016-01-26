package hr.foi.air.in4maticsquiz.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.in4maticsquiz.R;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.db.Poglavlje;

/**
 * Created by Dario on 26.1.2016..
 */


public class PitanjaListaAdapter extends ArrayAdapter<Pitanja> {
    private List<Pitanja> popisPitanjaLista;
    private Pitanja pitanje;

    Button btnIzbrisiPit,btnAzurirajPit;
    AlertDialog alertD;

    public PitanjaListaAdapter(Context context, int textViewResourceId, List<Pitanja> lista) {
        super(context, textViewResourceId, lista);
        this.popisPitanjaLista = lista;
    }

    private class ViewHolder{
        TextView pitanje;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));

        if(convertView ==null){
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.fragment_pitanja_lista,null);

            holder =new ViewHolder();
            holder.pitanje=(TextView) convertView.findViewById(R.id.pitanje);

            convertView.setTag(holder);

            holder.pitanje.setOnLongClickListener(
                    new View.OnLongClickListener(){
                        public boolean onLongClick(View v) {
                            TextView tv = (TextView) v;
                            pitanje = (Pitanja) tv.getTag();
                            Log.i("LongClickActivated","jupi");

                            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                            View promptView = layoutInflater.inflate(R.layout.dialog_azuriraj_pitanja, null);

                            alertD = new AlertDialog.Builder(getContext()).create();

                            btnIzbrisiPit = (Button) promptView.findViewById(R.id.btnIzbrisiPitanje);

                            btnAzurirajPit = (Button) promptView.findViewById(R.id.btnAzurirajPitanje);

                            btnIzbrisiPit.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {

                                }
                            });

                            btnAzurirajPit.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    alertD.dismiss();

                                }
                            });

                            alertD.setView(promptView);

                            alertD.show();


                            return false;
                        }
                    }
            );

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Pitanja pitanje = popisPitanjaLista.get(position);
        holder.pitanje.setText(pitanje.getPitanje());

        return convertView;
    }

}
