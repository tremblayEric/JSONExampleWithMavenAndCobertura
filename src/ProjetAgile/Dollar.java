
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
    /*
     * Metode qui test la sauvegarde avec un JSONObject bidon peut etre modifie
     * pour les calcul et aussi utilisee pour generer un test.
     * JE CROIS QUE CETTE METHODE OFFRE UNE EXELLENT EXEMPLE D'UTILISATION DU 
     * CODE FAIT EN FIN DE SEMAINE, JE CROIS QU'AVEC CA IL SERA ASSEZ SIMPLE DE 
     * MODIFIER QUELQUE METHODE DE CALCUL DE L'ITERATION 2 AFIN DE FAIRE CEUX DE
     * CELLE-CI.
     */
    public static void calculReclamation(){
        
        JSONObject folder = new JSONObject();
        folder.accumulate("dossier", "A100323");
        folder.accumulate("mois", "2013-03");
        
        JavaObjectReclamation  javaRefund1 = null;
        JavaObjectReclamation  javaRefund2 = null;
        JavaObjectReclamation  javaRefund3 = null;
        try{
            
           javaRefund1 = new JavaObjectReclamation("100",(new SimpleDateFormat("yyyy-MM-dd")).parse("2013-03-02"),"65.00");
           javaRefund2 = new JavaObjectReclamation("100",(new SimpleDateFormat("yyyy-MM-dd")).parse("2013-03-11"),"50.00");
           javaRefund3 = new JavaObjectReclamation("100",(new SimpleDateFormat("yyyy-MM-dd")).parse("2013-03-28"),"111.23");

        }catch (Exception e){
            System.out.println(" Probleme lors de la creation de reclamation bidon dans la classe Dollar");
        }
        
                
        JSONObject refund1 = new JSONObject();
        refund1.accumulate("soin", javaRefund1.getSoin());
        refund1.accumulate("date", javaRefund1.getDate().toString());
        refund1.accumulate("montant", javaRefund1.getMontant());
        
        JSONObject refund2 = new JSONObject();
        refund2.accumulate("soin", javaRefund2.getSoin());
        refund2.accumulate("date", javaRefund2.getDate().toString());
        refund2.accumulate("montant", javaRefund2.getMontant());
        
        JSONObject refund3 = new JSONObject();
        refund3.accumulate("soin", javaRefund3.getSoin());
        refund3.accumulate("date", javaRefund3.getDate().toString());
        refund3.accumulate("montant", javaRefund3.getMontant());
        
        JSONArray refunds = new JSONArray();
        refunds.add(refund1);
        refunds.add(refund2);
        refunds.add(refund3);
        
        folder.accumulate("remboursements", refunds);
        
        folder.accumulate("total", "226.23");
        
        JSONFileWriter.writeJSONObbjectToFile(folder);

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
