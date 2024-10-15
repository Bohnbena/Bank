import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class JsonJackson {

    public void ObjectToJson(Konto konto, String userID) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(userID + ".json");
        try {
            objectMapper.writeValue(file, konto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> JsonToObject(String userID) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(userID + ".json");
            return objectMapper.readValue(file, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void EditJsonObject(String methode, Map<String, Object> kontodaten) {

        Scanner scanner  = new Scanner(System.in);
        System.out.println("Betrag:");
        String userinput = scanner.nextLine();
        int betrag       = Integer.parseInt(userinput);

        if (methode.equals("E")) {
            //Wenn ein kontostand existiert müssen wir denn jetzigen betrag und denn neuen addieren
            int kontostand = (Integer) kontodaten.get("kontoStand");
            if (kontostand > 0) {
                kontostand = kontostand + betrag;
            } else {
                kontostand = betrag;
            }
            kontodaten.put("kontoStand", kontostand);
        } else if (methode.equals("A")) {
        }
        File file = new File((String) kontodaten.get("kontoNummer") + ".json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, kontodaten);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
