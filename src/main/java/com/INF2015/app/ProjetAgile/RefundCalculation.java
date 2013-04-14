/**/
package com.INF2015.app.ProjetAgile;

import com.INF2015.app.MockData.ContractList;
import com.INF2015.app.Parsing.JavaObjectDossier;
import com.INF2015.app.Parsing.JavaObjectReclamation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RefundCalculation {

    private JavaObjectDossier monthlyFile;
    private String typeContrat;
    private ContractList contractsList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-MM");
    private ArrayList familyMember;

    public RefundCalculation(JavaObjectDossier monthlyFile) {
        this.monthlyFile = monthlyFile;
        typeContrat = this.getFolderContract();
        contractsList = new ContractList();
        this.familyMember = new ArrayList();
        familyMemberRecuperation();
    }

    public List<JavaObjectReclamation> getRefundList() {
       
        List<JavaObjectReclamation> allRefundList = new ArrayList<JavaObjectReclamation>();
        
        for(int j = 0; j < familyMember.size(); ++ j){
        
        List reclamations = getFamlyMemberReclamationList((String)familyMember.get(j));
        List tempRefunList = new ArrayList();
       for (int i = 0; i < reclamations.size(); ++i) { // est multiplié par 100 à cause de doubleMontantToInteger               
            JavaObjectReclamation reclamation = (JavaObjectReclamation)reclamations.get(i);
            
                   String soin =  reclamation.getSoin();
                   int montant = reclamation.getMontant();
                   Date date = reclamation.getDate();
               
           tempRefunList.add(new JavaObjectReclamation(soin,reclamation.getCode(), date, Integer.toString(doCalcul(montant,soin,typeContrat,reclamation.getCode()))));
        }
       adjustRefundForMaximum(tempRefunList);
       for(int h = 0; h < tempRefunList.size(); ++h){
           allRefundList.add((JavaObjectReclamation)tempRefunList.get(h));
       }
       
        }

        return allRefundList;
    }

    protected Integer doCalcul(int valeur, String numeroSoin, String contrat, String code) {
        int refund;
        int ratio = contractsList.getContractRatioByCareNumber(numeroSoin, contrat);
        if((code.substring(0,1)).compareTo("H") == 0){
            ratio = ratio / 2;
        }
        
        refund = valeur * ratio;
        refund = refund / 100;
        if (contractsList.getContractMaxValueByCareNumberExist(numeroSoin, contrat)
                && refund > contractsList.getContractMaxValueByCareNumber(numeroSoin, contrat)) {
            refund = contractsList.getContractMaxValueByCareNumber(numeroSoin, contrat);
        }
        return refund;
    }

    protected String getFolderContract() {
        return monthlyFile.getFolderNumber().substring(0, 1);
    }

    private void adjustRefundForMaximum(List<JavaObjectReclamation> allRefundList) {

        ContractList contractDetails = new ContractList();
        int monthlyMaxOsteo = contractDetails.getCareMonthlyMaximumLimit("100")*100;
        int monthlyMaxGeneral = contractDetails.getCareMonthlyMaximumLimit("175")*100;
        int monthlyMaxPsycho = contractDetails.getCareMonthlyMaximumLimit("200")*100;
        int monthlyMaxChiro = contractDetails.getCareMonthlyMaximumLimit("500")*100;
        int monthlyMaxPhysio = contractDetails.getCareMonthlyMaximumLimit("600")*100;

        int osteoTotal = 0;
        int generalTotal = 0;
        int psychoTotal = 0;
        int chiroTotal = 0;
        int physioTotal = 0;
        int montant = 0;
        
        int result = 0;

        for (int i = 0; i < allRefundList.size(); ++i) {

            JavaObjectReclamation reclamation = allRefundList.get(i);

            if (reclamation.getSoin().compareTo("100") == 0) {
                
               result = refundAdjustment( reclamation,  osteoTotal,  monthlyMaxOsteo); 
               if(result != -1){
                   osteoTotal = result;
               }

            } else if (reclamation.getSoin().compareTo("175") == 0) {
                
               result = refundAdjustment( reclamation,  generalTotal,  monthlyMaxGeneral); 
               if(result != -1){
                   generalTotal = result;
               }
                
            } else if (reclamation.getSoin().compareTo("200") == 0) {
                
               result = refundAdjustment( reclamation,  psychoTotal,  monthlyMaxPsycho); 
               if(result != -1){
                   psychoTotal = result;
               }
                
            } else if (reclamation.getSoin().compareTo("500") == 0) {
                
               result = refundAdjustment( reclamation,  chiroTotal,  monthlyMaxChiro); 
               if(result != -1){
                   chiroTotal = result;
               }
                
                
            } else if (reclamation.getSoin().compareTo("600") == 0) {
                
               result = refundAdjustment( reclamation,  physioTotal,  monthlyMaxPhysio); 
               if(result != -1){
                   physioTotal = result;
               }
              
            }

        }

 
    }
    
    private int refundAdjustment(JavaObjectReclamation reclamation, int total, int monthlyMax){
        
        int retour = -1;
        int montant = reclamation.getMontant() / 100;
        
        if ((montant + total) >= monthlyMax) {
                    reclamation.setMontant((monthlyMax - total) * 100);
                    retour = monthlyMax;
                } else if (total >= monthlyMax) {
                    reclamation.setMontant(0);
                } else {
                    retour =  montant + total;
                }
        
        
        return retour;
    }

    private void familyMemberRecuperation(){
        
        List reclamations = monthlyFile.getFolderReclamationList();
        String code = "";
       for(int i = 0; i < reclamations.size(); ++i ){
           code = ((JavaObjectReclamation)reclamations.get(i)).getCode();
          
           if(!familyMember.contains(code)){
               familyMember.add(code);
           }
           
       }
    }
    
    
    private List getFamlyMemberReclamationList(String code){
        
        List reclamationMemberList = new ArrayList();
        
        List reclamations = monthlyFile.getFolderReclamationList();
        
        for(int i= 0; i < reclamations.size(); ++i){
            if(((JavaObjectReclamation)reclamations.get(i)).getCode().compareTo(code) == 0){
               reclamationMemberList.add(reclamations.get(i)); 
            }
        }
        
        return reclamationMemberList;
    }
    
}
