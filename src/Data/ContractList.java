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
package Data;

import java.util.ArrayList;
import java.util.List;

public class ContractList {

    private List<Soins> contractList = new ArrayList<>();

    public ContractList() {
        
        //creation  et ajout des soins dans la liste
        contractList.add(new Soins("0", "Massothérapie", massoContract()));
        contractList.add(new Soins("100", "Ostéopathie", osteoContract()));
        /*ajout iteration 2 : soin 150 Kinesitherapie*/
        contractList.add(new Soins("150", "kinesitherapie", kinesitherapieContract()));
        /*ajout iteration 2 : soin 175 Medecin generaliste prive*/
        contractList.add(new Soins("175", "Medecin generaliste prive", privateGeneralMedecineContract()));
        contractList.add(new Soins("200", "Psychologie individuelle", PsychoContract()));
        contractList.add(new Soins("300", "Soins dentaires", dentalContract()));
        contractList.add(new Soins("400", "Naturopathie, acuponcture", naturoAcupContract()));
        contractList.add(new Soins("500", "Chiropratie", chiroContract()));
        contractList.add(new Soins("600", "Physiothérapie", physioContract()));
        contractList.add(new Soins("700", "Orthophonie, ergothérapie", orthoErgoContract()));
        
    }

    public double getContractRatioByCareNumber(String careNumber, String contract) {

        int i = 0;
        boolean founded = false;
        double ratio = -1;
        String tempCare = roundCareNumber(careNumber);
        
        while (!founded && i < contractList.size()) {
            if (contractList.get(i).getcareNumber().compareTo(tempCare) == 0) {
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
            if (contractList.get(i).getcareNumber().compareTo(tempCare) == 0) {
                founded = !founded;

                if (contract.compareTo("A") == 0) {
                    max = contractList.get(i).getContract().getMavalueA();
                } else if (contract.compareTo("B") == 0) {
                    max = contractList.get(i).getContract().getMavalueB();
                } else if (contract.compareTo("C") == 0) {
                    max = contractList.get(i).getContract().getMavalueC();
                } else if (contract.compareTo("D") == 0) {
                    max = contractList.get(i).getContract().getMavalueD();
                } else if (contract.compareTo("E") == 0) {
                    max = contractList.get(i).getContract().getMavalueE();
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

    private String roundCareNumber(String careNumber){
        
        String tempCare = "";
        
        if(careNumber.charAt(0) == '3'){
            tempCare = "300";
        }else{
            tempCare = careNumber;
        }
        return tempCare;
         
    }
    
    private Contracts massoContract(){
        //masso
        Contracts massoContract = new Contracts();
        massoContract.setRatio(0.25, 0.5, 0.9, 1.0, 0.15);
        massoContract.setMax(false, true, false, true,false);
        massoContract.setMaxValue(-1, 40, -1, 85,-1);
        
        return massoContract;
  
    }
    
    private Contracts osteoContract(){
        /*osteo  :: modif iteraion 2 sur le contrat B couvert a 100% sans max
                                            contrat A couvert a 35%
                                            contrat C couvert a 95%*/
        Contracts osteoContract = new Contracts();
        osteoContract.setRatio(0.35, 0.5, 0.95, 1.0, 0.25);
        osteoContract.setMax(false, true, false, true,false);
        osteoContract.setMaxValue(-1, 50, -1, 75,-1);
        
        return osteoContract;
    }
    
    private Contracts kinesitherapieContract() {
        /*ajout iteration 2 : contrat de Kinesitherapie*/
        Contracts kinesitherapieContract = new Contracts();
        kinesitherapieContract.setRatio(0.0, 0.7, 0.85, 1.0, 0.15);
        kinesitherapieContract.setMax(false, false, false, true,false);
        kinesitherapieContract.setMaxValue(-1, -1, -1, 150,-1);
        
        return kinesitherapieContract;
}
    
    private Contracts privateGeneralMedecineContract(){    
        /*ajout iteration 2 : contrat de medecin generaliste prive*/
        Contracts privateGeneralMedecineContract = new Contracts();
        privateGeneralMedecineContract.setRatio(0.5, 0.75, 0.9, 0.95, 0.25);
        privateGeneralMedecineContract.setMax(false, false, false, false,true);
        privateGeneralMedecineContract.setMaxValue(-1, -1, -1, -1,20);
        
        return privateGeneralMedecineContract;
    }
    
    private Contracts PsychoContract(){
        //psycho
        Contracts psychoContract = new Contracts();
        psychoContract.setRatio(0.25, 1.0, 0.9, 1.0, 0.12);
        psychoContract.setMax(false, false, false, true,false);
        psychoContract.setMaxValue(-1, -1, -1, 100,-1);
        
        return psychoContract;
    }
    
    private Contracts dentalContract(){
        //dentaires
        Contracts dentalContract = new Contracts();
        dentalContract.setRatio(0.0, 0.5, 0.9, 1.0, 0.6);
        dentalContract.setMax(false, false, false, false,false);
        dentalContract.setMaxValue(-1, -1, -1, -1,-1);
        
        return dentalContract;
    }
    
    private Contracts naturoAcupContract(){
    //naturo et acupuncture
        Contracts naturoAcupContract = new Contracts();
        naturoAcupContract.setRatio(0.0, 0.0, 0.9, 1.0, 0.25);
        naturoAcupContract.setMax(false, false, false, true,true);
        naturoAcupContract.setMaxValue(-1, -1, -1, 65,15);
        
        return naturoAcupContract;
}
    
    private Contracts chiroContract(){
        //chiro modif iteration 2 contrat D remb. a 100% sans max
        Contracts chiroContract = new Contracts();
        chiroContract.setRatio(0.25, 0.5, 0.9, 1.0, 0.3);
        chiroContract.setMax(false, true, false, false,true);
        chiroContract.setMaxValue(-1, 50, -1, -1,20);
        
        return chiroContract;
    }
    
    private Contracts  physioContract(){
        //physio modif. iteration 2 contrat C couvert a 75%
        Contracts physioContract = new Contracts();
        physioContract.setRatio(0.4, 1.0, 0.75, 1.0, 0.15);
        physioContract.setMax(false, false, false, true,false);
        physioContract.setMaxValue(-1, -1, -1, 100,-1);
        
        return physioContract;
    }
    
    private Contracts orthoErgoContract(){
        
        //ortho
        Contracts orthoErgoContract = new Contracts();
        orthoErgoContract.setRatio(0.0, 0.7, 0.9, 1.0, 0.22);
        orthoErgoContract.setMax(false, false, false, true,false);
        orthoErgoContract.setMaxValue(-1, -1, -1, 90,-1);
        
        return orthoErgoContract;
    }
    
}
