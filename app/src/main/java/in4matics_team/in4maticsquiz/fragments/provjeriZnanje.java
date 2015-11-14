package in4matics_team.in4maticsquiz.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in4matics_team.in4maticsquiz.DataLoader;
import in4matics_team.in4maticsquiz.NavigationItem;
import in4matics_team.in4maticsquiz.R;
import in4matics_team.in4maticsquiz.loaders.WebServiceDataLoader;

/**
 * Created by Anabel Li on 14.11.2015..
 */
public class provjeriZnanje extends Fragment implements NavigationItem {


    private int position;
    private String name = "Provjeri znanje";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.provjeri_znanje, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataLoader dl = new WebServiceDataLoader();
        dl.LoadData(getActivity());
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
