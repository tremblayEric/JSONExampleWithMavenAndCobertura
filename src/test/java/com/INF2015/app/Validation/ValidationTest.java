/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INF2015.app.Validation;

import java.util.List;
import net.sf.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eric et bendjina
 */
public class ValidationTest {
    
    public ValidationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of checkElementsFolder method, of class Validation.
     */
    @Test
    public void testCheckElementsFolder() throws Exception {
        System.out.println("checkElementsFolder");
        JSONObject folder = null;
        String element = "";
        Validation.checkElementsFolder(folder, element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkSoin method, of class Validation.
     */
    @Test
    public void testCheckSoin() throws Exception {
        System.out.println("checkSoin");
        String soin = "";
        String expResult = "";
        String result = Validation.checkSoin(soin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCode method, of class Validation.
     */
    @Test
    public void testCheckCode() throws Exception {
        System.out.println("checkCode");
        String code = "";
        String expResult = "";
        String result = Validation.checkCode(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validCareList2 method, of class Validation.
     */
    @Test
    public void testValidCareList2() {
        System.out.println("validCareList2");
        List expResult = null;
        List result = Validation.validCareList2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkFolder method, of class Validation.
     */
    @Test
    public void testCheckFolder() throws Exception {
        System.out.println("checkFolder");
        String numeroDossier = "";
        String expResult = "";
        String result = Validation.checkFolder(numeroDossier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInteger2 method, of class Validation.
     */
    @Test
    public void testIsInteger2() throws Exception {
        System.out.println("isInteger2");
        String numero = "";
        String message = "";
        Validation.isInteger2(numero, message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkDate method, of class Validation.
     */
    @Test
    public void testCheckDate() throws Exception {
        System.out.println("checkDate");
        String date = "";
        String month = "";
        String expResult = "";
        String result = Validation.checkDate(date, month);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMonth method, of class Validation.
     */
    @Test
    public void testCheckMonth() throws Exception {
        System.out.println("checkMonth");
        String month = "";
        String expResult = "";
        String result = Validation.checkMonth(month);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkDateIsValid method, of class Validation.
     */
    @Test
    public void testCheckDateIsValid() throws Exception {
        System.out.println("checkDateIsValid");
        String laDate = "";
        String type = "";
        String expResult = "";
        String result = Validation.checkDateIsValid(laDate, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkDateMonthCoherence method, of class Validation.
     */
    @Test
    public void testCheckDateMonthCoherence() throws Exception {
        System.out.println("checkDateMonthCoherence");
        String dateS = "";
        String mois = "";
        Validation.checkDateMonthCoherence(dateS, mois);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMontant method, of class Validation.
     */
    @Test
    public void testCheckMontant() throws Exception {
        System.out.println("checkMontant");
        String montant = "";
        String expResult = "";
        String result = Validation.checkMontant(montant);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
