package service;

import model.Student;
import util.ExcelDataLoader;
import util.PropertiesUtil;

import java.util.List;

import static model.Entities.SHEET_NAME_STUDENTS;
import static util.PropertiesUtil.EXCEL_PATH;

public class StudentService {

    private final List<Student> students;

    public StudentService() {
        this.students = ExcelDataLoader.readStudents(
                PropertiesUtil.getProperty(EXCEL_PATH),
                SHEET_NAME_STUDENTS.getEntityName());
    }

    public List<Student> getStudents() {
        return students;
    }

}
