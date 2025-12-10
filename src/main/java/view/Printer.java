package view;

import service.StudentService;
import service.UniversityService;
import util.ComparatorUtil;
import util.StudentVariations;
import util.UniversityVariations;

import java.util.Objects;

public class Printer {

    private final StudentService studentService;
    private final UniversityService universityService;

    public Printer(StudentService studentService, UniversityService universityService) {
        this.studentService = studentService;
        this.universityService = universityService;
        System.out.println();
    }

    public void printStudentsSortedByName() {
        studentService.getStudents().sort(ComparatorUtil.getStudentSorted(StudentVariations.FULL_NAME));
        studentService.getStudents().forEach(System.out::println);
        System.out.println();
    }

    public void printStudentsSortedByAvgDesc() {
        studentService.getStudents().sort(Objects.requireNonNull(ComparatorUtil.getStudentSorted(StudentVariations.AVG_EXAM_SCORE)).reversed());
        studentService.getStudents().forEach(System.out::println);
        System.out.println();
    }

    public void printUniversitySortedByProfile() {
        universityService.getUniversities().sort(ComparatorUtil.getUniversitySorted(UniversityVariations.PROFILE));
        universityService.getUniversities().forEach(System.out::println);
        System.out.println();
    }
}
