
package com.INF2015.app.MockData;

import com.INF2015.app.MockData.ContractList;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;


public class ContractListTest {
    
    public ContractListTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getContractRatioByCareNumber method, of class ContractList.
     */
    @Test
    public void testGetContractRatioByCareNumber() {
        System.out.println("getContractRatioByCareNumber");
        String careNumber = "100";
        String contract = "E";
        ContractList instance = new ContractList();
        int expResult = 25;
        int result = instance.getContractRatioByCareNumber(careNumber, contract);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getContractMaxValueByCareNumber method, of class ContractList.
     */
    @Test
    public void testGetContractMaxValueByCareNumber() {
        System.out.println("getContractMaxValueByCareNumber");
        String careNumber = "100";
        String contract = "E";
        ContractList instance = new ContractList();
        int expResult = -1;
        int result = instance.getContractMaxValueByCareNumber(careNumber, contract);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getContractMaxValueByCareNumberExist method, of class ContractList.
     */
    @Test
    public void testGetContractMaxValueByCareNumberExist() {
        System.out.println("getContractMaxValueByCareNumberExist");
        String careNumber = "400";
        String contract = "E";
        ContractList instance = new ContractList();
        boolean expResult = true;
        boolean result = instance.getContractMaxValueByCareNumberExist(careNumber, contract);
        assertEquals(expResult, result);
        
    }
    
   
  @Test
  public void testGetCareMonthlyMaximumLimit(){
      ContractList instance = new ContractList();
      String careNumber = "100";
      int expResult = 250;
      int retour = instance.getCareMonthlyMaximumLimit(careNumber);
      assertEquals(expResult,retour);
  }
}
