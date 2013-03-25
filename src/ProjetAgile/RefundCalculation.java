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

    public RefundCalculation(JavaObjectDossier monthlyFile) {
        this.monthlyFile = monthlyFile;
        typeContrat = this.getFolderContract();
        contractsList = new ContractList();
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
            JavaObjectReclamation remboursement = new JavaObjectReclamation(reclamation.getSoin(), reclamation.getDate(), refund / 100 + "");
            listeReclamations.add(remboursement);

        }

        return listeReclamations;

    }

    public List<JavaObjectReclamation> getRefundList() {
        List<JavaObjectReclamation> allRefundList = new ArrayList<>();
        List careList = this.getCareList();
        List dateList = this.getDateFormatList();
        List<Integer> refundList = this.doCalculList();
        for (int i = 0; i < careList.size(); ++i) { // est multiplié par 100 à cause de doubleMontantToInteger               
            allRefundList.add(new JavaObjectReclamation((String) careList.get(i), (Date) dateList.get(i), Integer.toString(refundList.get(i))));
        }
        this.adjustRefundForMaximum(allRefundList);

        return allRefundList;
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

    private String getContractType() {
        String contractType = monthlyFile.getFolderNumber();
        contractType = contractType.substring(0, 1);
        return contractType;
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

    private List<String> getDateList() {
        List<String> dateList = new ArrayList<>();
        List uneListe = this.getReclamationList();
        for (int i = 0; i < uneListe.size(); ++i) {
            JavaObjectReclamation uneReclamation = (JavaObjectReclamation) uneListe.get(i);
            dateList.add(dateFormat.format(uneReclamation.getDate()));
        }
        return dateList;
    }

    private List<Date> getDateFormatList() {
        List<Date> dateList = new ArrayList<>();
        List uneListe = this.getReclamationList();
        for (int i = 0; i < uneListe.size(); ++i) {
            JavaObjectReclamation uneReclamation = (JavaObjectReclamation) uneListe.get(i);
            dateList.add(uneReclamation.getDate());
        }
        return dateList;
    }

    private List<String> getAmountList() {
        List<String> amountList = new ArrayList<>();
        List uneListe = this.getReclamationList();
        for (int i = 0; i < uneListe.size(); ++i) {
            JavaObjectReclamation uneReclamation = (JavaObjectReclamation) uneListe.get(i);
            Integer amount = new Integer(uneReclamation.getMontant());
            amountList.add(amount.toString());
        }
        return amountList;
    }

    private String getFolderContract() {
        return monthlyFile.getFolderNumber().substring(1);
    }

    private String getMonth() {
        String month;
        month = dateFormatMois.format(monthlyFile.getFolderDate());
        return month;
    }

    private void adjustRefundForMaximum(List<JavaObjectReclamation> allRefundList) {

        ContractList contractDetails = new ContractList();
        int monthlyMaxOsteo = contractDetails.getCareMonthlyMaximumLimit("100");
        int monthlyMaxGeneral = contractDetails.getCareMonthlyMaximumLimit("175");
        int monthlyMaxPsycho = contractDetails.getCareMonthlyMaximumLimit("200");
        int monthlyMaxChiro = contractDetails.getCareMonthlyMaximumLimit("500");
        int monthlyMaxPhysio = contractDetails.getCareMonthlyMaximumLimit("600");

        int osteoTotal = 0;
        int general = 0;
        int psycho = 0;
        int chiro = 0;
        int physio = 0;
        int montant = 0;

        for (int i = 0; i < allRefundList.size(); ++i) {
            System.out.println("soin " + allRefundList.get(i).getSoin() + " date " + allRefundList.get(i).getDate() + " montant " + allRefundList.get(i).getMontant());

            JavaObjectReclamation reclamation = allRefundList.get(i);

            if (reclamation.getSoin().compareTo("100") == 0) {

                montant = reclamation.getMontant() / 10000;

                if ((montant + osteoTotal) >= monthlyMaxOsteo) {
                    System.out.println(monthlyMaxOsteo + " " + osteoTotal);
                    reclamation.setMontant((monthlyMaxOsteo - osteoTotal) * 10000);
                    osteoTotal = monthlyMaxOsteo;
                } else if (osteoTotal >= monthlyMaxOsteo) {

                    reclamation.setMontant(0);
                } else {

                    osteoTotal += montant;
                }



            } else if (reclamation.getSoin().compareTo("175") == 0) {
                general += reclamation.getMontant();
                if (reclamation.getMontant() > monthlyMaxGeneral) {
                    reclamation.setMontant(monthlyMaxGeneral);
                }
            } else if (reclamation.getSoin().compareTo("200") == 0) {
                psycho += reclamation.getMontant();
                if (reclamation.getMontant() > monthlyMaxPsycho) {
                    reclamation.setMontant(monthlyMaxPsycho);
                }
            } else if (reclamation.getSoin().compareTo("500") == 0) {
                chiro += reclamation.getMontant();
                if (reclamation.getMontant() > monthlyMaxChiro) {
                    reclamation.setMontant(monthlyMaxChiro);
                }
            } else if (reclamation.getSoin().compareTo("600") == 0) {
                physio += reclamation.getMontant();
                if (reclamation.getMontant() > monthlyMaxPhysio) {
                    reclamation.setMontant(monthlyMaxPhysio);
                }
            }

        }

 
    }

    private List<String> findCareWithMax() {
        List<String> careList = this.getCareList();
        List<String> careMaxList = new ArrayList<>();
        int maxValue;
        String currentCare = "";
        ContractList contractsList = new ContractList();
        for (int i = 0; i < careList.size(); i++) {
            maxValue = contractsList.getMaxValueByCareNumber(careList.get(i));
            if (maxValue != -1 && !(currentCare.equals((String) careList.get(i)))) {
                currentCare = careList.get(i);
                careMaxList.add(careList.get(i));
            }
        }
        return careMaxList;
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
}
