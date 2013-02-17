/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dom;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.w3c.dom.Document;

/**
 *
 * @author TeninBoré
 */
public class ParserXMLTest {
    
    public ParserXMLTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getDocumentXMLInput method, of class ParserXML.
     */
    @Test
    public void testGetDocumentXMLInput() {
        System.out.println("getDocumentXMLInput");
        ParserXML instance = null;
        Document expResult = null;
        Document result = instance.getDocumentXMLInput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
