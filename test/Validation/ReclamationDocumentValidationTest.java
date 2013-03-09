/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author TeninBoré
 */
public class ReclamationDocumentValidationTest {
    
    public ReclamationDocumentValidationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of validerReclamation method, of class ReclamationDocumentValidation.
     */
    @Test
    public void testValiderReclamation() {
        System.out.println("validerReclamation");
        ReclamationDocumentValidation instance = null;
        boolean expResult = false;
        boolean result = instance.reclamationValidation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
