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
public class OdabirRazredaActivityTest extends ActivityInstrumentationTestCase2<MainActivity>  {

    private Solo solo;


    public OdabirRazredaActivityTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }


    public void testKrozAktivnosti(){

        solo.waitForActivity(MainActivity.class, 5000);

        //Pritisni gumb Prijava
        solo.clickOnView(solo.getView(R.id.btnPrijava));

        //Traži gumb za osmi razred, ako ga nađe, vraća true
        boolean lvExists = solo.waitForView(R.id.osmi_razred);
        assertEquals("Nema gumba za osmi razred", lvExists, true);

        //Pritisnu gumb 8
        solo.clickOnView(solo.getView(R.id.osmi_razred));

        //Provjerava piše li na gumbu btnProvjeri, "Provjeri znanje"
        Button detailsDesc = (Button) solo.getView(R.id.btnProvjeri);
        String expected = "Provjeri znanje";
        String real = detailsDesc.getText().toString();
        assertEquals("Description details are not correct", expected, real);


    }


}