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
 * 
 * 
 * 
 */
package com.INF2015.app.MockData;

import java.util.ArrayList;
import java.util.List;

public class ContractList {

    private List<Care> contractList = new ArrayList<Care>();

    public ContractList() {
        //creation  et ajout des soins dans la liste
        contractList.add(new Care("0", "Massothérapie", massoContract(), -1));
        contractList.add(new Care("100", "Ostéopathie", osteoContract(), 250));
        contractList.add(new Care("150", "kinesitherapie", kinesitherapieContract(), -1));
        contractList.add(new Care("175", "Medecin generaliste prive", privateGeneralMedecineContract(), 200));
        contractList.add(new Care("200", "Psychologie individuelle", psychoContract(), 250));
        contractList.add(new Care("300", "Soins dentaires", dentalContract(), -1));
        contractList.add(new Care("400", "Naturopathie, acuponcture", naturoAcupContract(), -1));
        contractList.add(new Care("500", "Chiropratie", chiroContract(), 150));
        contractList.add(new Care("600", "Physiothérapie", physioContract(), 300));
        contractList.add(new Care("700", "Orthophonie, ergothérapie", orthoErgoContract(), -1));
    }

    public int getContractRatioByCareNumber(String careNumber, String contract) {
        int i = 0;
        boolean founded = false;
        int ratio = -1;
        String tempCare = roundCareNumber(careNumber);
        while (!founded && i < contractList.size()) {
            if (contractList.get(i).getCareNumber().compareTo(tempCare) == 0) {
                founded = !founded;
                if (contract.compareTo("A") == 0) {
                    ratio = contractList.get(i).getContract().getRatioA();
                } else if (contract.compareTo("B") == 0) {
                    ratio = contractList.get(i).getContract().getRatioB();
                } else if (contract.compareTo("C") == 0) {
                    ratio = contractList.get(i).getContract().getRatioC();
                } else if (contract.compareTo("D") == 0) {
                    ratio = contractList.get(i).getContract().getRatioD();
                } else if (contract.compareTo("E") == 0) {
                    ratio = contractList.get(i).getContract().getRatioE();
                }
            }
            ++i;
        }
        return ratio;
    }

    public int getContractMaxValueByCareNumber(String careNumber, String contract) {
        int i = 0;
        boolean founded = false;
        int max = -1;
        String tempCare = roundCareNumber(careNumber);
        while (!founded && i < contractList.size()) {
            if (contractList.get(i).getCareNumber().compareTo(tempCare) == 0) {
                founded = !founded;
                if (contract.compareTo("A") == 0) {
                    max = contractList.get(i).getContract().getMaxValueA();
                } else if (contract.compareTo("B") == 0) {
                    max = contractList.get(i).getContract().getMaxValueB();
                } else if (contract.compareTo("C") == 0) {
                    max = contractList.get(i).getContract().getMaxValueC();
                } else if (contract.compareTo("D") == 0) {
                    max = contractList.get(i).getContract().getMaxValueD();
                } else if (contract.compareTo("E") == 0) {
                    max = contractList.get(i).getContract().getMaxValueE();
                }
            }
            ++i;
        }
        return max;
    }

    public boolean getContractMaxValueByCareNumberExist(String careNumber, String contract) {
        boolean exist = true;
        if (getContractMaxValueByCareNumber(careNumber, contract) == -1) {
            exist = false;
        }
        return exist;
    }

    public int getCareMonthlyMaximumLimit(String careNumber) {

        int i = 0;
        boolean founded = false;
        int max = -1;
        String tempCare = roundCareNumber(careNumber);
        while (!founded && i < contractList.size()) {
            if (contractList.get(i).getCareNumber().compareTo(tempCare) == 0) {
                founded = !founded;
                return contractList.get(i).getMonthlyMaxLimit();
            }
            ++i;
        }

        return 0;
    }

