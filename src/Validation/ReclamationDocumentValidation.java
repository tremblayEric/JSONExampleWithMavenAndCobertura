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
package Validation;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReclamationDocumentValidation {

    private Document document;
    private Element element;

    public ReclamationDocumentValidation(Document document) {
        this.document = document;
    }

    public void validerReclamation() throws ValidationInputFileException {

        checkFormXML();
        ifValidFolder();
        isMonthValid();
        isDateValid();
        dateMonthCoherence();
        dollardSymbolCheck();
        validCare();
        
    }

    private void checkFormXML() throws ValidationInputFileException {

        element = (Element)document;
        nodeIsUnique("reclamations",ErrorMessage.MESSAGE_ERREUR_ELEMENT_XML_RECLAMATIONS_MANQUANT);
        NodeList list = document.getElementsByTagName("reclamations");
        element = (Element) list.item(0);
        nodeIsUnique("dossier",ErrorMessage.MESSAGE_ERREUR_ELEMENT_XML_DOSSIER_MANQUANT);
        nodeIsUnique("mois",ErrorMessage.MESSAGE_ERREUR_ELEMENT_XML_MOIS_MANQUANT);
        nodeExist("reclamation",ErrorMessage.MESSAGE_ERREUR_ELEMENT_XML_RECLAMATION_MANQUANT);
        NodeList elementReclamation = element.getElementsByTagName("reclamation");
        checkReclamationForm(elementReclamation);
        
    }
    
    private void nodeIsUnique( String nodeName, String error) throws ValidationInputFileException{
        if (element.getElementsByTagName(nodeName).getLength() != 1) {
                throw new ValidationInputFileException(error);
            }   
    }
    
    private void nodeExist( String nodeName, String error) throws ValidationInputFileException{
        if (element.getElementsByTagName(nodeName).getLength() < 1) {
                throw new ValidationInputFileException(error);
            }   
    }
    
    private void checkReclamationForm(NodeList list) throws ValidationInputFileException{
        
        for (int i = 0; i < list.getLength(); ++i) {

            element = (Element) list.item(i);
            
            nodeIsUnique("soin",ErrorMessage.MESSAGE_ERREUR_ELEMENT_XML_SOIN_MANQUANT);
            nodeIsUnique("date",ErrorMessage.MESSAGE_ERREUR_ELEMENT_XML_DATE_MANQUANT);
            nodeIsUnique("montant",ErrorMessage.MESSAGE_ERREUR_ELEMENT_XML_MONTANT_MANQUANT);
        }
    }

    private void ifValidFolder() throws ValidationInputFileException {
        String numeroDossier = getNumeroDossier();

        if (!(numeroDossier.charAt(0) >= 'A' && numeroDossier.charAt(0) <= 'E')) {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_DOSSIER);
        } else if (!(numeroDossier.length() == 7 && isInteger(numeroDossier.substring(1)))) {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_NUMERO_CLIENT);
        }
         
    }

    private String getNumeroDossier() {
        return (String) getListNoeud("dossier").get(0);
    }

    private boolean isInteger(String numero) throws ValidationInputFileException {
        int i = 0;
        boolean isInteger = true;
        while (isInteger && i < numero.length()) {
            if (!(numero.charAt(i) >= '0' && numero.charAt(i) <= '9')) {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_ENTIER);
            }
            ++i;
        }
        return isInteger;
    }

    private void isDateValid(String laDate, String type) throws ValidationInputFileException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (type.compareTo("mois") == 0) {
            dateFormat = new SimpleDateFormat("yyyy-MM");
        }
        try {
            Date d = dateFormat.parse(laDate);
            String format = dateFormat.format(d);
            if (!(format.compareTo(laDate) == 0)) {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_DATE);
            }
        } catch (Exception e) {

            throw new ValidationInputFileException(e.getMessage());
        }
        

    }

    private void isDateValid() throws ValidationInputFileException {
        List<String> listedate = getListNoeud("date");
        int i = 0;
        if (listedate.size() >= 1) {
            while (i < listedate.size()) {
                isDateValid(listedate.get(i), "date");
                i = i + 1;
            }
        } else {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_DATE);
        }
        
    }

    private void isMonthValid() throws ValidationInputFileException {
        List<String> monthList = getListNoeud("mois");
        int i = 0;
        if (monthList.size() == 1) {
            while (i < monthList.size()) {
                isDateValid(monthList.get(i), "mois");
                i = i + 1;
            }
        } else {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_MOIS);
        }
        
    }

    private void dateMonthCoherence() throws ValidationInputFileException {
        int i = 0;
        List<String> dateList = getListNoeud("date");
        List<String> monthList = getListNoeud("mois");
        try {
            SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-MM");
            Date month = dateFormatMois.parse(monthList.get(0));

            Date date;

            SimpleDateFormat dateFormatM = new SimpleDateFormat("yyyy-MM");

            while (i < dateList.size()) {

                date = dateFormatM.parse(dateList.get(i));
                if (month.after(date) || month.before(date)) {
                    throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_MOIS);
                }
                i = i + 1;
            }
        } catch (Exception e) {
            throw new ValidationInputFileException(e.getMessage());
        }
        
    }

    private void dollardSymbolCheck() throws ValidationInputFileException {
        int i = 0;
        List<String> reclamationsList = getListNoeud("montant");
        while (i < reclamationsList.size()) {
            if (reclamationsList.get(i).charAt(reclamationsList.get(i).length() - 1) != '$') {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_SIGNE_DOLLAR);
            }
            ++i;
        }
        
    }

    private void validCare() throws ValidationInputFileException {
        int i = 0;
        List<String> list = getListNoeud("soin");
        List<String> validCareList = listSoinsValides();
        while (i < list.size()) {
            if (!validCareList.contains(list.get(i)) && !(Integer.parseInt(list.get(i)) >= 300 && Integer.parseInt(list.get(i)) <= 399)) {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_SOIN);
            }
            ++i;
        }
        
    }

    private List<String> getListNoeud(String noeud) {

        List<String> list = new ArrayList();
        NodeList nodeList = document.getElementsByTagName(noeud);
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Element client = (Element) nodeList.item(i);
            list.add(client.getTextContent());
        }
        return list;
    }

    private List<String> listSoinsValides() {
        List<String> listSoinsValides = new ArrayList();
        listSoinsValides.add("0");
        listSoinsValides.add("100");
        listSoinsValides.add("175");//ajout du soins 175 a la validation
        listSoinsValides.add("200");
        listSoinsValides.add("400");
        listSoinsValides.add("500");
        listSoinsValides.add("600");
        listSoinsValides.add("700");
        return listSoinsValides;
    }
}
