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
 * 
 */
package com.INF2015.app.Validation;

public class ErrorMessage {
    public final static String MESSAGE_ERROR_LOADING_FILE = "LE FICHIER N'A PAS PU ETRE CHARGE EN MEMOIRE";
    public final static String MESSAGE_ERROR_NUMERO_CLIENT = "LE NUMERO DE CLIENT ENTRE EST NON VALIDE";
    public final static String MESSAGE_ERROR_FOLDER = "LE DOSSIER ENTRE EST NON VALIDE";
    public final static String MESSAGE_ERROR_INTEGER = "LE NOMBRE ENTRER DOIT ETRE UN ENTIER";
    public final static String MESSAGE_ERROR_DATE = "LA DATE ENTREE EST NON VALIDE";
    public final static String MESSAGE_ERROR_MONTH = "LE MOIS ENTRE EST NON VALIDE";
    public final static String MESSAGE_ERROR_SIGNE_DOLLAR = "LE SIGNE DE DOLLAR EST MAL PLACE OU EST MANQUANT";
    public final static String MESSAGE_ERROR_SOIN = "LE SOIN ENTRE EST NON VALIDE";
    public final static String MESSAGE_ERROR_MONTANT = "LE MONTANT ENTRE EST NON VALIDE";
    public final static String MESSAGE_ERROR_INPUT_FILE = "LE FICHIER INPUT EST MANQUANT";
    public final static String MESSAGE_ERROR_ELEMENT_XML_RECLAMATIONS_MISSING = "L'ELEMENT RECLAMATIONS DU FICHIER XML D'ENTRE EST MANQUANT";
    public final static String MESSAGE_ERROR_ELEMENT_XML_FOLDER_MISSING = "L'ELEMENT DOSSIER DU FICHIER XML D'ENTRE EST MANQUANT";
    public final static String MESSAGE_ERROR_ELEMENT_XML_MONTH_MISSING = "L'ELEMENT RECLAMATIONS NE CONTIENT PAS D'ELEMENT MOIS";
    public final static String MESSAGE_ERROR_ELEMENT_XML_RECLAMATION_MISSING = "L'ELEMENT RECLAMATIONS NE CONTIENT PAS D'ELEMENT RECLAMATION";
    public final static String MESSAGE_ERROR_ELEMENT_XML_SOIN_MISSING = "UN ELEMENT RECLAMATION NE CONTIENT PAS D'ELEMENT SOIN";
    public final static String MESSAGE_ERROR_ELEMENT_XML_DATE_MISSING = "UN ELEMENT RECLAMATION NE CONTIENT PAS D'ELEMENT DATE";
    public final static String MESSAGE_ERROR_ELEMENT_XML_MONTANT_MISSING = "UN ELEMENT RECLAMATION NE CONTIENT PAS D'ELEMENT MONTANT";
    public final static String MESSAGE_ERROR_CONVERSION = "PROBLEME DE CONVERSION DE STRING A DOUBLE DANS CLASSE DOLLARD";
    public final static String MESSAGE_ERROR_CODE = "LE CODE ENTREE EST NON VALIDE";;
}