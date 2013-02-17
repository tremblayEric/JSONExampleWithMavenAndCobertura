/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author TeninBoré
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({Data.ContractListTest.class, Data.SoinsTest.class, Data.ContractsTest.class})
public class DataSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
}
