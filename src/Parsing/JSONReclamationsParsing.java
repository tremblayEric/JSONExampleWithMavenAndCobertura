/*
 * Copyright 2011 Jacques Berger.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Modifie par :
 * Sayon Cisse
 * Pierre Darveau
 * Jonatan Pokou
 * Eric Tremblay
 * 
 * dans le cadre du cours :
 * INF2015
 * UQAM
 * Session d'hiver 2013
 */
package Parsing;



import Validation.ErrorMessage;
import Validation.ValidationInputFileException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class JSONReclamationsParsing {
    
    private JavaObjectDossier javaDossier;
    private JSONObject folder;
    
    public JSONReclamationsParsing() throws IOException, ValidationInputFileException{
        
        String JSONFileContent = JSONFileReader.loadFileIntoString("JSONFile/inputFile.json");
        folder = JSONObject.fromObject(JSONFileContent);
        checkElementsFolder("dossier");
        checkElementsFolder("mois");
        checkElementsFolder("reclamations");
        javaDossierFabrication();
        
    }
    
    public JavaObjectDossier getJavaObjectDossier(){
        return this.javaDossier;
    }
    
    private void checkElementsFolder(String element){
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
    
    private void javaDossierFabrication() throws ValidationInputFileException{
        javaDossier = new JavaObjectDossier();
        //javaDossier.setDossier(folder.getString("dossier"));
        javaDossier.setDossier(checkFolder(folder.getString("dossier")));
        //javaDossier.setMois(folder.getString("mois"));
        javaDossier.setMois(checkMonth(folder.getString("mois")));
        reclamationsFromJSONToJava();
    }
    
    
    
    private void reclamationsFromJSONToJava() throws ValidationInputFileException{
        JSONArray reclamations = folder.getJSONArray("reclamations");
        for(int i = 0; i < reclamations.size();++i){
            javaDossier.addToReclamationList(javaReclamationCreation(reclamations.get(i)));
        }
        
    }
    
    private JavaObjectReclamation javaReclamationCreation(Object reclamation) throws ValidationInputFileException{
        JSONObject object = (JSONObject)reclamation;
        //String soin = object.getString("soin").toString().toString();
        String soin = checkSoin(object.getString("soin"));
        Date date = null;
        try{ 
         //date = (new SimpleDateFormat("yyyy-MM-dd")).parse(object.getString("date").toString());
         date = (new SimpleDateFormat("yyyy-MM-dd")).parse(checkDate(object.getString("date")).toString());
        }catch (Exception e){
            System.out.println("date pas correcte");
        }
        //String montant = object.getString("montant").toString();
        String montant = checkMontant(object.getString("montant"));
        return new JavaObjectReclamation(soin,date,montant);
    }
    
    private void displayReclamationList(){
        javaDossier.displayReclamationList();
    }    
    
    //verification des soins
    private String checkSoin( String soin ) 
            throws ValidationInputFileException {
        int i = 0;
        List<String> validCareList = validCareList();
        isInteger(soin, ErrorMessage.MESSAGE_ERROR_SOIN);
        if (!validCareList.contains(soin) && !(Integer.parseInt(soin) >= 300 
                && Integer.parseInt(soin) <= 399)) {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_SOIN);
        }
        return soin.toString();
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

    //verification du dossier
    private String checkFolder(String numeroDossier) 
            throws ValidationInputFileException {
        if (!(numeroDossier.length() == 7 && numeroDossier.charAt(0) >= 'A' && numeroDossier.charAt(0) <= 'E')) {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_FOLDER);
        }
        isInteger(numeroDossier.substring(1), ErrorMessage.MESSAGE_ERROR_FOLDER);
        return numeroDossier;   
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

    //verification de la date
    private String checkDate( String date ) 
            throws ValidationInputFileException {
        return isDateValid(date, "date");
    }

    //verification du month
    private String checkMonth(String month) 
            throws ValidationInputFileException {
        return isDateValid(month, "mois");
    }
       
    private String isDateValid(String laDate, String type) 
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

    private void dateMonthCoherence(String dateS, String mois) 
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
    private String checkMontant(String montant) 
            throws ValidationInputFileException {
            if (montant.charAt(montant.length() - 1) != '$') {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_SIGNE_DOLLAR);
            }
            return montant.toString();
    }   
}
