package com.INF2015.app.Parsing;
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
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JavaObjectFolder {

    SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-MM");
    private String dossier;
    private Date mois;
    private List reclamationsList;

    public JavaObjectFolder() {
        this.dossier = "";
        this.reclamationsList = new ArrayList();
    }

    public void setDossier(String dossier) {
        this.dossier = dossier;
    }

    public void setMois(String mois) {
        try {
            this.mois = dateFormatMois.parse(mois);
        } catch (Exception e) {
            System.out.println("Erreur avec le mois");
        }
    }

    public String getFolderNumber() {
        return this.dossier;
    }

    public Date getFolderDate() {
        return this.mois;
    }

    public List getFolderReclamationList() {
        return this.reclamationsList;
    }

    public void addToReclamationList(JavaObjectReclamation reclamation) {
        this.reclamationsList.add(reclamation);
    }

    public void displayReclamationList() {
        for (int i = 0; i < reclamationsList.size(); ++i) {
            System.out.println(reclamationsList.get(i).toString() + "\n");
        }
    }
}
