/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INF2015.app.Parsing;

import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eric et bendjina
 */
public class JavaObjectDossierTest {
    
    public JavaObjectDossierTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setDossier method, of class JavaObjectDossier.
     */
    @Test
    public void testSetDossier() {
        System.out.println("setDossier");
        String dossier = "";
        JavaObjectDossier instance = new JavaObjectDossier();
        instance.setDossier(dossier);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMois method, of class JavaObjectDossier.
     */
    @Test
    public void testSetMois() {
        System.out.println("setMois");
        String mois = "";
        JavaObjectDossier instance = new JavaObjectDossier();
        instance.setMois(mois);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFolderNumber method, of class JavaObjectDossier.
     */
    @Test
    public void testGetFolderNumber() {
        System.out.println("getFolderNumber");
        JavaObjectDossier instance = new JavaObjectDossier();
        String expResult = "";
        String result = instance.getFolderNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFolderDate method, of class JavaObjectDossier.
     */
    @Test
    public void testGetFolderDate() {
        System.out.println("getFolderDate");
        JavaObjectDossier instance = new JavaObjectDossier();
        Date expResult = null;
        Date result = instance.getFolderDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFolderReclamationList method, of class JavaObjectDossier.
     */
    @Test
    public void testGetFolderReclamationList() {
        System.out.println("getFolderReclamationList");
        JavaObjectDossier instance = new JavaObjectDossier();
        List expResult = null;
        List result = instance.getFolderReclamationList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToReclamationList method, of class JavaObjectDossier.
     */
    @Test
    public void testAddToReclamationList() {
        System.out.println("addToReclamationList");
        JavaObjectReclamation reclamation = null;
        JavaObjectDossier instance = new JavaObjectDossier();
        instance.addToReclamationList(reclamation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayReclamationList method, of class JavaObjectDossier.
     */
    @Test
    public void testDisplayReclamationList() {
        System.out.println("displayReclamationList");
        JavaObjectDossier instance = new JavaObjectDossier();
        instance.displayReclamationList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
