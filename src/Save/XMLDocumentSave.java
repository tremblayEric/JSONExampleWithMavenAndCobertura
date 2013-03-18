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
import ProjetAgile.ReclamationCalcul;

public class XMLDocumentSave {

    private String filePath = "refunt.xml";
    private Document document;
    private DecimalFormat df;
    private Element element;
    private ReclamationCalcul reclamation;
    private NodeList nodeList;

    public XMLDocumentSave() {
    }

    public XMLDocumentSave(String filePath) {
        this.filePath = filePath;
    }

    public void saveToFile(Document document) 
            throws Exception {
        Source domSource = new DOMSource(document);
        File xmlFile = new File(filePath);
        Result serializationResult = new StreamResult(xmlFile);
        Transformer xmlTransformer = TransformerFactory.newInstance().newTransformer();
        xmlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        xmlTransformer.transform(domSource, serializationResult);
    }

    public void saveReclamation(ReclamationCalcul reclamation) 
            throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        this.reclamation = reclamation;
        df = new DecimalFormat("#0.00");
        xmlDocumentRefundsInitialisation();
        nodeList = document.getElementsByTagName("remboursements");
        createFolderNode();
        createMonthNode();
        serialyzeReclamation();
        createTotalNode();
        try {
            saveToFile(document);
        } catch (Exception e) {
        }
    }

    public void saveSignalInvalidInputXML(String message) 
            throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        xmlDocumentRefundsInitialisation();
        createErrorMessageNode();
        nodeList.item(0).setTextContent(message);
        Source domSource = new DOMSource(document);
        File file = new File(filePath);
        Result result = new StreamResult(file);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSource, result);
    }

    private void createErrorMessageNode() {
        nodeList = document.getElementsByTagName("remboursements");
        element = document.createElement("message");
        nodeList.item(0).appendChild(element);
        nodeList = document.getElementsByTagName("message");
    }

    private void xmlDocumentRefundsInitialisation() 
            throws ParserConfigurationException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        document = builder.newDocument();
        document.setXmlVersion("1.0");
        element = document.createElement("remboursements");
        document.appendChild(element);
    }

    private void serialyzeReclamation() {
        for (int i = 0; i < reclamation.getReclamationList().size(); ++i) {
            createRefundNode();
            createCareNode(i);
            createDateNode(i);
            createAmountNode(i);
        }
    }
    
    private void createRefundNode() {
        nodeList = document.getElementsByTagName("remboursements");
        element = document.createElement("remboursement");
        nodeList.item(0).appendChild(element);
    }

    private void createCareNode(int i) {
        nodeList = document.getElementsByTagName("remboursement");
        element = document.createElement("soin");
        nodeList.item(i).appendChild(element);
        nodeList = document.getElementsByTagName("soin");
        nodeList.item(i).setTextContent(reclamation.getCareList().get(i));
    }

    private void createDateNode(int i) {
        nodeList = document.getElementsByTagName("remboursement");
        element = document.createElement("date");
        nodeList.item(i).appendChild(element);
        nodeList = document.getElementsByTagName("date");
        nodeList.item(i).setTextContent(reclamation.getDateList().get(i));
    }

    private void createAmountNode(int i) {
        nodeList = document.getElementsByTagName("remboursement");
        element = document.createElement("montant");
        nodeList.item(i).appendChild(element);
        nodeList = document.getElementsByTagName("montant");
        nodeList.item(i).setTextContent(df.format(((reclamation.doCalculList()).get(i))).toString() + "$");
    }

    private void createTotalNode() {
        nodeList = document.getElementsByTagName("remboursements");
        element = document.createElement("total");
        element.setTextContent(df.format((reclamation.addRefunds())).toString() + "$");
        nodeList.item(0).appendChild(element);
    }

    private void createMonthNode() {
        nodeList = document.getElementsByTagName("remboursements");
        element = document.createElement("mois");
        nodeList.item(0).appendChild(element);
        nodeList = document.getElementsByTagName("mois");
        nodeList.item(0).setTextContent(reclamation.getMonth());
    }

    public void createFolderNode() {
        element = document.createElement("dossier");
        nodeList.item(0).appendChild(element);
        nodeList = document.getElementsByTagName("dossier");
        nodeList.item(0).setTextContent(reclamation.getFolderNumber());
    }
}
