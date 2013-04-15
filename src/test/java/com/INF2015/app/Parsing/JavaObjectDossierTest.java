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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;


public class JavaObjectDossierTest {

    JavaObjectReclamation reclamation;
    JavaObjectFolder instance;
    String care;
    String code;
    Date date;
    String amount;
    SimpleDateFormat dateFormat;

    @Before
    public void setUp() throws Exception {
        instance = new JavaObjectFolder();
        instance.setFolder("A100323");
        instance.setMonth("2013-01");
        care = "175";
        code = "H1";
        amount = "400.00$";
        String mois = "2013-01";
        instance.setMonth(mois);
        date = instance.getFolderDate();
        reclamation = new JavaObjectReclamation(care, code, date, amount);
    }

    @After
    public void tearDown() throws Exception {
        instance = null;
        care = null;
        code = null;
        amount = null;
        dateFormat = null;
        date = null;
        reclamation = null;
    }

    @Test
    public void testSetFolder() {
        String folder = "A100323";
        instance.setFolder(folder);
        String result = instance.getFolderNumber();
        assertEquals(folder, result);

    }

    @Test
    public void testSetMonth() {
        SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-MM");
        String month = "2013-01";
        instance.setMonth(month);
        Date date = instance.getFolderDate();
        String result = dateFormatMois.format(date);
        assertEquals(month, result);
    }
    
    @Test 
    public void testSetMonthFailure(){
        
        String invalidMonth = "invalideMonth";
        String expectedResult = "Erreur avec le mois";
        
        try{
            instance.setMonth(invalidMonth);
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
