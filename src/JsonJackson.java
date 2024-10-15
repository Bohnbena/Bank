import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;

public class JsonJackson {

    public void ObjectToJson(Konto konto, String userID)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(userID + ".json");
        try {
            objectMapper.writeValue(file,konto);
            System.out.println("Saving file to: " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void JsonToObject()
    {

    }
}
