package com.INF2015.app.Validation;

import java.util.List;
import net.sf.json.JSONObject;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

public class ValidationTest {

    public ValidationTest() {
    }

    @Ignore
    public void testCheckElementsFolder() throws Exception {
        System.out.println("checkElementsFolder");
        JSONObject folder = null;
        String element = "";
        Validation.checkElementsFolder(folder, element);

    }

    @Test
    public void testCheckSoin() throws Exception {

        String soin = "0";
        String validCare = Validation.checkSoin(soin);
        soin = "666";
        try {
            Validation.checkSoin(soin);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_SOIN);
        }
    }

    @Test
    public void testCheckCodeAValid() throws Exception {

        String code = "A";
        String result = Validation.checkCode(code);
        assertEquals(code, result);

    }

    @Test
    public void testCheckCodeE1Valid() throws Exception {

        String code = "E1";
        String result = Validation.checkCode(code);
        assertEquals(code, result);

    }

    @Test
    public void testCheckCodeSecondCharNotAnInteger() throws Exception {

        String code = "EE";
        String result = "";

        try {
            result = Validation.checkCode(code);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_CODE);
        }

    }

    @Test
    public void testCheckCodeInvalid() throws Exception {

        String code = "";
        String result = "";

        try {
            result = Validation.checkCode(code);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_CODE);
        }

    }

    @Test
    public void testValidCareList2() {

        boolean expResult = true;
        boolean result = false;
        List<String> validCareList = Validation.validCareList2();

        for (int i = 0; i < validCareList.size(); ++i) {

            switch (i) {
                case 0:
                    result = (validCareList.get(i).compareTo("0") == 0);
                    break;
                case 1:
                    result = (validCareList.get(i).compareTo("100") == 0);
                    break;
                case 2:
                    result = (validCareList.get(i).compareTo("150") == 0);
                    break;
                case 3:
                    result = (validCareList.get(i).compareTo("175") == 0);
                    break;
                case 4:
                    result = (validCareList.get(i).compareTo("200") == 0);
                    break;
                case 5:
                    result = (validCareList.get(i).compareTo("400") == 0);
                    break;
                case 6:
                    result = (validCareList.get(i).compareTo("500") == 0);
                    break;
                case 7:
                    result = (validCareList.get(i).compareTo("600") == 0);
                    break;
                case 8:
                    result = (validCareList.get(i).compareTo("700") == 0);
                    break;
            }
        }
        assertTrue(result);

    }

    @Test
    public void testCheckFolderValid() throws Exception {

        String noDossier = "A234567";
        String expresult = Validation.checkFolder(noDossier);
        assertEquals(expresult, noDossier);

    }

    @Test
    public void testCheckFolderInvalidContract() throws Exception {

        String noDossier = "G123456";

        try {
            Validation.checkFolder(noDossier);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_FOLDER);
        }

    }

    @Test
    public void testCheckFolderInvalNumberId() throws Exception {

        String noDossier = "Aqwerty";

        try {
            Validation.checkFolder(noDossier);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_FOLDER);
        }
    }

    @Test
    public void testIsInteger2() throws Exception {
        String isNotAnInteger = "a";
        String expMessage = "ERROR";
        try {
            Validation.isInteger2(isNotAnInteger, expMessage);
        } catch (Exception e) {
            assertEquals(expMessage, e.getMessage());
        }
    }

    @Test
    public void testCheckDate() throws Exception {
        String date = "2013-03-02";
        String month = "2013-03";
        String expResult = Validation.checkDate(date, month);
        assertEquals(expResult, date);
    }

    @Test
    public void testCheckMonth() throws Exception {
        String expResult = "2013-03";
        String result = Validation.checkMonth(expResult);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckDateIsValid() throws Exception {

        String expResult = "2013-03-02";
        String type = "date";
        String result = Validation.checkDateIsValid(expResult, type);

        assertEquals(expResult, result);

        type = "invalidType";
        expResult = "invalideDate";
        try {
            Validation.checkDateIsValid(expResult, type);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_DATE);
        }

    }

    @Test
    public void testCheckDateIsValidButInvalid() throws Exception {

        String expResult = "invalideDate";
        String type = "invalidType";

        try {
            Validation.checkDateIsValid(expResult, type);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_DATE);
        }
    }

    @Test
    public void testCheckDateMonthCoherence() throws Exception {

        String date = "2013-03-02";
        String month = "2013-04";

        try {
            Validation.checkDateMonthCoherence(date, month);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_DATE);
        }

    }

    @Test
    public void testCheckMontantInvalid() throws Exception {
        
        String montant = "";
        try {
            Validation.checkMontant(montant);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_MONTANT);
        }
    }
    
    
    @Test
    public void testCheckMontantValid() throws Exception {
        
        String result = "";
        
        String montant = "19,99$";
        String expResult = "19.99$";
        result = Validation.checkMontant(montant);
        assertEquals(expResult, result);

    }
    
    @Test
    public void testCheckMontantNotDouble() throws Exception {
        
        String montant = "notADouble";
        try {
            Validation.checkMontant(montant);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_MONTANT);
        }

    }
    
    @Test
    public void testCheckMontantNoDollarSymbol() throws Exception {
        
        String montant = "19.99";
        try {
            Validation.checkMontant(montant);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_SIGNE_DOLLAR);
        }

    }
    
    @Test
    public void testRemoveChar(){
        String param = "\"123456\"";
        String expResult = "123456";
        String result = Validation.removeChar(param);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testValueIsNotEmpty(){
        
        String value = "NOTEMPTY";
        
        try{
            Validation.valueIsNotEmpty(value, "");
        }catch(Exception e){
            //should not have any exception if valid
        }
        assertTrue(true);
    }
    
    @Test
    public void testValueIsNotEmptyButEmpty(){
        
        String value = "";
        String errorMessage = "ERROR";
        try{
            Validation.valueIsNotEmpty(value, errorMessage);
        }catch(Exception e){
            assertEquals(e.getMessage(),errorMessage);
        }
    }
     
}
