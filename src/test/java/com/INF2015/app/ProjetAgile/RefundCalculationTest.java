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
 * 
 * 
 */
package com.INF2015.app.ProjetAgile;

import com.INF2015.app.Parsing.JavaObjectDossier;
import com.INF2015.app.Parsing.JavaObjectReclamation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    JavaObjectDossier file;
    JavaObjectReclamation refund;
    SimpleDateFormat dateFormat;
    Date date;
        
    @Before
    public void setUpClass() throws Exception {
        file = new JavaObjectDossier();
        file.setDossier("A100323");
        file.setMois("2013-01");
        instance = new RefundCalculation(file);
        dateFormat = new SimpleDateFormat("yyyy-MM-DD");
        date = dateFormat.parse("2013-01-11");
        refund = new JavaObjectReclamation("175", "H1",date, "400.00$");
    }

    @After
    public void tearDownClass() throws Exception {
        file = null;
        instance = null;
        refund = null;
    }

    @Test
    public void testGetRefundList() throws ParseException {
        List<String>  expResult = Arrays.asList("175 H1 2013-01-11 400.00$",
                "175 C 2013-01-14 130.00$", "175 E 2013-01-15 130.00$", 
                "175 E 2013-01-17 130.00$");
        List result = instance.getRefundList();          
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoCalcul() {
        int valeur = 1/2;
        String numeroSoin = "175";
        String code = "H1";
        String contract = "A100323";
        int expResult = 0;
        int result = instance.doCalcul(valeur, numeroSoin, contract, code);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFolderContract() {
        String expResult = "A";
        String result = instance.getFolderContract();
        assertEquals(expResult, result);
    }
}
