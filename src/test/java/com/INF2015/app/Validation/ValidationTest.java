
package com.INF2015.app.Validation;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;


public class ValidationTest {
    
    public ValidationTest() {
    }
    
    /**
     * Test of checkElementsFolder method, of class Validation.
     */
    @Ignore
    public void testCheckElementsFolder() throws Exception {
        System.out.println("checkElementsFolder");
        JSONObject folder = null;
        String element = "";
        Validation.checkElementsFolder(folder, element);
        
    }

    /**
     * Test of checkSoin method, of class Validation.
     */
    @Test
    public void testCheckSoin() throws Exception {
        
        String soin = "0";
        String validCare = Validation.checkSoin(soin );
        soin = "666";
        try{
            Validation.checkSoin(soin );
        }catch(Exception e){
            assertEquals(e.getMessage() ,ErrorMessage.MESSAGE_ERROR_SOIN);
        }
    }

    /**
     * Test of checkCode method, of class Validation.
     */
    @Test
    public void testCheckCode() throws Exception {
        
        String code = "A";
        String result = Validation.checkCode(code);
        assertEquals(code,result);
        
        code = "E1";
        result = Validation.checkCode(code);
        assertEquals(code,result);
        
        code = "EE";
        try{
            result = Validation.checkCode(code);
        }catch(Exception e){
            assertEquals(e.getMessage(),ErrorMessage.MESSAGE_ERROR_CODE);
        }
        
        code = "";
        try{
            result = Validation.checkCode(code);
        }catch(Exception e){
            assertEquals(e.getMessage(),ErrorMessage.MESSAGE_ERROR_CODE);
        }
        
    }

    /**
     * Test of validCareList2 method, of class Validation.
     */
    @Ignore
    public void testValidCareList2() {
        
        
       
    }

    /**
     * Test of checkFolder method, of class Validation.
     */
    @Test
    public void testCheckFolder() throws Exception {
       String noDossier = "A234567";
       String expresult = Validation.checkFolder(noDossier);
       assertEquals(expresult,noDossier);
       
       noDossier = "G123456";
       try{
           Validation.checkFolder(noDossier);
       }catch(Exception e){
           assertEquals(e.getMessage(),ErrorMessage.MESSAGE_ERROR_FOLDER);
       }
       
       noDossier = "Aqwerty";
       try{
          Validation.checkFolder(noDossier); 
       }catch(Exception e){
           assertEquals(e.getMessage(),ErrorMessage.MESSAGE_ERROR_FOLDER );
       }
    }

    /**
     * Test of isInteger2 method, of class Validation.
     */
    @Test
    public void testIsInteger2() throws Exception {
        String isNotAnInteger= "a";
        String expMessage = "ERROR";
        try{
            Validation.isInteger2(isNotAnInteger, expMessage);
        }catch(Exception e){
            assertEquals(expMessage,e.getMessage());
        }
        
    }

    /**
     * Test of checkDate method, of class Validation.
     */
    @Test
    public void testCheckDate() throws Exception {
       
        String date = "2013-03-02";
        String month = "2013-03";
        String expResult = Validation.checkDate(date, month);
        
        assertEquals(expResult,date);
        date = "invalide date";
       
    }

    /**
     * Test of checkMonth method, of class Validation.
     */
    @Test
    public void testCheckMonth() throws Exception {
        String expResult = "2013-03";
        String result = Validation.checkMonth(expResult);
        
        assertEquals(expResult,result);
        
    }

    /**
     * Test of checkDateIsValid method, of class Validation.
     */
    @Test
    public void testCheckDateIsValid() throws Exception {
        
        String expResult = "2013-03-02";
        String type = "date";
        String result = Validation.checkDateIsValid(expResult, type);
        
        assertEquals(expResult,result);
        
        type= "invalidType";
        expResult = "invalideDate";
        try{
            Validation.checkDateIsValid(expResult, type);
        }catch(Exception e){
            assertEquals(e.getMessage(),ErrorMessage.MESSAGE_ERROR_DATE);
        }
        
    }

    /**
     * Test of checkDateMonthCoherence method, of class Validation.
     */
    @Test
    public void testCheckDateMonthCoherence() throws Exception {
        
        String date = "2013-03-02";
        String month = "2013-04";
        
        try{
           Validation.checkDateMonthCoherence(date, month); 
        }catch(Exception e){
            assertEquals(e.getMessage(),ErrorMessage.MESSAGE_ERROR_DATE);
        }
        
    }

    /**
     * Test of checkMontant method, of class Validation.
     */
    @Test
    public void testCheckMontant() throws Exception {
        String expResult = "";
        String result = "";
        String montant = "";
        try{
            Validation.checkMontant(montant);
        }catch(Exception e){
            assertEquals(e.getMessage(),ErrorMessage.MESSAGE_ERROR_MONTANT);
        }
        
        montant = "19,99$";
        expResult = "19.99$";
        result = Validation.checkMontant(montant);
        assertEquals(expResult,result);
        
        montant = "notADouble";
        try{
            Validation.checkMontant(montant);
        }catch(Exception e){
            assertEquals(e.getMessage(),ErrorMessage.MESSAGE_ERROR_MONTANT);
        }
        
        montant = "19.99";
        try{
            Validation.checkMontant(montant);
        }catch(Exception e){
            assertEquals(e.getMessage(),ErrorMessage.MESSAGE_ERROR_SIGNE_DOLLAR);
        }
        
        
        
        
        
    }
    
    
}
