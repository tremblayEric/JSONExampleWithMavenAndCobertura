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


import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReclamationDocumentValidation {

  private Document document;
  
    public ReclamationDocumentValidation(Document document){
      this.document = document;
  }

    public boolean validerReclamation() {

        boolean reclamationValide = false;
        if (numeroClientValide() && estContratValide() && estMoisValide() && estDateValide() && coherenceMoisDate() && signeDollardPresentPartout() && soinsValide()) {
            reclamationValide = !reclamationValide;
        }
        return reclamationValide;
    }

    private boolean numeroClientValide() {

        boolean numeroEstValide = false;
        String numeroClient = getNumeroClient();

        if (numeroClient.length() == 6 && estUnEntier(numeroClient)) {
            numeroEstValide = !numeroEstValide;
           
        }

        return numeroEstValide;

    }

    private String getNumeroClient() {

        return (String) getListNoeud("client").get(0);
    }

    private boolean estUnEntier(String numero) {

        int i = 0;
        boolean estEntier = true;

        while (estEntier && i < numero.length()) {
            if (!(numero.charAt(i) >= '0' && numero.charAt(i) <= '9')) {
                estEntier = !estEntier;
            }
            ++i;
        }

        return estEntier;
    }

    private boolean estContratValide() {

        boolean estContratValide = false;
        String contrat = (String) getListNoeud("contrat").get(0);

        if(contrat.length() > 0){
        if (contrat.charAt(0) >= 'A' && contrat.charAt(0) <= 'D' && contrat.length() == 1) { //modification Boris exemple cas AAAA
            estContratValide = !estContratValide;
        }
        }

        return estContratValide;
    }

    private boolean estDateValide( String laDate, String type ){
        boolean moisValide = false;
        SimpleDateFormat dateFormat;
        if( type.compareTo("mois") == 0){
            dateFormat = new SimpleDateFormat("yyyy-MM");
        }else{
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        Date d = new Date();
        try {
            d = dateFormat.parse(laDate); //
            String format = dateFormat.format(d);
            if(format.compareTo(laDate) ==  0){ 
                moisValide = !moisValide;
            }
        } catch (Exception e) {
        }
        //System.out.println( "Validité de " + laDate + " : " + moisValide );
        return moisValide;
    
    }
    
    private boolean estDateValide() {
        boolean moisValide = true;
        List<String> listedate = getListNoeud("date");
        int i = 0;
        //String mois = (String) getListNoeud("mois").get(0);
        while( moisValide && i < listedate.size() ){
            moisValide = estDateValide(listedate.get(i), "date");
            i = i + 1;
        }
        return moisValide;
    }
    
    private boolean estMoisValide() {
        boolean moisValide = true;
        List<String> listeMois = getListNoeud("mois");
        int i = 0;
        if(listeMois.size() == 1){
            while( moisValide && i < listeMois.size() ){
                moisValide = estDateValide(listeMois.get(i), "mois");
                i = i + 1;
            }
        }else{
            moisValide = false;
        }
        return moisValide;
    }
    
    
    private boolean coherenceMoisDate() {
        boolean moisValide = true;
        int i = 0;
        List<String> listeDate = getListNoeud("date");
        List<String> listeMois = getListNoeud("mois");
        try {
            
            SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-MM");
            Date mois = new Date();
            mois = dateFormatMois.parse(listeMois.get(0));
        
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            
            SimpleDateFormat dateFormatM = new SimpleDateFormat("yyyy-MM");
        
            while(moisValide && i < listeDate.size()){
                
                date = dateFormat.parse(listeDate.get(i));
                date = dateFormatM.parse(listeDate.get(i));
                if( mois.after(date) || mois.before(date)){
                    moisValide = false;
                }
                i = i + 1;
                //System.out.println("Coherence " + listeDate.get(i) + ": " + moisValide );
            }
        
        } catch (Exception e) {
            moisValide = false;
        }
      
        return moisValide;
    }
  
    /*boolean moisValide = false;
        List<String> listeMois = getListNoeud("mois");
        String mois = (String) getListNoeud("mois").get(0);
        for(int i = 0; i < list ; ++i){
            moisValide = estDateValide( listeMois.get(i) );
        }
        
        if (mois.length() == 7 ) {
            moisValide = !moisValide;
        }
        return moisValide;
    */
    
    private boolean signeDollardPresentPartout() {

        int i = 0;
        boolean presentPartout = true;
        List<String> listeReclamations = getListNoeud("montant");

        while (presentPartout && i < listeReclamations.size()) {

            if (listeReclamations.get(i).charAt(listeReclamations.get(i).length() - 1) != '$') {
                presentPartout = !presentPartout;
            }
            ++i;

        }
        return presentPartout;
    }

    private boolean soinsValide() {

        int i = 0;
        boolean valide = true;
        List<String> list = getListNoeud("soin");
        List<String> listSoinsValides = new ArrayList();

        listSoinsValides.add("0");
        listSoinsValides.add("100");
        listSoinsValides.add("200");
        listSoinsValides.add("400");
        listSoinsValides.add("500");
        listSoinsValides.add("600");
        listSoinsValides.add("700");
        

       
          while (valide && i < list.size()) {
            
            if ( !listSoinsValides.contains(list.get(i)) && !(Integer.parseInt(list.get(i)) >= 300 && Integer.parseInt(list.get(i)) <= 399)) {
                valide = false;
            }
            ++i;
        } 
        
        
        return valide;
    }

    private List<String> getListNoeud(String noeud) {

        List<String> list = new ArrayList();

        NodeList listeNoeuds = document.getElementsByTagName(noeud);
        for (int i = 0; i < listeNoeuds.getLength(); ++i) {
            Element client = (Element) listeNoeuds.item(i);
            list.add(client.getTextContent());
        }

        return list;
    }

    
} 
