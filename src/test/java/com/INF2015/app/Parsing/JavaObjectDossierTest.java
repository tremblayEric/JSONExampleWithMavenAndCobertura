/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INF2015.app.Parsing;

import com.INF2015.app.ProjetAgile.Dollar;
import com.INF2015.app.ProjetAgile.RefundCalculation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;


public class JavaObjectDossierTest {

    JavaObjectReclamation reclamation;
    JavaObjectDossier instance;
    String soin;
    String code;
    Date date;
    String montant;
    SimpleDateFormat dateFormat;

    @Before
    public void setUp() throws Exception {
        instance = new JavaObjectDossier();
        instance.setDossier("A100323");
        instance.setMois("2013-01");
        soin = "175";
        code = "H1";
        montant = "400.00$";
        String mois = "2013-01";
        instance.setMois(mois);
        date = instance.getFolderDate();
        reclamation = new JavaObjectReclamation(soin, code, date, montant);
    }

    @After
    public void tearDown() throws Exception {
        instance = null;
        soin = null;
        code = null;
        montant = null;
        dateFormat = null;
        date = null;
        reclamation = null;
    }

    @Test
    public void testSetDossier() {
        String dossier = "A100323";
        instance.setDossier(dossier);
        String result = instance.getFolderNumber();
        assertEquals(dossier, result);

    }

    @Test
    public void testSetMois() {
        SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-MM");
        String mois = "2013-01";
        instance.setMois(mois);
        Date date = instance.getFolderDate();
        String result = dateFormatMois.format(date);
        assertEquals(mois, result);
    }
    
    @Test 
    public void testSetMoisFailure(){
        
        String invalidMonth = "invalideMonth";
        String expectedResult = "Erreur avec le mois";
        
        try{
            instance.setMois(invalidMonth);
        }catch(Exception e){
            assertEquals(expectedResult,e.getMessage());
        }
    }

    @Test
    public void testGetFolderNumber() {
        String expResult = "A100323";
        String result = instance.getFolderNumber();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFolderDate() {
        Date expResult = date;
        Date result = instance.getFolderDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFolderReclamationList() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
        List<JavaObjectReclamation> expResult = new ArrayList<JavaObjectReclamation>();
        Date date = dateFormat.parse("2013-01-11");
        JavaObjectReclamation reclamation1 = new JavaObjectReclamation("175", "H1", date, "40000");
        expResult.add(reclamation1);
        instance.addToReclamationList(reclamation1);

        date = dateFormat.parse("2013-01-14");
        JavaObjectReclamation reclamation2 = new JavaObjectReclamation("175", "C", date, "13000");
        expResult.add(reclamation2);
        instance.addToReclamationList(reclamation2);

        date = dateFormat.parse("2013-01-15");
        JavaObjectReclamation reclamation3 = new JavaObjectReclamation("175", "E", date, "13000");
        expResult.add(reclamation3);
        instance.addToReclamationList(reclamation3);

        date = dateFormat.parse("2013-01-14");
        JavaObjectReclamation reclamation4 = new JavaObjectReclamation("175", "H1", date, "13000");
        expResult.add(reclamation4);
        instance.addToReclamationList(reclamation4);
        List result = instance.getFolderReclamationList();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddToReclamationList() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
        List<JavaObjectReclamation> expResult = new ArrayList<JavaObjectReclamation>();
        Date date = dateFormat.parse("2013-01-11");
        JavaObjectReclamation addReclamation1 = new JavaObjectReclamation("175", "H1", date, "40000");
        expResult.add(addReclamation1);
        instance.addToReclamationList(addReclamation1);
        List result = instance.getFolderReclamationList();
        assertEquals(expResult, result);
    }

    @Test
    public void testDisplayReclamationList() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
        List<JavaObjectReclamation> expResult = new ArrayList<JavaObjectReclamation>();
        Date date = dateFormat.parse("2013-01-11");
        JavaObjectReclamation displayReclamation = new JavaObjectReclamation("175", "H1", date, "40000");
        expResult.add(displayReclamation);
        instance.addToReclamationList(displayReclamation);
        instance.displayReclamationList();
        List result = instance.getFolderReclamationList();
        assertEquals(expResult, result);
    }
    
    
}
