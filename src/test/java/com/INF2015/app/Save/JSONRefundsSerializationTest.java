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
package com.INF2015.app.Save;

import com.INF2015.app.Parsing.JavaObjectReclamation;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
