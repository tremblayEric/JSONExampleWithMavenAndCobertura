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

import static org.junit.Assert.*;
import org.junit.Test;

public class ErrorMessageTest {

    @Test
    public void testErrorMEssage() {

        final String MESSAGE_ERROR_LOADING_FILE = "LE FICHIER N'A PAS PU ETRE CHARGE EN MEMOIRE";
        final String MESSAGE_ERROR_NUMERO_CLIENT = "LE NUMERO DE CLIENT ENTRE EST NON VALIDE";
        final String MESSAGE_ERROR_FOLDER = "LE DOSSIER ENTRE EST NON VALIDE";
        final String MESSAGE_ERROR_INTEGER = "LE NOMBRE ENTRER DOIT ETRE UN ENTIER";
        final String MESSAGE_ERROR_DATE = "LA DATE ENTREE EST NON VALIDE";
        final String MESSAGE_ERROR_MONTH = "LE MOIS ENTRE EST NON VALIDE";
        final String MESSAGE_ERROR_SIGNE_DOLLAR = "LE SIGNE DE DOLLAR EST MAL PLACE OU EST MANQUANT";
        final String MESSAGE_ERROR_SOIN = "LE SOIN ENTRE EST NON VALIDE";
        final String MESSAGE_ERROR_MONTANT = "LE MONTANT ENTRE EST NON VALIDE";
        final String MESSAGE_ERROR_INPUT_FILE = "LE FICHIER INPUT EST MANQUANT";
        final String MESSAGE_ERROR_ELEMENT_JSON_RECLAMATIONS_MISSING = "L'ELEMENT RECLAMATIONS DU FICHIER XML D'ENTRE EST MANQUANT";
        final String MESSAGE_ERROR_ELEMENT_JSON_FOLDER_MISSING = "L'ELEMENT DOSSIER DU FICHIER XML D'ENTRE EST MANQUANT";
        final String MESSAGE_ERROR_ELEMENT_JSON_MONTH_MISSING = "L'ELEMENT RECLAMATIONS NE CONTIENT PAS D'ELEMENT MOIS";
        final String MESSAGE_ERROR_ELEMENT_JSON_RECLAMATION_MISSING = "L'ELEMENT RECLAMATIONS NE CONTIENT PAS D'ELEMENT RECLAMATION";
        final String MESSAGE_ERROR_ELEMENT_JSON_SOIN_MISSING = "UN ELEMENT RECLAMATION NE CONTIENT PAS D'ELEMENT SOIN";
        final String MESSAGE_ERROR_ELEMENT_JSON_DATE_MISSING = "UN ELEMENT RECLAMATION NE CONTIENT PAS D'ELEMENT DATE";
        final String MESSAGE_ERROR_ELEMENT_JSON_MONTANT_MISSING = "UN ELEMENT RECLAMATION NE CONTIENT PAS D'ELEMENT MONTANT";
        final String MESSAGE_ERROR_CONVERSION = "PROBLEME DE CONVERSION DE STRING A DOUBLE DANS CLASSE DOLLARD";
        final String MESSAGE_ERROR_CODE = "LE CODE ENTREE EST NON VALIDE";


        assertEquals(ErrorMessage.MESSAGE_ERROR_LOADING_FILE, MESSAGE_ERROR_LOADING_FILE);
        assertEquals(ErrorMessage.MESSAGE_ERROR_NUMERO_CLIENT, MESSAGE_ERROR_NUMERO_CLIENT);
        assertEquals(ErrorMessage.MESSAGE_ERROR_FOLDER, MESSAGE_ERROR_FOLDER);
        assertEquals(ErrorMessage.MESSAGE_ERROR_INTEGER, MESSAGE_ERROR_INTEGER);
        assertEquals(ErrorMessage.MESSAGE_ERROR_DATE, MESSAGE_ERROR_DATE);
        assertEquals(ErrorMessage.MESSAGE_ERROR_MONTH, MESSAGE_ERROR_MONTH);
        assertEquals(ErrorMessage.MESSAGE_ERROR_SIGNE_DOLLAR, MESSAGE_ERROR_SIGNE_DOLLAR);
        assertEquals(ErrorMessage.MESSAGE_ERROR_SOIN, MESSAGE_ERROR_SOIN);
        assertEquals(ErrorMessage.MESSAGE_ERROR_MONTANT, MESSAGE_ERROR_MONTANT);
        assertEquals(ErrorMessage.MESSAGE_ERROR_INPUT_FILE, MESSAGE_ERROR_INPUT_FILE);
        assertEquals(ErrorMessage.MESSAGE_ERROR_ELEMENT_JSON_RECLAMATIONS_MISSING, MESSAGE_ERROR_ELEMENT_JSON_RECLAMATIONS_MISSING);
        assertEquals(ErrorMessage.MESSAGE_ERROR_ELEMENT_JSON_FOLDER_MISSING, MESSAGE_ERROR_ELEMENT_JSON_FOLDER_MISSING);
        assertEquals(ErrorMessage.MESSAGE_ERROR_ELEMENT_JSON_MONTH_MISSING, MESSAGE_ERROR_ELEMENT_JSON_MONTH_MISSING);
        assertEquals(ErrorMessage.MESSAGE_ERROR_ELEMENT_JSON_RECLAMATION_MISSING, MESSAGE_ERROR_ELEMENT_JSON_RECLAMATION_MISSING);
        assertEquals(ErrorMessage.MESSAGE_ERROR_ELEMENT_JSON_SOIN_MISSING, MESSAGE_ERROR_ELEMENT_JSON_SOIN_MISSING);
        assertEquals(ErrorMessage.MESSAGE_ERROR_ELEMENT_JSON_DATE_MISSING, MESSAGE_ERROR_ELEMENT_JSON_DATE_MISSING);
        assertEquals(ErrorMessage.MESSAGE_ERROR_ELEMENT_JSON_MONTANT_MISSING, MESSAGE_ERROR_ELEMENT_JSON_MONTANT_MISSING);
        assertEquals(ErrorMessage.MESSAGE_ERROR_CONVERSION, MESSAGE_ERROR_CONVERSION);
        assertEquals(ErrorMessage.MESSAGE_ERROR_CODE, MESSAGE_ERROR_CODE);
    }
}
