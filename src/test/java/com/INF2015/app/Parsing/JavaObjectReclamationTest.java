/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INF2015.app.Parsing;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Sayon CissÃ©
 */
public class JavaObjectReclamationTest {
    
    JavaObjectReclamation instance;
    JavaObjectDossier dossier;
    String soin;
    String code;
    Date date;
    String montant;
    SimpleDateFormat dateFormat;
    
    @Before
    public void setUp() throws Exception{  
        dossier = new JavaObjectDossier();
        dossier.setDossier("A100323");
        dossier.setMois("2013-01");
        soin = "175";
        code = "H1";
        montant = "400.00$";
        String mois = "2013-01";
        dossier.setMois(mois);
        date = dossier.getFolderDate();
        instance = new JavaObjectReclamation(soin, code, date, montant);
    }

    @After
    public void tearDown() throws Exception{ 
        instance = null;
        soin = null;
        code = null;
        montant = null;
        dateFormat = null;
        date = null;
        dossier = null;
    }

    @Test
    public void testGetSoin() {
        String expResult = "175";
        String result = instance.getSoin();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetCode() {
        String expResult = "H1";
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetDate() {
        SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-MM-DD");
        String mois = "2013-01-01";
        Date date = instance.getDate();
        String result = dateFormatMois.format(date);
        assertEquals(mois, result);
    }

    @Test
    public void testGetMontant() {
        int expResult = 40000;
        int result = instance.getMontant();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetMontant() {
        int montant = 400000;
        instance.setMontant(montant);
        int result = instance.getMontant();
        assertEquals(montant, result);
    }
}
