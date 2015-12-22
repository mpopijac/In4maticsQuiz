package hr.foi.air.in4maticsquiz;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements  View.OnClickListener {

    private Toolbar mToolbar;
    private Context context;
    private Button provjeri, lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        provjeri = (Button)findViewById(R.id.btnProvjeri);
        provjeri.setOnClickListener(this);

        lista = (Button)findViewById(R.id.btnLista);
        lista.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnProvjeri:
                intent= new Intent(MenuActivity.this, ProvjeriZnanjeActivity.class);
                startActivity(intent);
                break;

            case R.id.btnLista:
                intent=new Intent(MenuActivity.this, RangListeActivity.class);
                startActivity(intent);
                break;
        }
    }




    @Override

    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem actionViewItem = menu.findItem(R.id.miActionButton);
        View v = MenuItemCompat.getActionView(actionViewItem);
        ImageButton b = (ImageButton) v.findViewById(R.id.btnCustomAction);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences korisnickiPodaci = MenuActivity.this.getSharedPreferences("korisnickiPodaci", MenuActivity.this.MODE_PRIVATE);
                SharedPreferences.Editor edit = korisnickiPodaci.edit();
                edit.clear();
                edit.commit();
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
        return super.onPrepareOptionsMenu(menu);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}