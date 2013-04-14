/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.INF2015.app.Parsing;

import net.sf.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eric et bendjina
 */
public class JSONFileWriterTest {

    public JSONFileWriterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of writeJSONObbjectToFile method, of class JSONFileWriter.
     */
    @Test
    public void testWriteJSONObbjectToFile() {
        System.out.println("writeJSONObbjectToFile");
        String path = "";
        JSONObject object = null;
        JSONFileWriter.writeJSONObbjectToFile(path, object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
