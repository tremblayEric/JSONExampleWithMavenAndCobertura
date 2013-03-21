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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ReclamationDocumentValidation {

    private Document document;
    private Element element;

    public ReclamationDocumentValidation(Document document) {
        this.document = document;
    }

    public void reclamationValidation() throws ValidationInputFileException {
        checkFormXML();
        ifValidFolder();
        isMonthValid();
        isDateValid();
        dateMonthCoherence();
        dollardSymbolCheck();
        validCare();
    }

    private void checkFormXML() throws ValidationInputFileException {
        rootExist( "reclamations",ErrorMessage.MESSAGE_ERROR_ELEMENT_XML_RECLAMATIONS_MISSING);
        NodeList list = document.getElementsByTagName("reclamations");
        element = (Element) list.item(0);
        nodeIsUnique("dossier", ErrorMessage.MESSAGE_ERROR_ELEMENT_XML_FOLDER_MISSING);
        nodeValueisNotEmpty("dossier", ErrorMessage.MESSAGE_ERROR_FOLDER);
        nodeIsUnique("mois", ErrorMessage.MESSAGE_ERROR_ELEMENT_XML_MONTH_MISSING);
        nodeValueisNotEmpty("mois", ErrorMessage.MESSAGE_ERROR_MONTH);
        nodeExist("reclamation", ErrorMessage.MESSAGE_ERROR_ELEMENT_XML_RECLAMATION_MISSING);
        NodeList elementReclamation = element.getElementsByTagName("reclamation");
        checkReclamationForm(elementReclamation);
    }
    
    private void rootExist(String rootName,String error) throws ValidationInputFileException{
        if (document.getElementsByTagName(rootName).getLength() != 1) {
            throw new ValidationInputFileException(error);
        }
    }
    
    private void nodeIsUnique(String nodeName, String error) 
            throws ValidationInputFileException {
        if (element.getElementsByTagName(nodeName).getLength() != 1) {
            throw new ValidationInputFileException(error);
        }
    }

    private void nodeExist(String nodeName, String error) 
            throws ValidationInputFileException {
        if (element.getElementsByTagName(nodeName).getLength() < 1) {
            throw new ValidationInputFileException(error);
        }
    }
    
    private void nodeValueisNotEmpty(String nodeName, String error) 
            throws ValidationInputFileException {
        for(int i = 0; i < element.getElementsByTagName(nodeName).getLength(); ++i) {
            String value = element.getElementsByTagName(nodeName).item(i).getTextContent();
            if(value.isEmpty()) {
                throw new ValidationInputFileException(error);
            }
        }
    }

    private void checkReclamationForm(NodeList list) 
            throws ValidationInputFileException {
        for (int i = 0; i < list.getLength(); ++i) {
            element = (Element) list.item(i);
            nodeIsUnique("soin", ErrorMessage.MESSAGE_ERROR_ELEMENT_XML_SOIN_MISSING);
            nodeValueisNotEmpty("soin", ErrorMessage.MESSAGE_ERROR_SOIN);
            nodeIsUnique("date", ErrorMessage.MESSAGE_ERROR_ELEMENT_XML_DATE_MISSING);
            nodeValueisNotEmpty("date", ErrorMessage.MESSAGE_ERROR_DATE);
            nodeIsUnique("montant", ErrorMessage.MESSAGE_ERROR_ELEMENT_XML_MONTANT_MISSING);
            nodeValueisNotEmpty("montant", ErrorMessage.MESSAGE_ERROR_MONTANT);
        }
    }

    private void ifValidFolder() 
            throws ValidationInputFileException {
        String numeroDossier = getNumeroDossier();
        if (!(numeroDossier.length() == 7 && numeroDossier.charAt(0) >= 'A' && numeroDossier.charAt(0) <= 'E')) {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_FOLDER);
        }
        isInteger(numeroDossier.substring(1), ErrorMessage.MESSAGE_ERROR_FOLDER);
        
    }

    private String getNumeroDossier() {
        return (String) getListNoeud("dossier").get(0);
    }
    
    private void isInteger(String numero, String message) 
            throws ValidationInputFileException {
        int i = 0;
        while (i < numero.length()) {
            if (!(numero.charAt(i) >= '0' && numero.charAt(i) <= '9')) {
                throw new ValidationInputFileException(message);
            }
            ++i;
        }
    }
    
    private void isDateValid(String laDate, String type) 
            throws ValidationInputFileException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (type.compareTo("mois") == 0) {
            dateFormat = new SimpleDateFormat("yyyy-MM");
        }
        try {
            Date d = dateFormat.parse(laDate);
            String format = dateFormat.format(d);
            if (!(format.compareTo(laDate) == 0)) {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_DATE);
            }
        } catch (Exception e) {
            throw new ValidationInputFileException(e.getMessage());
        }
    }

    private void isDateValid() 
            throws ValidationInputFileException {
        List<String> listedate = getListNoeud("date");
        int i = 0;
        if (listedate.size() >= 1) {
            while (i < listedate.size()) {
                isDateValid(listedate.get(i), "date");
                i = i + 1;
            }
        } else {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_DATE);
        }
    }

    private void isMonthValid() 
            throws ValidationInputFileException {
        List<String> monthList = getListNoeud("mois");
        int i = 0;
        if (monthList.size() == 1) {
            while (i < monthList.size()) {
                isDateValid(monthList.get(i), "mois");
                i = i + 1;
            }
        } else {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_MONTH);
        }
    }

    private void dateMonthCoherence() 
            throws ValidationInputFileException {
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
                    throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_DATE);
                }
                i = i + 1;
            }
        } catch (Exception e) {
            throw new ValidationInputFileException(e.getMessage());
        }
    }

    private void dollardSymbolCheck() 
            throws ValidationInputFileException {
        int i = 0;
        List<String> reclamationsList = getListNoeud("montant");
        while (i < reclamationsList.size()) {
            if (reclamationsList.get(i).charAt(reclamationsList.get(i).length() - 1) != '$') {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_SIGNE_DOLLAR);
            }
            ++i;
        }
    }

    private void validCare() 
            throws ValidationInputFileException {
        int i = 0;
        List<String> list = getListNoeud("soin");
        List<String> validCareList = validCareList();
        while (i < list.size()) {
            if (!validCareList.contains(list.get(i)) && !(Integer.parseInt(list.get(i)) >= 300 
                    && Integer.parseInt(list.get(i)) <= 399)) {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_SOIN);
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

    private List<String> validCareList() {
        List<String> validCareList = new ArrayList();
        validCareList.add("0");
        validCareList.add("100");
        validCareList.add("150");//ajout du soins 150 a la validation
        validCareList.add("175");//ajout du soins 175 a la validation
        validCareList.add("200");
        validCareList.add("400");
        validCareList.add("500");
        validCareList.add("600");
        validCareList.add("700");
        return validCareList;
    }
    
/*    
    public static void checkElementsFolder(JSONObject folder, String element){
        try{
            if(element.compareTo("reclamations") == 0){
                JSONArray reclamations = folder.getJSONArray(element);
                for(int i = 0; i < reclamations.size();++i){
                    ((JSONObject)reclamations.get(i)).getString("soin");
                    ((JSONObject)reclamations.get(i)).getString("date");
                    ((JSONObject)reclamations.get(i)).getString("montant");
                }
            }else{
                folder.getString(element);
            }
        }catch (Exception e){
            System.out.println("l'element " + element + " est manquant ou incomplet dans le fichier Jason d entree");
        }
    }
    
        //verification des soins
    public static String checkSoin( String soin ) 
            throws ValidationInputFileException {
        int i = 0;
        List<String> validCareList = validCareList2();
        isInteger2(soin, ErrorMessage.MESSAGE_ERROR_SOIN);
        if (!validCareList.contains(soin) && !(Integer.parseInt(soin) >= 300 
                && Integer.parseInt(soin) <= 399)) {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_SOIN);
        }
        return soin.toString();
    }

    public static List<String> validCareList2() {
        List<String> validCareList = new ArrayList();
        validCareList.add("0");
        validCareList.add("100");
        validCareList.add("150");//ajout du soins 150 a la validation
        validCareList.add("175");//ajout du soins 175 a la validation
        validCareList.add("200");
        validCareList.add("400");
        validCareList.add("500");
        validCareList.add("600");
        validCareList.add("700");
        return validCareList;
    }

    //verification du dossier
    public static String checkFolder(String numeroDossier) 
            throws ValidationInputFileException {
        if (!(numeroDossier.length() == 7 && numeroDossier.charAt(0) >= 'A' && numeroDossier.charAt(0) <= 'E')) {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_FOLDER);
        }
        isInteger2(numeroDossier.substring(1), ErrorMessage.MESSAGE_ERROR_FOLDER);
        return numeroDossier;   
    }
    
    public static void isInteger2(String numero, String message) 
            throws ValidationInputFileException {
        int i = 0;
        while (i < numero.length()) {
            if (!(numero.charAt(i) >= '0' && numero.charAt(i) <= '9')) {
                throw new ValidationInputFileException(message);
            }
            ++i;
        }
    }
    

    //verification de la date
    public static String checkDate( String date ) 
            throws ValidationInputFileException {
        return checkDateIsValid(date, "date");
    }

    //verification du month
    public static String checkMonth(String month) 
            throws ValidationInputFileException {
        return checkDateIsValid(month, "mois");
    }
       
    public static String checkDateIsValid(String laDate, String type) 
            throws ValidationInputFileException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (type.compareTo("mois") == 0) {
            dateFormat = new SimpleDateFormat("yyyy-MM");
        }
        try {
            Date d = dateFormat.parse(laDate);
            String format = dateFormat.format(d);
            if (!(format.compareTo(laDate) == 0)) {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_DATE);
            }
        } catch (Exception e) {
            throw new ValidationInputFileException(e.getMessage());
        }
        return laDate.toString();
    }

    public static void dateMonthCoherence(String dateS, String mois) 
            throws ValidationInputFileException {
        try {
            SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-MM");
            Date month = dateFormatMois.parse(mois);
            Date date;
            SimpleDateFormat dateFormatM = new SimpleDateFormat("yyyy-MM");
            date = dateFormatM.parse(dateS);
            if (month.after(date) || month.before(date)) {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_DATE);
            }
        } catch (Exception e) {
            throw new ValidationInputFileException(e.getMessage());
        }
    }    
    
    //verification du montant
    public static String checkMontant(String montant) 
            throws ValidationInputFileException {
            if (montant.charAt(montant.length() - 1) != '$') {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_SIGNE_DOLLAR);
            }
            return montant.toString();
    }
    */
}