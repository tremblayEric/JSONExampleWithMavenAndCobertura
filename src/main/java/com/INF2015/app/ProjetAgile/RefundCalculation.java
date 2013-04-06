/**/
package com.INF2015.app.ProjetAgile;

import com.INF2015.app.Parsing.JavaObjectDossier;
import com.INF2015.app.Parsing.JavaObjectReclamation;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.INF2015.app.MockData.ContractList;

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
               
           tempRefunList.add(new JavaObjectReclamation(soin,reclamation.getCode(), date, Integer.toString(doCalcul(montant,soin,typeContrat,"A"))));//ATTENTION MODIFIER LORS DU REFACTORING, BEAUCOUP TROP DE LISTE UTILISEES, PREFERABLE D'Y ALLER PAR LES CLASSES.
        }
       adjustRefundForMaximum(tempRefunList);
       for(int h = 0; h < tempRefunList.size(); ++h){
           allRefundList.add((JavaObjectReclamation)tempRefunList.get(h));
       }
       
        }
        //this.adjustRefundForMaximum(allRefundList);

        return allRefundList;
    }

    private Integer doCalcul(int valeur, String numeroSoin, String contrat, String code) {
        int refund;
        int ratio = contractsList.getContractRatioByCareNumber(numeroSoin, contrat);
        if((code.substring(0,1)).compareTo("H") == 0){
            ratio /= 2;
        }
        
        refund = valeur * ratio;
        refund = refund / 100;
        if (contractsList.getContractMaxValueByCareNumberExist(numeroSoin, contrat)
                && refund > contractsList.getContractMaxValueByCareNumber(numeroSoin, contrat)) {
            refund = contractsList.getContractMaxValueByCareNumber(numeroSoin, contrat);
        }
        return refund;
    }

    private String getFolderContract() {
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

        for (int i = 0; i < allRefundList.size(); ++i) {

            JavaObjectReclamation reclamation = allRefundList.get(i);

            if (reclamation.getSoin().compareTo("100") == 0) {
                  montant = reclamation.getMontant() / 100;

                if ((montant + osteoTotal) >= monthlyMaxOsteo) {
                    reclamation.setMontant((monthlyMaxOsteo - osteoTotal) * 100);
                    osteoTotal = monthlyMaxOsteo;
                } else if (osteoTotal >= monthlyMaxOsteo) {

                    reclamation.setMontant(0);
                } else {

                    osteoTotal += montant;
                }
                

            } else if (reclamation.getSoin().compareTo("175") == 0) {
                
                montant = reclamation.getMontant() / 100;

                if ((montant + generalTotal) >= monthlyMaxGeneral) {
                    reclamation.setMontant((monthlyMaxGeneral - generalTotal) * 100);
                    generalTotal = monthlyMaxGeneral;
                } else if (generalTotal >= monthlyMaxGeneral) {

                    reclamation.setMontant(0);
                } else {

                    generalTotal += montant;
                }
                
            } else if (reclamation.getSoin().compareTo("200") == 0) {
                
                montant = reclamation.getMontant() / 100;

                if ((montant + psychoTotal) >= monthlyMaxPsycho) {
                    reclamation.setMontant((monthlyMaxPsycho - psychoTotal) * 100);
                    psychoTotal = monthlyMaxPsycho;
                } else if (psychoTotal >= monthlyMaxPsycho) {
                    reclamation.setMontant(0);
                } else {
                    psychoTotal += montant;
                }
            } else if (reclamation.getSoin().compareTo("500") == 0) {
                montant = reclamation.getMontant() / 100;

                if ((montant + chiroTotal) >= monthlyMaxChiro) {
                    reclamation.setMontant((monthlyMaxChiro - chiroTotal) * 100);
                    chiroTotal = monthlyMaxChiro;
                } else if (chiroTotal >= monthlyMaxChiro) {

                    reclamation.setMontant(0);
                } else {

                    chiroTotal += montant;
                }
            } else if (reclamation.getSoin().compareTo("600") == 0) {
               montant = reclamation.getMontant() / 100;

                if ((montant + physioTotal) >= monthlyMaxPhysio) {
                    reclamation.setMontant((monthlyMaxPhysio - physioTotal) * 100);
                    physioTotal = monthlyMaxPhysio;
                } else if (physioTotal >= monthlyMaxPhysio) {

                    reclamation.setMontant(0);
                } else {

                    physioTotal += montant;
                }
            }

        }

 
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
