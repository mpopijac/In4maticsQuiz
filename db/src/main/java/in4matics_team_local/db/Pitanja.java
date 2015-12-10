package in4matics_team_local.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.widget.ModelAdapter;
import java.util.List;

/**
 * Created by Matija Popijač on 29.10.2015..
 */

@Table(name ="pitanja")
public class Pitanja extends Model {
    @Column(name = "IDpitanja")
    private long IDpitanja;

    @Column(name = "pitanje")
    private String pitanje;

    @Column(name = "IDpoglavlje")
    private long IDpoglavlje;

    @Column(name = "IDrazred")
    private long IDrazred;

    @Column(name = "razred")
    private Razred razred;

    @Column (name = "obrisano")
    private Integer obrisano;

    @Column(name = "poglavlje")
    private Poglavlje poglavlje;



    public Pitanja(){
        super();
    }


    public Pitanja(long IDpitanja, String pitanje, long IDpoglavlje, long IDrazred, Integer obrisano) {
        super();
        this.IDpitanja = IDpitanja;
        this.pitanje = pitanje;
        this.IDpoglavlje = IDpoglavlje;
        this.IDrazred = IDrazred;
        this.obrisano = obrisano;

    }


    public void updatePitanja(Pitanja updatePitanja){
        this.IDpitanja=updatePitanja.getIDpitanja();
        this.pitanje=updatePitanja.getPitanje();
        this.IDpoglavlje=updatePitanja.getIDpoglavlje();
        this.IDrazred=updatePitanja.getIDrazred();
        this.obrisano=updatePitanja.getObrisano();
        this.save();
    }

    public long getIDpitanja() {
        return IDpitanja;
    }

    public void setIDpitanja(long IDpitanja) {
        this.IDpitanja = IDpitanja;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public long getIDpoglavlje() {
        return IDpoglavlje;
    }

    public void setIDpoglavlje(long IDpoglavlje) {
        this.IDpoglavlje = IDpoglavlje;
    }

    public long getIDrazred() {
        return IDrazred;
    }

    public void setIDrazred(long IDrazred) {
        this.IDrazred = IDrazred;
    }

    public Razred getRazred() {
        return razred;
    }

    public void setRazred(Razred razred) {
        this.razred = razred;
    }

    public Poglavlje getPoglavlje() {
        return poglavlje;
    }

    public void setPoglavlje(Poglavlje poglavlje) {
        this.poglavlje = poglavlje;
    }

    public Integer getObrisano() {
        return obrisano;
    }

    public void setObrisano(Integer obrisano) {
        this.obrisano = obrisano;
    }
}
