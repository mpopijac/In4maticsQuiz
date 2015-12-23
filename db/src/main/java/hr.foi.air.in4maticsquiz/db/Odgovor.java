package hr.foi.air.in4maticsquiz.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.widget.ModelAdapter;
import java.util.List;

/**
 * Created by Matija Popijač on 29.10.2015..
 *
 * Entitet klase predstavlja jedan od odgovora na pitanje
 * Odgovoru pripada samo jedno pitanje
 * povezan sa Pitanja
 *
 */

@Table(name="odgovor")
public class Odgovor extends Model{
    @Column(name = "IDodgovor")
    private long IDodgovor;

    @Column(name="naziv")
    private String naziv;

    @Column (name = "tocan")
    private int tocan;

    @Column (name="IDpitanja")
    private long IDpitanja;

    @Column (name = "obrisano")
    private Integer obrisano;

    @Column(name = "pitanje")
    private Pitanja pitanje;

    public Odgovor(){
        super();
    }


    public Odgovor(long IDodgovor, String naziv, int tocan, long IDpitanja, Integer obrisano) {
        this.IDodgovor = IDodgovor;
        this.naziv = naziv;
        this.tocan = tocan;
        this.IDpitanja = IDpitanja;
        this.obrisano = obrisano;
    }

    /**
     * Method changes data in current object and updates it in database as well.
     * @param updateOdgovor An instance of object with updated data.
     */
    public void updateOdgovor(Odgovor updateOdgovor){
        this.IDodgovor=updateOdgovor.getIDodgovor();
        this.naziv=updateOdgovor.getNaziv();
        this.tocan=updateOdgovor.getTocan();
        this.IDpitanja=updateOdgovor.getIDpitanja();
        this.obrisano=updateOdgovor.getObrisano();
        this.save();
    }



    public long getIDodgovor() {
        return IDodgovor;
    }

    public void setIDodgovor(long IDodgovor) {
        this.IDodgovor = IDodgovor;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Pitanja getPitanje() {
        return pitanje;
    }

    public void setPitanje(Pitanja pitanje) {
        this.pitanje = pitanje;
    }

    public int getTocan() {
        return tocan;
    }

    public void setTocan(int tocan) {
        this.tocan = tocan;
    }

    public long getIDpitanja() {
        return IDpitanja;
    }

    public void setIDpitanja(long IDpitanja) {
        this.IDpitanja = IDpitanja;
    }

    public Integer getObrisano() {
        return obrisano;
    }

    public void setObrisano(Integer obrisano) {
        this.obrisano = obrisano;
    }
}
