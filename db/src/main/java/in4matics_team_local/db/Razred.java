package in4matics_team_local.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.widget.ModelAdapter;
import java.util.List;

/**
 * Created by Matija Popijač on 29.10.2015..
 */

@Table(name = "razred")
public class Razred extends Model {
    @Column(name = "IDrazred")
    private long IDrazred;

    @Column(name = "naziv")
    private String naziv;

    public Razred(){
        super();
    }

    public Razred(long IDrazred, String naziv) {
        super();
        this.IDrazred = IDrazred;
        this.naziv = naziv;
    }

    public List<Rezultat> rezultati(){
        return getMany(Rezultat.class,"Rezultat");
    }

    public List<Pitanja> pitanjas(){
        return getMany(Pitanja.class,"Pitanja");
    }

    public void updateRazred(Razred updateRazred){
        this.IDrazred=updateRazred.getIDrazred();
        this.naziv=updateRazred.getNaziv();
        this.save();
    }

    public long getIDrazred() {
        return IDrazred;
    }

    public void setIDrazred(long IDrazred) {
        this.IDrazred = IDrazred;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
