package in4matics_team.in4maticsquiz;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.List;

import in4matics_team_local.db.Odgovor;

/**
 * Created by Dario on 17.12.2015..
 */
public class CheckboxAdapterOdgovori extends ArrayAdapter<Odgovor> {
    private List<Odgovor> odgovorArrayList;
    Context context;
    public CheckboxAdapterOdgovori(Context context, List<Odgovor> resource) {
        super(context,R.layout.checkbox_layout,resource);
        this.context = context;
        this.odgovorArrayList = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.checkbox_layout, parent, false);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);

        String[] odgovori= new String[odgovorArrayList.size()];
        for(int i=0;i<odgovorArrayList.size();i++){
            Odgovor tr=odgovorArrayList.get(i);
            odgovori[i]=tr.getNaziv();
        }
        cb.setText(odgovori[position]);
        return convertView;
    }

}
