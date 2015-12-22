package hr.foi.air.in4maticsquiz.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import hr.foi.air.in4maticsquiz.MainActivity;
import hr.foi.air.in4maticsquiz.core.NavigationItem;

/**
 * Created by Dario on 29.11.2015..
 */
public class OdjavaFragment extends Fragment implements NavigationItem {

    private int position;
    private String name = "Odjavi se";
    private Context context;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences korisnickiPodaci =OdjavaFragment.this.getActivity().getSharedPreferences("korisnickiPodaci", OdjavaFragment.this.getActivity().MODE_PRIVATE);
        SharedPreferences.Editor edit = korisnickiPodaci.edit();
        edit.clear();
        edit.commit();
        Intent intent = new Intent(OdjavaFragment.this.getActivity(), MainActivity.class);
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
