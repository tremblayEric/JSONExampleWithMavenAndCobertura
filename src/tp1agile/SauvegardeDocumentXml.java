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

public class SauvegardeDocumentXml {
    
    public SauvegardeDocumentXml(){   
    }
    
    public void saveToFile(Document document, String filePath) throws Exception {
        Source domSource = new DOMSource(document);
        File xmlFile = new File(filePath);
        Result serializationResult = new StreamResult(xmlFile);
        Transformer xmlTransformer = TransformerFactory.newInstance().newTransformer();
        xmlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        xmlTransformer.transform(domSource, serializationResult);
    }

    public void saveSignalInvalidInputXML(String filePath) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

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
        File file = new File(filePath); // args[1]
        Result result = new StreamResult(file);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSource, result);
    }
}
