package in4matics_team.in4maticsquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import in4matics_team.in4maticsquiz.fragments.provjeraZnanja;

public class menuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Button provjeri = (Button)findViewById(R.id.btnProvjeri);

        provjeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentR = new Intent(menuActivity.this, provjeriZnanje.class);
                startActivity(intentR);

            }
        });

        Button lista= (Button)findViewById(R.id.btnLista);

       lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentR = new Intent(menuActivity.this, rangListeActivity.class);
                startActivity(intentR);

            }
        });
    }


}
