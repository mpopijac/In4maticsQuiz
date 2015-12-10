package in4matics_team_local.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.widget.ModelAdapter;
import java.util.List;
import java.util.Date;

/**
 * Created by Matija Popijač on 29.10.2015..
 */

@Table(name = "rezultat")
public class Rezultat extends Model {
    @Column(name="IDrezultat")
    private long IDrezultat;

    @Column(name = "IDkorisnik")
    private long IDkorisnik;

    @Column(name = "IDrazred")
    private long IDrazred;

    @Column(name = "bodovi")
    private long bodovi;

    @Column(name = "datum")
    private String datum;

    @Column(name = "korisnik")
    private Korisnik korisnik;

    @Column(name = "razred")
    private Razred razred;

    public Rezultat(){
        super();
    }



     public Rezultat(long IDrezultat, long IDkorisnik, long IDrazred, long bodovi, String datum) {
        this.IDrezultat = IDrezultat;
        this.IDkorisnik = IDkorisnik;
        this.IDrazred = IDrazred;
        this.bodovi = bodovi;
        this.datum = datum;
    }

    public void updateRezultat(Rezultat updateRezultat){
        this.IDrezultat=updateRezultat.getIDrezultat();
        this.IDkorisnik=updateRezultat.getIDkorisnik();
        this.IDrazred=updateRezultat.getIDrezultat();
        this.bodovi=updateRezultat.getBodovi();
        this.datum=updateRezultat.getDatum();
        this.save();
    }

    public long getIDrezultat() {
        return IDrezultat;
    }

    public void setIDrezultat(long IDrezultat) {
        this.IDrezultat = IDrezultat;
    }

    public long getIDkorisnik() {
        return IDkorisnik;
    }

    public void setIDkorisnik(long IDkorisnik) {
        this.IDkorisnik = IDkorisnik;
    }

    public long getIDrazred() {
        return IDrazred;
    }

    public void setIDrazred(long IDrazred) {
        this.IDrazred = IDrazred;
    }

    public long getBodovi() {
        return bodovi;
    }

    public void setBodovi(long bodovi) {
        this.bodovi = bodovi;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Razred getRazred() {
        return razred;
    }

    public void setRazred(Razred razred) {
        this.razred = razred;
    }
}
