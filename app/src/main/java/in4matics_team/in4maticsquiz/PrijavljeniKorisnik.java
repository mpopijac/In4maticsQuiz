package in4matics_team.in4maticsquiz;

/**
 * Created by Matija Popijač on 11.11.2015..
 */
public class PrijavljeniKorisnik {
    private static PrijavljeniKorisnik ourInstance = null;

    private long IDkorisnik;

    private String ime;

    private String prezime;

    private String korisnickoIme;

    private String email;

    private long IDtip;


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
}
