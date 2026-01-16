package model;

import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
public class University {

    @XmlElement(name = "UniversityID")
    @SerializedName(value = "university_id", alternate = "university_number")
    private final String id;

    @XmlElement(name = "UniversityName")
    @SerializedName(value = "full_university_name", alternate = "university_name")
    private final String fullName;

    @XmlTransient
    @SerializedName(value = "short_name", alternate = {"abbreviation", "short_university_name"})
    private final String shortName;

    @XmlTransient
    @SerializedName("foundation_year")
    private final int yearOfFoundation;

    @XmlElement(name = "UniversityProfile")
    @SerializedName(value = "study_profile", alternate = {"main_study_profile", "profile"})
    private final StudyProfile mainProfile;

    public University(String id, String fullName, String shortName, int yearOfFoundation, StudyProfile mainProfile) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.yearOfFoundation = yearOfFoundation;
        this.mainProfile = mainProfile;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public int getYearOfFoundation() {
        return yearOfFoundation;
    }

    public StudyProfile getMainProfile() {
        return mainProfile;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        University that = (University) object;
        return yearOfFoundation == that.yearOfFoundation && Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName) && Objects.equals(shortName, that.shortName) && mainProfile == that.mainProfile;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, shortName, yearOfFoundation, mainProfile);
    }

    @Override
    public String toString() {
        return "University: " +
                "id: " + id + "; " +
                "fullName: " + fullName + "; " +
                "shortName: " + shortName + "; " +
                "yearOfFoundation: " + yearOfFoundation +
                "mainProfile: " + mainProfile
                ;
    }
}
