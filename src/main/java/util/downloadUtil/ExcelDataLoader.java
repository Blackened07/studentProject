package util.downloadUtil;

import model.Student;
import model.StudyProfile;
import model.University;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public final class ExcelDataLoader {
    private static final int CELL_1 = 0;
    private static final int CELL_2 = 1;
    private static final int CELL_3 = 2;
    private static final int CELL_4 = 3;
    private static final int CELL_5 = 4;

    private static final Logger logger = LoggerFactory.getLogger(ExcelDataLoader.class);

    private ExcelDataLoader() {
    }

    public static List<Student> readStudents(String filePath, String sheetName) {
        return readData(filePath, sheetName, ExcelDataLoader::parseStudents);
    }

    public static List<University> readUniversities(String filePath, String sheetName) {
        return readData(filePath, sheetName, (ExcelDataLoader::parseUniversities));
    }

    private static <T> List<T> readData(String filePath, String sheetName, SheetParser<T> parser) {
        logger.info("Начало чтения файла; Путь: {}", filePath);
        Path path = Paths.get(filePath);
        try (
                InputStream inputStream = Files.newInputStream(path);
                Workbook workbook = new XSSFWorkbook(inputStream);
        ) {
            Sheet sheet = workbook.getSheet(sheetName);

            return Optional.ofNullable(sheet).map(s -> {
                try {
                    return parser.parse(s);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).orElseThrow(() -> {
                logger.warn("Страница {} не обнаружена в файле: {}", sheetName, filePath);
                return new NoSuchElementException("Sheet " + sheetName + " not found");
            });
        } catch (Exception e) {
            logger.error("Файл {} не найден: {}", filePath, e.getMessage());
            return Collections.emptyList();
        }
    }

    private static List<University> parseUniversities(Sheet sheet) {
        List<University> universities = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Optional.ofNullable(row).ifPresent(r -> {
                universities.add(new University(
                        row.getCell(CELL_1).getStringCellValue(),
                        row.getCell(CELL_2).getStringCellValue(),
                        row.getCell(CELL_3).getStringCellValue(),
                        (int) row.getCell(CELL_4).getNumericCellValue(),
                        StudyProfile.valueOf(row.getCell(CELL_5).getStringCellValue().trim())
                ));
            });
        }
        return universities;
    }

    private static List<Student> parseStudents(Sheet sheet) {
        return StreamSupport.stream(sheet.spliterator(), false)
                .skip(1)
                .map(Optional::ofNullable)
                .flatMap(Optional::stream)
                .map(row -> {
                    try {
                        return new Student(
                                row.getCell(CELL_1).getStringCellValue(),
                                row.getCell(CELL_2).getStringCellValue(),
                                (int) row.getCell(CELL_3).getNumericCellValue(),
                                (int) row.getCell(CELL_4).getNumericCellValue());
                    } catch (Exception e) {
                        logger.error("Ошибка парсинга строки {}: {}", row.getRowNum(), e.getMessage());
                        return null;
                    }
                }).toList();
    }
}
