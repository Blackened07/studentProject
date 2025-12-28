package service;

import com.google.gson.reflect.TypeToken;
import model.Student;
import util.JSONutil.JsonUtil;
import util.downloadUtil.ExcelDataLoader;
import util.downloadUtil.PropertiesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static model.Entities.SHEET_NAME_STUDENTS;
import static util.downloadUtil.PropertiesUtil.EXCEL_PATH;
import static util.downloadUtil.PropertiesUtil.STUDENTS_JSON_PATH;

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

    public void parseStudentsToJson() {
        JsonUtil.parseToJson(PropertiesUtil.getProperty(STUDENTS_JSON_PATH), students);
    }

    public List<Student> parseStudentsFromJson() {
        return JsonUtil.parseFromJson(PropertiesUtil.getProperty(STUDENTS_JSON_PATH), new TypeToken<>() {
        });
    }

    public Student getRandom() {
        return students.get(new Random().nextInt(students.size()));
    }

    public List<Student> getRandomStudents() {
        List<Student> result = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (i == new Random().nextInt(students.size())) {
                result.add(students.get(i));
            }
        }
        return result;
    }


}
