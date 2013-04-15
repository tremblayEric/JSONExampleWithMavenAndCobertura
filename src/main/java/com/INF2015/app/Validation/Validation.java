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
package com.INF2015.app.Validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class Validation {

    public static void checkElementsFolder(JSONObject folder, String element) throws ValidationInputFileException {
        try {
            if (element.compareTo("reclamations") == 0) {
                JSONArray reclamations = folder.getJSONArray(element);
                for (int i = 0; i < reclamations.size(); ++i) {
                    valueIsNotEmpty(((JSONObject) reclamations.get(i)).getString("soin"),
                            ErrorMessage.MESSAGE_ERROR_SOIN);
                    valueIsNotEmpty(((JSONObject) reclamations.get(i)).getString("date"),
                            ErrorMessage.MESSAGE_ERROR_DATE);
                    valueIsNotEmpty(((JSONObject) reclamations.get(i)).getString("montant"),
                            ErrorMessage.MESSAGE_ERROR_MONTANT);
                }
            } else if (element.compareTo("dossier") == 0 || element.compareTo("mois") == 0) {
                valueIsNotEmpty(folder.getString(element), ErrorMessage.MESSAGE_ERROR_FOLDER);
            } else {
                throw new ValidationInputFileException(" l'element " + element + " n est pas un element valide dans le fichier JSON d'entrée");
            }
        } catch (JSONException e) {
            throw new ValidationInputFileException(" l'element " + removeChar(e.getMessage()) + " est manquant dans le fichier JSON d'entrée");
        }
    }

    protected static String removeChar(String string) {
        return string.substring(string.indexOf("\"") + 1, string.lastIndexOf("\""));
    }

    protected static void valueIsNotEmpty(String value, String error) throws ValidationInputFileException {
        if (value.isEmpty()) {
            throw new ValidationInputFileException(error);
        }
    }

    public static String careValidation(String care)
            throws ValidationInputFileException {
        int i = 0;
        List<String> validCareList = careListValidation();
        isInteger(care, ErrorMessage.MESSAGE_ERROR_SOIN);
        if (!validCareList.contains(care) && !(Integer.parseInt(care) >= 300
                && Integer.parseInt(care) <= 399)) {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_SOIN);
        }
        return care.toString();
    }

    public static String codeValidation(String code)
            throws ValidationInputFileException {
        if (code.length() == 1 && (code.compareTo("A") == 0 || code.compareTo("C") == 0)) {
        } else if (code.length() >= 2 && (code.charAt(0) == 'E' || code.charAt(0) == 'H')) {
            isInteger(code.substring(1), ErrorMessage.MESSAGE_ERROR_CODE);
        } else {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_CODE);
        }
        return code.toString();
    }

    public static List<String> careListValidation() {
        List<String> validCareList = new ArrayList();
        validCareList.add("0");
        validCareList.add("100");
        validCareList.add("150");
        validCareList.add("175");
        validCareList.add("200");
        validCareList.add("400");
        validCareList.add("500");
        validCareList.add("600");
        validCareList.add("700");
        return validCareList;
    }

   public static String folderNumberValidation(String fileNumber)
            throws ValidationInputFileException {
        if (!(fileNumber.length() == 7 && fileNumber.charAt(0) >= 'A'
                && fileNumber.charAt(0) <= 'E')) {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_FOLDER);
        }
        isInteger(fileNumber.substring(1), ErrorMessage.MESSAGE_ERROR_FOLDER);
        return fileNumber;
    }

    public static void isInteger(String numero, String message)
            throws ValidationInputFileException {
        int i = 0;
        while (i < numero.length()) {
            if (!(numero.charAt(i) >= '0' && numero.charAt(i) <= '9')) {
                throw new ValidationInputFileException(message);
            }
            ++i;
        }
    }

    public static String dateValidation(String date, String month)
            throws ValidationInputFileException {
        checkDateIsValid(date, "date");
        checkDateMonthCoherence(date, month);
        return date;
    }

    public static String monthValidation(String month)
            throws ValidationInputFileException {
        return checkDateIsValid(month, "mois");
    }

    public static String checkDateIsValid(String laDate, String type)
            throws ValidationInputFileException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String messageError = ErrorMessage.MESSAGE_ERROR_DATE;
        if (type.compareTo("mois") == 0) {
            messageError = ErrorMessage.MESSAGE_ERROR_MONTH;
            dateFormat = new SimpleDateFormat("yyyy-MM");
        }
        try {
            Date d = dateFormat.parse(laDate);
            String format = dateFormat.format(d);
            if (!(format.compareTo(laDate) == 0)) {
                throw new ValidationInputFileException(messageError);
            }
        } catch (ParseException e) {
            throw new ValidationInputFileException(messageError);
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
        } catch (ParseException e) {
            throw new ValidationInputFileException(" L'ELEMENT " + removeChar(e.getMessage()) + " A UN FORMAT NON VALIDE");
        } catch (Exception e) {
            throw new ValidationInputFileException(e.getMessage());
        }
    }

    public static String amountValidation(String amount)
            throws ValidationInputFileException {
        if (amount.length() <= 1) {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_MONTANT);
        }
        amount = amount.replace(',', '.');
        checkMontantNumeric(amount.substring(0, amount.length() - 2));
        dollardSymbolValidation(amount);
        return amount;
    }

    protected static String checkMontantNumeric(String montant)
            throws ValidationInputFileException {
        try {
            Double.parseDouble(montant);
        } catch (NumberFormatException e) {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_MONTANT);
        }
        return montant;
    }

    protected static void dollardSymbolValidation(String montant) throws ValidationInputFileException {
        if (montant.charAt(montant.length() - 1) != '$') {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERROR_SIGNE_DOLLAR);
        }
    }
}