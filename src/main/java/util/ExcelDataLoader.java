package util;

import model.Entities;
import model.Student;
import model.StudyProfile;
import model.University;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

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

    private static <T> List<T> readData(String filePath, String sheetName, Function<Sheet, List<T>> parser) {
        Path path = Paths.get(filePath);

        try (
                InputStream inputStream = Files.newInputStream(path);
                Workbook workbook = new XSSFWorkbook(inputStream);
        ) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new NullPointerException("Sheet " + sheetName + " not found");
            }
            return parser.apply(sheet);

        } catch (IOException e) {
            System.out.println("Error reading excel file: " + e.getMessage());
            return Collections.emptyList();
        }

    }

    private static List<University> parseUniversities(Sheet sheet) {
        List<University> universities = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = sheet.rowIterator().next();
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

    //Оставлю и такую реализацию
    private static List<Student> parseStudents(Sheet sheet) {
        List<Student> students = new ArrayList<>();
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            try {
                Row row = sheet.getRow(i);
                if (row == null) {
                    throw new NullPointerException("Row " + i + " not found");
                }
                students.add(new Student(
                        row.getCell(CELL_1).getStringCellValue(),
                        row.getCell(CELL_2).getStringCellValue(),
                        (int) row.getCell(CELL_3).getNumericCellValue(),
                        (int) row.getCell(CELL_4).getNumericCellValue()
                ));
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
        return students;
    }

}
