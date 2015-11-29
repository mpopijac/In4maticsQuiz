package in4matics_team.in4maticsquiz;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Dario on 29.11.2015..
 */
public class odabir_razreda_fragment extends Fragment implements NavigationItem {

    private int position;
    private String name = "Odaberi razred";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.odabir_razreda_fragment, container, false);


    }

    @Override
    public void onStart() {
        super.onStart();

        // poruka sa imenom i prezimenom
        CharSequence text;
        text = PrijavljeniKorisnik.getInstance().getIme() +" "+ PrijavljeniKorisnik.getInstance().getPrezime();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this.getActivity(), text + getString(R.string.odabirRazredaUspjesnaPrijava), duration);
        toast.show();


        //odjava

        TextView odjava;

        PrijavljeniKorisnik currentUser = PrijavljeniKorisnik.getInstance();
        String struser = currentUser.getKorisnickoIme().toString();
        TextView txtuser = (TextView)getView().findViewById(R.id.imePrijavljenog);
        txtuser.setText(getString(R.string.odabirRazredaPrijava) +" "+ struser );

        odjava = (TextView)getView().findViewById(R.id.odjava);



        odjava.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences korisnickiPodaci =odabir_razreda_fragment.this.getActivity().getSharedPreferences("korisnickiPodaci", odabir_razreda_fragment.this.getActivity().MODE_PRIVATE);
                SharedPreferences.Editor edit = korisnickiPodaci.edit();
                edit.clear();
                edit.commit();
                Intent intent = new Intent(odabir_razreda_fragment.this.getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });





        Button peti = (Button)getView().findViewById(R.id.peti_razred);

        peti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PrijavljeniKorisnik.getInstance().setOdabraniRazred(5);
                Intent intent = new Intent(odabir_razreda_fragment.this.getActivity(), menuActivity.class);
                startActivity(intent);



            }


        });

        Button sesti = (Button)getView().findViewById(R.id.sesti_razred);

        sesti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrijavljeniKorisnik.getInstance().setOdabraniRazred(6);
                Intent intentR = new Intent(odabir_razreda_fragment.this.getActivity(),menuActivity.class);
                startActivity(intentR);

            }
        });

        Button sedmi = (Button)getView().findViewById(R.id.sedmi_razred);

        sedmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrijavljeniKorisnik.getInstance().setOdabraniRazred(7);
                Intent intentR = new Intent(odabir_razreda_fragment.this.getActivity(), menuActivity.class);
                startActivity(intentR);

            }
        });

        Button osmi = (Button)getView().findViewById(R.id.osmi_razred);

        osmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrijavljeniKorisnik.getInstance().setOdabraniRazred(8);
                Intent intentR = new Intent(odabir_razreda_fragment.this.getActivity(), menuActivity.class);
                startActivity(intentR);

            }
        });
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

