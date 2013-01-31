
package tp1agile;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


public class CalculReclamation {
    
    
   private Document document;
   //private String contrat;
   //private String numeroSoin;
   //private int montant;
    
    
  CalculReclamation(Document document){
      this.document = document;
  }
  
  /*
  public String getContrat(){
      return contrat;
  
  }
  
  public String getNumeroSoin(){
      return numeroSoin;
  }
  
  public int getMontant(){
      return montant;
  }
  
  public Document getDocument(){
      return document;
  }
  
  public void setContrat( String contrat){
      this.contrat = contrat;
  
  }
  
  public void setNumeroSoin( String numeroSoin ){
      this.numeroSoin = numeroSoin;
  }
  
  public void setMontant(int montant){
      this.montant = montant;
  }
  */
  public void setDocument( Document document ){
      this.document = document;
  }
  
  
  /**
   * il reste a coder les methodes pour recuperer les donnees, en gros il suffit
   * d'une methode pricipale qui parcoure le document et a chaque noeud mettre à
   * jour les variables numeroSoin et montant et appeler la methode de calcul pour
   * calculer le montant de la reclamation. lorsque tout ça est fait il faut 
   * un methode qui place un nouveau noeud dans le fichier de sortie pour chaque
   * nouveau montant calculé.Idealement ça prendrait une classe qui s'occupe de 
   * ça et lorsque tou est finit elle le sauvegarde.
   * 
   */
  
    /*
   public String getTypeDeContrat() {
        Element  typeDeContrat = (Element)document.getElementsByTagName("contrat");
        contrat = typeDeContrat.getTextContent();
        return contrat;
    } 
    */
   
   
   
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
            
            String element = date.item(i).getTextContent();
            
            listeMontant.add(element);
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
   
   
   
   
   
  
       
       
   
   
   
   
   /*
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
    */
    
   public List<Double> effectuerListCalcul() {
       List<Double> listRemboursement = new ArrayList<>();
       if(getListeSoins().size() == getListeMontant().size()){
            for(int i = 0; i < getListeSoins().size(); ++i ){
                String montant = getListeMontant().get(i);
                montant = montant.replace('$', ' ');
                Double it = Double.parseDouble( montant );
                String st = getListeSoins().get(i);
                String st2 = getTypeDeContrat2().get(0);
                listRemboursement.add(effectuerCalcul( it , st, st2 ));
            }
        }
       return listRemboursement;
    }
   
   
    
   public double effectuerCalcul( double valeur, String numeroSoin, String contrat ) {
        double remboursement = 0; 
        ContractList listeContrats = new ContractList();
        remboursement = valeur * listeContrats.getContractRatioByCareNumber( numeroSoin,  contrat );
        if( listeContrats.getContractMaxValueByCareNumberExist( numeroSoin,  contrat ) && remboursement > listeContrats.getContractMaxValueByCareNumber( numeroSoin,  contrat )  ){
            remboursement = listeContrats.getContractMaxValueByCareNumber( numeroSoin,  contrat ); 
        }
        return remboursement;
    }
    
    
    //public double effectuer() {
        
        
        //NodeList reclamation = document.getElementsByTagName("reclamation");

        //for (int i = 0; i < reclamation.getLength(); i++) {
            
            //Element element = (Element) reclamation.item(i);
            
            //listeReclamation.add(element.getTextContent());
            
            //numeroSoin = listeReclamation.get(0);
            
            //montant = Integer.parseInt(listeReclamation.get(2));
        //}
        //return listeReclamation;
        
        /*
        double remboursement = 0.0; 
        ContractList listeContrats = new ContractList();
        remboursement = valeur * listeContrats.getContractRatioByCareNumber( numeroSoin,  contrat );
        if( listeContrats.getContractMaxValueByCareNumberExist( numeroSoin,  contrat ) && remboursement > listeContrats.getContractMaxValueByCareNumber( numeroSoin,  contrat )  ){
            remboursement = listeContrats.getContractMaxValueByCareNumber( numeroSoin,  contrat ); 
        } 
        return remboursement;
        */
    //}

    
}

