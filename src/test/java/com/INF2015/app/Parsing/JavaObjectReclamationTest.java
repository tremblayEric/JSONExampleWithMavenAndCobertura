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
package com.INF2015.app.Parsing;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;


public class JavaObjectReclamationTest {

    JavaObjectReclamation instance;
    JavaObjectFolder folder;
    String care;
    String code;
    Date date;
    String amount;
    SimpleDateFormat dateFormat;

    @Before
    public void setUp() throws Exception {
        folder = new JavaObjectFolder();
        folder.setFolder("A100323");
        folder.setMonth("2013-01");
        care = "175";
        code = "H1";
        amount = "400.00$";
        String month = "2013-01";
        folder.setMonth(month);
        date = folder.getFolderDate();
        instance = new JavaObjectReclamation(care, code, date, amount);
    }

    @After
    public void tearDown() throws Exception {
        instance = null;
        care = null;
        code = null;
        amount = null;
        dateFormat = null;
        date = null;
        folder = null;
    }

    @Test
    public void testGetCare() {
        String expResult = "175";
        String result = instance.getCare();
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
    public void testGetAmount() {
        int expResult = 40000;
        int result = instance.getAmount();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAmount() {
        int amount = 400000;
        instance.setAmount(amount);
        int result = instance.getAmount();
        assertEquals(amount, result);
    }
    
    @Test 
    public void JavaObjectReclamationConstructorAmountError(){
        String expectedMessage = "plantage de conversion de string en double";
        try{
        JavaObjectReclamation result = new JavaObjectReclamation("","",null,"notADouble");
        }catch(Exception e){
            assertEquals(expectedMessage,e.getMessage());
        }
    }
    
    
}
