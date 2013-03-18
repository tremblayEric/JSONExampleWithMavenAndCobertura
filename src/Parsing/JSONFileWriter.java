
package Parsing;

import java.io.FileWriter;
import java.io.IOException;
import net.sf.json.JSONObject;


public class JSONFileWriter {
    
    
    
    public static void writeJSONObbjectToFile( JSONObject object){
        try{
            FileWriter file = new FileWriter("JSONFile/outputFile.json");
            file.write(object.toString());
            file.flush();
            file.close();
        }catch( IOException e){
            System.out.println("probleme lors de l'ecriture du fichier.");
        }
    }
    
}
