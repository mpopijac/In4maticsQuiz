package in4matics_team_local.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.widget.ModelAdapter;
import java.util.List;

/**
 * Created by Matija Popijač on 29.10.2015..
 */


@Table(name="tip_korisnika")
public class Tip_korisnika extends Model {
    @Column(name = "IDtip")
    private long IDtip;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "obrisano")
    private Integer obrisano;


    public Tip_korisnika(){

        super();
    }

    public Tip_korisnika(long IDtip, String naziv, Integer obrisano) {
        super();
        this.IDtip = IDtip;
        this.naziv = naziv;
        this.obrisano = obrisano;
    }


    public List<Korisnik> korisnici(){
        return getMany(Korisnik.class,"Tip_korisnika");

    }


    public void updateTip_korisnika(Tip_korisnika updateTip_korisnika){
        this.IDtip=updateTip_korisnika.getIDtip();
        this.naziv=updateTip_korisnika.getNaziv();
        this.obrisano=updateTip_korisnika.getObrisano();
        this.save();
    }

    public long getIDtip() {
        return IDtip;
    }

    public void setIDtip(long IDtip) {
        this.IDtip = IDtip;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getObrisano() {
        return obrisano;
    }

    public void setObrisano(Integer obrisano) {
        this.obrisano = obrisano;
    }
}
