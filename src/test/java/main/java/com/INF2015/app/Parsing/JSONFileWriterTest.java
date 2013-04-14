
package main.java.com.INF2015.app.Parsing;

import com.INF2015.app.Parsing.JSONFileWriter;
import net.sf.json.JSONObject;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;


public class JSONFileWriterTest {

    public JSONFileWriterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

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
