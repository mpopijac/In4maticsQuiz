package in4matics_team.in4maticsquiz.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import in4matics_team.in4maticsquiz.R;

/**
 * Created by Matija Popijač on 26.11.2015..
 */
public class AppPreferencesFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.app_preferences);
    }
}