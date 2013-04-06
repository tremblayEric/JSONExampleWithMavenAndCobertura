/**/
package com.INF2015.app.ProjetAgile;

import main.java.com.INF2015.app.Validation.ErrorMessage;
import main.java.com.INF2015.app.Validation.ValidationInputFileException;
import java.text.DecimalFormat;

public  class Dollar {
    
    public static int doubleMontantToInteger(double montant){
        return (int)(Math.ceil(montant * 100));
    }
    
    public static double stringToDouble(String amount) throws ValidationInputFileException{
        try{
         return Double.parseDouble(amount);
        }catch(Exception e){
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_CONVERSION);
        }
    }
    
    public static String fromStringtoConformCashAmount(String amount){
        return replaceComaByDot(removeDolarSymbol(amount));
    }
    
    public static String fromIntegerToConformStringAmount(int amount){
        return Double.toString(fromIntegerToDouble(amount)) + "$";
    }

    protected static double  fromIntegerToDouble(int amount){
        return ((double)amount)/10000;
    }
    
    protected static String removeDolarSymbol(String amount){
        return amount.replace('$', ' ');
    }
    
    protected static String replaceComaByDot(String amount){
        return amount.replace(',', '.');
    }
    
}
