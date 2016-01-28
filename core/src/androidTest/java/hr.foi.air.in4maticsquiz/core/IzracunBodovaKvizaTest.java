package hr.foi.air.in4maticsquiz.core;

import junit.framework.TestCase;

/**
 * Created by Tea on 27.1.2016..
 */
public class IzracunBodovaKvizaTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    public void testBodoviFormula() throws Exception {

        IzracunBodovaKviza iz= new IzracunBodovaKviza("08:00", "10");

        Integer proba= iz.bodoviFormula();

        System.out.println(proba);

        assertNotNull(proba);



    }
}