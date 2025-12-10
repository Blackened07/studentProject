import service.StudentService;
import service.UniversityService;
import util.*;
import view.Printer;

public class App {


    public static void main(String[] args) {

        StudentService studentService = new StudentService();
        UniversityService universityService = new UniversityService();

        Printer printer = new Printer(studentService, universityService);

        printer.printStudentsSortedByAvgDesc();
        printer.printStudentsSortedByName();
        printer.printUniversitySortedByProfile();

    }

}
