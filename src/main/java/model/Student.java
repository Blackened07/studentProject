package model;

import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlElement(name = "StudentName")
    @SerializedName(value = "full_name", alternate = "student_name")
    private final String fullName;

    @XmlElement(name = "UniversityID")
    @SerializedName(value = "university_number")
    private final String universityId;

    @XmlTransient
    @SerializedName(value = "course", alternate = {"current_course", "course_number"})
    private int currentCourseNumber;

    @XmlElement(name = "AverageScore")
    @SerializedName(value = "avg", alternate = {"average_score"})
    private float avgExamScore;

    public Student(String universityId, String fullName, int currentCourseNumber, float avgExamScore) {
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

    public void setCurrentCourseNumber(int currentCourseNumber) {
        this.currentCourseNumber = currentCourseNumber;
    }

    public void setAvgExamScore(float avgExamScore) {
        this.avgExamScore = avgExamScore;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Student student = (Student) object;
        return currentCourseNumber == student.currentCourseNumber && Float.compare(avgExamScore, student.avgExamScore) == 0 && Objects.equals(fullName, student.fullName) && Objects.equals(universityId, student.universityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, universityId, currentCourseNumber, avgExamScore);
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
