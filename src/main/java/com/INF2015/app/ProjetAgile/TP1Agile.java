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
package com.INF2015.app.ProjetAgile;

import com.INF2015.app.Parsing.JSONReclamationsParsing;
import com.INF2015.app.Parsing.JavaObjectDossier;
import com.INF2015.app.Save.JSONRefundsSerialization;
import com.INF2015.app.Validation.ErrorMessage;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TP1Agile {

    public static void main(String[] args)
            throws Exception {


        if (args.length == 2) {

            try {
                /*parsing du document en entre*/
                JSONReclamationsParsing test = new JSONReclamationsParsing("JSONFile/" + args[0]);
                /*recuperation des donnees pour les calculs*/
                JavaObjectDossier testDossier = test.getJavaObjectDossier();
                /*Calculs*/
                RefundCalculation calcul = new RefundCalculation(testDossier);
                List uneListe = calcul.getRefundList();
                /*Serialisation des resultats*/
                SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-MM");
                JSONRefundsSerialization.JSONRefundsSerialization("JSONFile/" + args[1], testDossier.getFolderNumber(), dateFormatMois.format(testDossier.getFolderDate()), uneListe);

            } catch (FileNotFoundException e) {
                JSONRefundsSerialization.JSONRefundsSerializationError("JSONFile/" + args[1], ErrorMessage.MESSAGE_ERROR_INPUT_FILE);
            } catch (Exception e) {
                JSONRefundsSerialization.JSONRefundsSerializationError("JSONFile/" + args[1], e.getMessage());
            }

        } else {
            JSONRefundsSerialization.JSONRefundsSerializationError("JSONFile/" + args[1], ErrorMessage.MESSAGE_ERROR_INPUT_FILE);
        }

    }
}
