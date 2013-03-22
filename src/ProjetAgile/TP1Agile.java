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
package ProjetAgile;

import Parsing.JSONReclamationsParsing;
import Parsing.JavaObjectDossier;
import Parsing.JavaObjectReclamation;
import MockData.ContractList;

import XMLParsing.Dom;
import Save.XMLDocumentSave;
import Validation.*;
import java.io.FileNotFoundException;
import java.util.List;

public class TP1Agile {

    public static void main(String[] args) 
            throws Exception {
        
        /*******************************
         * Nouveau code 
         /******************************/
        
        JSONReclamationsParsing test = new JSONReclamationsParsing();
        JavaObjectDossier testDossier = test.getJavaObjectDossier();
        
        System.out.println("Dossier # " + testDossier.getFolderNumber() + " en date du :  "+ testDossier.getFolderDate() +"\n" );
        testDossier.displayReclamationList();
        
        for(int i = 0; i < testDossier.getFolderReclamationList().size(); ++i){
            
            List uneListe = testDossier.getFolderReclamationList();
            
           JavaObjectReclamation uneReclamation = (JavaObjectReclamation)uneListe.get(i);
           System.out.println( "montant #" + i + ": " + uneReclamation.getMontant() );
        }
        
       RefundCalculation calcul = new RefundCalculation(testDossier);
       //System.out.println("getContractType : " + calcul.testRefundCalculation());
       calcul.testRefundCalculation();
       // System.out.println(" devrait egaler 6.21: " + Dollar.fromStringtoConformCashAmount("6,21"));
       // System.out.println(" devrait egaler 10.29$: " + Dollar.fromIntegerToConformStringAmount(1029));
       // Dollar.calculReclamation();
       /********************************/
        
        /*
        if (args.length == 2) {

            XMLDocumentSave dataPersistance = new XMLDocumentSave("src/XmlFiles/" + args[1]);
            try {
                Dom documentXML = new Dom("src/XmlFiles/" + args[0]);
                ReclamationDocumentValidation reclamation = new ReclamationDocumentValidation(documentXML.getDocumentXMLInput());
                reclamation.reclamationValidation();
                ReclamationCalcul calcul = new ReclamationCalcul(documentXML.getDocumentXMLInput());
                dataPersistance.saveReclamation(calcul);
            } catch (ValidationInputFileException e1) {
                dataPersistance.saveSignalInvalidInputXML(e1.getMessage());
            } catch (FileNotFoundException e2) {
                dataPersistance.saveSignalInvalidInputXML(ErrorMessage.MESSAGE_ERROR_INPUT_FILE);
            }

        } else {
            System.out.println("Des arguments de la ligne de commande sont manquants.");
            System.out.println("java -jar Refund.jar inputfile.xml refunds.xml\n");   
            
        }*/
        
    }
}
