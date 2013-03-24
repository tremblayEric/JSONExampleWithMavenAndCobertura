/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetAgile;

import MockData.ContractList;
import Parsing.JavaObjectDossier;
import Parsing.JavaObjectReclamation;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author pierre
 */

public class RefundCalculation {
    
    JavaObjectDossier monthlyFile;
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-MM");
    
    public RefundCalculation(JavaObjectDossier monthlyFile){
        this.monthlyFile = monthlyFile;
    }
    
    public void testRefundCalculation(){
        
        System.out.println("contractType : " + getContractType());
         List uneListe = this.getReclamationList(); 
         for(int i = 0; i < uneListe.size(); ++i){                        
           JavaObjectReclamation uneReclamation = (JavaObjectReclamation)uneListe.get(i);
           System.out.println( "Date #" + i + ": " + uneReclamation.getDate() );
           System.out.println( "Soin #" + i + ": " + uneReclamation.getSoin() );
           System.out.println( "montant #" + i + ": " + uneReclamation.getMontant() );           
        }   
        List uneListeSoin = this.getCareList();
        for(int i = 0; i < uneListeSoin.size(); ++i){  
            System.out.println("Care : " + uneListeSoin.get(i));        
        }
        List uneListeDate = this.getDateList();
        for(int i = 0; i < uneListeDate.size(); ++i){  
            System.out.println("Date : " + uneListeDate.get(i));
        }   
        List uneListeAmount = this.getAmountList();
        for(int i = 0; i < uneListeAmount.size(); ++i){  
            System.out.println("Amount : " + uneListeAmount.get(i));
        }  
        System.out.println("clientNumber : " + this.getFolderNumber());
        System.out.println("Month : " + this.getMonth());
        
        List<Integer> refundList = this.doCalculList();
        for(int i = 0; i < refundList.size(); ++i){  
            System.out.println("Refund : " + refundList.get(i));
        } 
        List<String> careMaxList = new ArrayList<>();
        careMaxList = this.findCareWithMax();
        for(int i = 0; i < careMaxList.size(); ++i){  
            System.out.println("Care no with max : " + careMaxList.get(i));
        }         
    }
    
    private String getContractType() { 
       String contractType = monthlyFile.getFolderNumber();
       contractType = contractType.substring(0, 1);        
       return contractType;
    }
    
    private List<JavaObjectReclamation> getReclamationList() {                  
            List uneListe = monthlyFile.getFolderReclamationList();
        return uneListe; 
    }

    public List<JavaObjectReclamation> getRefundList() {                  
        List<JavaObjectReclamation> allRefundList = new ArrayList<>();                     
        List careList = this.getCareList();
        List dateList = this.getDateFormatList();
        List<Integer> refundList = this.doCalculList();
        for(int i = 0; i < careList.size(); ++i){                                     // est multiplié par 100 à cause de doubleMontantToInteger               
           allRefundList.add( new JavaObjectReclamation( (String)careList.get(i) , (Date)dateList.get(i) , Integer.toString(refundList.get(i))) );  
        }
        this.adjustRefundForMaximum(allRefundList);
        //List<Integer> indexList = 
        return allRefundList; 
    } 
     
    private List<String> getCareList() {
        List<String> careList = new ArrayList<>();
        List uneListe = this.getReclamationList();
        for(int i = 0; i < uneListe.size(); ++i){
            JavaObjectReclamation uneReclamation = (JavaObjectReclamation)uneListe.get(i);
            careList.add(uneReclamation.getSoin());
        }
        return careList;
    }
    
    private List<String> getDateList() {
        List<String> dateList = new ArrayList<>();
        List uneListe = this.getReclamationList();
        for(int i = 0; i < uneListe.size(); ++i){
            JavaObjectReclamation uneReclamation = (JavaObjectReclamation)uneListe.get(i);
            dateList.add(dateFormat.format(uneReclamation.getDate()));
        }
        return dateList;
    }  
    private List<Date> getDateFormatList() {
        List<Date> dateList = new ArrayList<>();
        List uneListe = this.getReclamationList();
        for(int i = 0; i < uneListe.size(); ++i){
            JavaObjectReclamation uneReclamation = (JavaObjectReclamation)uneListe.get(i);
            dateList.add(uneReclamation.getDate());
        }
        return dateList;
    } 
    
           // amount = amount.replace(',', '.');
           // amount = amount.replace('$', ' ');
    private List<String> getAmountList() {
        List<String> amountList = new ArrayList<>();
        List uneListe = this.getReclamationList();
        for(int i = 0; i < uneListe.size(); ++i){
            JavaObjectReclamation uneReclamation = (JavaObjectReclamation)uneListe.get(i);            
            Integer amount = new Integer(uneReclamation.getMontant());
            amountList.add(amount.toString());
        }
        return amountList;
    }  
    
