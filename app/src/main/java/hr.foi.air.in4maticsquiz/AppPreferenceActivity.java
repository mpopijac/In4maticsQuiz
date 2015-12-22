package hr.foi.air.in4maticsquiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import hr.foi.air.in4maticsquiz.R;
import hr.foi.air.in4maticsquiz.fragments.AppPreferencesFragment;

public class AppPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_preference);

        // replace the entire content of the activity with fragments' layout
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AppPreferencesFragment())
                .commit();
    }
}
