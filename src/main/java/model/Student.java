package model;

public class Student {

    private final String fullName;
    private final String universityId;
    private final int currentCourseNumber;
    private final float avgExamScore;

    public Student(String fullName, String universityId, int currentCourseNumber, float avgExamScore) {
        this.fullName = fullName;
        this.universityId = universityId;
        this.currentCourseNumber = currentCourseNumber;
        this.avgExamScore = avgExamScore;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUniversityId() {
        return universityId;
    }

    public int getCurrentCourseNumber() {
        return currentCourseNumber;
    }

    public float getAvgExamScore() {
        return avgExamScore;
    }

    @Override
    public String toString() {
        return "Student:\n" +
                "fullName: " + fullName + "\n" +
                "universityId: " + universityId + "\n" +
                "currentCourseNumber: " + currentCourseNumber + "\n" +
                "avgExamScore: " + avgExamScore;
    }
}
