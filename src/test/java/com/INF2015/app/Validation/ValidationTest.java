package com.INF2015.app.Validation;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ValidationTest {

   
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
    public void testValidCareListCare0() {

        String expResult = "0";
        String result = Validation.validCareList2().get(0);
        assertEquals(expResult,result);

    }
    
    @Test
    public void testValidCareListCare100() {
        String expResult = "100";
        String result = Validation.validCareList2().get(1);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testValidCareListCare150() {
        String expResult = "150";
        String result = Validation.validCareList2().get(2);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testValidCareListCare175() {
        String expResult = "175";
        String result = Validation.validCareList2().get(3);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testValidCareListCare200() {
        String expResult = "200";
        String result = Validation.validCareList2().get(4);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testValidCareListCare400() {
        String expResult = "400";
        String result = Validation.validCareList2().get(5);
        assertEquals(expResult,result);
    }
    @Test
    public void testValidCareListCare500() {
        String expResult = "500";
        String result = Validation.validCareList2().get(6);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testValidCareListCare600() {
        String expResult = "600";
        String result = Validation.validCareList2().get(7);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testValidCareListCare700() {
        String expResult = "700";
        String result = Validation.validCareList2().get(8);
        assertEquals(expResult,result);
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

        String result;

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
    public void testRemoveChar() {
        String param = "\"123456\"";
        String expResult = "123456";
        String result = Validation.removeChar(param);
        assertEquals(expResult, result);
    }

    @Test
    public void testValueIsNotEmpty() {

        String value = "NOTEMPTY";

        try {
            Validation.valueIsNotEmpty(value, "");
        } catch (Exception e) {
            //should not have any exception if valid
        }
        assertTrue(true);
    }

    @Test
    public void testValueIsNotEmptyButEmpty() {

        String value = "";
        String errorMessage = "ERROR";
        try {
            Validation.valueIsNotEmpty(value, errorMessage);
        } catch (Exception e) {
            assertEquals(e.getMessage(), errorMessage);
        }
    }
}
