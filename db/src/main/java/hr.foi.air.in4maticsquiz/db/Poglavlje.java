package hr.foi.air.in4maticsquiz.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.widget.ModelAdapter;
import java.util.List;


/**
 * Created by Matija Popijač on 29.10.2015..
 *
 * Entitet kalse Poglavnje predstavlja poglavlje
 * jedno poglavlje može imati 0 ili više pitanja
 * povezano sa klasom Pitanja
 *
 */

@Table(name = "poglavlje")
public class Poglavlje extends Model {
    @Column(name = "IDpoglavlje")
    private long IDpoglavlje;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "ukljuceno")
    private Integer ukljuceno;

    @Column (name = "obrisano")
    private Integer obrisano;

    public Poglavlje(){
        super();
    }

    public Poglavlje(long IDpoglavlje, String naziv, Integer ukljuceno, Integer obrisano) {
        super();
        this.IDpoglavlje = IDpoglavlje;
        this.naziv = naziv;
        this.ukljuceno = ukljuceno;
        this.obrisano = obrisano;
    }


    /**
     * Using an existing relationship, we can easily get list of instances in related Model class.
     * @return List of pitanjas for this Poglavlje.
     */
    public List<Pitanja> pitanjas(){
        return getMany(Pitanja.class,"Pitanja");
    }

    /**
     * Method changes data in current object and updates it in database as well.
     * @param updatePoglavlje An instance of object with updated data.
     */
    public void updatePoglavlje(Poglavlje updatePoglavlje){
        this.IDpoglavlje=updatePoglavlje.getIDpoglavlje();
        this.naziv=updatePoglavlje.getNaziv();
        this.ukljuceno=updatePoglavlje.getUkljuceno();
        this.obrisano=updatePoglavlje.getObrisano();
        this.save();
    }


    public long getIDpoglavlje() {
        return IDpoglavlje;
    }

    public void setIDpoglavlje(long IDpoglavlje) {
        this.IDpoglavlje = IDpoglavlje;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getUkljuceno() {
        return ukljuceno;
    }

    public void setUkljuceno(Integer ukljuceno) {
        this.ukljuceno = ukljuceno;
    }

    public Integer getObrisano() {
        return obrisano;
    }

    public void setObrisano(Integer obrisano) {
        this.obrisano = obrisano;
    }

}
