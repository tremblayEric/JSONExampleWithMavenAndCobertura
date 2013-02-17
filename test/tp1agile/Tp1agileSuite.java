/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1agile;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author TeninBoré
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({tp1agile.TP1AgileTest.class, tp1agile.CalculReclamationTest.class})
public class Tp1agileSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
}
