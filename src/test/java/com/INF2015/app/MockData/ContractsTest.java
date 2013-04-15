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
package com.INF2015.app.MockData;

import org.junit.*;
import static org.junit.Assert.*;

public class ContractsTest {

    Contracts instanceContracts;
    int ratioA;
    int ratioB;
    int ratioC;
    int ratioD;
    int ratioE;
    int exceptedRatioA;
    int exceptedRatioB;
    int exceptedRatioC;
    int exceptedRatioD;
    int exceptedRatioE;
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
    public void setUp() throws Exception {
        instanceContracts = new Contracts();
        initializeMaxX();
        initializeMaxValueX();
        initializeRatio();
        instanceContracts.setRatio(ratioA, ratioB, ratioC, ratioD, ratioE);
        instanceContracts.setMax(maxA, maxB, maxC, maxD, maxE);
        instanceContracts.setMaxValue(maxValueA, maxValueB, maxValueC, maxValueD, maxValueE);
    }

    @After
    public void tearDown() throws Exception {
        instanceContracts = null;
        setRatioToZero();
        setMaxToZero();
        setMaxValueToZero();
    }

    public void initializeRatio() {
        ratioA = 25 / 100;
        ratioB = 5 / 10;
        ratioC = 9 / 10;
        ratioD = 1;
        ratioE = 15 / 100;
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
        ratioA = 0;
        ratioB = 0;
        ratioC = 0;
        ratioD = 0;
        ratioE = 0;
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
        exceptedRatioA = 25 / 100;
        double resultGetRatioA = instanceContracts.getRatioA();
        assertEquals(exceptedRatioA, resultGetRatioA, resultGetRatioA);
    }

    @Test
    public void testGetRatioB() {
        exceptedRatioB = 5 / 10;
        double resultGetRatioB = instanceContracts.getRatioB();
        assertEquals(exceptedRatioB, resultGetRatioB, 0.0);
    }

    @Test
    public void testGetRatioC() {
        exceptedRatioC = 9 / 10;
        double resultGetRatioC = instanceContracts.getRatioC();
        assertEquals(exceptedRatioC, resultGetRatioC, 0.0);
    }

    @Test
    public void testGetRatioD() {
        exceptedRatioD = 1;
        double resultGetRatioD = instanceContracts.getRatioD();
        assertEquals(exceptedRatioD, resultGetRatioD, 0.0);
    }

    @Test
    public void testGetRatioE() {
        exceptedRatioE = 15 / 100;
        double resultGetRatioE = instanceContracts.getRatioE();
        assertEquals(exceptedRatioE, resultGetRatioE, 0.0);
    }

    @Test
    public void testGetmaxA() {
        exceptedMaxA = false;
        boolean resultGetMaxA = instanceContracts.getMaxA();
        assertTrue(!resultGetMaxA);
        assertFalse(resultGetMaxA);
    }

    @Test
    public void testGetmaxB() {
        exceptedMaxB = true;
        boolean resultGetMaxB = instanceContracts.getMaxB();
        assertTrue(resultGetMaxB);
        assertFalse(!resultGetMaxB);
    }

    @Test
    public void testGetmaxC() {
        exceptedMaxC = false;
        boolean resultGetMaxC = instanceContracts.getMaxC();
        assertTrue(!resultGetMaxC);
        assertFalse(resultGetMaxC);
    }

    @Test
    public void testGetmaxD() {
        exceptedMaxD = true;
        boolean resultGetMaxD = instanceContracts.getMaxD();
        assertTrue(resultGetMaxD);
        assertFalse(!resultGetMaxD);
    }

    @Test
    public void testGetmaxE() {
        exceptedMaxE = false;
        boolean resultGetMaxE = instanceContracts.getMaxE();
        assertTrue(!resultGetMaxE);
        assertFalse(resultGetMaxE);
    }

    @Test
    public void testGetMavalueA() {
        exceptedMaxValueA = -1;
        double resultGetMavalueA = instanceContracts.getMaxValueA();
        assertEquals(exceptedMaxValueA, resultGetMavalueA, 0.0);
    }

    @Test
    public void testGetMavalueB() {
        exceptedMaxValueB = 40;
        double resultGetMavalueB = instanceContracts.getMaxValueB();
        assertEquals(exceptedMaxValueB, resultGetMavalueB, 0.0);
    }

    @Test
    public void testGetMavalueC() {
        exceptedMaxValueC = -1;
        double resultGetMavalueC = instanceContracts.getMaxValueC();
        assertEquals(exceptedMaxValueC, resultGetMavalueC, 0.0);
    }

    @Test
    public void testGetMavalueD() {
        exceptedMaxValueD = 85;
        double resultGetMavalueD = instanceContracts.getMaxValueD();
        assertEquals(exceptedMaxValueD, resultGetMavalueD, 0.0);
    }

    @Test
    public void testGetMavalueE() {
        exceptedMaxValueE = -1;
        double resultGetMavalueE = instanceContracts.getMaxValueE();
        assertEquals(exceptedMaxValueE, resultGetMavalueE, 0.0);
    }
}
