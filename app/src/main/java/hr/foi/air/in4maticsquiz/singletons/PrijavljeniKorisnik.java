package hr.foi.air.in4maticsquiz.singletons;

/**
 * Created by Matija Popijač on 11.11.2015..
 *
 * Singleton klasa PrijavljeniKorisnik služi za spremanje podataka o korisniku i njegovi akcijama
 */
public class PrijavljeniKorisnik {
    private static PrijavljeniKorisnik ourInstance = null;

    private long IDkorisnik;

    private String ime;

    private String prezime;

    private String korisnickoIme;

    private String email;

    private long IDtip;

    private boolean clicked;

    private int odabraniRazred;

    public int getOdabraniRazred() {
        return odabraniRazred;
    }

    public void setOdabraniRazred(int odabraniRazred) {
        this.odabraniRazred = odabraniRazred;
    }




    public static PrijavljeniKorisnik getInstance() {
        if (ourInstance == null){
            ourInstance = new PrijavljeniKorisnik();
        }
        return ourInstance;
    }


    private PrijavljeniKorisnik() {

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

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
