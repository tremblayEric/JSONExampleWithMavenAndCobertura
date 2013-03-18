/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1agile;

import ProjetAgile.ReclamationCalcul;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.w3c.dom.Document;

/**
 *
 * @author TeninBoré
 */
public class CalculReclamationTest {
    
    public CalculReclamationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of setDocument method, of class CalculReclamation.
     */
    @Test
    public void testSetDocument() {
        System.out.println("setDocument");
        Document document = null;
        ReclamationCalcul instance = null;
        instance.setDocument(document);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTypeDeContrat2 method, of class CalculReclamation.
     */
    @Test
    public void testGetTypeDeContrat2() {
        System.out.println("getTypeDeContrat2");
        ReclamationCalcul instance = null;
        List expResult = null;
        List result = instance.getTypeDeContrat2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListeDesReclamations method, of class CalculReclamation.
     */
    @Test
    public void testGetListeDesReclamations() {
        System.out.println("getListeDesReclamations");
        ReclamationCalcul instance = null;
        List expResult = null;
        List result = instance.getReclamationList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListeSoins method, of class CalculReclamation.
     */
    @Test
    public void testGetListeSoins() {
        System.out.println("getListeSoins");
        ReclamationCalcul instance = null;
        List expResult = null;
        List result = instance.getCareList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListeDate method, of class CalculReclamation.
     */
    @Test
    public void testGetListeDate() {
        System.out.println("getListeDate");
        ReclamationCalcul instance = null;
        List expResult = null;
        List result = instance.getDateList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListeMontant method, of class CalculReclamation.
     */
    @Test
    public void testGetListeMontant() {
        System.out.println("getListeMontant");
        ReclamationCalcul instance = null;
        List expResult = null;
        List result = instance.getAmountList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumeroClient method, of class CalculReclamation.
     */
    @Test
    public void testGetNumeroClient() {
        System.out.println("getNumeroClient");
        ReclamationCalcul instance = null;
        String expResult = "";
        String result = instance.getNumeroClient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMois method, of class CalculReclamation.
     */
    @Test
    public void testGetMois() {
        System.out.println("getMois");
        ReclamationCalcul instance = null;
        String expResult = "";
        String result = instance.getMonth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of effectuerListCalcul method, of class CalculReclamation.
     */
    @Test
    public void testEffectuerListCalcul() {
        System.out.println("effectuerListCalcul");
        ReclamationCalcul instance = null;
        List expResult = null;
        List result = instance.doCalculList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of effectuerCalcul method, of class CalculReclamation.
     */
    @Test
    public void testEffectuerCalcul() {
        System.out.println("effectuerCalcul");
        double valeur = 0.0;
        String numeroSoin = "";
        String contrat = "";
        ReclamationCalcul instance = null;
        double expResult = 0.0;
        double result = instance.doCalcul(valeur, numeroSoin, contrat);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
