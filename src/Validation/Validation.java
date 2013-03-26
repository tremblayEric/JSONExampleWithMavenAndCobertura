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
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class Validation {
    
    public static void checkElementsFolder(JSONObject folder, String element) throws ValidationInputFileException{
        try{
            if(element.compareTo("reclamations") == 0){
                JSONArray reclamations = folder.getJSONArray(element);
                for(int i = 0; i < reclamations.size();++i){
                    valueIsNotEmpty(((JSONObject)reclamations.get(i)).getString("soin"), 
                                     ErrorMessage.MESSAGE_ERROR_SOIN);
                    valueIsNotEmpty(((JSONObject)reclamations.get(i)).getString("date"),
                                     ErrorMessage.MESSAGE_ERROR_DATE);
                    valueIsNotEmpty(((JSONObject)reclamations.get(i)).getString("montant"),
                                     ErrorMessage.MESSAGE_ERROR_MONTANT);
                }
            }else if(element.compareTo("dossier") == 0 || element.compareTo("mois") == 0 ){
                valueIsNotEmpty(folder.getString(element), ErrorMessage.MESSAGE_ERROR_FOLDER);
            }else{
                throw new ValidationInputFileException(" l'element " + element + " n est pas un element valide dans le fichier JSON d'entrée");
            }
        }catch (JSONException e){
            //throw new ValidationInputFileException("l'element " + element + " est manquant ou incomplet dans le fichier Jason d entree");
            throw new ValidationInputFileException(" l'un des elements(soin, date, montant) contenu dans " + element + " est manquant dans le fichier JSON d'entrée");
        }
    }
    
    private static void valueIsNotEmpty(String value, String error) throws ValidationInputFileException {
        if(value.isEmpty()){
            throw new ValidationInputFileException(error);
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
        if (!(numeroDossier.length() == 7 && numeroDossier.charAt(0) >= 'A' 
                && numeroDossier.charAt(0) <= 'E')) {
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
    public static String checkDate( String date, String month ) 
            throws ValidationInputFileException {
        checkDateIsValid(date, "date");
        checkDateMonthCoherence(date, month);
        return date;
    }

    //verification du month
    public static String checkMonth(String month) 
            throws ValidationInputFileException {
        return checkDateIsValid(month, "mois");
    }
       
    public static String checkDateIsValid(String laDate, String type) 
            throws ValidationInputFileException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String messageError = ErrorMessage.MESSAGE_ERROR_DATE;
        if (type.compareTo("mois") == 0) {
            dateFormat = new SimpleDateFormat("yyyy-MM");
            messageError = ErrorMessage.MESSAGE_ERROR_MONTH;
        }
        try {
            Date d = dateFormat.parse(laDate);
            String format = dateFormat.format(d);
            if (!(format.compareTo(laDate) == 0)) {
                throw new ValidationInputFileException(messageError);
            }
        } catch (Exception e) {
            throw new ValidationInputFileException(e.getMessage());
        }
        return laDate.toString();
    }

    public static void checkDateMonthCoherence(String dateS, String mois) 
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
            montant = montant.replace(',', '.');
            checkMontantNumeric(montant.substring(0, montant.length() - 2));
            checkMontantSigneDollard(montant);
            return montant;
    }
    
    private static String checkMontantNumeric(String montant) 
            throws ValidationInputFileException {
            try{
                Double.parseDouble(montant);
            }catch(NumberFormatException e){
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_MONTANT);
            }
            return montant;
    }

    private static void checkMontantSigneDollard(String montant) throws ValidationInputFileException {
        if (montant.charAt(montant.length() - 1) != '$') {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_SIGNE_DOLLAR);
        }
    }

    
}