/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Save;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.w3c.dom.Document;
import ProjetAgile.ReclamationCalcul;

/**
 *
 * @author TeninBoré
 */
public class SauvegardeDocumentXmlTest {
    
    public SauvegardeDocumentXmlTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of saveToFile method, of class SauvegardeDocumentXml.
     */
    @Test
    public void testSaveToFile() throws Exception {
        System.out.println("saveToFile");
        Document document = null;
        String filePath = "";
        XMLDocumentSave instance = new XMLDocumentSave();
        instance.saveToFile(document);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveReclamation method, of class SauvegardeDocumentXml.
     */
    @Test
    public void testSaveReclamation() throws Exception {
        System.out.println("saveReclamation");
        String filePath = "";
        ReclamationCalcul reclamation = null;
        XMLDocumentSave instance = new XMLDocumentSave();
        instance.saveReclamation(reclamation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveSignalInvalidInputXML method, of class SauvegardeDocumentXml.
     */
    @Test
    public void testSaveSignalInvalidInputXML() throws Exception {
        System.out.println("saveSignalInvalidInputXML");
        String filePath = "";
        XMLDocumentSave instance = new XMLDocumentSave();
        instance.saveSignalInvalidInputXML(filePath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
