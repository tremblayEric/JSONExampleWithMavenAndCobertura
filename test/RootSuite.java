/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author TeninBoré
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({XmlFiles.XmlFilesSuite.class, Validation.ValidationSuite.class, Data.DataSuite.class, tp1agile.Tp1agileSuite.class, Save.SaveSuite.class, Dom.DomSuite.class})
public class RootSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
}
