
package JSONParsing;

import java.util.Date;


public class JavaObjectReclamation {
    
    private String soin;
    private Date date;
    private String montant;
    
    
    public JavaObjectReclamation(String soin, Date date, String montant){
        this.soin = soin;
        this.date = date;
        this.montant = montant;
    }
    
    public String toString(){
        
        return  "\tsoin: " + soin + "\n\t" + "Date: " + date.toString() + "\n\t" + 
                "montant: " + montant + "\n";
                
    }
    
    
    
    
    
}
