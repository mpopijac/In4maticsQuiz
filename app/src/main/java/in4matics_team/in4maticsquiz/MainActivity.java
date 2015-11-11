package in4matics_team.in4maticsquiz;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText korisnickoIme, lozinka;
    private EditText idKorisnikaEd;
    private String idKorisnika;
    private Button bLogin;
    private TextView bRegistriraj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActiveAndroid.initialize(this);
        setContentView(R.layout.activity_main);

        korisnickoIme = (EditText)findViewById(R.id.korisnickoIme);
        lozinka = (EditText)findViewById(R.id.lozinka);
        bLogin = (Button)findViewById(R.id.btnPrijava);
        bRegistriraj = (TextView)findViewById(R.id.btnRegistriraj);

        bLogin.setOnClickListener(this);
        bRegistriraj.setOnClickListener(this);


/*

        DataLoader dataLoader = new WebServiceDataLoader();
        dataLoader.LoadData(this);
*/
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnPrijava:

                String korisnickoImeSt = korisnickoIme.getText().toString();
                String lozinkaSt = lozinka.getText().toString();

                new SigninActivity(this,lozinka,"Prava").execute(korisnickoImeSt, lozinkaSt);

                break;
            case R.id.btnRegistriraj:

                Intent intent = new Intent(MainActivity.this,registracijaActivity.class);
                startActivity(intent);

                break;
        }
    }

}
