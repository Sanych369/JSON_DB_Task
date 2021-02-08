package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.User;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Converter {
    private final static String fileSeparator = System.getProperty("file.separator");
    private final static String jsonFilePath = "src" + fileSeparator + "main" + fileSeparator + "resources" + fileSeparator + "db.json";
    private final static File jsonFile = new File(jsonFilePath);
    private final static ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static void toJSON(List<User> userList) {
        try {
            mapper.writeValue(jsonFile, userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> toPOJO() {
        try {
            return Arrays.asList(mapper.readValue(jsonFile, User[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteJSON() {
        jsonFile.deleteOnExit();
    }
}
