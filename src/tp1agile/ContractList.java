/* Copyright 2011 Jacques Berger

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
 * Modifié dans le cadre du cours : 
 * Programmation dans un environnement agile INF2015 
 * TP1
 * 
 * Par:
 * jpokou
 * pdarveau
 * sayonCisse
 * tremblayEric
 * 
 * UQAM hiver 2013
 */
package tp1agile;

import java.util.ArrayList;
import java.util.List;

public class ContractList {

    private List<Soins> contractList = new ArrayList<>();

    public ContractList() {

        //masso
        Contracts massoContracts = new Contracts();
        massoContracts.setRatio(0.25, 0.5, 0.9, 1.0,0.15);
        massoContracts.setMax(false, true, false, true,false);
        massoContracts.setMaxValue(-1, 40, -1, 85,-1);

        /*osteo  :: modif iteraion 2 sur le contrat B couvert a 100% sans max
                                            contrat A couvert a 35%
                                            contrat C couvert a 95%*/
        Contracts osteoContracts = new Contracts();
        osteoContracts.setRatio(0.35, 0.5, 0.95, 1.0,0.25);
        osteoContracts.setMax(false, true, false, true,false);
        osteoContracts.setMaxValue(-1, 50, -1, 75,-1);
        
        /*ajout iteration 2 : contrat de Kinesitherapie*/
        Contracts kinesitherapieContract = new Contracts();
        kinesitherapieContract.setRatio(0.0, 0.7, 0.85, 1.0,0.15);
        kinesitherapieContract.setMax(false, false, false, true,false);
        kinesitherapieContract.setMaxValue(-1, -1, -1, 150,-1);
        
        /*ajout iteration 2 : contrat de medecin generaliste prive*/
        Contracts privateGeneralMedecineContract = new Contracts();
        privateGeneralMedecineContract.setRatio(0.5, 0.75, 0.9, 0.95,0.25);
        privateGeneralMedecineContract.setMax(false, false, false, false,true);
        privateGeneralMedecineContract.setMaxValue(-1, -1, -1, -1,20);

        //psycho
        Contracts psychoContracts = new Contracts();
        psychoContracts.setRatio(0.25, 1.0, 0.9, 1.0,0.12);
        psychoContracts.setMax(false, false, false, true,false);
        psychoContracts.setMaxValue(-1, -1, -1, 100,-1);

        //dentaires
        Contracts dentalContracts = new Contracts();
        dentalContracts.setRatio(0.0, 0.5, 0.9, 1.0,0.6);
        dentalContracts.setMax(false, false, false, false,false);
        dentalContracts.setMaxValue(-1, -1, -1, -1,-1);

        //naturo et acupuncture
        Contracts naturoAcupContracts = new Contracts();
        naturoAcupContracts.setRatio(0.0, 0.0, 0.9, 1.0,0.25);
        naturoAcupContracts.setMax(false, false, false, true,true);
        naturoAcupContracts.setMaxValue(-1, -1, -1, 65,15);

        //chiro modif iteration 2 contrat D remb. a 100% sans max
        Contracts chiroContracts = new Contracts();
        chiroContracts.setRatio(0.25, 0.5, 0.9, 1.0,0.3);
        chiroContracts.setMax(false, true, false, false,true);
        chiroContracts.setMaxValue(-1, 50, -1, -1,20);

        //physio modif. iteration 2 contrat C couvert a 75%
        Contracts physioContracts = new Contracts();
        physioContracts.setRatio(0.4, 1.0, 0.75, 1.0,0.15);
        physioContracts.setMax(false, false, false, true,false);
        physioContracts.setMaxValue(-1, -1, -1, 100,-1);

        //ortho
        Contracts orthoErgoContracts = new Contracts();
        orthoErgoContracts.setRatio(0.0, 0.7, 0.9, 1.0,0.22);
        orthoErgoContracts.setMax(false, false, false, true,false);
        orthoErgoContracts.setMaxValue(-1, -1, -1, 90,-1);
       
        //creation des soins 
        Soins massotherapie = new Soins("0", "Massothérapie", massoContracts);
        Soins osteopathie = new Soins("100", "Ostéopathie", osteoContracts);
        /*ajout iteration 2 : soin 150 Kinesitherapie*/
        Soins kinesitherapie = new Soins("150", "kinesitherapie", kinesitherapieContract);
        /*ajout iteration 2 : soin 175 Kinesitherapie*/
        Soins privateGeneralMedecine = new Soins("175", "Medecin generaliste prive", privateGeneralMedecineContract);
        Soins psychoIndividuelle = new Soins("200", "Psychologie individuelle", psychoContracts);
        Soins soinsDentaires = new Soins("300", "Soins dentaires", dentalContracts);
        Soins NaturoAcupuncture = new Soins("400", "Naturopathie, acuponcture", naturoAcupContracts);
        Soins Chiro = new Soins("500", "Chiropratie", chiroContracts);
        Soins Physio = new Soins("600", "Physiothérapie", physioContracts);
        Soins OrthoErgo = new Soins("700", "Orthophonie, ergothérapie", orthoErgoContracts);

        //le tout dans la liste
        contractList.add(massotherapie);
        contractList.add(osteopathie);
        contractList.add(kinesitherapie);
        contractList.add(privateGeneralMedecine);
        contractList.add(psychoIndividuelle);
        contractList.add(soinsDentaires);
        contractList.add(NaturoAcupuncture);
        contractList.add(Chiro);
        contractList.add(Physio);
        contractList.add(OrthoErgo);
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
    
}
