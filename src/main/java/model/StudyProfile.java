package model;

public enum StudyProfile {
    MEDICINE("Медицина"),
    ENGINEERING("Инженерные специальности");

    private final String profileName;

    StudyProfile(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileName() {
        return profileName;
    }
}
