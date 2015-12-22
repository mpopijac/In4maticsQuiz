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

/**
 * Created by Dario on 17.12.2015..
 */
public class CheckboxOdgovoriAdapter extends ArrayAdapter<Odgovor> {
    private List<Odgovor> odgovorArrayList;
    Context context;
    int layoutResourceId;
    public CheckboxOdgovoriAdapter(Context context, int layoutResourceId, List<Odgovor> resource) {
        super(context,layoutResourceId,resource);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.odgovorArrayList = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        CheckBox  check= (CheckBox) row.findViewById(R.id.checkBoxOdg);


        final String[] odgovori = new String[odgovorArrayList.size()];
        for (int i = 0; i < odgovorArrayList.size(); i++) {
            Odgovor tr = odgovorArrayList.get(i);
            odgovori[i] = tr.getNaziv();
        }
        check.setText(odgovori[position]);
        return row;

    }
}
