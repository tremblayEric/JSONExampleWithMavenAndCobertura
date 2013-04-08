/*
 * Copyright 2011 Jacques Berger.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Modifie par :
 * Sayon Cisse
 * Pierre Darveau
 * Jonatan Pokou
 * Eric Tremblay
 * 
 * dans le cadre du cours :
 * INF2015
 * UQAM
 * Session d'hiver 2013
 */

package com.INF2015.app.Parsing;

import main.java.com.INF2015.app.Validation.ErrorMessage;
import main.java.com.INF2015.app.Validation.ValidationInputFileException;
import main.java.com.INF2015.app.Validation.Validation;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.java.com.INF2015.app.Parsing.JSONFileReader;
import com.INF2015.app.Parsing.JavaObjectDossier;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class JSONReclamationsParsing {
    
    private JavaObjectDossier javaDossier;
    private JSONObject folder;
    
    public JSONReclamationsParsing(String path) throws IOException, ValidationInputFileException{
        
        //String JSONFileContent = JSONFileReader.loadFileIntoString("JSONFile/inputFile.json");
        String JSONFileContent = JSONFileReader.loadFileIntoString(path);
        folder = JSONObject.fromObject(JSONFileContent);
        Validation.checkElementsFolder(folder, "dossier");
        Validation.checkElementsFolder(folder, "mois");
        Validation.checkElementsFolder(folder, "reclamations");
        javaDossierFabrication();
        
    }
    
    public JavaObjectDossier getJavaObjectDossier(){
        return this.javaDossier;
    }
    
    private void javaDossierFabrication() throws ValidationInputFileException{
        javaDossier = new JavaObjectDossier();
        javaDossier.setDossier(Validation.checkFolder(folder.getString("dossier")));
        javaDossier.setMois(Validation.checkMonth(folder.getString("mois")));
        reclamationsFromJSONToJava();
    }
    
    private void reclamationsFromJSONToJava() throws ValidationInputFileException{
        JSONArray reclamations = folder.getJSONArray("reclamations");
        for(int i = 0; i < reclamations.size();++i){
            javaDossier.addToReclamationList(javaReclamationCreation(reclamations.get(i)));
        }
        
    }
    
    private JavaObjectReclamation javaReclamationCreation(Object reclamation) throws ValidationInputFileException{
        JSONObject object = (JSONObject)reclamation;
        String soin = Validation.checkSoin(object.getString("soin"));
        String code = object.getString("code");//DDC3
        Date date = null;
        try{ 
            date = (new SimpleDateFormat("yyyy-MM-dd")).parse(Validation.checkDate(object.getString("date"),folder.getString("mois")).toString());
        }catch (Exception e){
            throw new ValidationInputFileException(e.getMessage());
        }
        String montant = Validation.checkMontant(object.getString("montant"));
        return new JavaObjectReclamation(soin,code,date,montant);
    }
    
    private void displayReclamationList(){
        javaDossier.displayReclamationList();
    }    
    
       
}