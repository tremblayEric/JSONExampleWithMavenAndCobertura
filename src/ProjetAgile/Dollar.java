
package ProjetAgile;

import Parsing.JSONFileWriter;
import Parsing.JavaObjectReclamation;
import java.text.SimpleDateFormat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public  class Dollar {
    
    public static int doubleMontantToInteger(double montant){
        return (int)montant * 100;
    }
    
    public static double stringToDouble(String amount){
        try{
         return Double.parseDouble(amount);
        }catch(Exception e){
            System.out.println("probleme de convetion de string a double dans classe dollar");
        }
        return 0;
    }
    
    public static String fromStringtoConformCashAmount(String amount){
        return replaceComaByDot(removeDolarSymbol(amount));
    }
    
    public static String fromIntegerToConformStringAmount(int amount){
        return Double.toString(fromIntegerToDouble(amount)) + "$";
    }

    protected static double  fromIntegerToDouble(int amount){
        return ((double)amount)/100;
    }
    
    protected static String removeDolarSymbol(String amount){
        return amount.replace('$', ' ');
    }
    protected static String replaceComaByDot(String amount){
        return amount.replace(',', '.');
    }
    
}
