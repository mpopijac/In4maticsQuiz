package in4matics_team.in4maticsquiz.loaders;

import android.app.Activity;
import android.widget.Toast;

import in4matics_team.in4maticsquiz.DataLoader;
import in4matics_team.in4maticsquiz.JsonAdapter;
import in4matics_team.in4maticsquiz.R;
import in4matics_team_local.db.Tip_korisnika;
import in4matics_team_local.ws.WebServiceAsyncTask;
import in4matics_team_local.ws.WebServiceParams;
import in4matics_team_local.ws.WebServiceResultHandler;

/**
 * Created by Matija Popijač on 10.11.2015..
 */

public class WebServiceDataLoader extends DataLoader{
    Activity activity = null;
    private boolean tip_korisnikaLoaded=false;
    private boolean korisnikLoaded=false;
    private boolean rezultatLoaded=false;
    private boolean razredLoaded=false;
    private boolean pitanjaLoaded=false;
    private boolean poglavljaLoaded=false;
    private boolean odgovoriLoaded=false;


    public void LoadData(Activity activity){
        this.activity=activity;

        WebServiceAsyncTask atTip_korisnika= new WebServiceAsyncTask();
        WebServiceParams paramsTip_korisnika = new WebServiceParams();
        paramsTip_korisnika.jsonParams = "";
        paramsTip_korisnika.methodName = "getAll";
        paramsTip_korisnika.serviceName = "";
        paramsTip_korisnika.targetAttribute="";
        paramsTip_korisnika.resultHandler=getAllTip_korisnikaHandler;
        atTip_korisnika.execute(new WebServiceParams[]{paramsTip_korisnika});

    }


    WebServiceResultHandler getAllTip_korisnikaHandler = new WebServiceResultHandler() {
        @Override
        public void handleResult(String result, boolean ok, long timestamp) {
            if(ok){
                try {
                    tip_korisnikas = JsonAdapter.getTip_korisnika(result);
                    for (Tip_korisnika t : tip_korisnikas){
                        t.save();
                    }
                    Toast.makeText(activity, R.string.data_loaded_tip_korisnika, Toast.LENGTH_SHORT).show();
                    tip_korisnikaLoaded=true;

                }catch (Exception e){
                    Toast.makeText(activity,R.string.data_error_tip_korisnika, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };



}
