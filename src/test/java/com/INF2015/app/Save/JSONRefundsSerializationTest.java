package com.INF2015.app.Save;

import com.INF2015.app.Parsing.JavaObjectReclamation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class JSONRefundsSerializationTest {

    
    List reclamationList;
    
    @Before
    public void setUp()  {
      reclamationList = new ArrayList<JavaObjectReclamation>();
      
      reclamationList.add(new JavaObjectReclamation( "100", "A",  null,  "1000"));  
    }

    @After
    public void tearDown()  {
        
    }
    
    @Test
    public void testJSONRefundsSerialization(){
        try{
        JSONRefundsSerialization.JSONRefundsSerialization(null,"A123456","2000-12-12",reclamationList);
        }catch(Exception e){
           assertEquals(null,e.getMessage()); 
        }
        
    }
    
    
    @Test
    public void testJSONRefundsSerializationError(){
        String expectedMessage = "null";
        try{
          JSONRefundsSerialization.JSONRefundsSerializationError(null, null);
        }catch(Exception e){
            assertEquals(null,e.getMessage());
        }
    }
    
}
