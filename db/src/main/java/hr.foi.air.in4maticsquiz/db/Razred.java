package hr.foi.air.in4maticsquiz.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.widget.ModelAdapter;
import java.util.List;

/**
 * Created by Matija Popijač on 29.10.2015..
 *
 * Entitet klase Razred predstavlja razred
 * jednom razredu pripada 0 ili više Pitanja i razred ima 0 ili više Rezultat-a
 * povezan sa klasom Pitanja
 * povezan sa klasom Rezultat
 *
 */

@Table(name = "razred")
public class Razred extends Model {
    @Column(name = "IDrazred")
    private long IDrazred;

    @Column(name = "naziv")
    private String naziv;

    @Column (name = "obrisano")
    private Integer obrisano;

    public Razred(){
        super();
    }

    public Razred(long IDrazred, String naziv, Integer obrisano) {
        super();
        this.IDrazred = IDrazred;
        this.naziv = naziv;
        this.obrisano = obrisano;
    }

    /**
     * Using an existing relationship, we can easily get list of instances in related Model class.
     * @return List of rezultati for this Razred.
     */
    public List<Rezultat> rezultati(){
        return getMany(Rezultat.class,"Rezultat");
    }

    /**
     * Using an existing relationship, we can easily get list of instances in related Model class.
     * @return List of pitanjas for this Razred.
     */
    public List<Pitanja> pitanjas(){
        return getMany(Pitanja.class,"Pitanja");
    }

    /**
     * Method changes data in current object and updates it in database as well.
     * @param updateRazred An instance of object with updated data.
     */
    public void updateRazred(Razred updateRazred){
        this.IDrazred=updateRazred.getIDrazred();
        this.naziv=updateRazred.getNaziv();
        this.obrisano=updateRazred.getObrisano();
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

    public Integer getObrisano() {
        return obrisano;
    }

    public void setObrisano(Integer obrisano) {
        this.obrisano = obrisano;
    }
}
