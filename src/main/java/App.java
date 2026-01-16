import model.FullData;
import model.Statistic;
import model.Student;
import model.University;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.StatisticService;
import service.StudentService;
import service.UniversityService;
import util.JSONutil.JsonUtil;
import util.SizeComparatorUtil;
import util.StatisticUtil;
import util.downloadUtil.JsonWriter;
import util.downloadUtil.PropertiesUtil;
import util.downloadUtil.XlsWriter;
import util.downloadUtil.XmlWriter;
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
        StatisticService statisticService = new StatisticService(studentService.getStudents(), universityService.getUniversities());

        FullData fullData = new FullData();

        fullData.setStudentList(studentService.getStudents());
        fullData.setUniversityList(universityService.getUniversities());
        fullData.setStatisticList(statisticService.getStatistics());

        statisticService.writeToXLS(statisticService.getStatistics());

        XmlWriter.writeToXml(fullData);
        JsonWriter.write(fullData);

        logger.info("Работа приложения завершена!");
    }

}
