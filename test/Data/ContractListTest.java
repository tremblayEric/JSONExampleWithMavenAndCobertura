/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author TeninBoré
 */
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
        String careNumber = "";
        String contract = "";
        ContractList instance = new ContractList();
        double expResult = 0.0;
        double result = instance.getContractRatioByCareNumber(careNumber, contract);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContractMaxValueByCareNumber method, of class ContractList.
     */
    @Test
    public void testGetContractMaxValueByCareNumber() {
        System.out.println("getContractMaxValueByCareNumber");
        String careNumber = "";
        String contract = "";
        ContractList instance = new ContractList();
        int expResult = 0;
        int result = instance.getContractMaxValueByCareNumber(careNumber, contract);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContractMaxValueByCareNumberExist method, of class ContractList.
     */
    @Test
    public void testGetContractMaxValueByCareNumberExist() {
        System.out.println("getContractMaxValueByCareNumberExist");
        String careNumber = "";
        String contract = "";
        ContractList instance = new ContractList();
        boolean expResult = false;
        boolean result = instance.getContractMaxValueByCareNumberExist(careNumber, contract);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
