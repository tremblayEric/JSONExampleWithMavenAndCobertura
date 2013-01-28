
package tp1agile;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class CalculReclamation {
    
    
   private Document document;
   private String contrat;
   private String numeroSoin;
   private int montant;
    
    
  CalculReclamation(Document docuement){
      this.document = document;
  }
  
  /**
   * il reste a coder les methodes pour recuperer les donnees, en gros il suffit
   * d'une methode pricipale qui parcoure le document et a chaque noeud mettre à
   * jour les variables numeroSoinet montant et appeler la methode de calcul pour
   * calculer le montant de la reclamation. lorsque tout ça est fait il faut 
   * un methode qui place un nouveau noeud dans le fichier de sortie pour chaque
   * nouveau montant calculé.Idealement ça prendrait une classe qui s'occupe de 
   * ça et lorsque tou est finit elle le sauvegarde.
   * 
   */
   public String getTypeDeContrat() {      
        Element  typeDeContrat = (Element)document.getElementsByTagName("contrat");
        contrat = typeDeContrat.getTextContent();
        return contrat;
    } 
    
    public List<String> getListeDesReclamations() {
        
        List<String> listeReclamation = new ArrayList<String>();

        NodeList reclamation = document.getElementsByTagName("reclamation");

        for (int i = 0; i < reclamation.getLength(); i++) {
            
            Element element = (Element) reclamation.item(i);
            
            listeReclamation.add(element.getTextContent());
            
            numeroSoin = listeReclamation.get(0);
            
            montant = Integer.parseInt(listeReclamation.get(2));
        }
        return listeReclamation;
    }

    
}
