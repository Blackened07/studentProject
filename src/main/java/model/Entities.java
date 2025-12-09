package model;

public enum Entities {
    SHEET_NAME_STUDENTS("Студенты"),
    SHEET_NAME_UNIVERSITY("Университеты");

    private final String entityName;


    Entities(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}
