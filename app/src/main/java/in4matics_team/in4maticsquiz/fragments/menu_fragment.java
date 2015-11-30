package in4matics_team.in4maticsquiz.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import in4matics_team.in4maticsquiz.NavigationItem;
import in4matics_team.in4maticsquiz.R;
import in4matics_team.in4maticsquiz.provjeriZnanje;
import in4matics_team.in4maticsquiz.rangListeActivity;

/**
 * Created by Dario on 29.11.2015..
 */
public class menu_fragment extends Fragment implements NavigationItem {

    private int position;
    private String name = "Menu";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.activity_menu, container, false);


    }

    @Override
    public void onStart() {
        super.onStart();

        Button provjeri = (Button) getView().findViewById(R.id.btnProvjeri);

        provjeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentR = new Intent(menu_fragment.this.getActivity(), provjeriZnanje.class);
                startActivity(intentR);

            }
        });

        Button lista = (Button)getView().findViewById(R.id.btnLista);

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentR = new Intent(menu_fragment.this.getActivity(), rangListeActivity.class);
                startActivity(intentR);

            }
        });

    }

    @Override
    public String getItemName() {
        return null;
    }

    @Override
    public int getPosition() {
        return 0;
    }

    @Override
    public void setPosition(int position) {

    }

    @Override
    public Fragment getFragment() {
        return null;
    }
}
