/* Copyright 2013
 jpokou
 pdarveau
 sayonCisse
 tremblayEric
  
 UQAM hiver 2013

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.INF2015.app.Validation;

import static org.junit.Assert.*;
import org.junit.Test;

public class ValidationTest {

   
    @Test
    public void testCareValidation() throws Exception {

        String care = "0";
        String validCare = Validation.careValidation(care);
        care = "666";
        try {
            Validation.careValidation(care);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_SOIN);
        }
    }

    @Test
    public void testCheckCodeAValid() throws Exception {

        String code = "A";
        String result = Validation.codeValidation(code);
        assertEquals(code, result);

    }

    @Test
    public void testCheckCodeE1Valid() throws Exception {

        String code = "E1";
        String result = Validation.codeValidation(code);
        assertEquals(code, result);

    }

    @Test
    public void testCheckCodeSecondCharNotAnInteger() throws Exception {

        String code = "EE";
        String result = "";

        try {
            result = Validation.codeValidation(code);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_CODE);
        }

    }

    @Test
    public void testCheckCodeInvalid() throws Exception {

        String code = "";
        String result = "";

        try {
            result = Validation.codeValidation(code);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_CODE);
        }

    }

    @Test
    public void testValidCareListCare0() {

        String expResult = "0";
        String result = Validation.careListValidation().get(0);
        assertEquals(expResult,result);

    }
    
    @Test
    public void testValidCareListCare100() {
        String expResult = "100";
        String result = Validation.careListValidation().get(1);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testValidCareListCare150() {
        String expResult = "150";
        String result = Validation.careListValidation().get(2);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testValidCareListCare175() {
        String expResult = "175";
        String result = Validation.careListValidation().get(3);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testValidCareListCare200() {
        String expResult = "200";
        String result = Validation.careListValidation().get(4);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testValidCareListCare400() {
        String expResult = "400";
        String result = Validation.careListValidation().get(5);
        assertEquals(expResult,result);
    }
    @Test
    public void testValidCareListCare500() {
        String expResult = "500";
        String result = Validation.careListValidation().get(6);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testValidCareListCare600() {
        String expResult = "600";
        String result = Validation.careListValidation().get(7);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testValidCareListCare700() {
        String expResult = "700";
        String result = Validation.careListValidation().get(8);
        assertEquals(expResult,result);
    }

    @Test
    public void testCheckFolderValid() throws Exception {

        String folderNumber = "A234567";
        String expresult = Validation.folderNumberValidation(folderNumber);
        assertEquals(expresult, folderNumber);

    }

    @Test
    public void testCheckFolderInvalidContract() throws Exception {

        String folderNumber = "G123456";

        try {
            Validation.folderNumberValidation(folderNumber);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_FOLDER);
        }

    }

    @Test
    public void testCheckFolderInvalNumberId() throws Exception {

        String folderNumber = "Aqwerty";

        try {
            Validation.folderNumberValidation(folderNumber);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_FOLDER);
        }
    }

    @Test
    public void testIsInteger() throws Exception {
        String isNotAnInteger = "a";
        String expMessage = "ERROR";
        try {
            Validation.isInteger(isNotAnInteger, expMessage);
        } catch (Exception e) {
            assertEquals(expMessage, e.getMessage());
        }
    }

    @Test
    public void testDateValidation() throws Exception {
        String date = "2013-03-02";
        String month = "2013-03";
        String expResult = Validation.dateValidation(date, month);
        assertEquals(expResult, date);
    }

    @Test
    public void testMonthValidation() throws Exception {
        String expResult = "2013-03";
        String result = Validation.monthValidation(expResult);
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

        String amount = "";
        try {
            Validation.amountValidation(amount);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_MONTANT);
        }
    }

    @Test
    public void testCheckMontantValid() throws Exception {

        String result;

        String amount = "19,99$";
        String expResult = "19.99$";
        result = Validation.amountValidation(amount);
        assertEquals(expResult, result);

    }

    @Test
    public void testCheckMontantNotDouble() throws Exception {

        String amount = "notADouble";
        try {
            Validation.amountValidation(amount);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorMessage.MESSAGE_ERROR_MONTANT);
        }

    }

    @Test
    public void testCheckMontantNoDollarSymbol() throws Exception {

        String amount = "19.99";
        try {
            Validation.amountValidation(amount);
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
