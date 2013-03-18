
package Parsing;

import ProjetAgile.Dollar;
import java.text.DecimalFormat;
import java.util.Date;


public class JavaObjectReclamation {
    
    private String soin;
    private Date date;
    private int montant;
    
    
   public JavaObjectReclamation(String soin, Date date, String montant){
        this.soin = soin;
        this.date = date;
        
        try{
          this.montant = Dollar.doubleMontantToInteger(Dollar.stringToDouble(Dollar.fromStringtoConformCashAmount(montant)));  
        }catch(Exception e){
            System.out.println("plantage de conversion de string en double");
        }     
    }
    
    public String getSoin(){
        return this.soin;
    }
    
    public Date getDate(){
        return this.date;
    }
    
    public int getMontant(){
        return this.montant;
    }
    
    public String toString(){
        return  "\tsoin: " + soin + "\n\t" + "Date: " + date.toString() + "\n\t" + 
                "montant: " + montant + "\n";
    }
    
    
    
    
    
    
    
}
