/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INF2015.app.Parsing;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eric et bendjina
 */
public class JSONFileReaderTest {
    
    public JSONFileReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of loadFileIntoString method, of class JSONFileReader.
     */
    @Test
    public void testLoadFileIntoString() throws Exception {
        System.out.println("loadFileIntoString");
        String filePath = "";
        String expResult = "";
        String result = JSONFileReader.loadFileIntoString(filePath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
