import model.Student;
import model.University;
import util.ExcelDataLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static model.Entities.SHEET_NAME_STUDENTS;
import static model.Entities.SHEET_NAME_UNIVERSITY;

public class App {
    private static final Properties PROPERTIES = new Properties();
    private static final String EXCEL_PATH = "excel.path";

    static {
        loadProperties();
    }

    public static void main(String[] args) {

        List<Student> students = ExcelDataLoader.readStudents(
                PROPERTIES.getProperty(EXCEL_PATH),
                SHEET_NAME_STUDENTS.getEntityName());

        List<University> universities = ExcelDataLoader.readUniversities(
                PROPERTIES.getProperty(EXCEL_PATH),
                SHEET_NAME_UNIVERSITY.getEntityName());

        for (Student student : students) {
            System.out.println(student);
        }
        for (University university : universities) {
            System.out.println(university);
        }
    }

    private static void loadProperties() {
        try (InputStream inputStream = App.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
