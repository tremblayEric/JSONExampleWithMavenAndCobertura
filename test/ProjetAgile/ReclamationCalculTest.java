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
package tp1agile;

import ProjetAgile.ReclamationCalcul;
import XMLParsing.Dom;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;


public class ReclamationCalculTest {
    
    ReclamationCalcul instance;
    Dom documentXML;
    Document document;

    @Before
    public void setUpClass() throws Exception {
        documentXML = new Dom("src/XmlFiles/" + "1.xml");
        document = documentXML.getDocumentXMLInput();
        instance = new ReclamationCalcul(document);
        instance.setDocument(document);
        
    }

    @After
    public void tearDownClass() throws Exception {
        instance = null;
        documentXML = null;
        document = null;
    }

    @Test
    public void testGetReclamationList() {
        List<String>  expResult = Arrays.asList("0 2013-01-11 110.00$",
                "100 2013-01-11 110.00$", "200 2013-01-11 100.00$", 
                "315 2013-01-22 450.00$", "400 2013-01-23 60.00$", 
                "500 2013-01-11 110.00$", "600 2013-01-25 100.00$", 
                "700 2013-01-30 100.00$");
        List result = instance.getReclamationList();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCareList() {
        List<String>  expResult = Arrays.asList("0", "100", "200", "315", "400",
                "500","600", "700");
        List result = instance.getCareList();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetDateList() {
        List<String>  expResult = Arrays.asList("2013-01-11", "2013-01-11", 
                "2013-01-11", "2013-01-22", "2013-01-23", "2013-01-11", 
                "2013-01-25", "2013-01-30");
        List result = instance.getDateList();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAmountList() {
        List<String>  expResult = Arrays.asList("110.00", "110.00", "100.00",
                "450.00", "60.00", "100.00", "100.00", "100.00");
        List result = instance.getAmountList();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFolderNumber() {        
         String expResult = "D100323";
         String result = instance.getFolderNumber();
         assertEquals(expResult, result);
    }

    @Test
    public void testGetMonth() {
        String expResult = "2013-01";
        String result = instance.getMonth();
        assertEquals(expResult, result);
    }

    @Test
    public void testDoCalculList() {
        List<Double>  expResult = Arrays.asList(85.0, 75.0, 100.0,
                450.0, 60.0, 100.0, 100.0, 90.0);
        List result = instance.doCalculList();
        assertEquals(expResult, result);
    }

    @Test
    public void testDoCalcul() {
        double valeur = 0.5;
        String numeroSoin = "200";
        String contrat = "B";
        double expResult = 0.5;
        double result = instance.doCalcul(valeur, numeroSoin, contrat);
        assertEquals(expResult, result, 0.0);
    }
}
