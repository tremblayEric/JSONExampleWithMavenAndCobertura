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
package tp1agile;

import MockData.ContractList;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class ReclamationCalcul {

    private Document document;

    ReclamationCalcul(Document document) {
        this.document = document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    
    public String getContractType() {
        NodeList contracts = document.getElementsByTagName("dossier");
        String contractType = contracts.item(0).getTextContent();
        contractType = contractType.substring(0, 1);
        return contractType;
    }
    
    public List<Element> getReclamationList() {

        List<Element> reclamationList = new ArrayList<>();
        NodeList reclamation = document.getElementsByTagName("reclamation");

        for (int i = 0; i < reclamation.getLength(); i++) {
            Element element = (Element) reclamation.item(i);
            reclamationList.add(element);
        }
        return reclamationList;
    }

    public List<String> getCareList() {

        List<String> careList = new ArrayList<>();
        NodeList care = document.getElementsByTagName("soin");

        for (int i = 0; i < care.getLength(); i++) {
            String element = care.item(i).getTextContent();
            careList.add(element);
        }
        return careList;
    }

    public List<String> getDateList() {

        List<String> dateList = new ArrayList<>();
        NodeList date = document.getElementsByTagName("date");

        for (int i = 0; i < date.getLength(); i++) {
            String element = date.item(i).getTextContent();
            dateList.add(element);
        }
        return dateList;
    }

    public List<String> getAmountList() {

        List<String> amountList = new ArrayList<>();
        NodeList date = document.getElementsByTagName("montant");

        for (int i = 0; i < date.getLength(); i++) {
            String amount = date.item(i).getTextContent();
            amount = amount.replace(',', '.');
            amount = amount.replace('$', ' ');
            amountList.add(amount);
        }
        return amountList;
    } 
    
    public String getFolderNumber() {
        String clientNumber;
        NodeList date = document.getElementsByTagName("dossier");
        clientNumber = date.item(0).getTextContent();
        return clientNumber;
    }


    public String getMonth() {
        String clientNumber;
        NodeList date = document.getElementsByTagName("mois");
        clientNumber = date.item(0).getTextContent();
        return clientNumber;
    }

    public List<Double> doCalculList() {
        List<Double> refundList = new ArrayList<>();
        if (getCareList().size() == getAmountList().size()) {
            for (int i = 0; i < getCareList().size(); ++i) {
                String montant = getAmountList().get(i);
                Double it = Double.parseDouble(montant);
                String st = getCareList().get(i);
                String st2 = getContractType();
                refundList.add(effectuerCalcul(it, st, st2));
            }
        }
    
        return refundList;
    }

    public Double ajoutDesRefunds() {
        Double totalRefund = 0.0;
        if (getCareList().size() == getAmountList().size()) {
            for (int i = 0; i < getCareList().size(); ++i) {
                String montant = getAmountList().get(i);
                Double it = Double.parseDouble(montant);
                String st = getCareList().get(i);
                String st2 = getContractType();
                totalRefund += effectuerCalcul(it, st, st2);
            }
        }
        return totalRefund;
    }

    public double effectuerCalcul(double valeur, String numeroSoin, String contrat) {
        double remboursement = 0;
        ContractList listeContrats = new ContractList();
        remboursement = valeur * listeContrats.getContractRatioByCareNumber(numeroSoin, contrat);      
        if (listeContrats.getContractMaxValueByCareNumberExist(numeroSoin, contrat) && remboursement > listeContrats.getContractMaxValueByCareNumber(numeroSoin, contrat)) {
            remboursement = listeContrats.getContractMaxValueByCareNumber(numeroSoin, contrat);
        }
        return remboursement;
    }
}
