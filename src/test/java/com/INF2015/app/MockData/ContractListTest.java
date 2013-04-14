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

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;


public class ContractListTest {
    
    List<Care> contractList;
    Contracts dentalContracts;
    Contracts naturoAcupContracts;
    Care soinsDentaires;
    Care NaturoAcupuncture;
    int maxMonthlyRefundDent;
    int maxMonthlyRefundNat;
    ContractList instanceContractList;
        
    @Before
    public void setUp() throws Exception{           
        contractList = new ArrayList<Care>();      
        instanceContractList = new ContractList();
        Contracts dentalContracts = new Contracts();
        dentalContracts.setRatio(0, 5/10, 9/10, 1, 6/10);
        dentalContracts.setMax(false, false, false, false,false);
        dentalContracts.setMaxValue(-1, -1, -1, -1,-1);
        
        Contracts naturoAcupContracts = new Contracts();
        naturoAcupContracts.setRatio(0, 0, 9/10, 1, 25/100);
        naturoAcupContracts.setMax(false, false, false, true,true);
        naturoAcupContracts.setMaxValue(-1, -1, -1, 65, 15);
        
        soinsDentaires = new Care("300", "Soins dentaires", dentalContracts, maxMonthlyRefundDent);
        NaturoAcupuncture = new Care("400", "Naturopathie, acuponcture",
                naturoAcupContracts, maxMonthlyRefundNat);    
    }
    
    @After
    public void tearDown() throws Exception{ 
        contractList = null;  
        instanceContractList = null;
        dentalContracts = null;
        naturoAcupContracts = null;
        soinsDentaires = null;
        NaturoAcupuncture = null;
    }

    @Test
    public void testGetContractRatioByCareNumber() {
        contractList.add(soinsDentaires);       
        double expContractRatioByCareNumber = -1.0;
        String careNumber = contractList.get(0).getCareNumber();
        String contract = contractList.get(0).getContract()+"";
        double result = instanceContractList.getContractRatioByCareNumber(careNumber, contract);
        assertEquals(expContractRatioByCareNumber, result, 0.0);
    }

    @Test
    public void testGetContractMaxValueByCareNumber() {
        contractList.add(NaturoAcupuncture);   
        String careNumber = contractList.get(0).getCareNumber();
        System.out.println("CareNumber = " + careNumber);
        String contract = contractList.get(0).getContract()+"";
        int expContractMaxValueByCareNumber = -1;
        int result = instanceContractList.getContractMaxValueByCareNumber(careNumber, contract);
        assertEquals(expContractMaxValueByCareNumber, result);
    }

    @Test
    public void testGetContractMaxValueByCareNumberExist() {
        contractList.add(NaturoAcupuncture);
        String careNumber = contractList.get(0).getCareNumber();
        String contract = contractList.get(0).getContract()+"";
        boolean expContractMaxValueByCareNumberExist = false;
        boolean result = instanceContractList.getContractMaxValueByCareNumberExist(careNumber, contract);       
        assertEquals(expContractMaxValueByCareNumberExist, result);
        
    }

    @Test
    public void testGetCareMonthlyMaximumLimit() {
        String careNumber = "300";
        int expResult = -1;
        int result = instanceContractList.getCareMonthlyMaximumLimit(careNumber);
        assertEquals(expResult, result);
    }
}
