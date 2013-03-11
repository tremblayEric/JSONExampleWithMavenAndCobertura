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
package Data;

import MockData.Contracts;
import org.junit.*;
import static org.junit.Assert.*;


public class ContractsTest {
    
    Contracts instanceContracts;
    double ratioA;
    double ratioB;
    double ratioC;
    double ratioD;
    double ratioE;
    double exceptedRatioA;
    double exceptedRatioB;
    double exceptedRatioC;
    double exceptedRatioD;
    double exceptedRatioE;
    
    boolean maxA;
    boolean maxB;
    boolean maxC;
    boolean maxD;
    boolean maxE;
    boolean exceptedMaxA;
    boolean exceptedMaxB;
    boolean exceptedMaxC;
    boolean exceptedMaxD;
    boolean exceptedMaxE;
    
    int maxValueA;
    int maxValueB;
    int maxValueC;
    int maxValueD;
    int maxValueE;
    int exceptedMaxValueA;
    int exceptedMaxValueB;
    int exceptedMaxValueC;
    int exceptedMaxValueD;
    int exceptedMaxValueE;

    @Before
    public void setUp() throws Exception{     
        instanceContracts = new Contracts(); 
        initializeMaxX();
        initializeMaxValueX(); 
        initializeRatio();
        instanceContracts.setRatio(ratioA, ratioB, ratioC, ratioD, ratioE);
        instanceContracts.setMax(maxA, maxB, maxC, maxD, maxE);
        instanceContracts.setMaxValue(maxValueA, maxValueB, maxValueC, maxValueD, maxValueE);       
    }
    
    @After
    public void tearDown() throws Exception{      
        instanceContracts = null;
        setRatioToZero();
        setMaxToZero();
        setMaxValueToZero();    
    }

    public void initializeRatio() {                        
        ratioA = 0.25;
        ratioB = 0.5;
        ratioC = 0.9;
        ratioD = 1.0;
        ratioE = 0.15;  
    }
    public void initializeMaxX() {                        
        maxA = false;
        maxB = true;
        maxC = false;
        maxD = true;
        maxE = false;
    }
   
    public void initializeMaxValueX() {
        maxValueA = -1;
        maxValueB = 40;
        maxValueC = -1;
        maxValueD = 85;
        maxValueE = -1;
    }
    public void setRatioToZero() {                        
        ratioA = 0.0;
        ratioB = 0.0;
        ratioC = 0.0;
        ratioD = 0.0;
        ratioE = 0.0;
    }
    public void setMaxToZero() {                        
        maxA = false;
        maxB = false;
        maxC = false;
        maxD = false;
        maxE = false; 
    }
    public void setMaxValueToZero() {                        
        maxValueA = 0;
        maxValueB = 0;
        maxValueC = 0;
        maxValueD = 0;
        maxValueE = 0; 
    }
    
    @Test
    public void testGetRatioA() {
        exceptedRatioA = 0.25;
        double resultGetRatioA = instanceContracts.getRatioA();
        assertEquals(exceptedRatioA, resultGetRatioA, resultGetRatioA);  
    }

    @Test
    public void testGetRatioB() {
        exceptedRatioB = 0.5;
        double resultGetRatioB = instanceContracts.getRatioB();
        assertEquals(exceptedRatioB, resultGetRatioB, 0.0);       
    }

    @Test
    public void testGetRatioC() {
        exceptedRatioC = 0.9;      
        double resultGetRatioC = instanceContracts.getRatioC();
        assertEquals(exceptedRatioC, resultGetRatioC, 0.0);  
    }

    @Test
    public void testGetRatioD() {       
        exceptedRatioD = 1.0;  
        double resultGetRatioD = instanceContracts.getRatioD();
        assertEquals(exceptedRatioD, resultGetRatioD, 0.0);  
    }

    @Test
    public void testGetRatioE() {
        exceptedRatioE = 0.15;       
        double resultGetRatioE = instanceContracts.getRatioE();
        assertEquals(exceptedRatioE, resultGetRatioE, 0.0);       
    }
    
    @Test
    public void testGetmaxA() {
        exceptedMaxA = false;
        boolean resultGetMaxA = instanceContracts.getmaxA();
        assertTrue(!resultGetMaxA);
        assertFalse(resultGetMaxA);
    }

    @Test
    public void testGetmaxB() {
        exceptedMaxB = true; 
        boolean resultGetMaxB = instanceContracts.getmaxB();
        assertTrue(resultGetMaxB);
        assertFalse(!resultGetMaxB);      
    }

    @Test
    public void testGetmaxC() {      
        exceptedMaxC = false;
        boolean resultGetMaxC = instanceContracts.getmaxC();
        assertTrue(!resultGetMaxC);
        assertFalse(resultGetMaxC);
    }

    @Test
    public void testGetmaxD() {
        exceptedMaxD = true;
        boolean resultGetMaxD = instanceContracts.getmaxD();
        assertTrue(resultGetMaxD);
        assertFalse(!resultGetMaxD);
    }

    @Test
    public void testGetmaxE() {
        exceptedMaxE = false;
        boolean resultGetMaxE = instanceContracts.getmaxE();
        assertTrue(!resultGetMaxE);
        assertFalse(resultGetMaxE);
    }

    @Test
    public void testGetMavalueA() {
        exceptedMaxValueA = -1;
        double resultGetMavalueA = instanceContracts.getMavalueA();
        assertEquals(exceptedMaxValueA, resultGetMavalueA, 0.0);  
    }

    @Test
    public void testGetMavalueB() {
        exceptedMaxValueB = 40;
        double resultGetMavalueB = instanceContracts.getMavalueB();
        assertEquals(exceptedMaxValueB, resultGetMavalueB, 0.0);
    }

    @Test
    public void testGetMavalueC() {
        exceptedMaxValueC = -1;
        double resultGetMavalueC = instanceContracts.getMavalueC();
        assertEquals(exceptedMaxValueC, resultGetMavalueC, 0.0);
    }

    @Test
    public void testGetMavalueD() {
        exceptedMaxValueD = 85;
        double resultGetMavalueD = instanceContracts.getMavalueD();
        assertEquals(exceptedMaxValueD, resultGetMavalueD, 0.0);
    }

    @Test
    public void testGetMavalueE() {
        exceptedMaxValueE = -1; 
        double resultGetMavalueE = instanceContracts.getMavalueE();
        assertEquals(exceptedMaxValueE, resultGetMavalueE, 0.0);
    }
}
