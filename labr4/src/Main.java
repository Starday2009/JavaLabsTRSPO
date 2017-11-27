import java.io.FileReader;
import java.util.Iterator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main{

    private static final String FILENAME= "d:\\Study\\TRSPO\\file.json";

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(
                    new FileReader(FILENAME));
            // Получаем имя
            String name = (String) object.get("name");
            System.out.println("Name: " + name);
            // Получаем сайт
            String site = (String) object.get("site");
            System.out.println("Site: " + site);
            // Получаем возраст
            Long age = (Long) object.get("age");
            System.out.println("Name: " + age);
            // Получаем сообщения
            JSONArray messages = (JSONArray) object.get("messages");
            System.out.println("Messages:");
            Iterator<String> iterator = messages.iterator();
            while(iterator.hasNext()) {
                System.out.println("> " + iterator.next());
            }
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Main.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}