    public ArrayList getCareMonthlyMaxLimitArrayList() {

        ArrayList<Integer> monthlyMaxLimitArrayLis = new ArrayList();

        int monthlyMaxOsteo = getCareMonthlyMaximumLimit("100") * 100;
        int monthlyMaxGeneral = getCareMonthlyMaximumLimit("175") * 100;
        int monthlyMaxPsycho = getCareMonthlyMaximumLimit("200") * 100;
        int monthlyMaxChiro = getCareMonthlyMaximumLimit("500") * 100;
        int monthlyMaxPhysio = getCareMonthlyMaximumLimit("600") * 100;

        monthlyMaxLimitArrayLis.add(monthlyMaxOsteo);
        monthlyMaxLimitArrayLis.add(monthlyMaxGeneral);
        monthlyMaxLimitArrayLis.add(monthlyMaxPsycho);
        monthlyMaxLimitArrayLis.add(monthlyMaxChiro);
        monthlyMaxLimitArrayLis.add(monthlyMaxPhysio);

        return monthlyMaxLimitArrayLis;
    }

    protected String roundCareNumber(String careNumber) {
        String tempCare = careNumber;
        if (careNumber.charAt(0) == '3') {
            tempCare = "300";
        }
        return tempCare;
    }

    protected Contracts massoContract() {
        Contracts massoContract = new Contracts();
        massoContract.setRatio(25, 50, 90, 100, 15);
        massoContract.setMax(false, true, false, true, false);
        massoContract.setMaxValue(-1, 4000, -1, 8500, -1);
        return massoContract;
    }

    protected Contracts osteoContract() {
        Contracts osteoContract = new Contracts();
        osteoContract.setRatio(35, 50, 95, 100, 25);
        osteoContract.setMax(false, true, false, true, false);
        osteoContract.setMaxValue(-1, 5000, -1, 7500, -1);
        return osteoContract;
    }

    protected Contracts kinesitherapieContract() {
        Contracts kinesitherapieContract = new Contracts();
        kinesitherapieContract.setRatio(0, 0, 85, 100, 15);
        kinesitherapieContract.setMax(false, false, false, true, false);
        kinesitherapieContract.setMaxValue(-1, -1, -1, 15000, -1);
        return kinesitherapieContract;
    }

    protected Contracts privateGeneralMedecineContract() {
        Contracts privateGeneralMedecineContract = new Contracts();
        privateGeneralMedecineContract.setRatio(50, 75, 90, 95, 25);
        privateGeneralMedecineContract.setMax(false, false, false, false, true);
        privateGeneralMedecineContract.setMaxValue(-1, -1, -1, -1, 2000);
        return privateGeneralMedecineContract;
    }

    protected Contracts psychoContract() {
        Contracts psychoContract = new Contracts();
        psychoContract.setRatio(25, 100, 90, 100, 12);
        psychoContract.setMax(false, false, false, true, false);
        psychoContract.setMaxValue(-1, -1, -1, 10000, -1);
        return psychoContract;
    }

    protected Contracts dentalContract() {
        Contracts dentalContract = new Contracts();
        dentalContract.setRatio(0, 50, 90, 100, 60);
        dentalContract.setMax(false, false, false, false, false);
        dentalContract.setMaxValue(-1, -1, -1, -1, -1);
        return dentalContract;
    }

    protected Contracts naturoAcupContract() {
        Contracts naturoAcupContract = new Contracts();
        naturoAcupContract.setRatio(0, 0, 90, 100, 25);
        naturoAcupContract.setMax(false, false, false, true, true);
        naturoAcupContract.setMaxValue(-1, -1, -1, 6500, 1500);
        return naturoAcupContract;
    }

    protected Contracts chiroContract() {
        Contracts chiroContract = new Contracts();
        chiroContract.setRatio(25, 50, 90, 100, 30);
        chiroContract.setMax(false, true, false, false, true);
        chiroContract.setMaxValue(-1, 5000, -1, -1, 2000);
        return chiroContract;
    }

    protected Contracts physioContract() {
        Contracts physioContract = new Contracts();
        physioContract.setRatio(40, 100, 75, 100, 15);
        physioContract.setMax(false, false, false, true, false);
        physioContract.setMaxValue(-1, -1, -1, 10000, -1);
        return physioContract;
    }

    protected Contracts orthoErgoContract() {
        Contracts orthoErgoContract = new Contracts();
        orthoErgoContract.setRatio(0, 70, 90, 100, 22);
        orthoErgoContract.setMax(false, false, false, true, false);
        orthoErgoContract.setMaxValue(-1, -1, -1, 9000, -1);
        return orthoErgoContract;
    }
}
