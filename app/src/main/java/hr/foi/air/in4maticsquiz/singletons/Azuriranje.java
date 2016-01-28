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
    public Long zadnjeDodanoPoglavljeId;
    public Long zadnjeDodanoPitanjeId;
    public Long zadnjiDodaniOdgovorId;
    public ArrayList<Pitanja> pitanjaLista = new ArrayList<Pitanja>();
    public Long idPit;
    public Pitanja pitanje =new Pitanja();
    public ArrayList<Odgovor> odgovor = new ArrayList<Odgovor>();
    public Boolean zastavica=false;

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


    public Long getZadnjeDodanoPoglavljeId() {
        return zadnjeDodanoPoglavljeId;
    }

    public void setZadnjeDodanoPoglavljeId(Long zadnjeDodanoPoglavljeId) {
        this.zadnjeDodanoPoglavljeId = zadnjeDodanoPoglavljeId;
    }

    public Long getIdPit() {
        return idPit;
    }

    public void setIdPit(Long idPit) {
        this.idPit = idPit;
    }

    public Pitanja getPitanje() {
        return pitanje;
    }

    public void setPitanje(Pitanja pitanje) {
        this.pitanje = pitanje;
    }

    public Long getZadnjeDodanoPitanjeId() {
        return zadnjeDodanoPitanjeId;
    }

    public void setZadnjeDodanoPitanjeId(Long zadnjeDodanoPitanjeId) {
        this.zadnjeDodanoPitanjeId = zadnjeDodanoPitanjeId;
    }

    public Long getZadnjiDodaniOdgovorId() {
        return zadnjiDodaniOdgovorId;
    }

    public void setZadnjiDodaniOdgovorId(Long zadnjiDodaniOdgovorId) {
        this.zadnjiDodaniOdgovorId = zadnjiDodaniOdgovorId;
    }

    public Boolean getZastavica() {
        return zastavica;
    }

    public void setZastavica(Boolean zastavica) {
        this.zastavica = zastavica;
    }

    public ArrayList<Odgovor> getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(ArrayList<Odgovor> odgovor) {
        this.odgovor = odgovor;
    }
}
