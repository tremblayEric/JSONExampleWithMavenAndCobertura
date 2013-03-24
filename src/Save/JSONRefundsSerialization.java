/* Copyright 2013
 * Dans le cadre du cours : 
 * Programmation dans un environnement agile INF2015 
 *
 * TP1
 * 
 * Par:
 * jpokou
 * pdarveau
 * sayonCisse
 * tremblayEric
 * 
 * UQAM hiver 2013

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
 */
package Save;

import Parsing.JSONFileWriter;
import Parsing.JavaObjectReclamation;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class JSONRefundsSerialization {
    
    
    
    public static void JSONRefundsSerialization(String folderNmber, String folderDate, List reclamationList){
        
        int total = 0;
        JSONObject folder = new JSONObject();
        folder.accumulate("dossier", folderNmber);
        folder.accumulate("mois", folderDate);
        JSONArray refunds = new JSONArray();
        
        SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-MM-dd");
        
        for(int i = 0; i < reclamationList.size(); ++i){ 
           JavaObjectReclamation refund = (JavaObjectReclamation)reclamationList.get(i);
           
           
           JSONObject JSONRefund = new JSONObject();
           JSONRefund.accumulate("soin", refund.getSoin());
           JSONRefund.accumulate("date", dateFormatMois.format(refund.getDate()));
           JSONRefund.accumulate("montant", refund.getMontant());
           total += refund.getMontant();
           
           refunds.add(JSONRefund);
        }
        
        folder.accumulate("remboursements", refunds);
        folder.accumulate("total", total);
        
        JSONFileWriter.writeJSONObbjectToFile(folder);
        
        
        
    }
    
    
    
}