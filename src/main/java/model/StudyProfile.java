package model;


public enum StudyProfile {
    MEDICINE("Медицина"),
    ENGINEERING("Инженерные специальности"),
    PHYSICS("Физика"),
    LINGUISTICS("Лингвистика"),
    MATHEMATICS("Математика");

    private final String profileName;


    StudyProfile(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileName() {
        return profileName;
    }
}
