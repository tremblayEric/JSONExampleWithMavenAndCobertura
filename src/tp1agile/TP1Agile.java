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

import Data.ContractList;

import Dom.ParserXML;
import Save.SauvegardeDocumentXml;
import Validation.*;
import java.io.FileNotFoundException;


public class TP1Agile {

    
    public static void main(String[] args) throws Exception {

        /*
        if(args.length == 2){
          
        ParserXML documentXML = new ParserXML("src/XmlFiles/" + args[0]  );
        ReclamationDocumentValidation reclamation = new ReclamationDocumentValidation(documentXML.getDocumentXMLInput());
        SauvegardeDocumentXml persistanceDesDonnees = new SauvegardeDocumentXml ();

        if(reclamation.validerReclamation()){
            ContractList listeContrats = new ContractList();
            CalculReclamation calcul = new CalculReclamation(documentXML.getDocumentXMLInput());
            persistanceDesDonnees.saveReclamation( "src/XmlFiles/" + args[1], calcul);
        }else{
            persistanceDesDonnees.saveSignalInvalidInputXML( "src/XmlFiles/" + args[1]);
        }
          
        }
        */
        SauvegardeDocumentXml persistanceDesDonnees = new SauvegardeDocumentXml("src/XmlFiles/" + args[1]);          
        try{
            ParserXML documentXML = new ParserXML("src/XmlFiles/" + args[0]  );
            ReclamationDocumentValidation reclamation = new ReclamationDocumentValidation(documentXML.getDocumentXMLInput());
            reclamation.validerReclamation();
            CalculReclamation calcul = new CalculReclamation(documentXML.getDocumentXMLInput());    
            persistanceDesDonnees.saveReclamation(calcul);
        }catch(ValidationInputFileException e1){
            persistanceDesDonnees.saveSignalInvalidInputXML(e1.getMessage());    
        } catch(FileNotFoundException e2){
            persistanceDesDonnees.saveSignalInvalidInputXML(ErrorMessage.MESSAGE_ERREUR_INPUT_FILE);    
        } 
        
       
    }
}
