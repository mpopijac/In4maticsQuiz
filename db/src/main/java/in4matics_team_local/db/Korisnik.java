package in4matics_team_local.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.widget.ModelAdapter;
import java.util.List;


/**
 * Created by Matija Popijač on 29.10.2015..
 */
@Table(name="korisnik")
public class Korisnik extends Model {
    @Column(name = "IDkorisnik")
    private long IDkorisnik;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "korisnickoIme")
    private String korisnickoIme;

    @Column(name = "lozinka")
    private String lozinka;

    @Column(name = "email")
    private String email;

    @Column(name = "IDtip")
    private long IDtip;

    @Column(name = "tip_korisnika")
    private Tip_korisnika tip_korisnika;



    public Korisnik(){
        super();
    }



    public Korisnik(long IDkorisnik, String ime, String prezime, String korisnickoIme, String lozinka, String email, long IDtip) {
        super();
        this.IDkorisnik = IDkorisnik;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.email = email;
        this.IDtip = IDtip;
    }

    public List<Rezultat> rezultati(){
        return getMany(Rezultat.class,"Rezultat");

    }

    public void updateKorisnik(Korisnik updatedKorisnik){
        this.IDkorisnik=updatedKorisnik.getIDkorisnik();
        this.ime=updatedKorisnik.getIme();
        this.prezime=updatedKorisnik.getPrezime();
        this.korisnickoIme=updatedKorisnik.getKorisnickoIme();
        this.lozinka=updatedKorisnik.getLozinka();
        this.email=updatedKorisnik.getEmail();
        this.IDtip=updatedKorisnik.getIDtip();
        this.save();
    }

    public long getIDkorisnik() {
        return IDkorisnik;
    }

    public void setIDkorisnik(long IDkorisnik) {
        this.IDkorisnik = IDkorisnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getIDtip() {
        return IDtip;
    }

    public void setIDtip(long IDtip) {
        this.IDtip = IDtip;
    }

    public Tip_korisnika getTip_korisnika() {
        return tip_korisnika;
    }

    public void setTip_korisnika(Tip_korisnika tip_korisnika) {
        this.tip_korisnika = tip_korisnika;
    }

}
