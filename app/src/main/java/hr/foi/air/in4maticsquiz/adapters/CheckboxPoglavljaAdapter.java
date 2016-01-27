package hr.foi.air.in4maticsquiz.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.List;

import hr.foi.air.in4maticsquiz.R;
import hr.foi.air.in4maticsquiz.db.Odgovor;
import hr.foi.air.in4maticsquiz.db.Poglavlje;

/**
 * Created by Dario on 27.1.2016..
 */
public class CheckboxPoglavljaAdapter extends ArrayAdapter<Poglavlje> {
    private List<Poglavlje> poglavljeArrayList;
    Context context;
    int layoutResourceId;
    public CheckboxPoglavljaAdapter(Context context, int layoutResourceId, List<Poglavlje> resource) {
        super(context,layoutResourceId,resource);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.poglavljeArrayList = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        CheckBox check= (CheckBox) row.findViewById(R.id.checkBoxOdg);


        final String[] poglavlja = new String[poglavljeArrayList.size()];
        for (int i = 0; i < poglavljeArrayList.size(); i++) {
            Poglavlje tr = poglavljeArrayList.get(i);
            poglavlja[i] = tr.getNaziv();
        }
        //postavi text odgovora u checkbox
        check.setText(poglavlja[position]);
        return row;

    }
}
