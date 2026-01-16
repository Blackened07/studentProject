package util.JSONutil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public final class JsonUtil {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private JsonUtil() {}

    public static <T> T  parseFromJson(String pathName, TypeToken<T> typeToken) {
        logger.info("Начало парсинга из: {}", pathName);
        Path path = Paths.get(pathName);
        try {
            String inputJson = Files.readString(path);
            Type typeOfT = typeToken.getType();
            logger.info("Успешное завершение");
            return GSON.fromJson(inputJson, typeOfT);
        } catch (IOException e) {
            logger.error("Ошибка чтения файла: {}; {}", pathName, e.getMessage());
        }
        return null;
    }

    public static <T> void parseToJson(String pathName, T object) {
        logger.info("Начало парсинга в: {}", pathName);
        Path path = Paths.get(pathName);
        try {
            String outputJson = GSON.toJson(object);
            Files.writeString(path, outputJson);
            logger.info("Успешное завершение");
        } catch (IOException e) {
            logger.error("Ошибка записи в файл: {}; {}", pathName, e.getMessage());
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
