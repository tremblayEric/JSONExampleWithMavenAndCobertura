/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INF2015.app.ProjetAgile;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eric et bendjina
 */
public class RefundCalculationTest {
    
    public RefundCalculationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getRefundList method, of class RefundCalculation.
     */
    @Test
    public void testGetRefundList() {
        System.out.println("getRefundList");
        RefundCalculation instance = null;
        List expResult = null;
        List result = instance.getRefundList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
