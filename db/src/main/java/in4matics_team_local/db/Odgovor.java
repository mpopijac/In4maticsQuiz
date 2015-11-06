package in4matics_team_local.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.widget.ModelAdapter;
import java.util.List;

/**
 * Created by Matija Popijač on 29.10.2015..
 */

@Table(name="odgovor")
public class Odgovor extends Model{
    @Column(name = "IDodgovor")
    private long IDodgovor;

    @Column(name="naziv")
    private String naziv;

    @Column(name = "pitanje")
    private Pitanja pitanje;

    @Column (name = "tocan")
    private int tocan;

    public Odgovor(){
        super();
    }

    public Odgovor(long IDodgovor, String naziv, int tocan) {
        this.IDodgovor = IDodgovor;
        this.naziv = naziv;
        this.tocan = tocan;
    }


    public void updateOdgovor(Odgovor updateOdgovor){
        this.IDodgovor=updateOdgovor.getIDodgovor();
        this.naziv=updateOdgovor.getNaziv();
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
}
