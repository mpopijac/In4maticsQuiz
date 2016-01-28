package hr.foi.air.in4maticsquiz;

import junit.framework.TestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;


/**
 * Created by Tea on 27.1.2016..
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>  {

    private Solo solo;


    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }


    public void testKrozProvjeriZnanje(){

        solo.waitForActivity(MainActivity.class, 5000);

        //Briše tekst iz polja
        solo.clearEditText(0);
        solo.clearEditText(1);

        //Unosi tekst
        solo.enterText(0, "tjarcov");
        solo.enterText(1, "123456");
        solo.clickOnView(solo.getView(R.id.btnPrijava));

        solo.clickOnView(solo.getView(R.id.peti_razred));

        solo.clickOnView(solo.getView(R.id.btnProvjeri));

        //Provjerava svako postavljeno pitanje koje je vrste te naspram toga odabire odgovor za kliknuti
        for(int i=0; i<10; i++){
            boolean lvExists1 = solo.waitForView(R.id.odgovorNaPitanje, 1,500);
            boolean lvExists2 = solo.waitForView(R.id.checkBoxOdg, 1, 500);
            boolean lvExists3 = solo.waitForView(R.id.radioButtonTocno,1,500);

            if(lvExists1){
                solo.clearEditText(0);
                solo.enterText(0, "proba");
                solo.clickOnView(solo.getView(R.id.btnSljedeceP));

            }
            if(lvExists2){
                solo.clickInList(1);
                solo.clickOnView(solo.getView(R.id.btnSljedeceP));
            }
            if(lvExists3){
                solo.clickOnView(solo.getView(R.id.radioButtonTocno));
                solo.clickOnView(solo.getView(R.id.btnSljedeceP));

            }
        }

        //Nakon završetka kviza provjerava je li se pojavio dijalog s bodovima i stišće na gumb ok
        solo.waitForDialogToOpen(1000);
        solo.clickOnButton("ok");

        //Provjerava nalazi li se u dobroj klasi nakon završetka kviza
        boolean lvExists = solo.waitForActivity(MenuActivity.class); //method one
        assertEquals("Nije u dobroj klasi", lvExists, true);


    }


}