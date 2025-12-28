package util;

import model.Statistic;
import model.Student;
import model.StudyProfile;
import model.University;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class StatisticUtil {

    private StatisticUtil() {
    }

    public static List<Statistic> getStatistics(List<Student> students, List<University> universities) {
        return universities.stream()
                .collect(Collectors.groupingBy(University::getMainProfile))
                .entrySet().stream()
                .map(entry -> getEntitiesForStatistic(entry, students))
                .toList();
    }

    private static Statistic getEntitiesForStatistic(Map.Entry<StudyProfile, List<University>> entry, List<Student> students) {
        StudyProfile profile = entry.getKey();
        List<University> universities = entry.getValue();
        StringBuilder sb = new StringBuilder();

        Map<String, List<Student>> studentsByUniversities = students.stream()
                .collect(Collectors.groupingBy(Student::getUniversityId));

        List<Student> studentsList = universities.stream()
                .map(University::getId)
                .map(studentsByUniversities::get)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .toList();

        OptionalDouble avg = studentsList.stream()
                .mapToDouble(Student::getAvgExamScore)
                .average();

        float resultAvg = avg.isPresent()
                ? BigDecimal.valueOf(avg.getAsDouble())
                .setScale(2, RoundingMode.HALF_UP)
                .floatValue()
                : 0.0f;

        universities.forEach(u -> {
            sb.append(u.getShortName())
                    .append(" - ")
                    .append(u.getFullName())
                    .append("; ");
        });

        return new Statistic(
                profile,
                resultAvg,
                studentsList.size(),
                universities.size(),
                sb.toString()
        );
    }
}
