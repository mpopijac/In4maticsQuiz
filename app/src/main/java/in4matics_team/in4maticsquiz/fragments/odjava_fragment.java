package in4matics_team.in4maticsquiz.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import in4matics_team.in4maticsquiz.MainActivity;
import in4matics_team.in4maticsquiz.NavigationItem;

/**
 * Created by Dario on 29.11.2015..
 */
public class odjava_fragment extends Fragment implements NavigationItem {

    private int position;
    private String name = "Odjavi se";
    private Context context;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences korisnickiPodaci =odjava_fragment.this.getActivity().getSharedPreferences("korisnickiPodaci", odjava_fragment.this.getActivity().MODE_PRIVATE);
        SharedPreferences.Editor edit = korisnickiPodaci.edit();
        edit.clear();
        edit.commit();
        Intent intent = new Intent(odjava_fragment.this.getActivity(), MainActivity.class);
        startActivity(intent);

    }



    @Override
    public String getItemName() {
        return name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }


    @Override
    public Fragment getFragment() {
        return this;
    }
}
