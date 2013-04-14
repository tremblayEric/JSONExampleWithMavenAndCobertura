/* Copyright 2011 Jacques Berger

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 * 
 * ModifiÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â© dans le cadre du cours : 
 * Programmation dans un environnement agile INF2015 
 * TP1
 * 
 * Par:
 * jpokou
 * pdarveau
 * sayonCisse
 * tremblayEric
 * 
 * UQAM hiver 2013
 */
package com.INF2015.app.MockData;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class ContractListTest {

    List<Care> contractList;
    int maxMonthlyRefundDent;
    int maxMonthlyRefundNat;
    ContractList instanceContractList;

    @Before
    public void setUp() throws Exception {
        contractList = new ArrayList<Care>();
        instanceContractList = new ContractList();
    }

    @After
    public void tearDown() throws Exception {
        contractList = null;
        instanceContractList = null;
    }

    @Test
    public void testGetContractRatioByCareNumberContractA() {
        int expContractRatioByCareNumber = 35;
        int result = instanceContractList.getContractRatioByCareNumber("100", "A");
        assertEquals(expContractRatioByCareNumber, result);
    }
    
    @Test
    public void testGetContractRatioByCareNumberContractB() {
        int expContractRatioByCareNumber = 50;
        int result = instanceContractList.getContractRatioByCareNumber("100", "B");
        assertEquals(expContractRatioByCareNumber, result);
    }
    
    @Test
    public void testGetContractRatioByCareNumberContractC() {
        int expContractRatioByCareNumber = 95;
        int result = instanceContractList.getContractRatioByCareNumber("100", "C");
        assertEquals(expContractRatioByCareNumber, result);
    }
    
    @Test
    public void testGetContractRatioByCareNumberContractD() {
        int expContractRatioByCareNumber = 100;
        int result = instanceContractList.getContractRatioByCareNumber("100", "D");
        assertEquals(expContractRatioByCareNumber, result);
    }
    
    @Test
    public void testGetContractRatioByCareNumberContractE() {
        int expContractRatioByCareNumber = 25;
        int result = instanceContractList.getContractRatioByCareNumber("100", "E");
        assertEquals(expContractRatioByCareNumber, result);
    }

    @Test
    public void testGetContractMaxValueByCareNumberContractA() {
        
        int expContractMaxValueByCareNumber = -1;
        int result = instanceContractList.getContractMaxValueByCareNumber("100", "A");
        assertEquals(expContractMaxValueByCareNumber, result);
    }
    
    @Test
    public void testGetContractMaxValueByCareNumberContractB() {
        
        int expContractMaxValueByCareNumber = 5000;
        int result = instanceContractList.getContractMaxValueByCareNumber("100", "B");
        assertEquals(expContractMaxValueByCareNumber, result);
    }
    
    @Test
    public void testGetContractMaxValueByCareNumberContractC() {
        
        int expContractMaxValueByCareNumber = -1;
        int result = instanceContractList.getContractMaxValueByCareNumber("100", "C");
        assertEquals(expContractMaxValueByCareNumber, result);
    }
    
    @Test
    public void testGetContractMaxValueByCareNumberContractD() {
        
        int expContractMaxValueByCareNumber = 7500;
        int result = instanceContractList.getContractMaxValueByCareNumber("100", "D");
        assertEquals(expContractMaxValueByCareNumber, result);
    }
    
    @Test
    public void testGetContractMaxValueByCareNumberContractE() {
        
        int expContractMaxValueByCareNumber = -1;
        int result = instanceContractList.getContractMaxValueByCareNumber("100", "E");
        assertEquals(expContractMaxValueByCareNumber, result);
    }

    @Test
    public void testGetContractMaxValueByCareNumberExist() {
        
        boolean expContractMaxValueByCareNumberExist = false;
        boolean result = instanceContractList.getContractMaxValueByCareNumberExist("100", "A");
        assertEquals(expContractMaxValueByCareNumberExist, result);
}

    @Test
    public void testGetCareMonthlyMaximumLimitExist() {
        String careNumber = "300";
        int expResult = -1;
        int result = instanceContractList.getCareMonthlyMaximumLimit(careNumber);
        assertEquals(expResult, result);
    }
    
     @Test
    public void testGetCareMonthlyMaximumLimitNotExist() {
        String careNumber = "1200";
        int expResult = 0;
        int result = instanceContractList.getCareMonthlyMaximumLimit(careNumber);
        assertEquals(expResult, result);
    }
     
     @Test
     public void testRoundCareNumber(){
         String expRestult = "300";
         String result = instanceContractList.roundCareNumber("319");
         assertEquals(expRestult,result);
     }
     
     @Test
     public void testMassoContract(){
         Contracts masso=  instanceContractList.massoContract();
         assertNotNull(masso);
     }
     
     @Test
     public void testOsteoContract(){
         Contracts osteo=  instanceContractList.osteoContract();
         assertNotNull(osteo);
     }
     
     @Test
     public void testKinesitherapieContract(){
         Contracts kine=  instanceContractList.kinesitherapieContract();
         assertNotNull(kine);
     }
     
     @Test
     public void testPrivateGeneralMedecineContract(){
         Contracts general=  instanceContractList.privateGeneralMedecineContract();
         assertNotNull(general);
     }
     
     @Test
     public void testPsychoContract(){
         Contracts psycho=  instanceContractList.psychoContract();
         assertNotNull(psycho);
     }
     
     @Test
     public void testDentalContract(){
         Contracts dental=  instanceContractList.dentalContract();
         assertNotNull(dental);
     }
     
     @Test
     public void testNaturoAcupContract(){
         Contracts naturo=  instanceContractList.naturoAcupContract();
         assertNotNull(naturo);
     }
     
     @Test
     public void testChiroContract(){
         Contracts masso=  instanceContractList.massoContract();
         assertNotNull(masso);
     }
     
     @Test
     public void testPhysioContract(){
         Contracts physio=  instanceContractList.physioContract();
         assertNotNull(physio);
     }
     
     @Test
     public void testOrthoErgoContract(){
         Contracts ortho=  instanceContractList.orthoErgoContract();
         assertNotNull(ortho);
     }
}
