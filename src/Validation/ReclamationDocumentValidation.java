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
  
    public ReclamationDocumentValidation(Document document){
      this.document = document;
  }

    public boolean validerReclamation() throws ValidationInputFileException{
//        numeroClientValide(); // client
//        estContratValide();  // contrat
        estDossierValide();
        estMoisValide();
        estDateValide(); 
        coherenceMoisDate();
        signeDollardPresentPartout();
        soinsValide();
        return true;
    }

    private boolean estDossierValide() throws ValidationInputFileException{
        String numeroDossier = getNumeroDossier();
       /* if (numeroDossier.substring(0, 1) != "A" || numeroDossier.substring(0, 1) != "B" || 
            numeroDossier.substring(0, 1) != "C" || numeroDossier.substring(0, 1) != "D" ||
            numeroDossier.substring(0, 1) != "E"){  */
        if (!(numeroDossier.charAt(0) >= 'A' && numeroDossier.charAt(0) <= 'E' )){
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_CONTRAT);
        }else if (!(numeroDossier.length() == 7 && estUnEntier(numeroDossier.substring(1)))){
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_NUMERO_CLIENT);
        }       
        return true;
    }
    
   /* private boolean estContratValide() throws ValidationInputFileException{
        String contrat = (String) getListNoeud("contrat").get(0);
        if ( !(contrat.charAt(0) >= 'A' && contrat.charAt(0) <= 'D' && contrat.length() == 1) ) { //modification Boris exemple cas AAAA
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_CONTRAT);
        }
        return true;
    } */
    
   /* private boolean numeroClientValide() throws ValidationInputFileException{
        String numeroClient = getNumeroClient();
        if (!(numeroClient.length() == 6 && estUnEntier(numeroClient))) {
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_NUMERO_CLIENT);
        }
        return true;
    } */

    private String getNumeroDossier() {
        return (String) getListNoeud("dossier").get(0);
    }

    private boolean estUnEntier(String numero) throws ValidationInputFileException{
        int i = 0;
        boolean estEntier = true;
        while (estEntier && i < numero.length()) {
            if (!(numero.charAt(i) >= '0' && numero.charAt(i) <= '9')) {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_ENTIER);
            }
            ++i;
        }
        return estEntier;
    }



    private boolean estDateValide( String laDate, String type )throws ValidationInputFileException{
        SimpleDateFormat dateFormat;
        if( type.compareTo("mois") == 0){
            dateFormat = new SimpleDateFormat("yyyy-MM");
        }else{
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        Date d;
        try {
            d = dateFormat.parse(laDate);
            String format = dateFormat.format(d);
            if(!(format.compareTo(laDate) ==  0)){ 
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_DATE);
            }
        } catch (Exception e) {
           
            throw new ValidationInputFileException(e.getMessage());
        }
        return true;
    
    }
    
    private boolean estDateValide() throws ValidationInputFileException{
        List<String> listedate = getListNoeud("date");
        int i = 0;
        //String mois = (String) getListNoeud("mois").get(0);
        if(listedate.size() >= 1){
            while( i < listedate.size() ){
                estDateValide(listedate.get(i), "date");
                i = i + 1;
            }
        }else{
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_DATE);
        }
        return true;
    }
    
    private boolean estMoisValide()throws ValidationInputFileException {
        List<String> listeMois = getListNoeud("mois");
        int i = 0;
        if(listeMois.size() == 1){
            while( i < listeMois.size() ){
                estDateValide(listeMois.get(i), "mois");
                i = i + 1;
            }
        }else{
            throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_MOIS);
        }
        return true;
    }
    
    
    private boolean coherenceMoisDate() throws ValidationInputFileException{
        int i = 0;
        List<String> listeDate = getListNoeud("date");
        List<String> listeMois = getListNoeud("mois");
        try {
            SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-MM");
            Date mois;
            mois = dateFormatMois.parse(listeMois.get(0));
        
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            
            SimpleDateFormat dateFormatM = new SimpleDateFormat("yyyy-MM");
        
            while(i < listeDate.size()){
                
                //date = dateFormat.parse(listeDate.get(i));
                date = dateFormatM.parse(listeDate.get(i));
                if( mois.after(date) || mois.before(date)){
                    throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_MOIS);
                }
                i = i + 1;
            }
        } catch (Exception e) {
            throw new ValidationInputFileException(e.getMessage());
        }
      
        return true;
    }
  
    
    private boolean signeDollardPresentPartout() throws ValidationInputFileException {
        int i = 0;
        List<String> listeReclamations = getListNoeud("montant");
        while ( i < listeReclamations.size()) {
            if (listeReclamations.get(i).charAt(listeReclamations.get(i).length() - 1) != '$') {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_SIGNE_DOLLAR);
            }
            ++i;
        }
        return true;
    }

    private boolean soinsValide()throws ValidationInputFileException {
        int i = 0;
        List<String> list = getListNoeud("soin");
        List<String> listSoinsValides = listSoinsValides();
          while ( i < list.size()) {
            if ( !listSoinsValides.contains(list.get(i)) && !(Integer.parseInt(list.get(i)) >= 300 && Integer.parseInt(list.get(i)) <= 399)) {
                throw new ValidationInputFileException(ErrorMessage.MESSAGE_ERREUR_SOIN);
            }
            ++i;
        } 
        return true;
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

    private List<String> listSoinsValides() {
        List<String> listSoinsValides = new ArrayList();
        listSoinsValides.add("0");
        listSoinsValides.add("100");
        listSoinsValides.add("200");
        listSoinsValides.add("400");
        listSoinsValides.add("500");
        listSoinsValides.add("600");
        listSoinsValides.add("700");
        return listSoinsValides;
    }
} 