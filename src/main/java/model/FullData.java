package model;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class FullData {

    @XmlElementWrapper(name = "StudentInfo")
    @XmlElement(name = "StudentEntry")
    private List<Student> studentList;

    @XmlElementWrapper(name = "UniversityInfo")
    @XmlElement(name = "UniversityEntry")
    private List<University> universityList;

    @XmlElementWrapper(name = "StatisticInfo")
    @XmlElement(name = "StatisticEntry")
    private List<Statistic> statisticList;

    public FullData() {
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<University> getUniversityList() {
        return universityList;
    }

    public void setUniversityList(List<University> universityList) {
        this.universityList = universityList;
    }

    public List<Statistic> getStatisticList() {
        return statisticList;
    }

    public void setStatisticList(List<Statistic> statisticList) {
        this.statisticList = statisticList;
    }
}
