package hr.foi.air.in4maticsquiz.adapters;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
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

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.in4maticsquiz.AsyncTaskClass.AddUpdateDeletePitanja;
import hr.foi.air.in4maticsquiz.R;
import hr.foi.air.in4maticsquiz.azuriraj;
import hr.foi.air.in4maticsquiz.db.Odgovor;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.db.Poglavlje;
import hr.foi.air.in4maticsquiz.singletons.Azuriranje;
import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;

/**
 * Created by Dario on 26.1.2016..
 */


public class PitanjaListaAdapter extends ArrayAdapter<Pitanja> {
    private List<Pitanja> popisPitanjaLista;
    private Pitanja pitanje;
    List<Odgovor> odgovori = new ArrayList<Odgovor>();

    Button btnIzbrisiPit,btnAzurirajPit;
    AlertDialog alertD;
    private Boolean postojiUListi = false;

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
            holder.pitanje=(TextView) convertView.findViewById(R.id.pitanjeAzuriraj);
            convertView.setTag(holder);

            holder.pitanje.setOnLongClickListener(
                    new View.OnLongClickListener() {
                        public boolean onLongClick(View v) {
                            TextView tv = (TextView) v;
                            pitanje = (Pitanja) tv.getTag();
                            Log.i("LongClickActivated", pitanje.getPitanje());

                            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                            View promptView = layoutInflater.inflate(R.layout.dialog_azuriraj_pitanja, null);

                            alertD = new AlertDialog.Builder(getContext()).create();

                            btnIzbrisiPit = (Button) promptView.findViewById(R.id.btnIzbrisiPitanje);

                            btnAzurirajPit = (Button) promptView.findViewById(R.id.btnAzurirajPitanje);

                            btnIzbrisiPit.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    alertD.dismiss();
                                    popisPitanjaLista.remove(pitanje);
                                    notifyDataSetChanged();
                                    pitanje.setObrisano(1);
                                    pitanje.updatePitanja(pitanje);

                                    //dodavanje this, 0 dodavanje - 1 azuriranje, 0 ne obrisano - 1 obrisano
                                    new AddUpdateDeletePitanja(getContext().getApplicationContext(), 1, pitanje.getObrisano().toString()).execute(Long.toString(pitanje.getIDpitanja()),pitanje.getPitanje(), Long.toString(pitanje.getIDpoglavlje()),Long.toString(PrijavljeniKorisnik.getInstance().getOdabraniRazred()));

                                    odgovori = new Select().from(Odgovor.class).where("IDpitanja==?",pitanje.getIDpitanja()).execute();

                                    for (Odgovor o:odgovori){
                                        o.setObrisano(1);
                                        o.updateOdgovor(o);
                                    }

                                }
                            });

                            btnAzurirajPit.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    Intent in=new Intent(getContext(),azuriraj.class);
                                    Azuriranje.getInstance().setIdPit(pitanje.getIDpitanja());
                                    getContext().startActivity(in);
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
        Pitanja pitanja = popisPitanjaLista.get(position);
        holder.pitanje.setText(pitanja.getPitanje());

        holder.pitanje.setTag(pitanja);

        return convertView;
    }
}




