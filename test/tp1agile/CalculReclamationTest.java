/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1agile;

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
        CalculReclamation instance = null;
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
        CalculReclamation instance = null;
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
        CalculReclamation instance = null;
        List expResult = null;
        List result = instance.getListeDesReclamations();
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
        CalculReclamation instance = null;
        List expResult = null;
        List result = instance.getListeSoins();
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
        CalculReclamation instance = null;
        List expResult = null;
        List result = instance.getListeDate();
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
        CalculReclamation instance = null;
        List expResult = null;
        List result = instance.getListeMontant();
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
        CalculReclamation instance = null;
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
        CalculReclamation instance = null;
        String expResult = "";
        String result = instance.getMois();
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
        CalculReclamation instance = null;
        List expResult = null;
        List result = instance.effectuerListCalcul();
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
        CalculReclamation instance = null;
        double expResult = 0.0;
        double result = instance.effectuerCalcul(valeur, numeroSoin, contrat);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
