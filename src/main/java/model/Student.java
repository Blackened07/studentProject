package model;

import java.util.Objects;

public class Student {

    private final String fullName;
    private final String universityId;
    private final int currentCourseNumber;
    private final float avgExamScore;

    public Student( String universityId, String fullName, int currentCourseNumber, float avgExamScore) {
        this.universityId = universityId;
        this.fullName = fullName;
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
        return "Student: " +
                "fullName: " + fullName + "; " +
                "universityId: " + universityId + "; " +
                "currentCourseNumber: " + currentCourseNumber + "; " +
                "avgExamScore: " + avgExamScore;
    }
}
