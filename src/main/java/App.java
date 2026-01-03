import model.Statistic;
import model.Student;
import model.University;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.StudentService;
import service.UniversityService;
import util.JSONutil.JsonUtil;
import util.SizeComparatorUtil;
import util.StatisticUtil;
import util.downloadUtil.PropertiesUtil;
import util.downloadUtil.XlsWriter;
import view.Printer;

import java.util.List;

import static util.downloadUtil.PropertiesUtil.EXCEL_PATH;
import static util.downloadUtil.PropertiesUtil.RANDOM_STUDENTS_JSON_PATH;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Приложение запущено!");
        StudentService studentService = new StudentService();
        UniversityService universityService = new UniversityService();

        Printer printer = new Printer(studentService, universityService);

       /* printer.printStudentsSortedByAvgDesc();
        printer.printStudentsSortedByName();
        printer.printUniversitySortedByProfile();*/

        studentService.parseStudentsToJson();
        universityService.parseUniversitiesToJson();

        List<Student> studentsFronJson = studentService.parseStudentsFromJson();
        List<University> universitiesFromJson = universityService.parseUniversityFromJson();

        /*studentsFronJson.forEach(System.out::println);
        universitiesFromJson.forEach(System.out::println);*/

        //can void
/*        List<Student> list = JsonUtil.showProcess(studentService.getRandomStudents(), Student.class);
        List<University> list1 = JsonUtil.showProcess(universityService.getRandomUniversities(), University.class);*/

        List<Statistic> statistics = StatisticUtil.getStatistics(studentService.getStudents(), universityService.getUniversities());

        XlsWriter.write(statistics, PropertiesUtil.getProperty(EXCEL_PATH));

        logger.info("Работа приложения завершена!");
    }

}
