/**/
package ProjetAgile;

import MockData.ContractList;
import Parsing.JavaObjectDossier;
import Parsing.JavaObjectReclamation;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public List<JavaObjectReclamation> remboursement() {

        List listeReclamations = new ArrayList();
        for (int i = 0; i < monthlyFile.getFolderReclamationList().size(); ++i) {

            int refund = 0;
            JavaObjectReclamation reclamation = (JavaObjectReclamation) monthlyFile.getFolderReclamationList().get(i);

            if (contractsList.getContractMaxValueByCareNumber(reclamation.getSoin(), typeContrat) == -1) {
                refund = (reclamation.getMontant() / 100);
            } else {
                if (reclamation.getMontant() > contractsList.getContractMaxValueByCareNumber(reclamation.getSoin(), typeContrat)) {
                    refund = contractsList.getContractMaxValueByCareNumber(reclamation.getSoin(), typeContrat);
                } else {
                    refund = reclamation.getMontant();
                }
            }
            JavaObjectReclamation remboursement = new JavaObjectReclamation(reclamation.getSoin(),reclamation.getCode(), reclamation.getDate(), refund / 100 + "");
            listeReclamations.add(remboursement);

        }

        return listeReclamations;

    }

    public List<JavaObjectReclamation> getRefundList() {
        List<JavaObjectReclamation> allRefundList = new ArrayList<>();
        List careList = this.getCareList();
        List reclamations = monthlyFile.getFolderReclamationList();
        
       for (int i = 0; i < careList.size(); ++i) { // est multiplié par 100 à cause de doubleMontantToInteger               
            JavaObjectReclamation reclamation = (JavaObjectReclamation)reclamations.get(i);
            
                   String soin =  reclamation.getSoin();
                   int montant = reclamation.getMontant();
                   Date date = reclamation.getDate();
               
           allRefundList.add(new JavaObjectReclamation(soin,reclamation.getCode(), date, Integer.toString(doCalcul(montant,soin,typeContrat))));//ATTENTION MODIFIER LORS DU REFACTORING, BEAUCOUP TROP DE LISTE UTILISEES, PREFERABLE D'Y ALLER PAR LES CLASSES.
        }
        this.adjustRefundForMaximum(allRefundList);

        return allRefundList;
    }

    private Integer doCalcul(int valeur, String numeroSoin, String contrat) {
        int refund;
        refund = valeur * contractsList.getContractRatioByCareNumber(numeroSoin, contrat);
        refund = refund / 100;
        if (contractsList.getContractMaxValueByCareNumberExist(numeroSoin, contrat)
                && refund > contractsList.getContractMaxValueByCareNumber(numeroSoin, contrat)) {
            refund = contractsList.getContractMaxValueByCareNumber(numeroSoin, contrat);
        }
        return refund;
    }

    private List<JavaObjectReclamation> getReclamationList() {
        List uneListe = monthlyFile.getFolderReclamationList();
        return uneListe;
    }

    private List<String> getCareList() {
        List<String> careList = new ArrayList<>();
        List uneListe = this.getReclamationList();
        for (int i = 0; i < uneListe.size(); ++i) {
            JavaObjectReclamation uneReclamation = (JavaObjectReclamation) uneListe.get(i);
            careList.add(uneReclamation.getSoin());
        }
        return careList;
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
    
}
