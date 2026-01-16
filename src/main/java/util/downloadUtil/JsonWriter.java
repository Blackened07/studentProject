package util.downloadUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.FullData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.JSONutil.JsonUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public final class JsonWriter {

    private static final Logger logger = LoggerFactory.getLogger(JsonWriter.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH-mm");
    private static final String FILE_NAME = "_json_result.json";

    private JsonWriter() {
    }

    public static void write(FullData fullData) {
        logger.info("Начало преобразования в Json");
        try {
            String fileName = LocalDateTime.now().format(FORMATTER) + FILE_NAME;
            Path full = Paths.get(PropertiesUtil.getProperty(PropertiesUtil.JSON_PATH)).resolve(fileName);

            Optional.ofNullable(full.getParent()).ifPresent(p -> {
                try {
                    createJsonDir(p);
                } catch (IOException e) {
                    logger.error("Ошибка записи; {}", e.getMessage());
                    throw new RuntimeException(e);
                }
            });
            ;
            JsonUtil.parseToJson(full.toString(), fullData.getStudentList());
            JsonUtil.parseToJson(full.toString(), fullData.getUniversityList());
            JsonUtil.parseToJson(full.toString(), fullData.getStatisticList());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.info("Преобразование в Json завершено");
    }

    private static void createJsonDir(Path dirPath) throws IOException {
        try {
            Files.createDirectories(dirPath);
            logger.info("Директория успешно создана");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
