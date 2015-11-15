package in4matics_team.in4maticsquiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import in4matics_team.in4maticsquiz.R;

public class registracijaActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etIme, etPrezime, etKorisnickoIme, etEmail, etLozinka, etPotvrdaLozinke;
    private Button btRegistrairaj;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);

        etIme = (EditText)findViewById(R.id.unos_ime);
        etPrezime = (EditText)findViewById(R.id.unos_prezime);
        etKorisnickoIme = (EditText)findViewById(R.id.unos_korime);
        etEmail = (EditText)findViewById(R.id.unos_email);
        etLozinka = (EditText)findViewById(R.id.unos_lozinke);
        etPotvrdaLozinke = (EditText)findViewById(R.id.unos_lozinke_provjera);

        btRegistrairaj = (Button)findViewById(R.id.gumb_registracije);
        btRegistrairaj.setOnClickListener(this);
        PrijavljeniKorisnik.getInstance().setClicked(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gumb_registracije:
                if ((etIme.length()!=0) &&(etPrezime.length()!=0) && (etKorisnickoIme.length()!=0) && (etEmail.length()!=0) && (etLozinka.length()!=0) && (etPotvrdaLozinke.length()!=0) ){
                    if (!(etLozinka.getText().toString().equals(etPotvrdaLozinke.getText().toString()))){
                        Toast.makeText(this, "Lozinke nisu jednake!", Toast.LENGTH_LONG).show();
                    }else {
                        if (etLozinka.length() < 6){
                            Toast.makeText(this, "Lozinka je prekratka! Minimalno 6 znakova.", Toast.LENGTH_LONG).show();
                        }
                        else {
                            if (PrijavljeniKorisnik.getInstance().isClicked() == false) {
                                new RegistrationActivity(this, "", "").execute(etIme.getText().toString(), etPrezime.getText().toString(), etKorisnickoIme.getText().toString(), etEmail.getText().toString(), etLozinka.getText().toString());
                                PrijavljeniKorisnik.getInstance().setClicked(true);
                            }
                        }
                    }
                }else {
                    Toast.makeText(this, "Niste popunili sva polja!!!", Toast.LENGTH_LONG).show();
                }


                break;
        }
    }
}
