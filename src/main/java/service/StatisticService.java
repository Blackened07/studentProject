package service;

import model.Statistic;
import model.Student;
import model.University;
import util.StatisticUtil;
import util.downloadUtil.PropertiesUtil;
import util.downloadUtil.XlsWriter;

import java.util.List;

import static util.downloadUtil.PropertiesUtil.EXCEL_PATH;

public class StatisticService {

    private final List<Statistic> statistics;

    public StatisticService(List<Student> first, List<University> second) {
        this.statistics = StatisticUtil.getStatistics(first, second);
    }

    public List<Statistic> getStatistics() {
        return statistics;
    }

    public void writeToXLS (List<Statistic> stats) {
        XlsWriter.write(stats, PropertiesUtil.getProperty(EXCEL_PATH));
    }
}
