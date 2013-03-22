
package Parsing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONObject;


public class JavaObjectDossier {
    
    private String dossier;
    private Date mois;
    private List reclamationsList;
    
    public JavaObjectDossier(){
        this.dossier = "";
        this.reclamationsList = new ArrayList();
    }
    
    public void setDossier(String dossier){
        this.dossier = dossier;
    }
    
    public void setMois(String mois){
        try{
        this.mois = (new SimpleDateFormat("MM-dd")).parse(mois);
        }catch(Exception e){
            System.out.println("Erreur avec le mois");
        }
    }
    
    public String getFolderNumber(){
        return this.dossier;
    }
    
    public Date getFolderDate(){
        return this.mois;
    }
    
    public List getFolderReclamationList(){
        return this.reclamationsList;
    }
    
    public void addToReclamationList(JavaObjectReclamation reclamation){
        this.reclamationsList.add(reclamation);
    }
    
    public void displayReclamationList(){
        for(int i = 0; i < reclamationsList.size();++i){
            System.out.println( reclamationsList.get(i).toString() + "\n");
        }
    }
    
}