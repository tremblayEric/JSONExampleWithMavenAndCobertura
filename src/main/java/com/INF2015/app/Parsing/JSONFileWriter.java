package main.java.com.INF2015.app.Parsing;

import java.io.FileWriter;
import java.io.IOException;
import net.sf.json.JSONObject;

public class JSONFileWriter {

    public static void writeJSONObbjectToFile(String path, JSONObject object) {
        try {
            //FileWriter file = new FileWriter("JSONFile/outputFile.json");
            FileWriter file = new FileWriter(path);
            file.write(object.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("probleme lors de l'ecriture du fichier.");
        }
    }
}
