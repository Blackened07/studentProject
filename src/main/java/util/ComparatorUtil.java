package util;

import model.Student;
import model.University;

import java.util.Comparator;

public class ComparatorUtil {

    public static Comparator<Student> getStudentSorted(StudentVariations studentVariations) {
        switch (studentVariations) {
            case UNIVERSITY_ID -> {
                return Comparator.comparing(Student::getUniversityId);
            }
            case FULL_NAME -> {
                return Comparator.comparing(Student::getFullName);
            }
            case COURSE -> {
                return Comparator.comparing(Student::getCurrentCourseNumber);
            }
            case AVG_EXAM_SCORE -> {
                return Comparator.comparing(Student::getAvgExamScore);
            }
            default -> {
                return null;
            }
        }
    }

    public static Comparator<University> getUniversitySorted(UniversityVariations universityVariations) {
        switch (universityVariations) {
            case ID -> {
                return Comparator.comparing(University::getId);
            }
            case FULL_NAME -> {
                return Comparator.comparing(University::getFullName);
            }
            case SHORT_NAME -> {
                return Comparator.comparing(University::getShortName);
            }
            case YEAR_OF_FOUNDATION -> {
                return Comparator.comparing(University::getYearOfFoundation);
            }
            case PROFILE -> {
                return Comparator.comparing(University::getMainProfile);
            }
            default -> {
                return null;
            }
        }

    }

}
