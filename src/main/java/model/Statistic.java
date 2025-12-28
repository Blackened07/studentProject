package model;

public class Statistic {
    private final StudyProfile studyProfile;
    private float avgExamScore;
    private int studentsNumber;
    private int universitiesNumber;
    private final String universityNames;

    public Statistic(StudyProfile studyProfile, float avgExamScore, int studentsNumber, int universitiesNumber, String universityName) {
        this.studyProfile = studyProfile;
        this.avgExamScore = avgExamScore;
        this.studentsNumber = studentsNumber;
        this.universitiesNumber = universitiesNumber;
        this.universityNames = universityName;
    }

    public StudyProfile getStudyProfile() {
        return studyProfile;
    }

    public float getAvgExamScore() {
        return avgExamScore;
    }

    public void setAvgExamScore(float avgExamScore) {
        this.avgExamScore = avgExamScore;
    }

    public int getStudentsNumber() {
        return studentsNumber;
    }

    public void setStudentsNumber(int studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public int getUniversitiesNumber() {
        return universitiesNumber;
    }

    public void setUniversitiesNumber(int universitiesNumber) {
        this.universitiesNumber = universitiesNumber;
    }

    public String getUniversityNames() {
        return universityNames;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "studyProfile=" + studyProfile +
                ", avgExamScore=" + avgExamScore +
                ", studentsNumber=" + studentsNumber +
                ", universitiesNumber=" + universitiesNumber +
                ", universityNames='" + universityNames + '\'' +
                '}';
    }
}
