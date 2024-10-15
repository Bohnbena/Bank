import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class JsonUtils {
    public JSONObject readJsonBank(String kontonummer) { //Erwartet String parameter und gibt json object zurück
        String filePath = kontonummer + ".json";
        try {
            String jsoncontent = new String(Files.readAllBytes(Paths.get(filePath)));
            return new JSONObject(jsoncontent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeJsonBank(JSONObject jsonkonto, String userID) {
        try (FileWriter file = new FileWriter(userID + ".json")) {
            file.write(jsonkonto.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyJsonBank(String action, JSONObject jsonkonto) {
        Scanner scanner = new Scanner(System.in);
        JsonUtils jsonUtils = new JsonUtils();

        //Holen uns denn kontostand in einem int format
        int kontostand = jsonkonto.getInt("Kontostand");

        //jetzt denn userinput , userinput muss zahl sein/Convertieren direkt
        System.out.println("Betrag:");
        String userinput = scanner.nextLine();
        int betrag = Integer.parseInt(userinput);

        if (action.equals("E")) {
            //Wenn ein kontostand existiert müssen wir denn jetzigen betrag und denn neuen addieren
            if (kontostand > 0) {
                kontostand = kontostand + betrag;
            }else
            {
                kontostand = betrag;
            }
            jsonkonto.put("Kontostand", kontostand);
        } else if (action.equals("A")) {

        }
        jsonUtils.writeJsonBank(jsonkonto,jsonkonto.getString("Kontonummer"));
    }
}
