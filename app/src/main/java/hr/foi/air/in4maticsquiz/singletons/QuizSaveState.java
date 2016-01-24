package hr.foi.air.in4maticsquiz.singletons;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.in4maticsquiz.db.Pitanja;

/**
 * Created by Matija Popijač on 26.12.2015..
 *
 * Klasa koja služi za spremanje podataka kod testa prilikom promjene orjentacije ekrana.
 *
 */
public class QuizSaveState {
    private static QuizSaveState ourInstance = new QuizSaveState();

    private List<Pitanja> pitanja = new ArrayList<Pitanja>();

    private Integer IDpitanja;

    private Pitanja trenutno;

    private Integer trajanjeTesta;

    private Integer zadnjePitanje;

    private Integer ukupnoBodova;

    private Integer monitor = 0;

    public static QuizSaveState getInstance() {
        if (ourInstance == null){
            ourInstance = new QuizSaveState();
        }
        return ourInstance;
    }

    private QuizSaveState() {
    }

    public List<Pitanja> getPitanja() {
        return pitanja;
    }

    public void setPitanja(List<Pitanja> pitanja) {
        this.pitanja = pitanja;
    }

    public Integer getIDpitanja() {
        return IDpitanja;
    }

    public void setIDpitanja(Integer IDpitanja) {
        this.IDpitanja = IDpitanja;
    }

    public Pitanja getTrenutno() {
        return trenutno;
    }

    public void setTrenutno(Pitanja trenutno) {
        this.trenutno = trenutno;
    }

    public Integer getTrajanjeTesta() {
        return trajanjeTesta;
    }

    public void setTrajanjeTesta(Integer trajanjeTesta) {
        this.trajanjeTesta = trajanjeTesta;
    }

    public Integer getZadnjePitanje() {
        return zadnjePitanje;
    }

    public void setZadnjePitanje(Integer zadnjePitanje) {
        this.zadnjePitanje = zadnjePitanje;
    }

    public Integer getUkupnoBodova() {
        return ukupnoBodova;
    }

    public void setUkupnoBodova(Integer ukupnoBodova) {
        this.ukupnoBodova = ukupnoBodova;
    }

    public Integer getMonitor() {
        return monitor;
    }

    public void setMonitor(Integer monitor) {
        this.monitor = monitor;
    }
}
