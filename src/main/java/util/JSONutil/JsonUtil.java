package util.JSONutil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUtil {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private JsonUtil() {}

    public static <T> T  parseFromJson(String pathName, TypeToken<T> typeToken) {
        Path path = Paths.get(pathName);
        try {
            String inputJson = Files.readString(path);
            Type typeOfT = typeToken.getType();
            return GSON.fromJson(inputJson, typeOfT);
        } catch (IOException e) {
            System.out.println("Error reading file " + pathName + "; " + e.getMessage());
        }
        return null;
    }

    public static <T> void parseToJson(String pathName, T object) {
        Path path = Paths.get(pathName);
        try {
            String outputJson = GSON.toJson(object);
            Files.writeString(path, outputJson);
        } catch (IOException e) {
            System.out.println("Error while writing to file " + e.getMessage());
        }
    }

    public static  <T> List<T> showProcess(List<T> list, Class<T> tClass) {
        return list.stream()
                .map(GSON::toJson)
                .peek(j -> System.out.println("To: " + j))
                .map(j -> GSON.fromJson(j, tClass))
                .peek(j -> System.out.println("From: " + j))
                .toList();
    }

}
