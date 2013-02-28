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
package Save;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.text.*;
import tp1agile.CalculReclamation;

public class SauvegardeDocumentXml {

    private String filePath = "refunt.xml";

    public SauvegardeDocumentXml() {
    }

    public SauvegardeDocumentXml(String filePath) {
        this.filePath = filePath;
    }

    public void saveToFile(Document document) throws Exception {
        Source domSource = new DOMSource(document);
        File xmlFile = new File(filePath);
        Result serializationResult = new StreamResult(xmlFile);
        Transformer xmlTransformer = TransformerFactory.newInstance().newTransformer();
        xmlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        xmlTransformer.transform(domSource, serializationResult);
    }

    public void saveReclamation(CalculReclamation reclamation) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        Document document;
        DecimalFormat df = new DecimalFormat("#0.00");
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        document = builder.newDocument();
        document.setXmlVersion("1.0");
        Element element = document.createElement("remboursements");
        document.appendChild(element);

        NodeList nodeList = document.getElementsByTagName("remboursements");
        element = document.createElement("dossier");
        nodeList.item(0).appendChild(element);
        nodeList = document.getElementsByTagName("dossier");
        nodeList.item(0).setTextContent(reclamation.getNumeroDossier());


        createMonthNode(reclamation, nodeList, document, element);
        serialyzeReclamation(reclamation, nodeList, document, element, df);
        createTotalNode(reclamation, nodeList, document, element, df);


        try {
            saveToFile(document);
        } catch (Exception e) {
        }

    }

    public void createMonthNode(CalculReclamation reclamation, NodeList nodeList, Document document, Element element) {
        nodeList = document.getElementsByTagName("remboursements");
        element = document.createElement("mois");
        nodeList.item(0).appendChild(element);
        nodeList = document.getElementsByTagName("mois");
        nodeList.item(0).setTextContent(reclamation.getMois());
    }

    public void saveSignalInvalidInputXML(String message) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

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
        nodeList.item(0).setTextContent(message);

        Source domSource = new DOMSource(doc2);
        File file = new File(filePath);
        Result result = new StreamResult(file);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSource, result);
    }

    private void serialyzeReclamation(CalculReclamation reclamation, NodeList nodeList, Document document, Element element, DecimalFormat df) {

        for (int i = 0; i < reclamation.getListeDesReclamations().size(); ++i) {
            createRefundNode(reclamation, nodeList, document, element, i);
            createCareNode(reclamation, nodeList, document, element, i);
            createDateNode(reclamation, nodeList, document, element, i);
            createAmountNode(reclamation, nodeList, document, element, i, df);
        }
    }

    private void createRefundNode(CalculReclamation reclamation, NodeList nodeList, Document document, Element element, int i) {
        nodeList = document.getElementsByTagName("remboursements");
        element = document.createElement("remboursement");
        nodeList.item(0).appendChild(element);
    }

    private void createCareNode(CalculReclamation reclamation, NodeList nodeList, Document document, Element element, int i) {
        nodeList = document.getElementsByTagName("remboursement");
        element = document.createElement("soin");
        nodeList.item(i).appendChild(element);
        nodeList = document.getElementsByTagName("soin");
        nodeList.item(i).setTextContent(reclamation.getListeSoins().get(i));
    }

    private void createDateNode(CalculReclamation reclamation, NodeList nodeList, Document document, Element element, int i) {
        nodeList = document.getElementsByTagName("remboursement");
        element = document.createElement("date");
        nodeList.item(i).appendChild(element);
        nodeList = document.getElementsByTagName("date");
        nodeList.item(i).setTextContent(reclamation.getListeDate().get(i));
    }

    private void createAmountNode(CalculReclamation reclamation, NodeList nodeList, Document document, Element element, int i, DecimalFormat df) {
        nodeList = document.getElementsByTagName("remboursement");
        element = document.createElement("montant");
        nodeList.item(i).appendChild(element);
        nodeList = document.getElementsByTagName("montant");
        nodeList.item(i).setTextContent(df.format(((reclamation.effectuerListCalcul()).get(i))).toString() + "$");
    }

    private void createTotalNode(CalculReclamation reclamation, NodeList nodeList, Document document, Element element, DecimalFormat df) {
        nodeList = document.getElementsByTagName("remboursements");
        element = document.createElement("total");
        element.setTextContent(df.format((reclamation.addAllRefunds())).toString() + "$");
        nodeList.item(0).appendChild(element);
    }
}
