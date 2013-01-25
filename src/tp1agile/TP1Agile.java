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


public class TP1Agile {

    
    public static void main(String[] args) throws Exception {

        
        ParserXML documentXML = new ParserXML(args[0]);
        ReclamationDocument reclamation = new ReclamationDocument(documentXML.getDocumentXMLInput());
        SauvegardeDocumentXml persistanceDesDonnees = new SauvegardeDocumentXml ();
        
        ContractList listeContrats = new ContractList();
        
        System.out.println(listeContrats.getContractMaxValueByCareNumber( "100",  "B"));
        System.out.println(listeContrats.getContractRatioByCareNumber("5",  "D"));
        
        
        if(reclamation.validerReclamation()){
            System.out.println("valide");
        }else{
            System.out.println("non-valide " + args[1]);
            persistanceDesDonnees.saveSignalInvalidInputXML(args[1]);
        }
           

    }
}
