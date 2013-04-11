/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INF2015.app.ProjetAgile;


import com.INF2015.app.Validation.ValidationInputFileException;
import com.INF2015.app.ProjetAgile.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pierre
 */
public class DollarTest {
    
    public DollarTest() {
    }
    
    Dollar instance = null;
    
    @Test
    public void testDoubleMontantToInteger() {
        double montant = 12.0;
        int expResult = 1200;
        int result = Dollar.doubleMontantToInteger(montant);
        assertEquals(expResult, result);
    }

    @Test
    public void testStringToDouble() throws ValidationInputFileException {
        String amount = "100";
        double expResult = 100.0;
        double result = Dollar.stringToDouble(amount);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testFromStringtoConformCashAmount() {
        String amount = "525,55$";
        String expResult = "525.55 ";
        String result = Dollar.fromStringtoConformCashAmount(amount);
        assertEquals(expResult, result);
    }

    @Test
    public void testFromIntegerToConformStringAmount() {
        int amount = 33000;
        String expResult = "3.30$";
        String result = Dollar.fromIntegerToConformStringAmount(amount);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFromIntegerToDouble() {
        int amount = 70000;
        double expResult = 7.0;
        double result = Dollar.fromIntegerToDouble(amount);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testRemoveDolarSymbol() {
        String amount = "150$";
        String expResult = "150 ";
        String result = Dollar.removeDolarSymbol(amount);
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
