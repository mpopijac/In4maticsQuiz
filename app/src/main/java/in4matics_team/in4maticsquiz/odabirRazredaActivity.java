package in4matics_team.in4maticsquiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import in4matics_team.in4maticsquiz.R;

public class odabirRazredaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabir_razreda);


        CharSequence text;
        text = PrijavljeniKorisnik.getInstance().getIme() + PrijavljeniKorisnik.getInstance().getPrezime();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();


    }

}
