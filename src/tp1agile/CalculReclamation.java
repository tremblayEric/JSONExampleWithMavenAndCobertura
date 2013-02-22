package tp1agile;

import Data.ContractList;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class CalculReclamation {

    private Document document;

    CalculReclamation(Document document) {
        this.document = document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<String> getTypeDeContrat2() {
        NodeList lesContrats = document.getElementsByTagName("contrat");
        List<String> contrats = new ArrayList<>();

        for (int i = 0; i < lesContrats.getLength(); i++) {
            Node contrat = lesContrats.item(i);
            Element element = (Element) contrat;
            contrats.add(element.getTextContent());
        }
        return contrats;

    }

    public List<Element> getListeDesReclamations() {

        List<Element> listeReclamation = new ArrayList<>();
        NodeList reclamation = document.getElementsByTagName("reclamation");

        for (int i = 0; i < reclamation.getLength(); i++) {
            Element element = (Element) reclamation.item(i);
            listeReclamation.add(element);
        }
        return listeReclamation;
    }

    public List<String> getListeSoins() {

        List<String> listeReclamation = new ArrayList<>();
        NodeList soin = document.getElementsByTagName("soin");

        for (int i = 0; i < soin.getLength(); i++) {
            String element = soin.item(i).getTextContent();
            listeReclamation.add(element);
        }
        return listeReclamation;
    }

    public List<String> getListeDate() {

        List<String> listeDate = new ArrayList<>();
        NodeList date = document.getElementsByTagName("date");

        for (int i = 0; i < date.getLength(); i++) {
            String element = date.item(i).getTextContent();
            listeDate.add(element);
        }
        return listeDate;
    }

    public List<String> getListeMontant() {

        List<String> listeMontant = new ArrayList<>();
        NodeList date = document.getElementsByTagName("montant");

        for (int i = 0; i < date.getLength(); i++) {
            String montant = date.item(i).getTextContent();
            montant = montant.replace(',', '.');
            montant = montant.replace('$', ' ');
            montant = montant.trim();            
            listeMontant.add(montant);
        }
        return listeMontant;
    }

    public String getNumeroClient() {
        String numeroClient;
        NodeList date = document.getElementsByTagName("client");
        numeroClient = date.item(0).getTextContent();
        return numeroClient;
    }

    public String getMois() {
        String numeroClient;
        NodeList date = document.getElementsByTagName("mois");
        numeroClient = date.item(0).getTextContent();
        return numeroClient;
    }

    public List<Double> effectuerListCalcul() {
        List<Double> listRemboursement = new ArrayList<>();
        if (getListeSoins().size() == getListeMontant().size()) {
            for (int i = 0; i < getListeSoins().size(); ++i) {
                String montant = getListeMontant().get(i);
               // montant = montant.replace('$', ' '); note : voir getListeMontant()
                Double it = Double.parseDouble(montant);
                String st = getListeSoins().get(i);
                String st2 = getTypeDeContrat2().get(0);
                listRemboursement.add(effectuerCalcul(it, st, st2));
            }
        }
        return listRemboursement;
    }
      public Double addAllRefunds() {
        Double totalRefund = 0.0;
        if (getListeSoins().size() == getListeMontant().size()) {
            for (int i = 0; i < getListeSoins().size(); ++i) {
                String montant = getListeMontant().get(i);
                Double it = Double.parseDouble(montant);
                String st = getListeSoins().get(i);
                String st2 = getTypeDeContrat2().get(0);
                totalRefund += effectuerCalcul(it, st, st2);
            }
        }
        return totalRefund;
    }  
    
    public double effectuerCalcul(double valeur, String numeroSoin, String contrat) {
        double remboursement = 0;
        ContractList listeContrats = new ContractList();
        remboursement = valeur * listeContrats.getContractRatioByCareNumber(numeroSoin, contrat);
        if (listeContrats.getContractMaxValueByCareNumberExist(numeroSoin, contrat) && remboursement > listeContrats.getContractMaxValueByCareNumber(numeroSoin, contrat)) {
            remboursement = listeContrats.getContractMaxValueByCareNumber(numeroSoin, contrat);
        }
        return remboursement;
    }
}
