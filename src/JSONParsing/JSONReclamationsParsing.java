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
package JSONParsing;



import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class JSONReclamationsParsing {
    
    public JSONReclamationsParsing() throws IOException{
        
        String json = FileReader.loadFileIntoString("JSONFile/inputFile.json");
        JavaObjectDossier javaDossier = new JavaObjectDossier();
        JSONObject dossier = JSONObject.fromObject(json);
        
//        JSONObject catalog = JSONObject.fromObject(json);
        //JSONArray albums = catalog.getJSONArray("albums");
        
        
    }
        
    
    
}
