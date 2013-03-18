
package ProjetAgile;


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
    
    public static String toConformCashAmount(String amount){
        return replaceComaByDot(removeDolarSymbol(amount));
    }
    
    private static String removeDolarSymbol(String amount){
        return amount.replace('$', ' ');
    }
    private static String replaceComaByDot(String amount){
        return amount.replace(',', '.');
    }
    
}
