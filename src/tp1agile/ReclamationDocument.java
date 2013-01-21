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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public class ReclamationDocument {

    private Document document;

    public ReclamationDocument(String documentFilePath)
            throws ParserConfigurationException, SAXException, IOException {
        parseXmlDocument(documentFilePath);
    }

    private void parseXmlDocument(String documentFilePath)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentFactory = initializeDocumentFactory();
        DocumentBuilder parser = documentFactory.newDocumentBuilder();
        document = parser.parse(documentFilePath);
    }

    private DocumentBuilderFactory initializeDocumentFactory() {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setIgnoringComments(true);
        documentFactory.setCoalescing(true);
        documentFactory.setNamespaceAware(true);
        return documentFactory;
    }

    public boolean validerReclamation() {

        boolean reclamationValide = false;


        if (numeroClientValide() && estContratValide() && estMoisValide() && signeDollardPresentPartout() && soinsValide()) {
            reclamationValide = !reclamationValide;
        }

        return reclamationValide;
    }

    public void saveToFile(String filePath) throws Exception {
        Source domSource = new DOMSource(document);
        File xmlFile = new File(filePath);
        Result serializationResult = new StreamResult(xmlFile);
        Transformer xmlTransformer = TransformerFactory.newInstance().newTransformer();
        xmlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        xmlTransformer.transform(domSource, serializationResult);
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

        if (contrat.charAt(0) >= 'A' && contrat.charAt(0) <= 'D' && contrat.length() == 1) { //modification Boris exemple cas AAAA
            estContratValide = !estContratValide;
        }

        return estContratValide;
    }

    private boolean estDateValide( String laDate ){
        boolean moisValide = false;
        SimpleDateFormat dateFormat;
        if(laDate.length() == 7){
            dateFormat = new SimpleDateFormat("yyyy-MM");
        }else{
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        Date d = new Date();
        try {
            d = dateFormat.parse(laDate); //
            String format = dateFormat.format(d);
            //System.out.println("Apres tranformation: " + format);
            if(format.compareTo(laDate) ==  0){ 
                moisValide = !moisValide;
            }
        } catch (Exception e) {
        }
        System.out.println( "Validité de la date: " + moisValide );
        return moisValide;
    
    }
    
    private boolean estMoisValide() {
        boolean moisValide = false;
        String mois = (String) getListNoeud("mois").get(0);
        if (mois.length() == 7 ) {
            moisValide = !moisValide;
        }
        return moisValide;
    }
    
    

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
        listSoinsValides.add("600");

        while (valide && i < list.size()) {
            System.out.println(list.get(i));
            if (!listSoinsValides.contains(list.get(i)) && !(Integer.parseInt(list.get(i)) >= 300 && Integer.parseInt(list.get(i)) <= 399)) {
                valide = !valide;
            }
            ++i;
        }

        if (valide) {
            System.out.println("valide");

        } else {

            System.out.println("non-valide");
        }

        return valide;


    }

    private List getListNoeud(String noeud) {

        List<String> list = new ArrayList();

        NodeList listeNoeuds = document.getElementsByTagName(noeud);
        for (int i = 0; i < listeNoeuds.getLength(); i++) {
            Element client = (Element) listeNoeuds.item(i);
            list.add(client.getTextContent());
        }

        return list;
    }

    private boolean moisValide(String date) {

        boolean valide = false;

        valide = validerAnnee(date);

        if (valide) {
            valide = validerMois(date);
        }

        return valide;

    }

    private boolean validerAnnee(String annee) {

        int i = 0;
        boolean valide = true;


        while (valide && i < 4) {
            if (!(annee.charAt(i) >= '0' && annee.charAt(i) <= '9')) {
                valide = !valide;
            }
        }


        return valide;

    }

    private boolean validerMois(String mois) {


        boolean valide = false;


        if (!(mois.charAt(0) >= '0' && mois.charAt(0) <= '3') || !(mois.charAt(1) >= '0' && mois.charAt(1) <= '3')) {
            valide = !valide;
        }


        return valide;
    }

    private boolean validerJour(String jour) {

        boolean valide = false;

        return valide;
    }
    
    /**
     * temprairement nommée sNonValide
     * méthode invoquée lorsqu'une erreur est détectée dans le fichier d'entrée
     */
    public void sNonValide() throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        Document doc2;
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        doc2 = builder.newDocument();
        doc2.setXmlVersion("1.0");
        Element element = doc2.createElement("remboursements");
        doc2.appendChild(element);
        NodeList nodeList = doc2.getElementsByTagName("remboursements");
        element = doc2.createElement("message");
        nodeList.item(0).appendChild(element);
        nodeList = doc2.getElementsByTagName("message");
        nodeList.item(0).setTextContent("Données invalides");
        
        Source domSource = new DOMSource(doc2);
        File file = new File("refunds2.xml"); // args[1]
        Result result = new StreamResult(file);
        
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSource, result);        
    }
}
