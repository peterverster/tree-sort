package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {


    public static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
       // mapper.registerModule(new JavaTimeModule());
    }

    public static String readFile(String path) throws IOException {
        if (!path.equals("")) {
            return new String(Files.readAllBytes(Paths.get(path)));
        }
        return null;
    }

    public static void writeJsonFile(String path, Object value) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), value);
    }

    public static String toJson(Object value) throws IOException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
    }

    public static String toUnprettyJson(Object value) throws IOException {
        return mapper.writeValueAsString(value);
    }

    public static <T> T fromJson(String json, Class<T> type) throws IOException {
        return mapper.readValue(json, type);
    }

    public static <T> T readJsonFile(String fileName, Class<T> type) throws IOException {
        return mapper.readValue(readFile(fileName), type);
    }

    public static <T> T cloneObject(Class<T> type, Object object) {

        try {
            String s = mapper.writer().writeValueAsString(object);
            return mapper.readValue(s, type);
        } catch (IOException e) {

            return null;

        }
    }
}
