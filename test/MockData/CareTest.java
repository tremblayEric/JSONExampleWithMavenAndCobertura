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
 * ModifiÃ© dans le cadre du cours : 
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
package MockData;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CareTest {
    Care instance, instance2;
    String careNumber, expectedCareNumber;
    String careCategory, expectedCareCategory;
    Contracts contract;
           
    @Before
    public void setUp() throws Exception{
        instance = new Care(null, null, null) ;
        contract = new Contracts();
        expectedCareNumber = "100";
	expectedCareCategory = "OstÃ©opathie"; 
        instance2 = new Care(expectedCareNumber, expectedCareCategory, contract);
    }
    
    @After
    public void tearDown() throws Exception{
	instance =  null;
	careNumber = null;
	careCategory = null;
	contract = null; 
        instance2 =  null;
	expectedCareNumber = null;
	expectedCareCategory = null;       
    }
    
    @Test
    public void testGetcareNumber() {  
        assertNull(instance.getCareNumber()); 
        assertEquals(expectedCareNumber, instance2.getCareNumber());   
        assertFalse(instance.getCareNumber() != null);     
    }
    
    @Test
    public void testGetCareCategorie() {
        assertNull(instance.getCareCategorie()); 
        assertEquals(expectedCareCategory, instance2.getCareCategorie());
        assertFalse(instance.getCareCategorie() != null); 
    }

    @Test
    public void testGetContract() {
        assertNull(instance.getContract()); 
        assertTrue(instance2.getContract() != null);
    }
}
