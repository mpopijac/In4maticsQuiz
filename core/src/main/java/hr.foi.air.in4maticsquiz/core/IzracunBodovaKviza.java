package hr.foi.air.in4maticsquiz.core;

/**
 * Created by Tea on 22.12.2015..
 */

/**
 * funkcija služi za računanje ostvarenih bodova u kvizu
 prvi parametar koji prima je broj točnih odgovora, a drugi je vrijeme koje je preostalo za rješavanje kviza
 */
public class IzracunBodovaKviza {
    private String vrijeme;
    private String bodovi;

    public IzracunBodovaKviza(String vrijeme, String bodovi) {
        this.vrijeme = vrijeme;
        this.bodovi = bodovi;
    }

    public Integer bodoviFormula(){

        String[] vrij= vrijeme.split(":");
        int vrijemeUSec=0, min=0, sec=0;

        min= Integer.parseInt(vrij[0]);
        sec= Integer.parseInt(vrij[1]);

        vrijemeUSec= (min*60) + sec;

        int bod= Integer.parseInt(bodovi);
        int netocan= 10-bod;

        int ukupno= (vrijemeUSec * 10) + (bod * 100) - (netocan*20);

        if(bod==0){
            ukupno=0;
        }


        return ukupno;
    }




}



