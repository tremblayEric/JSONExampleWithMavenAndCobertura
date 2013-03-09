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
 * Modifié dans le cadre du cours : 
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

/**
 *
 * @author SayonCissé
 */
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
        maxA = false;
        maxB = true;
        maxC = false;
        maxD = true;
        maxE = false;
        maxValueA = -1;
        maxValueB = 40;
        maxValueC = -1;
        maxValueD = 85;
        maxValueE = -1;
        ratioA = 0.25;
        ratioB = 0.5;
        ratioC = 0.9;
        ratioD = 1.0;
        ratioE = 0.15;             
    }
    
    @After
    public void tearDown() throws Exception{      
        instanceContracts = null;
        ratioA = 0.0;
        ratioB = 0.0;
        ratioC = 0.0;
        ratioD = 0.0;
        ratioE = 0.0;    
        maxA = false;
        maxB = false;
        maxC = false;
        maxD = false;
        maxE = false;        
        maxValueA = 0;
        maxValueB = 0;
        maxValueC = 0;
        maxValueD = 0;
        maxValueE = 0;     
    }

    @Test
    public void testSetRatio() {                       
        exceptedRatioA = 0.25;
        exceptedRatioB = 0.5;
        exceptedRatioC = 0.9;
        exceptedRatioD = 1.0;
        exceptedRatioE = 0.15;      
        instanceContracts.setRatio(ratioA, ratioB, ratioC, ratioD, ratioE);
    }
   
    @Test
    public void testSetMax() {            
        instanceContracts.setMax(maxA, maxB, maxC, maxD, maxE);      
    }
    
    @Test
    public void testSetMaxValue() {             
        instanceContracts.setMaxValue(maxValueA, maxValueB, maxValueC, maxValueD, maxValueE);
    }
    
    @Test
    public void testGetRatioA() {
        exceptedRatioA = 0.25;
        assertEquals(exceptedRatioA, instanceContracts.getRatioA(), 0.0);       
    }

    @Test
    public void testGetRatioB() {
        exceptedRatioB = 0.5;
        //assertEquals(exceptedRatioB, instanceContracts.getRatioB());      
    }

    @Test
    public void testGetRatioC() {
        exceptedRatioC = 0.9;      
        //assertEquals(expResult, instance.getRatioC());
    }

    @Test
    public void testGetRatioD() {       
        exceptedRatioD = 1.0;       
        assertEquals(exceptedRatioD, instanceContracts.getRatioD(), 0.0);
    }

    @Test
    public void testGetRatioE() {       
        exceptedRatioE = 0.15;       
        assertEquals(exceptedRatioE, instanceContracts.getRatioE(), 0.0);       
    }
    
    @Test
    public void testGetmaxA() {
        exceptedMaxA = false;
        assertEquals(exceptedMaxA, instanceContracts.getmaxA());
    }

    @Test
    public void testGetmaxB() {
        exceptedMaxB = true; 
        assertEquals(exceptedMaxB, instanceContracts.getmaxB());   
    }

    @Test
    public void testGetmaxC() {      
        exceptedMaxC = false;
        assertEquals(exceptedMaxC, instanceContracts.getmaxC());
    }

    @Test
    public void testGetmaxD() {
        exceptedMaxD = true;
        assertEquals(exceptedMaxD, instanceContracts.getmaxD());
    }

    @Test
    public void testGetmaxE() {
        exceptedMaxE = false;
        assertEquals(exceptedMaxE, instanceContracts.getmaxE());
    }

    @Test
    public void testGetMavalueA() {
        exceptedMaxValueA = -1;
        assertEquals(exceptedMaxValueA, instanceContracts.getMavalueA());
    }

    @Test
    public void testGetMavalueB() {
        exceptedMaxValueB = 40;
        assertEquals(exceptedMaxValueB, instanceContracts.getMavalueB());
    }

    @Test
    public void testGetMavalueC() {
        exceptedMaxValueC = -1;
        assertEquals(exceptedMaxValueC, instanceContracts.getMavalueC());
    }

    @Test
    public void testGetMavalueD() {
        exceptedMaxValueD = 85;
        assertEquals(exceptedMaxValueD, instanceContracts.getMavalueD());
    }

    @Test
    public void testGetMavalueE() {
        exceptedMaxValueE = -1; 
        assertEquals(exceptedMaxValueE, instanceContracts.getMavalueE());
    }
}
