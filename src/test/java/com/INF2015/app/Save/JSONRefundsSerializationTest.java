
package com.INF2015.app.Save;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;


public class JSONRefundsSerializationTest {
    
    public JSONRefundsSerializationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    
    @Ignore
    public void testJSONRefundsSerialization() {
        System.out.println("JSONRefundsSerialization");
        String path = "";
        String folderNmber = "";
        String folderDate = "";
        List reclamationList = null;
        JSONRefundsSerialization.JSONRefundsSerialization(path, folderNmber, folderDate, reclamationList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of JSONRefundsSerializationError method, of class JSONRefundsSerialization.
     */
    @Ignore
    public void testJSONRefundsSerializationError() {
        System.out.println("JSONRefundsSerializationError");
        String path = "";
        String errorMessage = "";
        JSONRefundsSerialization.JSONRefundsSerializationError(path, errorMessage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
