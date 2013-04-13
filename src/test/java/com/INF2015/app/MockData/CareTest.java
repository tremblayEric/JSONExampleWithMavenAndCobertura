
package com.INF2015.app.MockData;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CareTest {
    
    public CareTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

   
    @Test
    public void testGetCareNumber() {
        System.out.println("getCareNumber");
        Care instance = new Care("100", null, null,0);
        String expResult = "100";
        String result = instance.getCareNumber();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getCareCategorie method, of class Care.
     */
    @Test
    public void testGetCareCategorie() {
        System.out.println("getCareCategorie");
        Care instance = null;
        String expResult = "";
        String result = instance.getCareCategorie();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContract method, of class Care.
     */
    @Test
    public void testGetContract() {
        System.out.println("getContract");
        Care instance = null;
        Contracts expResult = null;
        Contracts result = instance.getContract();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMonthlyMaxLimit method, of class Care.
     */
    @Test
    public void testGetMonthlyMaxLimit() {
        System.out.println("getMonthlyMaxLimit");
        Care instance = null;
        int expResult = 0;
        int result = instance.getMonthlyMaxLimit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Care.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Care instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
