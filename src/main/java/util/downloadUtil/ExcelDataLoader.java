package util.downloadUtil;

import model.Student;
import model.StudyProfile;
import model.University;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ExcelDataLoader {
    private static final int CELL_1 = 0;
    private static final int CELL_2 = 1;
    private static final int CELL_3 = 2;
    private static final int CELL_4 = 3;
    private static final int CELL_5 = 4;

    private ExcelDataLoader() {
    }

    public static List<Student> readStudents(String filePath, String sheetName) {
        return readData(filePath, sheetName, ExcelDataLoader::parseStudents);
    }

    public static List<University> readUniversities(String filePath, String sheetName) {
        return readData(filePath, sheetName, ExcelDataLoader::parseUniversities);
    }

    private static <T> List<T> readData(String filePath, String sheetName, SheetParser<T> parser) {
        Path path = Paths.get(filePath);
        try (
                InputStream inputStream = Files.newInputStream(path);
                Workbook workbook = new XSSFWorkbook(inputStream);
        ) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new NullPointerException("Sheet " + sheetName + " not found");
            }
            return parser.parse(sheet);
        } catch (Exception e) {
            System.out.println("Error reading excel file: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    //метод по заданию
    private static List<University> parseUniversities(Sheet sheet) throws NullPointerException {
        List<University> universities = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row == null) {
                throw new NullPointerException("Row not found");
            }
            universities.add(new University(
                    row.getCell(CELL_1).getStringCellValue(),
                    row.getCell(CELL_2).getStringCellValue(),
                    row.getCell(CELL_3).getStringCellValue(),
                    (int) row.getCell(CELL_4).getNumericCellValue(),
                    StudyProfile.valueOf(row.getCell(CELL_5).getStringCellValue())
            ));
        }
        return universities;
    }

    //Оставлю и такую реализацию, тк сделал до того как посмотрел в мануал к заданию
    private static List<Student> parseStudents(Sheet sheet) throws NullPointerException {
        return StreamSupport.stream(sheet.spliterator(), false)
                .skip(1)
                .map(row -> {
                    if (row == null) {
                        throw new NullPointerException("Row not found");
                    }
                    return new Student(
                            row.getCell(CELL_1).getStringCellValue(),
                            row.getCell(CELL_2).getStringCellValue(),
                            (int) row.getCell(CELL_3).getNumericCellValue(),
                            (int) row.getCell(CELL_4).getNumericCellValue()
                    );
                })
                .collect(Collectors.toList());
    }
}
