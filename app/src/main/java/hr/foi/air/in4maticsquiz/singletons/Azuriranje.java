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
    public Integer zadnjeDodanoPoglavljeId = 0;
    public ArrayList<Pitanja> pitanjaLista = new ArrayList<Pitanja>();
    public Long idPit;

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


    public ArrayList<Pitanja> getPitanjaLista() {
        return pitanjaLista;
    }

    public void setPitanjaLista(ArrayList<Pitanja> pitanjaLista) {
        this.pitanjaLista = pitanjaLista;
    }


    public Integer getZadnjeDodanoPoglavljeId() {
        return zadnjeDodanoPoglavljeId;
    }

    public void setZadnjeDodanoPoglavljeId(Integer zadnjeDodanoPoglavljeId) {
        this.zadnjeDodanoPoglavljeId = zadnjeDodanoPoglavljeId;
    }

    public Long getIdPit() {
        return idPit;
    }

    public void setIdPit(Long idPit) {
        this.idPit = idPit;
    }
}
