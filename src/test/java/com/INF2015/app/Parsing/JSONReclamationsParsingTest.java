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
public class JSONReclamationsParsingTest {
    
    public JSONReclamationsParsingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getJavaObjectDossier method, of class JSONReclamationsParsing.
     */
    @Test
    public void testGetJavaObjectDossier() {
        System.out.println("getJavaObjectDossier");
        JSONReclamationsParsing instance = null;
        JavaObjectDossier expResult = null;
        JavaObjectDossier result = instance.getJavaObjectDossier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
