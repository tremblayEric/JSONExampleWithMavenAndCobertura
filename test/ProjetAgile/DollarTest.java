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
 * ModifiÃƒÂ© dans le cadre du cours : 
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
package ProjetAgile;

import Validation.ValidationInputFileException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;


public class DollarTest {
    
    Dollar instance = null;

    @Test
    public void testDoubleMontantToInteger() {
        double montant = 12.0;
        int expResult = 1200;
        int result = instance.doubleMontantToInteger(montant);
        assertEquals(expResult, result);
    }

    @Test
    public void testStringToDouble() throws ValidationInputFileException {
        String amount = "100";
        double expResult = 100.0;
        double result = instance.stringToDouble(amount);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testFromStringtoConformCashAmount() {
        String amount = "525,55$";
        String expResult = "525.55 ";
        String result = instance.fromStringtoConformCashAmount(amount);
        assertEquals(expResult, result);
    }

    @Test
    public void testFromIntegerToConformStringAmount() {
        int amount = 33000;
        String expResult = "3.3$";
        String result = instance.fromIntegerToConformStringAmount(amount);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFromIntegerToDouble() {
        int amount = 70000;
        double expResult = 7.0;
        double result = instance.fromIntegerToDouble(amount);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testRemoveDolarSymbol() {
        String amount = "150$";
        String expResult = "150 ";
        String result = instance.removeDolarSymbol(amount);
        assertEquals(expResult, result);
    }

    @Test
    public void testReplaceComaByDot() {
        String amount = "111,11";
        String expResult = "111.11";
        String result = Dollar.replaceComaByDot(amount);
        assertEquals(expResult, result);
    }
}