    // getClientNumber()
    private String getFolderNumber() {
        String clientNumber = monthlyFile.getFolderNumber();
        clientNumber = clientNumber.substring(1);   
        return clientNumber;
    }
    
    // getReclamationMonth();
    private String getMonth() {
        String month;
        month = dateFormatMois.format(monthlyFile.getFolderDate());
        return month;
    }
    //  List<Integer> 
    private void adjustRefundForMaximum(List<JavaObjectReclamation> allRefundList){
        List careList = this.getCareList();
        List dateList = this.getDateFormatList();
        List<Integer> refundList = this.doCalculList();
        ContractList contractsList = new ContractList();
        List<Integer> indexList = new ArrayList<>();
        List<String> careMaxList; // = new ArrayList<>();
        careMaxList = this.findCareWithMax();
        if (!careMaxList.isEmpty()){
            int j = 0;
            int totalRefund = 0;
            int tmpAmount = 0;
            int countSameCareNo = 0;
            for(int i = 0; i < careMaxList.size(); ++i){  
                do{
                   JavaObjectReclamation uneReclamation = (JavaObjectReclamation)allRefundList.get(j); 
                   tmpAmount = uneReclamation.getMontant()/100;
                   if(careMaxList.get(i).equals(uneReclamation.getSoin()) ) {
                       indexList.add(j);
                       totalRefund += uneReclamation.getMontant()/100;
                       countSameCareNo ++;                       
                   }
                   j++;
                }while(j<allRefundList.size());
                int maxValue = contractsList.getMaxValueByCareNumber(careMaxList.get(i));
                
 		j = 0;
                int trop = totalRefund - maxValue*100;
                boolean valueNotReached = true;
                while (valueNotReached && j< indexList.size()){
                    if (trop <= tmpAmount){
                        allRefundList.set( indexList.get(j),  new JavaObjectReclamation( (String)careList.get(indexList.get(j)) , (Date)dateList.get(indexList.get(j)) , Integer.toString(refundList.get(indexList.get(j))-trop)) );                        
                        valueNotReached = !valueNotReached;
                    } else {
                        allRefundList.set( indexList.get(j),  new JavaObjectReclamation( (String)careList.get(indexList.get(j)) , (Date)dateList.get(indexList.get(j)) , Integer.toString(refundList.get(indexList.get(j))-tmpAmount)) ); 
                        trop -= tmpAmount;                        
                        j++;
                    }                                     
                }                                                                                                                                 
            }
        }
    }
    
    
    private List<String> findCareWithMax(){
       List<String> careList = this.getCareList();
       List<String> careMaxList = new ArrayList<>();
       int maxValue;
       String currentCare = "";
       ContractList contractsList = new ContractList();
       for (int i = 0; i<careList.size() ;i++){
           maxValue = contractsList.getMaxValueByCareNumber(careList.get(i));
           if(maxValue != -1 && !(currentCare.equals((String)careList.get(i)))){
               currentCare = careList.get(i);
               careMaxList.add(careList.get(i));               
           }
       }
       return careMaxList;
    }
    
    private List<Integer> doCalculList() {
        List<Integer> refundList = new ArrayList<>();
        if (getCareList().size() == getAmountList().size()) {
            for (int i = 0; i < getCareList().size(); ++i) {
                String amount = getAmountList().get(i);
                int it = Integer.parseInt(amount);
                String st = getCareList().get(i);
                String st2 = getContractType();
                refundList.add(doCalcul(it, st, st2));
            }
        }
        return refundList;
    }
    
     private int addRefunds() {
        int totalRefund = 0;
        if (getCareList().size() == getAmountList().size()) {
            for (int i = 0; i < getCareList().size(); ++i) {
                String montant = getAmountList().get(i);
                int it = Integer.parseInt(montant);
                String st = getCareList().get(i);
                String st2 = getContractType();
                totalRefund += doCalcul(it, st, st2);
            }
        }
        return totalRefund;
    }   
    
    
    private Integer doCalcul(int valeur, String numeroSoin, String contrat) {
        int refund;
        ContractList contractsList = new ContractList();
        refund = valeur * (int)(contractsList.getContractRatioByCareNumber(numeroSoin, contrat)*100);   
        refund = refund / 100;
        if (contractsList.getContractMaxValueByCareNumberExist(numeroSoin, contrat) 
                && refund > contractsList.getContractMaxValueByCareNumber(numeroSoin, contrat)) {
            refund = contractsList.getContractMaxValueByCareNumber(numeroSoin, contrat);
        }
        return refund;
    }   
}
