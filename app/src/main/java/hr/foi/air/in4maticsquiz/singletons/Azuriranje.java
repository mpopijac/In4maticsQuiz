package hr.foi.air.in4maticsquiz.singletons;

import java.util.ArrayList;

import hr.foi.air.in4maticsquiz.db.Odgovor;
import hr.foi.air.in4maticsquiz.db.Pitanja;
import hr.foi.air.in4maticsquiz.db.Poglavlje;

/**
 * Created by Anabel Li on 26.1.2016..
 */
public class Azuriranje {

    private static Azuriranje ourInstance = null;

    public ArrayList<Poglavlje> poglavljeLista = new ArrayList<Poglavlje>();

    public static Azuriranje getInstance() {
        if (ourInstance == null){
            ourInstance = new Azuriranje();
        }
        return ourInstance;
    }


    private Azuriranje() {

    }


    public ArrayList<Poglavlje> getPoglavljeLista() {
        return poglavljeLista;
    }

    public void setPoglavljeLista(ArrayList<Poglavlje> poglavljeLista) {
        this.poglavljeLista = poglavljeLista;
    }

}
