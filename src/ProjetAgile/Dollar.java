
package ProjetAgile;

import Parsing.JSONFileWriter;
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
    /*
     * Metode qui test la sauvegarde avec un JSONObject bidon peut etre modifie
     * pour les calcul et aussi utilisee pour generer un test.
     */
    public static void calculReclamation(){
        
        JSONObject folder = new JSONObject();
        folder.accumulate("dossier", "A100323");
        folder.accumulate("mois", "2013-03");
        
        JSONObject refund1 = new JSONObject();
        refund1.accumulate("soin", "100");
        refund1.accumulate("date", "2013-03-02");
        refund1.accumulate("montant", "65.00");
        
        JSONObject refund2 = new JSONObject();
        refund2.accumulate("soin", "200");
        refund2.accumulate("date", "2013-03-11");
        refund2.accumulate("montant", "54.00");
        
        JSONObject refund3 = new JSONObject();
        refund3.accumulate("soin", "341");
        refund3.accumulate("date", "2013-03-23");
        refund3.accumulate("montant", "13.00");
        
        JSONArray refunds = new JSONArray();
        refunds.add(refund1);
        refunds.add(refund2);
        refunds.add(refund3);
        
        folder.accumulate("remboursements", refunds);
        
        folder.accumulate("total", "132.00");
        
        JSONFileWriter.writeJSONObbjectToFile(folder);

    }
    
    private static double  fromIntegerToDouble(int amount){
        return ((double)amount)/100;
    }
    
    private static String removeDolarSymbol(String amount){
        return amount.replace('$', ' ');
    }
    private static String replaceComaByDot(String amount){
        return amount.replace(',', '.');
    }
    
}
