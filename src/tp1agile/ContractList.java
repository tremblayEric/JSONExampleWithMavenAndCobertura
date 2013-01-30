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
        massoContracts.setRatio(0.25, 0.5, 0.9, 1.0);
        massoContracts.setMax(false, true, false, true);
        massoContracts.setMaxValue(-1, 40, -1, 85);

        //osteo
        Contracts osteoContracts = new Contracts();
        osteoContracts.setRatio(0.25, 0.5, 0.9, 1.0);
        osteoContracts.setMax(false, true, false, true);
        osteoContracts.setMaxValue(-1, 50, -1, 75);

        //psycho
        Contracts psychoContracts = new Contracts();
        psychoContracts.setRatio(0.25, 1.0, 0.9, 1.0);
        psychoContracts.setMax(false, true, false, true);
        psychoContracts.setMaxValue(-1, 70, -1, 100);

        //dentaires
        Contracts dentalContracts = new Contracts();
        dentalContracts.setRatio(0.0, 5.0, 0.9, 1.0);
        dentalContracts.setMax(false, false, false, false);
        dentalContracts.setMaxValue(-1, -1, -1, -1);

        //naturo et acupuncture
        Contracts naturoAcupContracts = new Contracts();
        naturoAcupContracts.setRatio(0.0, 0.0, 0.9, 1.0);
        naturoAcupContracts.setMax(false, false, false, true);
        naturoAcupContracts.setMaxValue(-1, -1, -1, 65);

        //chiro
        Contracts chiroContracts = new Contracts();
        chiroContracts.setRatio(0.25, 0.5, 0.9, 1.0);
        chiroContracts.setMax(false, true, false, true);
        chiroContracts.setMaxValue(-1, 50, -1, 75);

        //physio
        Contracts physioContracts = new Contracts();
        physioContracts.setRatio(0.4, 1.0, 0.9, 1.0);
        physioContracts.setMax(false, false, false, true);
        physioContracts.setMaxValue(-1, 50, -1, 100);

        //ortho
        Contracts orthoErgoContracts = new Contracts();
        orthoErgoContracts.setRatio(0.0, 0.7, 0.9, 1.0);
        orthoErgoContracts.setMax(false, false, false, true);
        orthoErgoContracts.setMaxValue(-1, -1, -1, 90);

        //creation des soins 
        Soins massotherapie = new Soins("0", "Massothérapie", massoContracts);
        Soins osteopathie = new Soins("100", "Ostéopathie", osteoContracts);
        Soins psychoIndividuelle = new Soins("200", "Psychologie individuelle", psychoContracts);
        Soins soinsDentaires = new Soins("300", "Soins dentaires", dentalContracts);
        Soins NaturoAcupuncture = new Soins("400", "Naturopathie, acuponcture", naturoAcupContracts);
        Soins Chiro = new Soins("500", "Chiropratie", chiroContracts);
        Soins Physio = new Soins("600", "Physiothérapie", physioContracts);
        Soins OrthoErgo = new Soins("700", "Orthophonie, ergothérapie", orthoErgoContracts);

        //le tout dans la liste
        contractList.add(massotherapie);
        contractList.add(osteopathie);
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
        //BUG!!!!!


        while (!founded && i < contractList.size()) {
            if (contractList.get(i).getcareNumber().compareTo(careNumber) == 0) {
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
        
        while (!founded && i < contractList.size()) {
            if (contractList.get(i).getcareNumber().compareTo(careNumber) == 0) {
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



}
