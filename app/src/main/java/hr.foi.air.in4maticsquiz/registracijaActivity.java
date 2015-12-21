package hr.foi.air.in4maticsquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.gumb_registracije:

                if ((etIme.length()!=0) &&(etPrezime.length()!=0) && (etKorisnickoIme.length()!=0) && (etEmail.length()!=0) && (etLozinka.length()!=0) && (etPotvrdaLozinke.length()!=0) ){

                    if (!(etLozinka.getText().toString().equals(etPotvrdaLozinke.getText().toString()))){
                        Toast.makeText(this, getString(R.string.registracijaLozinke), Toast.LENGTH_LONG).show();

                    }else {

                        boolean validacijaEmaila= validate(etEmail.getText().toString());

                        if (etLozinka.length() < 6 || !validacijaEmaila){
                            if(!validacijaEmaila){

                                Toast.makeText(this, getString(R.string.registracijaEmailGreska), Toast.LENGTH_LONG).show();
                            }
                            else {

                                Toast.makeText(this, getString(R.string.registracijaLozinkaPrekratka), Toast.LENGTH_LONG).show();
                            }
                        }
                        else {

                            if (PrijavljeniKorisnik.getInstance().isClicked() == false) {
                                Toast.makeText(this, getString(R.string.registracijaUspjesna), Toast.LENGTH_LONG).show();
                                new RegistrationActivity(this, "", "").execute(etIme.getText().toString(), etPrezime.getText().toString(), etKorisnickoIme.getText().toString(), etEmail.getText().toString(), etLozinka.getText().toString());
                                PrijavljeniKorisnik.getInstance().setClicked(true);

                            }
                        }
                    }
                }else {
                    Toast.makeText(this, getString(R.string.registracijaNijePopunjeno), Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}
