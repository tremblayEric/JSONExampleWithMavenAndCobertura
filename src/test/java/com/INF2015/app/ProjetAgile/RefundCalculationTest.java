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
package com.INF2015.app.ProjetAgile;

import com.INF2015.app.Parsing.JavaObjectFolder;
import com.INF2015.app.Parsing.JavaObjectReclamation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class RefundCalculationTest {

    RefundCalculation instance;
    JavaObjectFolder file;
    JavaObjectReclamation refund;
    SimpleDateFormat dateFormat;
    Date date;
    ArrayList familyMember;

    @Before
    public void setUpClass() throws Exception {
        familyMember = new ArrayList();
        file = new JavaObjectFolder();
        file.setFolder("A100323");
        file.setMonth("2013-01");
        
        dateFormat = new SimpleDateFormat("yyyy-MM-DD");
        date = dateFormat.parse("2013-01-11");
        refund = new JavaObjectReclamation("175", "H1", date, "400.00$");
        file.addToReclamationList(refund);
        instance = new RefundCalculation(file);
    }

    @After
    public void tearDownClass() throws Exception {
        file = null;
        instance = null;
        refund = null;
    }

    @Ignore
    public void testGetRefundList() throws ParseException {
        List<String> expResult = Arrays.asList("175 H1 2013-01-11 400.00$");
        List result = instance.getRefundList();
        JavaObjectReclamation reclamation = (JavaObjectReclamation)result.get(0);
        String resultat = reclamation.getCare()+" "+reclamation.getCode() + " "+reclamation.getDate().toString()+" "+reclamation.getAmount();
        assertEquals(expResult, resultat);
    }

    @Test
    public void testDoCalcul() {
        int value = 1 / 2;
        String careNumber = "175";
        String code = "H1";
        String contract = "A100323";
        int expResult = 0;
        int result = instance.doCalcul(value, careNumber, contract, code);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFolderContract() {
        String expResult = "A";
        String result = instance.getFolderContract();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetFamlyMemberReclamationList(){
        
        List reclamation = instance.getFamlyMemberReclamationList("H1");
        String result = ((JavaObjectReclamation)reclamation.get(0)).toString(); 
        String expResult = refund.toString();
        assertEquals(expResult,result);
        
    }
    
    @Ignore
    public void testFamilyMemberRecuperation(){
       assertTrue(familyMember.size() == 1);
    }
    
    @Test
    public void testRefundAdjustmentExcedeMonthlyMax(){
        int total = 100;
        int monthlyMax = 500;
        int expResult = monthlyMax;
        int result = instance.refundAdjustment(refund, total, monthlyMax);
        assertEquals(expResult,result);
        
    }
    
    @Ignore
    public void testRefundAdjustmentTotalExcedeMonthlyMax(){
        int total = 500;
        int monthlyMax = 50;
        int expResult = 0;
        instance.refundAdjustment(refund, total, monthlyMax);
        int result = refund.getAmount();
        assertEquals(expResult,result);
        
    }
}
