package hr.foi.air.in4maticsquiz;

import android.app.ListActivity;
import android.os.Bundle;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import hr.foi.air.in4maticsquiz.adapters.RangListaAdapter;

import hr.foi.air.in4maticsquiz.db.Korisnik;
import hr.foi.air.in4maticsquiz.db.Rezultat;
import hr.foi.air.in4maticsquiz.singletons.PrijavljeniKorisnik;

public class RangListeActivity extends ListActivity {

    private List<Rezultat> rezultatArrayList = new ArrayList<Rezultat>();
    private RangListaAdapter m_adapter;
    private String[] polja;
    private String[] polja1;
    List<rangLista> sortRangLista= new CopyOnWriteArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Integer odabraniRazred = PrijavljeniKorisnik.getInstance().getOdabraniRazred();

        rezultatArrayList = new Select().from(Rezultat.class).where("IDrazred==?", odabraniRazred).execute();

        for (Rezultat rez: rezultatArrayList) {

            Korisnik korisnickoIme;
            korisnickoIme = new Select().from(Korisnik.class).where("IDkorisnik==?", rez.getIDkorisnik()).executeSingle();
            String username = korisnickoIme.getKorisnickoIme();


            rangLista pojedinac= new rangLista();
            pojedinac.setBodovi2(rez.getBodovi());
            pojedinac.setKorisnici(username);
            pojedinac.setDatumi(rez.getDatum());

            sortRangLista.add(pojedinac);

        }

        for (rangLista pojedinac : sortRangLista) {
            for (rangLista pojedinac2 : sortRangLista) {
                if(!pojedinac.equals(pojedinac2)){
                    if (pojedinac2.getKorisnici().equals(pojedinac.getKorisnici())) {
                        if (pojedinac2.getBodovi2() >= pojedinac.getBodovi2()) {
                            sortRangLista.remove(pojedinac);

                        } else {
                            sortRangLista.remove(pojedinac2);
                        }
                    }
                }

            }
        }
        List<rangLista> sortRangLista2= new ArrayList<>(sortRangLista);
        Collections.sort(sortRangLista2);



        m_adapter = new RangListaAdapter(this, R.layout.rang_liste_fragment, sortRangLista2);

        setListAdapter(m_adapter);




    }


    public class rangLista implements Comparable {
        private String korisnici;
        private Long bodovi2;
        private String datumi;


        public String getKorisnici() {
            return korisnici;
        }

        public void setKorisnici(String korisnici) {
            this.korisnici = korisnici;
        }

        public Long getBodovi2() {
            return bodovi2;
        }

        public void setBodovi2(Long bodovi2) {
            this.bodovi2 = bodovi2;
        }

        public String getDatumi() {
            return datumi;
        }

        public void setDatumi(String datumi) {
            this.datumi = datumi;
        }


        @Override
        public int compareTo(Object another) {

            int compareQuantity = ((rangLista) another).getBodovi2().intValue();

            //ascending order
            return compareQuantity - this.bodovi2.intValue();

            //descending order
            //return compareQuantity - this.quantity;

        }
    }

}
