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
 */
package MockData;

import java.util.ArrayList;
import java.util.List;

public class ContractList {

    private List<Care> contractList = new ArrayList<>();

    public ContractList(){
        //creation  et ajout des soins dans la liste
        contractList.add(new Care("0", "Massothérapie", massoContract()));
        contractList.add(new Care("100", "Ostéopathie", osteoContract()));
        contractList.add(new Care("150", "kinesitherapie", kinesitherapieContract()));
        contractList.add(new Care("175", "Medecin generaliste prive", privateGeneralMedecineContract()));
        contractList.add(new Care("200", "Psychologie individuelle", psychoContract()));
        contractList.add(new Care("300", "Soins dentaires", dentalContract()));
        contractList.add(new Care("400", "Naturopathie, acuponcture", naturoAcupContract()));
        contractList.add(new Care("500", "Chiropratie", chiroContract()));
        contractList.add(new Care("600", "Physiothérapie", physioContract()));
        contractList.add(new Care("700", "Orthophonie, ergothérapie", orthoErgoContract()));
    }

    public Double getContractRatioByCareNumber(String careNumber, String contract){
        int i = 0;
        boolean founded = false;
        double ratio = -1;
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

    public int getContractMaxValueByCareNumber(String careNumber, String contract){
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
    
    public boolean getContractMaxValueByCareNumberExist(String careNumber, String contract){
        boolean exist = true;
        if( getContractMaxValueByCareNumber( careNumber, contract ) == -1 ){
            exist = false;
        }
        return exist;
    }

       public int getMaxValueByCareNumber(String careNumber){
        int i = 0;
        boolean founded = false;
        int max = -1;
        String tempCare = roundCareNumber(careNumber);
        while (!founded && i < contractList.size()) {
            if (contractList.get(i).getCareNumber().compareTo(tempCare) == 0) {
                founded = !founded;
                max = contractList.get(i).getContract().getMaxMonthlyRefund();
            }        
            ++i;
        }
        return max;           
    }  
       
    private String roundCareNumber(String careNumber){
        String tempCare = careNumber;
        if(careNumber.charAt(0) == '3'){
            tempCare = "300";
        }
        return tempCare;
    }
    
    private Contracts massoContract(){
        Contracts massoContract = new Contracts();
        massoContract.setRatio(0.25, 0.5, 0.9, 1.0, 0.15);
        massoContract.setMax(false, true, false, true,false);
        massoContract.setMaxValue(-1, 4000, -1, 8500,-1);
        massoContract.setMaxMonthlyRefund(-1);//ajout iteration #3
        return massoContract;
    }
    
    private Contracts osteoContract(){
        Contracts osteoContract = new Contracts();
        osteoContract.setRatio(0.35, 0.5, 0.95, 1.0, 0.25);
        osteoContract.setMax(false, true, false, true,false);
        osteoContract.setMaxValue(-1, 5000, -1, 7500,-1);
        osteoContract.setMaxMonthlyRefund(250);//ajout iteration #3
        return osteoContract;
    }
    
    private Contracts kinesitherapieContract() {
        Contracts kinesitherapieContract = new Contracts();
        kinesitherapieContract.setRatio(0.0, 0.0, 0.85, 1.0, 0.15);
        kinesitherapieContract.setMax(false, false, false, true,false);
        kinesitherapieContract.setMaxValue(-1, -1, -1, 15000,-1);
        kinesitherapieContract.setMaxMonthlyRefund(-1);//ajout iteration #3
        return kinesitherapieContract;
    }
    
    private Contracts privateGeneralMedecineContract(){    
        Contracts privateGeneralMedecineContract = new Contracts();
        privateGeneralMedecineContract.setRatio(0.5, 0.75, 0.9, 0.95, 0.25);
        privateGeneralMedecineContract.setMax(false, false, false, false,true);
        privateGeneralMedecineContract.setMaxValue(-1, -1, -1, -1,2000);
        privateGeneralMedecineContract.setMaxMonthlyRefund(200);//ajout iteration #3
        return privateGeneralMedecineContract;
    }
    
    private Contracts psychoContract(){
        Contracts psychoContract = new Contracts();
        psychoContract.setRatio(0.25, 1.0, 0.9, 1.0, 0.12);
        psychoContract.setMax(false, false, false, true,false);
        psychoContract.setMaxValue(-1, -1, -1, 10000,-1);
        psychoContract.setMaxMonthlyRefund(250);//ajout iteration #3
        return psychoContract;
    }
    
    private Contracts dentalContract(){
        Contracts dentalContract = new Contracts();
        dentalContract.setRatio(0.0, 0.5, 0.9, 1.0, 0.6);
        dentalContract.setMax(false, false, false, false,false);
        dentalContract.setMaxValue(-1, -1, -1, -1,-1);
        dentalContract.setMaxMonthlyRefund(-1);//ajout iteration #3
        return dentalContract;
    }
    
    private Contracts naturoAcupContract(){
        Contracts naturoAcupContract = new Contracts();
        naturoAcupContract.setRatio(0.0, 0.0, 0.9, 1.0, 0.25);
        naturoAcupContract.setMax(false, false, false, true,true);
        naturoAcupContract.setMaxValue(-1, -1, -1, 6500,1500);
        naturoAcupContract.setMaxMonthlyRefund(-1);//ajout iteration #3
        return naturoAcupContract;
    }
    
    private Contracts chiroContract(){
        Contracts chiroContract = new Contracts();
        chiroContract.setRatio(0.25, 0.5, 0.9, 1.0, 0.3);
        chiroContract.setMax(false, true, false, false,true);
        chiroContract.setMaxValue(-1, 5000, -1, -1,2000);
        chiroContract.setMaxMonthlyRefund(150);//ajout iteration #3
        return chiroContract;
    }
    
    private Contracts  physioContract(){
        Contracts physioContract = new Contracts();
        physioContract.setRatio(0.4, 1.0, 0.75, 1.0, 0.15);
        physioContract.setMax(false, false, false, true,false);
        physioContract.setMaxValue(-1, -1, -1, 10000,-1);
        physioContract.setMaxMonthlyRefund(300);//ajout iteration #3
        return physioContract;
    }
    
    private Contracts orthoErgoContract(){
        Contracts orthoErgoContract = new Contracts();
        orthoErgoContract.setRatio(0.0, 0.7, 0.9, 1.0, 0.22);
        orthoErgoContract.setMax(false, false, false, true,false);
        orthoErgoContract.setMaxValue(-1, -1, -1, 9000,-1);
        orthoErgoContract.setMaxMonthlyRefund(-1);//ajout iteration #3
        return orthoErgoContract;
    }
}
