package com.INF2015.app.ProjetAgile;
/* Copyright 2013
 jpokou
 pdarveau
 sayonCisse
 tremblayEric
  
 UQAM hiver 2013

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
import com.INF2015.app.MockData.ContractList;
import com.INF2015.app.Parsing.JavaObjectFolder;
import com.INF2015.app.Parsing.JavaObjectReclamation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RefundCalculation {

    private JavaObjectFolder monthlyFile;
    private String contractType;
    private ContractList contractsList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat monthDateFormat = new SimpleDateFormat("yyyy-MM");
    private ArrayList familyMember;
    private ArrayList<Integer> careMonthlyMaximumLimitList;

    public RefundCalculation(JavaObjectFolder monthlyFile) {
        this.monthlyFile = monthlyFile;
        contractType = this.getFolderContract();
        contractsList = new ContractList();
        this.familyMember = new ArrayList();
        familyMemberRecuperation();
        this.careMonthlyMaximumLimitList = contractsList.getCareMonthlyMaxLimitArrayList();
    }

    public List<JavaObjectReclamation> getRefundList() {

        List<JavaObjectReclamation> allRefundList = new ArrayList<JavaObjectReclamation>();

        for (int j = 0; j < familyMember.size(); ++j) {

            List reclamations = getFamlyMemberReclamationList((String) familyMember.get(j));
            List tempRefunList = new ArrayList();
            for (int i = 0; i < reclamations.size(); ++i) {                
                JavaObjectReclamation reclamation = (JavaObjectReclamation) reclamations.get(i);

                String care = reclamation.getSoin();
                int amount = reclamation.getMontant();
                Date date = reclamation.getDate();

                tempRefunList.add(new JavaObjectReclamation(care, reclamation.getCode(), date, Integer.toString(doCalcul(amount, care, contractType, reclamation.getCode()))));
            }
            adjustRefundForMaximum(tempRefunList);
            for (int h = 0; h < tempRefunList.size(); ++h) {
                allRefundList.add((JavaObjectReclamation) tempRefunList.get(h));
            }

        }

        return allRefundList;
    }

    protected Integer doCalcul(int value, String careNumber, String contract, String code) {
        int refund;
        int ratio = contractsList.getContractRatioByCareNumber(careNumber, contract);
        if ((code.substring(0, 1)).compareTo("H") == 0) {
            ratio = ratio / 2;
        }

        refund = value * ratio;
        refund = refund / 100;
        if (contractsList.getContractMaxValueByCareNumberExist(careNumber, contract)
                && refund > contractsList.getContractMaxValueByCareNumber(careNumber, contract)) {
            refund = contractsList.getContractMaxValueByCareNumber(careNumber, contract);
        }
        return refund;
    }

    protected String getFolderContract() {
        return monthlyFile.getFolderNumber().substring(0, 1);
    }

    protected void adjustRefundForMaximum(List<JavaObjectReclamation> refundsList) {

        List<Integer> totalList = Arrays.asList(0, 0, 0, 0, 0, 0);

        for (int i = 0; i < refundsList.size(); ++i) {

            JavaObjectReclamation reclamation = refundsList.get(i);
            careFiltering(reclamation, totalList);
        }
    }

    protected void careFiltering(JavaObjectReclamation reclamation, List<Integer> totalList) {

        int result = 0;

        if (reclamation.getSoin().compareTo("100") == 0) {
            applyRefundToCare(reclamation,  totalList, 0);
        } else if (reclamation.getSoin().compareTo("175") == 0) {
            applyRefundToCare(reclamation,  totalList, 1);
        } else if (reclamation.getSoin().compareTo("200") == 0) {
            applyRefundToCare(reclamation,  totalList, 2);
        } else if (reclamation.getSoin().compareTo("500") == 0) {
            applyRefundToCare(reclamation, totalList, 3);
        } else if (reclamation.getSoin().compareTo("600") == 0) {
            applyRefundToCare(reclamation,  totalList, 4);
        }
    }

    protected void applyRefundToCare(JavaObjectReclamation reclamation,  List<Integer> totalList, int care) {

        int result = refundAdjustment(reclamation, totalList.get(4), careMonthlyMaximumLimitList.get(care));
        if (result != -1) {
            totalList.set(care, result);
        }
    }

    protected int refundAdjustment(JavaObjectReclamation reclamation, int total, int monthlyMax) {

        int adjustedRefund = -1;
        int amount = reclamation.getMontant() / 100;

        if ((amount + total) >= monthlyMax) {
            reclamation.setMontant((monthlyMax - total) * 100);
            adjustedRefund = monthlyMax;
        } else if (total >= monthlyMax) {
            reclamation.setMontant(0);
        } else {
            adjustedRefund = amount + total;
        }
       return adjustedRefund;
    }

    protected void familyMemberRecuperation() {

        List reclamations = monthlyFile.getFolderReclamationList();
        String code;
        for (int i = 0; i < reclamations.size(); ++i) {
            code = ((JavaObjectReclamation) reclamations.get(i)).getCode();

            if (!familyMember.contains(code)) {
                familyMember.add(code);
            }
        }
    }

    protected List getFamlyMemberReclamationList(String code) {

        List reclamationMemberList = new ArrayList();
        List reclamations = monthlyFile.getFolderReclamationList();

        for (int i = 0; i < reclamations.size(); ++i) {
            if (((JavaObjectReclamation) reclamations.get(i)).getCode().compareTo(code) == 0) {
                reclamationMemberList.add(reclamations.get(i));
            }
        }
        return reclamationMemberList;
    }
}
